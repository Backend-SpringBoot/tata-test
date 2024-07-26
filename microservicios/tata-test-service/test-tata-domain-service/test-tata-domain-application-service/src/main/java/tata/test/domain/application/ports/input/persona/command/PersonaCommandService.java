package tata.test.domain.application.ports.input.persona.command;

import tata.test.record.request.PersonaRequestRecord;
import tata.test.record.response.PersonaResponseRecord;

public interface PersonaCommandService {

  PersonaResponseRecord createOrUpdate(PersonaRequestRecord personaRequestRecord);

  void delete(Integer id);

}
