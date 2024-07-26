package tata.test.proyecto.base.controller.persona.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tata.test.domain.application.ports.input.persona.command.PersonaCommandService;
import tata.test.proyecto.base.controller.persona.command.PersonaCommandRestController;
import tata.test.record.request.PersonaRequestRecord;
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
public class PersonaCommandRestControllerImpl implements PersonaCommandRestController {

  private final PersonaCommandService personaCommandService;

  @Override
  public PersonaResponseRecord createorUpdate(PersonaRequestRecord personaRequestRecord) {
    return personaCommandService.createOrUpdate(personaRequestRecord);
  }

  @Override
  public void delete(Integer id) {
    personaCommandService.delete(id);
  }
}