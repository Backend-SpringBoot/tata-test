package tata.test.dataaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

  @NotBlank(message = "{Tipo cuenta no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "tipoCuenta", nullable = false)
  private String tipoCuenta;

  @Column(name = "saldoInicial", nullable = false)
  @NotNull(message = "Saldo inicial no puede ser nulo")
  private double saldoInicial;

  @Column(name = "saldoDisponible", nullable = false)
  @NotNull(message = "Saldo disponible no debe ser nulo")
  @PositiveOrZero(message = "Saldo disponible debe ser positivo")
  private double saldoDisponible;

  @NotBlank(message = "{Identificación no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "cliente", nullable = false)
  private String ciente;

  @NotBlank(message = "{Número cuenta no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25 ")
  @Column(name = "numeroCuenta", nullable = false)
  private String numeroCuenta;

  @Column(name = "movimiento", nullable = false)
  @NotNull(message = "Movimiento disponible no debe ser nulo")
  @Positive(message = "Movimiento disponible debe ser positivo")
  private double movimiento;

  @NotBlank(message = "{Tipo movimiento no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25 ")
  private String tipoMovimiento;
}
