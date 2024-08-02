package tata.test.proyecto.base.controller.cuenta.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tata.test.domain.application.ports.input.cuenta.command.CuentaCommandService;
import tata.test.proyecto.base.controller.cuenta.command.CuentaCommandRestController;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.CuentaRequestRecord;

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
public class CuentaCommandRestControllerImpl implements CuentaCommandRestController {

  private final CuentaCommandService cuentaCommandService;


  @Override
  public ResponseEntity<ExceptionResponseRecord> createorUpdate(
      CuentaRequestRecord cuentaRequestRecord) {
    return cuentaCommandService.createOrUpdate(cuentaRequestRecord);
  }

  @Override
  public ResponseEntity<ExceptionResponseRecord> delete(Integer id) {
    return cuentaCommandService.delete(id);
  }
}