package tata.test.domain.application.ports.input.movimientos.command;

import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.MovimientosRequestRecord;

public interface MovimientosCommandService {

  ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      MovimientosRequestRecord movimientosRequestRecord);

  void delete(Integer id);

}
