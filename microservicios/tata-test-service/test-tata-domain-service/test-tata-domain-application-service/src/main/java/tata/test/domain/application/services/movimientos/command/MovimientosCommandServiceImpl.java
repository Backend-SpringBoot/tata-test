package tata.test.domain.application.services.movimientos.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.movimientos.command.MovimientosCommandService;
import tata.test.domain.application.ports.output.repository.movimientos.command.MovimientosCommandRepository;
import tata.test.record.request.MovimientosRequestRecord;
import tata.test.record.response.MovimientosResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientosCommandServiceImpl implements MovimientosCommandService {

  private final MovimientosCommandRepository movimientosCommandRepository;

  @Override
  @Transactional
  public MovimientosResponseRecord createOrUpdate(
      MovimientosRequestRecord movimientosRequestRecord) {
    return movimientosCommandRepository.createOrUpdate(movimientosRequestRecord);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    movimientosCommandRepository.delete(id);
  }
}
