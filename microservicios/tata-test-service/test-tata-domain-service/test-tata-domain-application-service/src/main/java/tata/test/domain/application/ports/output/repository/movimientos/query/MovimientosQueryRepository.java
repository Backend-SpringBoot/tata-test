package tata.test.domain.application.ports.output.repository.movimientos.query;

import java.util.List;
import tata.test.record.response.MovimientosResponseRecord;

public interface MovimientosQueryRepository {

  MovimientosResponseRecord getTransaction(Integer id);

  List<MovimientosResponseRecord> getTransactions();
}
