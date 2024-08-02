package tata.test.dataaccess.adapter.movimientos.query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.MovimientosEntity;
import tata.test.dataaccess.mappers.MovimientosMapper;
import tata.test.dataaccess.repository.MovimientosJpaRepository;
import tata.test.domain.application.ports.output.repository.movimientos.query.MovimientosQueryRepository;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.MovimientosResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientosQueryRepositoryImpl implements MovimientosQueryRepository {

  private final MovimientosJpaRepository movimientosJpaRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getTransaction(String id) {
    Optional<MovimientosEntity> movimientoOptional =
        movimientosJpaRepository
            .findByNumeroCuenta(id);
    if (movimientoOptional.isEmpty()) {
      return new ResponseEntity<>(CreateException("No existe registros", null), HttpStatus.OK);
    }
    MovimientosEntity movimiento = movimientoOptional.get();
    MovimientosResponseRecord data = MovimientosMapper.INSTANCE.entityToResponseRecord(movimiento);
    return new ResponseEntity<>(CreateException("Correcto !", data), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getTransactions() {
    List<MovimientosEntity> movimientoEntities =
        movimientosJpaRepository
            .findAll();
    List<MovimientosResponseRecord> data = MovimientosMapper.INSTANCE.entitiesToResponseRecords(
        movimientoEntities);
    return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
      LocalDateTime startDate, LocalDateTime endDate) {

    List<MovimientosEntity> movimientosEntities = movimientosJpaRepository.findByFechMovimientoBetween(
        startDate, endDate);

    if (movimientosEntities.isEmpty()) {
      return new ResponseEntity<>(List.of(
          CreateException("No se encontraron movimientos en el rango de fechas proporcionado",
              null)), HttpStatus.OK);
    }
    List<MovimientosResponseRecord> data = MovimientosMapper.INSTANCE.entitiesToResponseRecords(
        movimientosEntities);
    return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }
}
