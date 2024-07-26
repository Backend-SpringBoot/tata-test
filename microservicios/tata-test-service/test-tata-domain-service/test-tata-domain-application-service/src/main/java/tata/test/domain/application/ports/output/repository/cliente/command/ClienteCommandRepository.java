package tata.test.domain.application.ports.output.repository.cliente.command;

import tata.test.record.request.ClienteRequestRecord;
import tata.test.record.response.ClienteResponseRecord;

public interface ClienteCommandRepository {

  ClienteResponseRecord createOrUpdate(ClienteRequestRecord clienteRequestRecord);

  void delete(Integer id);
}
