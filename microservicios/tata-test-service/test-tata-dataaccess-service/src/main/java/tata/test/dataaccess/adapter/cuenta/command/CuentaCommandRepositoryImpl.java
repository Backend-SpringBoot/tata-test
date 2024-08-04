package tata.test.dataaccess.adapter.cuenta.command;

import java.util.Arrays;
import java.util.List;
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
    Optional<CuentaEntity> cuentaOptional;
    Optional<ClienteEntity> clienteOptional;
    ClienteEntity cliente;
    final List<String> TIPO_TRANSACCIONES = Arrays.asList("Ahorros", "Corriente");
    if (!isValidTransactionType(TIPO_TRANSACCIONES, cuentaRequestRecord.tipoCuenta())) {
      return new ResponseEntity<>(CreateException(
          "Tipo de transacción no sportada", null), HttpStatus.OK);
    }
    cuentaOptional = cuentaJpaRepository.findByNumeroCuenta(
        cuentaRequestRecord.numeroCuenta());
    clienteOptional = clienteJpaRepository.findByIdentificacion(
        cuentaRequestRecord.cedulaCliente());
    if (clienteOptional.isEmpty()) {
      return new ResponseEntity<>(CreateException(
          "Usuario no registrado", null), null);
    }
    cliente = clienteOptional.get();
    if (cuentaOptional.isPresent()) {
      entity = updateExistingCuenta(cuentaRequestRecord);
    } else {
      entity = CuentaMapper.INSTANCE.requestRecordToEntity(cuentaRequestRecord);
      entity.setCliente(cliente);
    }
    CuentaResponseRecord saved = CuentaMapper.INSTANCE.entityToResponseRecord(
        cuentaJpaRepository.save(entity));
    assert cliente != null;
    return new ResponseEntity<>(CreateException(
        "Cuenta almacenada para el usuario: " + cliente.getNombre(), saved), HttpStatus.OK);
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
    Optional<CuentaEntity> cuentaOptional =
        cuentaJpaRepository
            .findByNumeroCuenta(requestRecord.numeroCuenta());
    CuentaEntity existingEntity = cuentaOptional.get();
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

  public boolean isValidTransactionType(List<String> TIPO_TRANSACCIONES, String tipoCuenta) {
    return TIPO_TRANSACCIONES.contains(tipoCuenta);
  }
}
