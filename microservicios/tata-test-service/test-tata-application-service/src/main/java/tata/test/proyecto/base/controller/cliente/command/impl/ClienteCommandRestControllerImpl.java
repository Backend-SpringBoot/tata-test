package tata.test.proyecto.base.controller.cliente.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tata.test.domain.application.ports.input.cliente.command.ClienteCommandService;
import tata.test.proyecto.base.controller.cliente.command.ClienteCommandRestController;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.request.ClienteRequestRecord;

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
public class ClienteCommandRestControllerImpl implements ClienteCommandRestController {

  private final ClienteCommandService clienteCommandService;


  @Override
  public ResponseEntity<ExceptionResponseRecord> createorUpdate(
      ClienteRequestRecord clienteRequestRecord) {
    return clienteCommandService.createOrUpdate(clienteRequestRecord);
  }

  @Override
  public ResponseEntity<ExceptionResponseRecord> delete(String identificacion) {
    return clienteCommandService.delete(identificacion);
  }
}