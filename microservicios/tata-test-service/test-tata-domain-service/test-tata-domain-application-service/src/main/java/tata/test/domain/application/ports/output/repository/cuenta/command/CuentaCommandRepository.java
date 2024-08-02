package tata.test.domain.application.ports.output.repository.cuenta.command;

import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.CuentaRequestRecord;

public interface CuentaCommandRepository {

  ResponseEntity<ExceptionResponseRecord> createOrUpdate(CuentaRequestRecord cuentaRequestRecord);

  ResponseEntity<ExceptionResponseRecord> delete(Integer id);
}
