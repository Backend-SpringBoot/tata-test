package tata.test.dataaccess.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tata.test.dataaccess.entities.CuentaEntity;
import tata.test.dataaccess.mappers.core.GenericMapper;
import tata.test.record.request.CuentaRequestRecord;
import tata.test.record.response.CuentaResponseRecord;

@Mapper
public interface CuentaMapper extends GenericMapper<CuentaEntity, CuentaResponseRecord> {

  CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

  CuentaEntity requestRecordToEntity(CuentaRequestRecord cuentaRequestRecord);

  @Override
  CuentaResponseRecord entityToResponseRecord(CuentaEntity cuentaEntity);

  List<CuentaResponseRecord> entitiesToResponseRecords(List<CuentaEntity> cuentaEntities);
}
