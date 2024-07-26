package tata.test.domain.application.services.persona.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.persona.command.PersonaCommandService;
import tata.test.domain.application.ports.output.repository.persona.commad.PersonaCommandRepository;
import tata.test.record.request.PersonaRequestRecord;
import tata.test.record.response.PersonaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonaCommandServiceImpl implements PersonaCommandService {

  private final PersonaCommandRepository personaCommandRepository;

  @Override
  @Transactional
  public PersonaResponseRecord createOrUpdate(PersonaRequestRecord personaRequestRecord) {
    return personaCommandRepository.createOrUpdate(personaRequestRecord);
  }

  @Override
  public void delete(Integer id) {
    personaCommandRepository.delete(id);
  }
}
