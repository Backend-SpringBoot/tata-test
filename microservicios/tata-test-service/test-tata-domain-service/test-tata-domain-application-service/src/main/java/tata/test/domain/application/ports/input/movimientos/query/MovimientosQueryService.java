package tata.test.domain.application.ports.input.movimientos.query;

import java.util.List;
import tata.test.record.response.MovimientosResponseRecord;

public interface MovimientosQueryService {

  MovimientosResponseRecord getTransaction(Integer id);

  List<MovimientosResponseRecord> getTransactions();

}
