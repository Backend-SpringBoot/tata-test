package tata.test.domain.application.ports.input.movimientos.command;

import tata.test.record.request.MovimientosRequestRecord;
import tata.test.record.response.MovimientosResponseRecord;

public interface MovimientosCommandService {

  MovimientosResponseRecord createOrUpdate(MovimientosRequestRecord movimientosRequestRecord);

  void delete(Integer id);

}
