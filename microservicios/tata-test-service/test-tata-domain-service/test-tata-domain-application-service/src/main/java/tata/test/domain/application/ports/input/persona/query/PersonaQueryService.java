package tata.test.domain.application.ports.input.persona.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;

public interface PersonaQueryService {

  ResponseEntity<ExceptionResponseRecord> getUser(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getUsers();

}
