package tata.test.dataaccess.adapter.cliente.query;

import java.util.List;
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
    return clienteJpaRepository.findByIdentificacion(id)
        .map(this::getClienteResponse)
        .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getCliets() {
    List<ClienteEntity> clienteEntities = clienteJpaRepository.findAll();
    List<ClienteResponseRecord> data = ClienteMapper.INSTANCE.entitiesToResponseRecords(
        clienteEntities);
    return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }

  private ResponseEntity<ExceptionResponseRecord> getClienteResponse(ClienteEntity cliente) {
    ClienteResponseRecord data = ClienteMapper.INSTANCE.entityToResponseRecord(cliente);
    return createSuccessResponse("Correcto!", data);
  }

  private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
    return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
  }

  private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
      ClienteResponseRecord data) {
    return new ResponseEntity<>(CreateException(message, data), HttpStatus.OK);
  }
}
