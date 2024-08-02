package tata.test.domain.application.ports.output.repository.persona.query;

import java.util.List;
import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;

public interface PersonaQueryRepository {

  ResponseEntity<ExceptionResponseRecord> getUser(String id);

  ResponseEntity<List<ExceptionResponseRecord>> getUsers();
}
