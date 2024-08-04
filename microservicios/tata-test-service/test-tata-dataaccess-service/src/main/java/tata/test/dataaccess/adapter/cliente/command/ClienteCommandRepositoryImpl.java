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

    ClienteEntity entity = getClienteEntity(clienteRequestRecord);
    ClienteResponseRecord saved = ClienteMapper.INSTANCE.entityToResponseRecord(
        clienteJpaRepository.save(entity));
    return createSuccessResponse(
        "Cuenta almacenada para el usuario: " + clienteRequestRecord.nombre(),
        saved);
  }

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> delete(String identificacion) {
    return clienteJpaRepository.findByIdentificacion(identificacion)
        .map(this::deleteCliente)
        .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
  }

  private ResponseEntity<ExceptionResponseRecord> deleteCliente(ClienteEntity cliente) {
    clienteJpaRepository.deleteByIdentificacion(cliente.getIdentificacion());
    return createSuccessResponse("Cliente : " + cliente.getNombre() + " eliminad@",
        ClienteMapper.INSTANCE.entityToResponseRecord(cliente));
  }

  private ClienteEntity updateExistingCliente(ClienteRequestRecord requestRecord) {
    ClienteEntity existingEntity =
        clienteJpaRepository
            .findByIdentificacion(requestRecord.identificacion())
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

  private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
      ClienteResponseRecord saved) {
    return new ResponseEntity<>(CreateException(message, saved), HttpStatus.OK);
  }

  private ClienteEntity getClienteEntity(ClienteRequestRecord clienteRequestRecord) {
    Optional<ClienteEntity> clienteOptional = clienteJpaRepository.findByIdentificacion(
        clienteRequestRecord.identificacion());
    return clienteOptional.isPresent() ? updateExistingCliente(clienteRequestRecord)
        : createNewCliente(clienteRequestRecord);
  }

  private ClienteEntity createNewCliente(ClienteRequestRecord clienteRequestRecord) {
    return ClienteMapper.INSTANCE.requestRecordToEntity(clienteRequestRecord);
  }

  private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
    return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
  }
}
