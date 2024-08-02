package tata.test.dataaccess.adapter.cliente.command;

import java.util.Optional;
import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.ClienteEntity;
import tata.test.dataaccess.mappers.ClienteMapper;
import tata.test.dataaccess.repository.ClienteJpaRepository;
import tata.test.domain.application.ports.output.repository.cliente.command.ClienteCommandRepository;
import tata.test.exception.ClienteException;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.ClienteRequestRecord;
import tata.test.record.response.ClienteResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteCommandRepositoryImpl implements ClienteCommandRepository {

  private final ClienteJpaRepository clienteJpaRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      ClienteRequestRecord clienteRequestRecord) {
    ClienteEntity entity;
    if (clienteRequestRecord.id() != null) {
      entity = updateExistingCliente(clienteRequestRecord);
    } else {
      entity = ClienteMapper.INSTANCE.requestRecordToEntity(clienteRequestRecord);
      Optional<ClienteEntity> clienteOptional = clienteJpaRepository.findByIdentificacion(
          clienteRequestRecord.identificacion());

      if (clienteOptional.isPresent()) {
        ExceptionResponseRecord response = CreateException(
            "Cliente con cédula :" + clienteRequestRecord.identificacion() + " ya registrado",
            null);
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
    }
    ClienteResponseRecord saved = ClienteMapper.INSTANCE.entityToResponseRecord(
        clienteJpaRepository.save(entity));
    ExceptionResponseRecord response = CreateException(
        "Cliente registrado exitosamente !", saved);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> delete(String identificacion) {
    Optional<ClienteEntity> clienteOptional = clienteJpaRepository.findByIdentificacion(
        identificacion);
    if (clienteOptional.isEmpty()) {
      ExceptionResponseRecord response = CreateException(
          "No se encontró ningún registro", null);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    ClienteEntity cliente = clienteOptional.get();
    clienteJpaRepository.deleteByIdentificacion(cliente.getIdentificacion());
    ExceptionResponseRecord response = CreateException(
        "Cleinte : " + cliente.getNombre() + " eliminad@",
        ClienteMapper.INSTANCE.entityToResponseRecord(cliente));
    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  private ClienteEntity updateExistingCliente(ClienteRequestRecord requestRecord) {
    ClienteEntity existingEntity =
        clienteJpaRepository
            .findById(requestRecord.id())
            .orElseThrow(() -> new ClienteException("Cliente no encontrado"));

    final StampedLock lock = existingEntity.getLock();
    long stamp = lock.writeLock();
    try {
      existingEntity.setContrasenia(requestRecord.contrasenia());
      existingEntity.setEstado(requestRecord.estado());

    } finally {
      lock.unlockWrite(stamp);
    }
    return existingEntity;
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }
}
