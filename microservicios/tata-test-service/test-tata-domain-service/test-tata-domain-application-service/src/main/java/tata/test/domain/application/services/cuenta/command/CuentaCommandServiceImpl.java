package tata.test.domain.application.services.cuenta.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.cuenta.command.CuentaCommandService;
import tata.test.domain.application.ports.output.repository.cuenta.command.CuentaCommandRepository;
import tata.test.record.request.CuentaRequestRecord;
import tata.test.record.response.CuentaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CuentaCommandServiceImpl implements CuentaCommandService {

  private final CuentaCommandRepository cuentaCommandRepository;

  @Override
  @Transactional
  public CuentaResponseRecord createOrUpdate(CuentaRequestRecord cuentaRequestRecord) {
    return cuentaCommandRepository.createOrUpdate(cuentaRequestRecord);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    cuentaCommandRepository.delete(id);
  }
}
