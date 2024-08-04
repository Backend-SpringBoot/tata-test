package tata.test.domain.application.ports.input.persona.command;

import org.springframework.http.ResponseEntity;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.PersonaRequestRecord;

public interface PersonaCommandService {

  ResponseEntity<ExceptionResponseRecord> createOrUpdate(PersonaRequestRecord personaRequestRecord);

  ResponseEntity<ExceptionResponseRecord> delete(String id);

}
