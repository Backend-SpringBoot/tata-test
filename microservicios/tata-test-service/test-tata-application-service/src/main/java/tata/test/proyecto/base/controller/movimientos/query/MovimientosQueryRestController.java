package tata.test.proyecto.base.controller.movimientos.query;

import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.MovimientosResponseRecord;

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
@RequestMapping("/query/movimientos")
@Validated
public interface MovimientosQueryRestController {

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Obtiene un movimiento")
  MovimientosResponseRecord getTransaction(@PathVariable("id") Integer id);


  @GetMapping("/transactions")
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Obtiene lista de movimientos")
  List<MovimientosResponseRecord> getTransactions();


  @GetMapping("/transactions/byDate")
  ResponseEntity<List<ExceptionResponseRecord>> getMovimientosPorRangoFechas(
      @RequestParam("startDate") LocalDateTime startDate,
      @RequestParam("endDate") LocalDateTime endDate);
}