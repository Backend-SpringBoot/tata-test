package tata.test.dataaccess.adapter.persona.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.PersonaEntity;
import tata.test.dataaccess.mappers.PersonaMapper;
import tata.test.dataaccess.repository.PersonaJpaRepository;
import tata.test.domain.application.ports.output.repository.persona.query.PersonaQueryRepository;
import tata.test.exception.PersonaException;
import tata.test.record.response.PersonaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonaQueryRepositoryImpl implements PersonaQueryRepository {

  private final PersonaJpaRepository personaJpaRepository;

  @Override
  public PersonaResponseRecord getUser(Integer id) {
    PersonaEntity personaEntity =
        personaJpaRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new PersonaException(String.format("La persona con el id %d no existe.", id)));
    return PersonaMapper.INSTANCE.entityToResponseRecord(personaEntity);
  }

  @Override
  public List<PersonaResponseRecord> getUsers() {
    List<PersonaEntity> personaEntity =
        personaJpaRepository
            .findAll();
    return PersonaMapper.INSTANCE.entitiesToResponseRecords(personaEntity);
  }
}
