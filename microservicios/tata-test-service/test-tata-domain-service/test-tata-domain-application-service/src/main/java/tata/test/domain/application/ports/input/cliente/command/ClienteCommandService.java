package tata.test.domain.application.ports.input.cliente.command;

import tata.test.record.request.ClienteRequestRecord;
import tata.test.record.response.ClienteResponseRecord;

public interface ClienteCommandService {

  ClienteResponseRecord createOrUpdate(ClienteRequestRecord clienteRequestRecord);

  void delete(Integer id);

}
