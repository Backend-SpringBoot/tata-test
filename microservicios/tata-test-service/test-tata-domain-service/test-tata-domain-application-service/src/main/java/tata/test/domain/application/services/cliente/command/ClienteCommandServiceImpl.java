package tata.test.domain.application.services.cliente.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.cliente.command.ClienteCommandService;
import tata.test.domain.application.ports.output.repository.cliente.command.ClienteCommandRepository;
import tata.test.record.request.ClienteRequestRecord;
import tata.test.record.response.ClienteResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteCommandServiceImpl implements ClienteCommandService {

  private final ClienteCommandRepository clienteCommandRepository;

  @Override
  @Transactional
  public ClienteResponseRecord createOrUpdate(ClienteRequestRecord clienteRequestRecord) {
    return clienteCommandRepository.createOrUpdate(clienteRequestRecord);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    clienteCommandRepository.delete(id);
  }
}
