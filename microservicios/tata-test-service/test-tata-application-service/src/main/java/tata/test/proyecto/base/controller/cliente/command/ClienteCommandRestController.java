package tata.test.proyecto.base.controller.cliente.command;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import tata.test.record.request.ClienteRequestRecord;
import tata.test.record.response.ClienteResponseRecord;

/**
 * -- AQUI AÑADIR LA DESCRIPCION DE LA INTERFACE --.
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
@RequestMapping("/command/clientes")
@Validated
public interface ClienteCommandRestController {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Crea o actualiza un cliente")
  ClienteResponseRecord createorUpdate(
      @Valid @NotNull @RequestBody ClienteRequestRecord clienteRequestRecord);


  @DeleteMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Crea o actualiza un cliente")
  void delete(@PathVariable("id") Integer id);
}