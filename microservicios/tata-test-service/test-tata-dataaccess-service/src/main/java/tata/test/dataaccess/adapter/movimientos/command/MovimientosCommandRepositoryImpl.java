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
import tata.test.domain.application.ports.output.repository.cuenta.command.CuentaCommandRepository;
import tata.test.domain.application.ports.output.repository.movimientos.command.MovimientosCommandRepository;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.CuentaRequestRecord;
import tata.test.record.request.MovimientosRequestRecord;
import tata.test.record.response.MovimientosResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientosCommandRepositoryImpl implements MovimientosCommandRepository {

  private final MovimientosJpaRepository movimientosJpaRepository;
  private final CuentaJpaRepository cuentaJpaRepository;
  private final ClienteJpaRepository clienteJpaRepository;
  private final CuentaCommandRepository cuentaCommandRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      MovimientosRequestRecord movimientosRequestRecord) {
    MovimientosEntity entity;
    ClienteEntity cliente;
    CuentaEntity cuenta;

    entity = MovimientosMapper.INSTANCE.requestRecordToEntity(movimientosRequestRecord);
    cliente = validateCliente(movimientosRequestRecord.cedula());
    cuenta = validateCuenta(movimientosRequestRecord.cedula(),
        movimientosRequestRecord.numeroCuenta());

    if (cliente == null) {
      return new ResponseEntity<>(CreateException("Usuario no registrado", null), HttpStatus.OK);
    }
    if (cuenta == null) {
      return new ResponseEntity<>(CreateException(
          "Cuenta no registrada para el usuario" + movimientosRequestRecord.cedula(), null),
          HttpStatus.OK);
    }

    if (movimientosRequestRecord.tipoMovimiento().equals("Retiro")
        || movimientosRequestRecord.tipoMovimiento().equals("Deposito")) {
      double resp = calculateSaldoDisponible(movimientosRequestRecord, cuenta,
          movimientosRequestRecord.cedula());
      if (resp == 0) {
        return new ResponseEntity<>(CreateException(
            "No existe saldo suficiente", null),
            HttpStatus.OK);
      }
      entity.setSaldoInicial(cuenta.getSaldoInicial());
      entity.setSaldoDisponible(resp);
      entity.setCiente(cliente.getNombre());
      entity.setFechMovimiento(LocalDateTime.now());
    } else {
      return new ResponseEntity<>(CreateException(
          "Tipo de transacción no sportada", null),
          HttpStatus.OK);
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

  private ClienteEntity validateCliente(String cedula) {
    Optional<ClienteEntity> clienteOptional = clienteJpaRepository.findByIdentificacion(cedula);
    return clienteOptional.orElse(null);
  }

  private CuentaEntity validateCuenta(String cedula, String numeroCuenta) {
    Optional<CuentaEntity> cuentaOptional = cuentaJpaRepository.findCuentaByUsuarioAndNumCuenta(
        cedula, numeroCuenta);
    return cuentaOptional.orElse(null);
  }

  private double calculateSaldoDisponible(MovimientosRequestRecord movimientosRequestRecord,
      CuentaEntity cuenta, String identificacion) {
    double saldo;
    if (movimientosRequestRecord.tipoMovimiento().equals("Retiro")
        && cuenta.getSaldoInicial() >= movimientosRequestRecord.movimiento()) {
      saldo = cuenta.getSaldoInicial() - movimientosRequestRecord.movimiento();
      updateCuenta(saldo, cuenta);
      return saldo;
    } else if (movimientosRequestRecord.tipoMovimiento().equals("Deposito")) {
      saldo = cuenta.getSaldoInicial() + movimientosRequestRecord.movimiento();
      updateCuenta(saldo, cuenta);
      return saldo;
    } else {
      return 0;
    }
  }

  private void updateCuenta(double saldo, CuentaEntity cuenta) {
    cuentaCommandRepository.createOrUpdate(CuentaRequestRecord.builder()
        .numeroCuenta(cuenta.getNumeroCuenta())
        .tipoCuenta(cuenta.getTipoCuenta())
        .saldoInicial(saldo)
        .estado(cuenta.getEstado())
        .cedulaCliente(cuenta.getCliente().getIdentificacion())
        .build());
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }

}
