package tata.test.domain.application.ports.input.cliente.query;

import java.util.List;
import tata.test.record.response.ClienteResponseRecord;

public interface ClienteQueryService {

  ClienteResponseRecord getClient(Integer id);

  List<ClienteResponseRecord> getClients();

}
