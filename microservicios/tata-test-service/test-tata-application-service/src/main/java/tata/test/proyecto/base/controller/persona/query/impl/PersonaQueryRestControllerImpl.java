package tata.test.proyecto.base.controller.persona.query.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tata.test.domain.application.ports.input.persona.query.PersonaQueryService;
import tata.test.proyecto.base.controller.persona.query.PersonaQueryRestController;
import tata.test.record.response.PersonaResponseRecord;

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
public class PersonaQueryRestControllerImpl implements PersonaQueryRestController {

  private final PersonaQueryService personaQueryService;

  @Override
  public PersonaResponseRecord getUser(Integer id) {
    return personaQueryService.getUser(id);
  }

  @Override
  public List<PersonaResponseRecord> getUsers() {
    return personaQueryService.getUsers();
  }
}