package tata.test.domain.application.ports.input.cliente.command;

import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.ClienteRequestRecord;

public interface ClienteCommandService {

  ResponseEntity<ExceptionResponseRecord> createOrUpdate(ClienteRequestRecord clienteRequestRecord);

  ResponseEntity<ExceptionResponseRecord> delete(String identificacion);

}
