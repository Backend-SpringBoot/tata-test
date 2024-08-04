package tata.test.domain.application.ports.output.repository.persona.commad;

import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.PersonaRequestRecord;

public interface PersonaCommandRepository {

  ResponseEntity<ExceptionResponseRecord> createOrUpdate(PersonaRequestRecord personaRequestRecord);

  ResponseEntity<ExceptionResponseRecord> delete(String id);
}
