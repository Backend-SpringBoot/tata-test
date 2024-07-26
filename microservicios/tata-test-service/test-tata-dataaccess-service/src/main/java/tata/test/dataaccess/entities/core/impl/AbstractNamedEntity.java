package tata.test.dataaccess.entities.core.impl;

import tata.test.dataaccess.entities.core.Named;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import tata.test.dataaccess.entities.core.Named;

@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@Audited
public abstract class AbstractNamedEntity<T, P> extends AbstractEntity<T, P> implements Named {

  @Serial private static final long serialVersionUID = -412218241272244614L;

  @NotBlank(message = "{cargo.nombre.notBlank}")
  @Size(max = 200, message = "{cargo.nombre.size}")
  @Column(name = "nombre", nullable = false, length = 200)
  private String nombre;

  @NotBlank(message = "{cargo.descripcion.notBlank}")
  @Size(max = 500, message = "{cargo.descripcion.size}")
  @Column(name = "descripcion", nullable = false, length = 500)
  private String descripcion;

  @Override
  public String getNombre() {
    return nombre;
  }

  @Override
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String getDescripcion() {
    return descripcion;
  }

  @Override
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
