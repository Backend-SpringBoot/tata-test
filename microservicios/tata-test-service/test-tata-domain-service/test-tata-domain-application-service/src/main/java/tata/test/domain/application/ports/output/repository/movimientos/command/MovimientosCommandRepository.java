package tata.test.domain.application.ports.output.repository.movimientos.command;

import tata.test.record.request.MovimientosRequestRecord;
import tata.test.record.response.MovimientosResponseRecord;

public interface MovimientosCommandRepository {

  MovimientosResponseRecord createOrUpdate(MovimientosRequestRecord movimientosRequestRecord);

  void delete(Integer id);
}
