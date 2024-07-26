package tata.test.domain.application.ports.input.cuenta.command;

import tata.test.record.request.CuentaRequestRecord;
import tata.test.record.response.CuentaResponseRecord;

public interface CuentaCommandService {

  CuentaResponseRecord createOrUpdate(CuentaRequestRecord cuentaRequestRecord);

  void delete(Integer id);

}
