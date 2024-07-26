package tata.test.dataaccess.adapter.persona.command;

import java.util.Optional;
import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.PersonaEntity;
import tata.test.dataaccess.mappers.PersonaMapper;
import tata.test.dataaccess.repository.PersonaJpaRepository;
import tata.test.domain.application.ports.output.repository.persona.commad.PersonaCommandRepository;
import tata.test.exception.PersonaException;
import tata.test.record.request.PersonaRequestRecord;
import tata.test.record.response.PersonaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonaCommandRepositoryImpl implements PersonaCommandRepository {

  private final PersonaJpaRepository personaJpaRepository;

  @Override
  @Transactional
  public PersonaResponseRecord createOrUpdate(PersonaRequestRecord personaRequestRecord) {
    PersonaEntity entity;

    if (personaRequestRecord.id() != null) {
      entity = updateExistingPersona(personaRequestRecord);
    } else {
      Optional<PersonaEntity> persona = personaJpaRepository.findByIdentificacion(
          personaRequestRecord.identificacion());

      if (persona.isEmpty()) {
        entity = PersonaMapper.INSTANCE.requestRecordToEntity(personaRequestRecord);
      } else {
        PersonaRequestRecord requestRecord = PersonaMapper.INSTANCE.entityToRequestRecord(
            persona.get());
        entity = updateExistingPersona(requestRecord);
      }
    }
    return PersonaMapper.INSTANCE.entityToResponseRecord(personaJpaRepository.save(entity));
  }

  @Override
  public void delete(Integer id) {
    personaJpaRepository.deleteById(id);
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
}
