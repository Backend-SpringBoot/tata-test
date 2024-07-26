package tata.test.domain.application.ports.input.persona.query;

import java.util.List;
import tata.test.record.response.PersonaResponseRecord;

public interface PersonaQueryService {

  PersonaResponseRecord getUser(Integer id);

  List<PersonaResponseRecord> getUsers();

}
