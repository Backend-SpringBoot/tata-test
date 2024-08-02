package tata.test.dataaccess.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tata.test.dataaccess.entities.ClienteEntity;
import tata.test.dataaccess.mappers.core.GenericMapper;
import tata.test.record.request.ClienteRequestRecord;
import tata.test.record.response.ClienteResponseRecord;

@Mapper
public interface ClienteMapper extends GenericMapper<ClienteEntity, ClienteResponseRecord> {

  ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

  @Mapping(target = "contrasenia", source = "contrasenia")
  @Mapping(target = "nombre", source = "nombre")
  @Mapping(target = "genero", source = "genero")
  @Mapping(target = "edad", source = "edad")
  @Mapping(target = "identificacion", source = "identificacion")
  @Mapping(target = "direccion", source = "direccion")
  @Mapping(target = "telefono", source = "telefono")
  ClienteEntity requestRecordToEntity(ClienteRequestRecord clienteRequestRecord);

  @Override
  @Mapping(target = "cuentas", source = "cuentas")
  @Mapping(target = "nombre", source = "nombre")
  @Mapping(target = "identificacion", source = "identificacion")
  ClienteResponseRecord entityToResponseRecord(ClienteEntity clienteEntity);

  List<ClienteResponseRecord> entitiesToResponseRecords(List<ClienteEntity> clienteEntities);
}
