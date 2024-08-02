package tata.test.dataaccess.adapter.persona.query;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.PersonaEntity;
import tata.test.dataaccess.mappers.PersonaMapper;
import tata.test.dataaccess.repository.PersonaJpaRepository;
import tata.test.domain.application.ports.output.repository.persona.query.PersonaQueryRepository;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.PersonaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonaQueryRepositoryImpl implements PersonaQueryRepository {

  private final PersonaJpaRepository personaJpaRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getUser(String id) {
    Optional<PersonaEntity> personaOptional =
        personaJpaRepository
            .findByIdentificacion(id);
    if (personaOptional.isEmpty()) {
      return new ResponseEntity<>(CreateException("No existe registros", null), HttpStatus.OK);
    }
    PersonaEntity persona = personaOptional.get();
    PersonaResponseRecord data = PersonaMapper.INSTANCE.entityToResponseRecord(persona);
    return new ResponseEntity<>(CreateException("Correcto !", data), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getUsers() {
    List<PersonaEntity> personaEntities =
        personaJpaRepository
            .findAll();
    List<PersonaResponseRecord> data = PersonaMapper.INSTANCE.entitiesToResponseRecords(
        personaEntities);
    return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }
}
