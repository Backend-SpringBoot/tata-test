package tata.test.domain.application.services.cuenta.command;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.cuenta.command.CuentaCommandService;
import tata.test.domain.application.ports.output.repository.cuenta.command.CuentaCommandRepository;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.CuentaRequestRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CuentaCommandServiceImpl implements CuentaCommandService {

  private final CuentaCommandRepository cuentaCommandRepository;

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> createOrUpdate(
      CuentaRequestRecord cuentaRequestRecord) {
    return cuentaCommandRepository.createOrUpdate(cuentaRequestRecord);
  }

  @Override
  @Transactional
  public ResponseEntity<ExceptionResponseRecord> delete(Integer id) {
    return cuentaCommandRepository.delete(id);
  }
}
