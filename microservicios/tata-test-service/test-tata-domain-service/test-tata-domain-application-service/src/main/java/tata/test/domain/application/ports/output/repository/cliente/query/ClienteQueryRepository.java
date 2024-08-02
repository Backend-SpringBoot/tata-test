package tata.test.domain.application.ports.output.repository.cliente.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;

public interface ClienteQueryRepository {

  ResponseEntity<ExceptionResponseRecord> getClient(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getCliets();
}
