package tata.test.domain.application.ports.output.repository.persona.commad;

import tata.test.record.request.PersonaRequestRecord;
import tata.test.record.response.PersonaResponseRecord;

public interface PersonaCommandRepository {

  PersonaResponseRecord createOrUpdate(PersonaRequestRecord personaRequestRecord);

  void delete(Integer id);
}
