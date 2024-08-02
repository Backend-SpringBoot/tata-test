package tata.test.domain.application.services.movimientos.query;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.movimientos.query.MovimientosQueryService;
import tata.test.domain.application.ports.output.repository.movimientos.query.MovimientosQueryRepository;
import tata.test.record.ExceptionResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientosQueryServiceImpl implements MovimientosQueryService {

  private final MovimientosQueryRepository movimientosQueryRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getTransaction(String id) {
    return movimientosQueryRepository.getTransaction(id);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getTransactions() {
    return movimientosQueryRepository.getTransactions();
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
      LocalDateTime startDate, LocalDateTime endDate) {
    return movimientosQueryRepository.getMovimientosPorRangoFechas(startDate, endDate);
  }
}
