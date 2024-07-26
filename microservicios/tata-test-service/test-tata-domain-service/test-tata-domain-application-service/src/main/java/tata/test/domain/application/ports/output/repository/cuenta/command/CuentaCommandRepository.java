package tata.test.domain.application.ports.output.repository.cuenta.command;

import tata.test.record.request.CuentaRequestRecord;
import tata.test.record.response.CuentaResponseRecord;

public interface CuentaCommandRepository {

  CuentaResponseRecord createOrUpdate(CuentaRequestRecord cuentaRequestRecord);

  void delete(Integer id);
}
