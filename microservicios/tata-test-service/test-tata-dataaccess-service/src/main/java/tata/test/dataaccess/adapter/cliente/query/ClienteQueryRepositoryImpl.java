package tata.test.dataaccess.adapter.cliente.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.ClienteEntity;
import tata.test.dataaccess.mappers.ClienteMapper;
import tata.test.dataaccess.repository.ClienteJpaRepository;
import tata.test.domain.application.ports.output.repository.cliente.query.ClienteQueryRepository;
import tata.test.exception.PersonaException;
import tata.test.record.response.ClienteResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteQueryRepositoryImpl implements ClienteQueryRepository {

  private final ClienteJpaRepository clienteJpaRepository;

  @Override
  public ClienteResponseRecord getClient(Integer id) {
    ClienteEntity clienteEntity =
        clienteJpaRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new PersonaException(String.format("El cliente con el id %d no existe.", id)));
    return ClienteMapper.INSTANCE.entityToResponseRecord(clienteEntity);
  }

  @Override
  public List<ClienteResponseRecord> getCliets() {
    List<ClienteEntity> clienteEntities =
        clienteJpaRepository
            .findAll();
    return ClienteMapper.INSTANCE.entitiesToResponseRecords(clienteEntities);
  }
}
