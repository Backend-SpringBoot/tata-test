package tata.test.dataaccess.adapter.persona.command;

import java.util.Optional;
import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.PersonaEntity;
import tata.test.dataaccess.mappers.PersonaMapper;
import tata.test.dataaccess.repository.PersonaJpaRepository;
import tata.test.domain.application.ports.output.repository.persona.commad.PersonaCommandRepository;
import tata.test.exception.PersonaException;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.PersonaRequestRecord;
import tata.test.record.response.PersonaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonaCommandRepositoryImpl implements PersonaCommandRepository {

  private final PersonaJpaRepository personaJpaRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      PersonaRequestRecord personaRequestRecord) {

    PersonaEntity entity = getPersonaEntity(personaRequestRecord);
    PersonaResponseRecord saved = PersonaMapper.INSTANCE.entityToResponseRecord(
        personaJpaRepository.save(entity));
    return createSuccessResponse(
        "Cuenta almacenada para el usuario: ",
        saved);
  }

  @Override
  public ResponseEntity<ExceptionResponseRecord> delete(String id) {
    return personaJpaRepository.findByIdentificacion(id)
        .map(this::deleteCuenta)
        .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
  }

  private ResponseEntity<ExceptionResponseRecord> deleteCuenta(PersonaEntity persona) {
    personaJpaRepository.deleteByIdentificacion(persona.getIdentificacion());
    return createSuccessResponse("Persona eliminada",
        PersonaMapper.INSTANCE.entityToResponseRecord(persona));
  }

  private PersonaEntity updateExistingPersona(PersonaRequestRecord requestRecord) {
    PersonaEntity existingEntity =
        personaJpaRepository
            .findById(requestRecord.id())
            .orElseThrow(() -> new PersonaException("Persona no encontrada"));

    final StampedLock lock = existingEntity.getLock();
    long stamp = lock.writeLock();
    try {
      existingEntity.setNombre(requestRecord.nombre());
      existingEntity.setGenero(requestRecord.genero());
      existingEntity.setEstado(requestRecord.estado());
      existingEntity.setEdad(requestRecord.edad());
      existingEntity.setIdentificacion(requestRecord.identificacion());
      existingEntity.setDireccion(requestRecord.direccion());
      existingEntity.setTelefono(requestRecord.telefono());

    } finally {
      lock.unlockWrite(stamp);
    }
    return existingEntity;
  }

  private PersonaEntity getPersonaEntity(PersonaRequestRecord personaRequestRecord) {
    Optional<PersonaEntity> personaOptional = personaJpaRepository.findByIdentificacion(
        personaRequestRecord.identificacion());
    return personaOptional.isPresent() ? updateExistingPersona(personaRequestRecord)
        : createNewPersona(personaRequestRecord);
  }

  private PersonaEntity createNewPersona(PersonaRequestRecord personaRequestRecord) {
    return PersonaMapper.INSTANCE.requestRecordToEntity(personaRequestRecord);
  }

  private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
      PersonaResponseRecord saved) {
    return new ResponseEntity<>(CreateException(message, saved), HttpStatus.OK);
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
}
