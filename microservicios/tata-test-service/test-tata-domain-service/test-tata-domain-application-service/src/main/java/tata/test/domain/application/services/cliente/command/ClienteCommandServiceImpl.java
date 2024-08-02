package tata.test.domain.application.services.cliente.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.cliente.command.ClienteCommandService;
import tata.test.domain.application.ports.output.repository.cliente.command.ClienteCommandRepository;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.ClienteRequestRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClienteCommandServiceImpl implements ClienteCommandService {

  private final ClienteCommandRepository clienteCommandRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      ClienteRequestRecord clienteRequestRecord) {
    return clienteCommandRepository.createOrUpdate(clienteRequestRecord);
  }

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> delete(String identificacion) {
    return clienteCommandRepository.delete(identificacion);
  }
}
