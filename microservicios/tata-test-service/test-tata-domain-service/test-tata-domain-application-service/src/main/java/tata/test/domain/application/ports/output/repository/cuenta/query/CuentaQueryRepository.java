package tata.test.domain.application.ports.output.repository.cuenta.query;

import java.util.List;
import tata.test.record.response.CuentaResponseRecord;

public interface CuentaQueryRepository {

  CuentaResponseRecord getAccount(Integer id);

  List<CuentaResponseRecord> getAccounts();
}
