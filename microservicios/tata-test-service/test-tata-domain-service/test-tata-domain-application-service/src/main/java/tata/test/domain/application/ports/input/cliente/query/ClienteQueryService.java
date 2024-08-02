package tata.test.domain.application.ports.input.cliente.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.ClienteResponseRecord;

public interface ClienteQueryService {

  ResponseEntity<ExceptionResponseRecord> getClient(String id);

  List<ClienteResponseRecord> getClients();

}
