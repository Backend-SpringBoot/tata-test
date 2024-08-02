package tata.test.dataaccess.adapter.movimientos.command;

import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.ClienteEntity;
import tata.test.dataaccess.entities.CuentaEntity;
import tata.test.dataaccess.entities.MovimientosEntity;
import tata.test.dataaccess.mappers.MovimientosMapper;
import tata.test.dataaccess.repository.ClienteJpaRepository;
import tata.test.dataaccess.repository.CuentaJpaRepository;
import tata.test.dataaccess.repository.MovimientosJpaRepository;
import tata.test.domain.application.ports.output.repository.movimientos.command.MovimientosCommandRepository;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.MovimientosRequestRecord;
import tata.test.record.response.MovimientosResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientosCommandRepositoryImpl implements MovimientosCommandRepository {

  private final MovimientosJpaRepository movimientosJpaRepository;
  private final CuentaJpaRepository cuentaJpaRepository;
  private final ClienteJpaRepository clienteJpaRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      MovimientosRequestRecord movimientosRequestRecord) {
    MovimientosEntity entity;
    Optional<CuentaEntity> cuentaOptional;
    ClienteEntity cliente;
    CuentaEntity cuenta;

    entity = MovimientosMapper.INSTANCE.requestRecordToEntity(movimientosRequestRecord);

    Optional<ClienteEntity> clienteOptional = clienteJpaRepository.findByIdentificacion(
        movimientosRequestRecord.cedula());

    if (clienteOptional.isEmpty()) {
      return new ResponseEntity<>(CreateException("Usuario no registrado", null), HttpStatus.OK);
    } else {
      cliente = clienteOptional.get();
      entity.setCiente(cliente.getNombre());
    }

    cuentaOptional = cuentaJpaRepository.findByNumeroCuenta(
        movimientosRequestRecord.numeroCuenta());

    if (cuentaOptional.isEmpty()) {
      return new ResponseEntity<>(CreateException("Cuenta no registrada", null), HttpStatus.OK);
    } else {
      cuenta = cuentaOptional.get();
      entity.setNumeroCuenta(cuenta.getNumeroCuenta());
      entity.setTipoCuenta(cuenta.getTipoCuenta());
    }

    cuentaOptional = cuentaJpaRepository.findCuentaByUsuarioAndNumCuenta(
        movimientosRequestRecord.cedula(), movimientosRequestRecord.numeroCuenta());

    if (cuentaOptional.isEmpty()) {
      return new ResponseEntity<>(CreateException(
          "Cuenta no registrada para el usuario" + movimientosRequestRecord.cedula(), null),
          HttpStatus.OK);
    }

    if (movimientosRequestRecord.tipoMovimiento().equals("Retiro")
        && movimientosRequestRecord.saldoInicial() > movimientosRequestRecord.movimiento()) {
      entity.setSaldoDisponible(
          movimientosRequestRecord.saldoInicial() - movimientosRequestRecord.movimiento());
      entity.setCiente(cliente.getNombre());
      entity.setFechMovimiento(LocalDateTime.now());
    }

    if (movimientosRequestRecord.tipoMovimiento().equals("Deposito")) {
      entity.setSaldoDisponible(
          movimientosRequestRecord.saldoInicial() + movimientosRequestRecord.movimiento());
      entity.setFechMovimiento(LocalDateTime.now());
    }

    MovimientosResponseRecord saved = MovimientosMapper.INSTANCE.entityToResponseRecord(
        movimientosJpaRepository.save(entity));
    ExceptionResponseRecord response = CreateException(
        "Movimiento registrado exitosamente", saved);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    movimientosJpaRepository.deleteById(id);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }

}
