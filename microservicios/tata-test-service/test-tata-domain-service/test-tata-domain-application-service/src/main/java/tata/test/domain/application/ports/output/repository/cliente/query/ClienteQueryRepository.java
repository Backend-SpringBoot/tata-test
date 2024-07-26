package tata.test.domain.application.ports.output.repository.cliente.query;

import java.util.List;
import tata.test.record.response.ClienteResponseRecord;

public interface ClienteQueryRepository {

  ClienteResponseRecord getClient(Integer id);

  List<ClienteResponseRecord> getCliets();
}
