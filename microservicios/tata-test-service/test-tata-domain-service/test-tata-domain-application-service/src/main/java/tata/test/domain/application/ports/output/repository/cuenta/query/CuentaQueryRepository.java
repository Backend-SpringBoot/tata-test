package tata.test.domain.application.ports.output.repository.cuenta.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;

public interface CuentaQueryRepository {

  ResponseEntity<ExceptionResponseRecord> getAccount(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getAccounts();
}
