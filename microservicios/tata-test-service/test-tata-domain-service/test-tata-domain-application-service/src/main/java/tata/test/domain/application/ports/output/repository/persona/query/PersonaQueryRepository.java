package tata.test.domain.application.ports.output.repository.persona.query;

import java.util.List;
import tata.test.record.response.PersonaResponseRecord;

public interface PersonaQueryRepository {

  PersonaResponseRecord getUser(Integer id);

  List<PersonaResponseRecord> getUsers();
}
