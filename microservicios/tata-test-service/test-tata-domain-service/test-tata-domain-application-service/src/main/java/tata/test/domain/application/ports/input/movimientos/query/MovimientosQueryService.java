package tata.test.domain.application.ports.input.movimientos.query;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;

public interface MovimientosQueryService {

  ResponseEntity<ExceptionResponseRecord> getTransaction(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getTransactions();

  ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
      LocalDateTime startDate, LocalDateTime endDate);

}
