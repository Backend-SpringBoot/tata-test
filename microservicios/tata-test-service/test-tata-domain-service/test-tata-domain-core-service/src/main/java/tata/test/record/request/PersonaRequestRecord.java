package tata.test.record.request;

/**
 * -- AQUI AÑADIR LA DESCRIPCION DEL RECORD --.
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
public record PersonaRequestRecord(
    Integer id,
    String nombre,
    String genero,
    Integer edad,
    String identificacion,
    String direccion,
    String estado,
    String telefono

) {

}