package tata.test.dataaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.concurrent.locks.StampedLock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
@Audited
@Table(
    name = "persona"
)
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public class PersonaEntity {

  @NotBlank(message = "{Nombre no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25 ")
  @Column(name = "nombre", nullable = false)
  private String nombre;

  @NotBlank(message = "{Edad no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "genero", nullable = false)
  private String genero;

  @Column(name = "edad", nullable = false)
  @NotNull(message = "Edad no debe ser nulo")
  @Positive(message = "Edad debe ser positivo")
  private Integer edad;

  @Id
  @NotBlank(message = "{Identificación no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "identificacion", nullable = false)
  private String identificacion;

  @NotBlank(message = "{Dirección no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "direccion", nullable = false)
  private String direccion;

  @NotBlank(message = "{Teléfono no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "telefono", nullable = false)
  private String telefono;

  @NotBlank(message = "Estado no puede ser blanco")
  @Size(max = 5, message = "Estado es True o False")
  @Column(name = "estado", nullable = false, length = 5)
  private String estado;

  @Transient
  private StampedLock lock = new StampedLock();

  public StampedLock getLock() {
    return lock;
  }
}
