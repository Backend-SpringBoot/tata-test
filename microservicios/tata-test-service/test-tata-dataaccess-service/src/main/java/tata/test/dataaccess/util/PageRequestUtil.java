package tata.test.dataaccess.util;

import java.util.Objects;
import org.springframework.data.domain.PageRequest;

/**
 * -- AQUI AÑADIR LA DESCRIPCION DE LA CLASE --.
 *
 * <p>Historial de cambios:
 *
 * <ul>
 *   <li>1.0.0 - Descripción del cambio inicial - Francisco.Zabala - 14/11/2023
 *       <!-- Añadir nuevas entradas de cambios aquí -->
 * </ul>
 *
 * @author Francisco.Zabala
 * @version 1.0.0 $
 * @since 14/11/2023
 */
public final class PageRequestUtil {

  private PageRequestUtil() {
  }

  public static void validateId(Integer id, String message) {
    if (Objects.isNull(id)) {
      throw new IllegalArgumentException(message);
    }
  }

  public static PageRequest createPageRequest(int page, int size) {
    page = Math.max(page, 0);
    size = size <= 0 ? 10 : size;
    return PageRequest.of(page, size);
  }
}
