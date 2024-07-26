package tata.test.domain.application.services.persona.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.persona.query.PersonaQueryService;
import tata.test.domain.application.ports.output.repository.persona.query.PersonaQueryRepository;
import tata.test.record.response.PersonaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonaQueryServiceImpl implements PersonaQueryService {

  private final PersonaQueryRepository personaQueryRepository;

  @Override
  public PersonaResponseRecord getUser(Integer id) {
    return personaQueryRepository.getUser(id);
  }

  @Override
  public List<PersonaResponseRecord> getUsers() {
    return personaQueryRepository.getUsers();
  }
}
