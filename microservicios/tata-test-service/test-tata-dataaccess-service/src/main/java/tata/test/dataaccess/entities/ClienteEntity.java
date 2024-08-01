package tata.test.dataaccess.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
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
    name = "cliente"
)
@EntityListeners(AuditingEntityListener.class)
public class ClienteEntity extends PersonaEntity {

  @NotBlank(message = "{Contraseña no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25 ")
  @Column(name = "contrasenia", nullable = false)
  private String contrasenia;

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CuentaEntity> cuentas;

}
