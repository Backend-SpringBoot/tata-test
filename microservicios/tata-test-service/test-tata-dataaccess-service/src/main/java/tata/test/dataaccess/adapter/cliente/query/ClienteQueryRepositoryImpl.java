package tata.test.dataaccess.adapter.cliente.query;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.ClienteEntity;
import tata.test.dataaccess.mappers.ClienteMapper;
import tata.test.dataaccess.repository.ClienteJpaRepository;
import tata.test.domain.application.ports.output.repository.cliente.query.ClienteQueryRepository;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.ClienteResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteQueryRepositoryImpl implements ClienteQueryRepository {

  private final ClienteJpaRepository clienteJpaRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getClient(String id) {
    Optional<ClienteEntity> clienteoptional =
        clienteJpaRepository
            .findByIdentificacion(id);
    if (clienteoptional.isEmpty()) {
      return new ResponseEntity<>(CreateException("No existe registros", null), HttpStatus.OK);

    }
    ClienteEntity cliente = clienteoptional.get();
    ClienteResponseRecord data = ClienteMapper.INSTANCE.entityToResponseRecord(cliente);
    return new ResponseEntity<>(CreateException("Correcto !", data), HttpStatus.OK);
  }

  @Override
  public List<ClienteResponseRecord> getCliets() {
    List<ClienteEntity> clienteEntities =
        clienteJpaRepository
            .findAll();
    return ClienteMapper.INSTANCE.entitiesToResponseRecords(clienteEntities);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }
}
