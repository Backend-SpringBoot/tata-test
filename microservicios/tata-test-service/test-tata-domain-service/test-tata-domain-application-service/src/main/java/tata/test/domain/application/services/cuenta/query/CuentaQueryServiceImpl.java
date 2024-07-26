package tata.test.domain.application.services.cuenta.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.cuenta.query.CuentaQueryService;
import tata.test.domain.application.ports.output.repository.cuenta.query.CuentaQueryRepository;
import tata.test.record.response.CuentaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CuentaQueryServiceImpl implements CuentaQueryService {

  private final CuentaQueryRepository cuentaQueryRepository;

  @Override
  public CuentaResponseRecord getAccount(Integer id) {
    return cuentaQueryRepository.getAccount(id);
  }

  @Override
  public List<CuentaResponseRecord> getAccounts() {
    return cuentaQueryRepository.getAccounts();
  }
}
