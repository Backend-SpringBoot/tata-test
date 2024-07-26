package tata.test.dataaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tata.test.dataaccess.entities.core.impl.AbstractEntity;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@DynamicUpdate
@DynamicInsert
@Audited
@Table(
    name = "movimientos"
)
@EntityListeners(AuditingEntityListener.class)
public class MovimientosEntity extends AbstractEntity<Integer, Integer> {

  @Column(name = "fechMovimiento", nullable = false)
  private LocalDateTime fechMovimiento;

  @NotBlank(message = "{Tipo movimiento no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "tipoMovimiento", nullable = false)
  private String tipoMovimiento;

  @Column(name = "valor", nullable = false)
  @NotNull(message = "Valor no debe ser nulo")
  private Integer valor;

  @Column(name = "saldo", nullable = false)
  @NotNull(message = "Saldo no debe ser nulo")
  @Positive(message = "Saldo debe ser positivo")
  private Integer saldo;
}
