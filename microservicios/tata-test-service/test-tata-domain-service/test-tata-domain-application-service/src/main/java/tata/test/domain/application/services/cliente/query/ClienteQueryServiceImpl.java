package tata.test.domain.application.services.cliente.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.cliente.query.ClienteQueryService;
import tata.test.domain.application.ports.output.repository.cliente.query.ClienteQueryRepository;
import tata.test.record.response.ClienteResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteQueryServiceImpl implements ClienteQueryService {

  private final ClienteQueryRepository clienteQueryRepository;

  @Override
  public ClienteResponseRecord getClient(Integer id) {
    return clienteQueryRepository.getClient(id);
  }

  @Override
  public List<ClienteResponseRecord> getClients() {
    return clienteQueryRepository.getCliets();
  }
}
