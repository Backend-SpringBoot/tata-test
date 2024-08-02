package tata.test.proyecto.base.controller.cliente.query.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tata.test.domain.application.ports.input.cliente.query.ClienteQueryService;
import tata.test.proyecto.base.controller.cliente.query.ClienteQueryRestController;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.ClienteResponseRecord;

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
public class ClienteQueryRestControllerImpl implements ClienteQueryRestController {

  private final ClienteQueryService clienteQueryService;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getClient(String id) {
    return clienteQueryService.getClient(id);
  }

  @Override
  public List<ClienteResponseRecord> getClients() {
    return clienteQueryService.getClients();
  }
}