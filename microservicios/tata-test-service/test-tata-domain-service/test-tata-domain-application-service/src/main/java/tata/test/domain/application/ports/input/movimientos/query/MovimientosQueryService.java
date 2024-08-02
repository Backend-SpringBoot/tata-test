package tata.test.domain.application.ports.input.movimientos.query;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.MovimientosResponseRecord;

public interface MovimientosQueryService {

  ResponseEntity<ExceptionResponseRecord> getTransaction(String id);

  List<MovimientosResponseRecord> getTransactions();

  ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
      LocalDateTime startDate, LocalDateTime endDate);

}
