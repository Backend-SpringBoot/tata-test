package tata.test.domain.application.ports.output.repository.cliente.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.ClienteResponseRecord;

public interface ClienteQueryRepository {

  ResponseEntity<ExceptionResponseRecord> getClient(String id);

  List<ClienteResponseRecord> getCliets();
}
