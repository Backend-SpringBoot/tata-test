package tata.test.domain.application.services.movimientos.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.domain.application.ports.input.movimientos.query.MovimientosQueryService;
import tata.test.domain.application.ports.output.repository.movimientos.query.MovimientosQueryRepository;
import tata.test.record.response.MovimientosResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientosQueryServiceImpl implements MovimientosQueryService {

  private final MovimientosQueryRepository movimientosQueryRepository;

  @Override
  public MovimientosResponseRecord getTransaction(Integer id) {
    return movimientosQueryRepository.getTransaction(id);
  }

  @Override
  public List<MovimientosResponseRecord> getTransactions() {
    return movimientosQueryRepository.getTransactions();
  }
}
