package tata.test.domain.application.ports.input.cliente.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;

public interface ClienteQueryService {

  ResponseEntity<ExceptionResponseRecord> getClient(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getClients();

}
