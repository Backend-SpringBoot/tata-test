package tata.test.dataaccess.adapter.persona.query;

import java.util.List;
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
    return personaJpaRepository.findByIdentificacion(id)
        .map(this::getPersonaResponse)
        .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getUsers() {
    List<PersonaEntity> personaEntities = personaJpaRepository.findAll();
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

  private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
    return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
  }

  private ResponseEntity<ExceptionResponseRecord> getPersonaResponse(PersonaEntity persona) {
    PersonaResponseRecord data = PersonaMapper.INSTANCE.entityToResponseRecord(persona);
    return createSuccessResponse("Correcto!", data);
  }

  private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
      PersonaResponseRecord data) {
    return new ResponseEntity<>(CreateException(message, data), HttpStatus.OK);
  }
}
