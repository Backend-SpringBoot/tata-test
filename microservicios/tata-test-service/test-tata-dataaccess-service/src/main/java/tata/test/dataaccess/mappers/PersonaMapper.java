package tata.test.dataaccess.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tata.test.dataaccess.entities.PersonaEntity;
import tata.test.dataaccess.mappers.core.GenericMapper;
import tata.test.record.request.PersonaRequestRecord;
import tata.test.record.response.PersonaResponseRecord;

@Mapper
public interface PersonaMapper extends GenericMapper<PersonaEntity, PersonaResponseRecord> {

  PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);

  PersonaEntity requestRecordToEntity(PersonaRequestRecord personaRequestRecord);

  @Override
  PersonaResponseRecord entityToResponseRecord(PersonaEntity personaEntity);

  PersonaRequestRecord entityToRequestRecord(PersonaEntity personaEntity);

  List<PersonaResponseRecord> entitiesToResponseRecords(List<PersonaEntity> personaEntities);
}
