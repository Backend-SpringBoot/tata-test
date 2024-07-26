package tata.test.proyecto.base.controller.movimientos.query.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tata.test.domain.application.ports.input.movimientos.query.MovimientosQueryService;
import tata.test.proyecto.base.controller.movimientos.query.MovimientosQueryRestController;
import tata.test.record.response.MovimientosResponseRecord;

/**
 * -- AQUI AÑADIR LA DESCRIPCION DE LA IMPLMENTACIÓN DE LA INTERFACE --.
 *
 * <p>Historial de cambios:
 *
 * <ul>
 *   <li>1.0.0 - Descripción del cambio inicial - developer - 6/25/24
 *       <!-- Añadir nuevas entradas de cambios aquí -->
 * </ul>
 *
 * @author developer
 * @version 1.0.0
 * @since 6/25/24
 */
@RestController
@RequiredArgsConstructor
public class MovimientosQueryRestControllerImpl implements MovimientosQueryRestController {

  private final MovimientosQueryService movimientosQueryService;


  @Override
  public MovimientosResponseRecord getTransaction(Integer id) {
    return movimientosQueryService.getTransaction(id);
  }

  @Override
  public List<MovimientosResponseRecord> getTransactions() {
    return movimientosQueryService.getTransactions();
  }
}