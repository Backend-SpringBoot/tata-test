package tata.test.proyecto.base.controller.cuenta.query.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tata.test.domain.application.ports.input.cuenta.query.CuentaQueryService;
import tata.test.proyecto.base.controller.cuenta.query.CuentaQueryRestController;
import tata.test.record.response.CuentaResponseRecord;

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
public class CuentaQueryRestControllerImpl implements CuentaQueryRestController {

  private final CuentaQueryService cuentaQueryService;

  @Override
  public CuentaResponseRecord getAccount(Integer id) {
    return cuentaQueryService.getAccount(id);
  }

  @Override
  public List<CuentaResponseRecord> getAccounts() {
    return cuentaQueryService.getAccounts();
  }
}