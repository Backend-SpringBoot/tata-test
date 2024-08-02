package tata.test.dataaccess.adapter.cuenta.command;

import java.util.Optional;
import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.ClienteEntity;
import tata.test.dataaccess.entities.CuentaEntity;
import tata.test.dataaccess.mappers.CuentaMapper;
import tata.test.dataaccess.repository.ClienteJpaRepository;
import tata.test.dataaccess.repository.CuentaJpaRepository;
import tata.test.domain.application.ports.output.repository.cuenta.command.CuentaCommandRepository;
import tata.test.exception.CuentaException;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.CuentaRequestRecord;
import tata.test.record.response.CuentaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CuentaCommandRepositoryImpl implements CuentaCommandRepository {

  private final CuentaJpaRepository cuentaJpaRepository;
  private final ClienteJpaRepository clienteJpaRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      CuentaRequestRecord cuentaRequestRecord) {
    CuentaEntity entity;
    ClienteEntity cliente = null;
    if (cuentaRequestRecord.id() != null) {
      entity = updateExistingCuenta(cuentaRequestRecord);
    } else {

      Optional<ClienteEntity> clienteOptional = clienteJpaRepository.findByIdentificacion(
          cuentaRequestRecord.cedulaCliente());
      if (clienteOptional.isEmpty()) {
        ExceptionResponseRecord response = CreateException("Usuario no registrado", null);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
      }
      cliente = clienteOptional.get();
      entity = CuentaMapper.INSTANCE.requestRecordToEntity(cuentaRequestRecord);
      entity.setCliente(cliente);

      Optional<CuentaEntity> cuentaOptional = cuentaJpaRepository.findByTipoCuenta(
          cuentaRequestRecord.tipoCuenta());

      if (cuentaOptional.isPresent()) {
        ExceptionResponseRecord response = CreateException(
            "El usuario: " + cliente.getNombre() + " ya tiene cuenta: "
                + cuentaRequestRecord.tipoCuenta(), null);
        return new ResponseEntity<>(response, HttpStatus.OK);
      }
    }
    CuentaResponseRecord saved = CuentaMapper.INSTANCE.entityToResponseRecord(
        cuentaJpaRepository.save(entity));
    assert cliente != null;
    ExceptionResponseRecord response = CreateException(
        "Cuenta almacenada para el usuario: " + cliente.getNombre(), saved);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> delete(Integer id) {
    Optional<CuentaEntity> cuentaOptional = cuentaJpaRepository.findById(id);
    if (cuentaOptional.isEmpty()) {
      ExceptionResponseRecord response = CreateException(
          "No se encontró ningún registro", null);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    cuentaJpaRepository.deleteById(id);
    CuentaEntity cuenta = cuentaOptional.get();
    ExceptionResponseRecord response = CreateException(
        "Cuenta de : " + cuenta.getTipoCuenta() + " eliminada",
        CuentaMapper.INSTANCE.entityToResponseRecord(cuenta));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }

  private CuentaEntity updateExistingCuenta(CuentaRequestRecord requestRecord) {
    CuentaEntity existingEntity =
        cuentaJpaRepository
            .findById(requestRecord.id())
            .orElseThrow(() -> new CuentaException("Cuenta no encontrado"));

    final StampedLock lock = existingEntity.getLock();
    long stamp = lock.writeLock();
    try {
      existingEntity.setNumeroCuenta(requestRecord.numeroCuenta());
      existingEntity.setTipoCuenta(requestRecord.tipoCuenta());
      existingEntity.setSaldoInicial(requestRecord.saldoInicial());
      existingEntity.setEstado(requestRecord.estado());

    } finally {
      lock.unlockWrite(stamp);
    }
    return existingEntity;
  }
}
