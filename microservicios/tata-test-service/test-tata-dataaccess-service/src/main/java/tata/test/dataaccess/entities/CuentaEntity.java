package tata.test.dataaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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
    name = "cuenta"
)
@EntityListeners(AuditingEntityListener.class)
public class CuentaEntity extends AbstractEntity<Integer, Integer> {

  @NotBlank(message = "{Número cuenta no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25 ")
  @Column(name = "numeroCuenta", nullable = false)
  private String numeroCuenta;

  @NotBlank(message = "{Tipo cuenta no debe estar en blanco")
  @Size(max = 25, message = "{Tamaño maximo de 25")
  @Column(name = "tipoCuenta", nullable = false)
  private String tipoCuenta;

  @Column(name = "saldoInicial", nullable = false)
  @NotNull(message = "Saldo inicial no debe ser nulo")
  @PositiveOrZero(message = "Saldo inicial debe ser positivo o cero")
  private Integer saldoInicial;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "identificacion", nullable = false)
  private ClienteEntity cliente;

}
