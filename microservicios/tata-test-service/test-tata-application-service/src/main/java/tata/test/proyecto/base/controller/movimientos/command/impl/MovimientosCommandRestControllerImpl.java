package tata.test.proyecto.base.controller.movimientos.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tata.test.domain.application.ports.input.movimientos.command.MovimientosCommandService;
import tata.test.proyecto.base.controller.movimientos.command.MovimientosCommandRestController;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.MovimientosRequestRecord;

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
public class MovimientosCommandRestControllerImpl implements MovimientosCommandRestController {

  private final MovimientosCommandService movimientosCommandService;


  @Override
  public ResponseEntity<ExceptionResponseRecord> createorUpdate(
      MovimientosRequestRecord movimientosRequestRecord) {
    return movimientosCommandService.createOrUpdate(movimientosRequestRecord);
  }

  @Override
  public void delete(Integer id) {
    movimientosCommandService.delete(id);
  }
}