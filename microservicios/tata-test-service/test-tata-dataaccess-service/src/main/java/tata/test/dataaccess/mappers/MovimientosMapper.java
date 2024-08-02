package tata.test.dataaccess.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tata.test.dataaccess.entities.MovimientosEntity;
import tata.test.dataaccess.mappers.core.GenericMapper;
import tata.test.record.request.MovimientosRequestRecord;
import tata.test.record.response.MovimientosResponseRecord;

@Mapper
public interface MovimientosMapper extends
    GenericMapper<MovimientosEntity, MovimientosResponseRecord> {

  MovimientosMapper INSTANCE = Mappers.getMapper(MovimientosMapper.class);

  MovimientosEntity requestRecordToEntity(MovimientosRequestRecord movimientosRequestRecord);

  @Override
  @Mapping(target = "cliente", source = "ciente")
  MovimientosResponseRecord entityToResponseRecord(MovimientosEntity movimientosEntity);
  
  List<MovimientosResponseRecord> entitiesToResponseRecords(
      List<MovimientosEntity> movimientosEntities);
}
