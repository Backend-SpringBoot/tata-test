package tata.test.domain.application.ports.input.cuenta.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;

public interface CuentaQueryService {

  ResponseEntity<ExceptionResponseRecord> getAccount(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getAccounts();

}
