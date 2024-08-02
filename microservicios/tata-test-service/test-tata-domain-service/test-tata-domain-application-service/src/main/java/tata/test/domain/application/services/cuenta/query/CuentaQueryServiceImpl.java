package tata.test.domain.application.services.cuenta.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.cuenta.query.CuentaQueryService;
import tata.test.domain.application.ports.output.repository.cuenta.query.CuentaQueryRepository;
import tata.test.record.ExceptionResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CuentaQueryServiceImpl implements CuentaQueryService {

  private final CuentaQueryRepository cuentaQueryRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getAccount(String id) {
    return cuentaQueryRepository.getAccount(id);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getAccounts() {
    return cuentaQueryRepository.getAccounts();
  }
}
