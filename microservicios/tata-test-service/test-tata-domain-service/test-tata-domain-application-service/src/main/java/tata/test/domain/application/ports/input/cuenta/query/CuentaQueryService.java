package tata.test.domain.application.ports.input.cuenta.query;

import java.util.List;
import tata.test.record.response.CuentaResponseRecord;

public interface CuentaQueryService {

  CuentaResponseRecord getAccount(Integer id);

  List<CuentaResponseRecord> getAccounts();

}
