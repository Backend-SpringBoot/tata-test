package tata.test.domain.application.services.cliente.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.cliente.query.ClienteQueryService;
import tata.test.domain.application.ports.output.repository.cliente.query.ClienteQueryRepository;
import tata.test.record.ExceptionResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteQueryServiceImpl implements ClienteQueryService {

  private final ClienteQueryRepository clienteQueryRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getClient(String id) {
    return clienteQueryRepository.getClient(id);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getClients() {
    return clienteQueryRepository.getCliets();
  }
}
