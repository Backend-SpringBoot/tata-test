package tata.test.dataaccess.entities.core.impl;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.concurrent.locks.StampedLock;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import tata.test.dataaccess.entities.core.Auditable;
import tata.test.dataaccess.entities.core.Entity;
import tata.test.enumeration.StateEnum;

@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@Audited
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity<T, P> implements Entity<T>, Auditable<P> {

  @Serial
  private static final long serialVersionUID = -412218241272244613L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private T id;

  @NotBlank(message = "{entity.estado.notBlank}")
  @Size(max = 1, message = "{entity.estado.size}")
  @Column(name = "estado", nullable = false, length = 1)
  private String estado;

  @CreatedBy
  @NotNull(message = "{entity.idPersonaCrea.notNull}")
  @Column(name = "idPersonaCrea", nullable = false)
  private P idPersonaCrea;

  @CreatedDate
  @NotNull(message = "{entity.fechaCrea.notNull}")
  @Column(name = "fechaCrea", nullable = false)
  private LocalDateTime fechaCrea;

  @LastModifiedBy
  @NotNull(message = "{entity.idPersonaModifica.notNull}")
  @Column(name = "idPersonaModifica", nullable = false)
  private P idPersonaModifica;

  @LastModifiedDate
  @NotNull(message = "{entity.fechaModifica.notNull}")
  @Column(name = "fechaModifica", nullable = false)
  private LocalDateTime fechaModifica;

  @NotBlank(message = "{entity.ipCrea.notBlank}")
  @Size(max = 25, message = "{entity.ipCrea.size}")
  @Column(name = "ipCrea", nullable = false, length = 25)
  private String ipCrea;

  @NotBlank(message = "{entity.ipModifica.notBlank}")
  @Size(max = 25, message = "{entity.ipModifica.size}")
  @Column(name = "ipModifica", nullable = false, length = 25)
  private String ipModifica;

  @NotBlank(message = "{entity.equipoCrea.notBlank}")
  @Size(max = 50, message = "{entity.equipoCrea.size}")
  @Column(name = "equipoCrea", nullable = false, length = 50)
  private String equipoCrea;

  @NotBlank(message = "{entity.equipoModifica.notBlank}")
  @Size(max = 50, message = "{entity.equipoModifica.size}")
  @Column(name = "equipoModifica", nullable = false, length = 50)
  private String equipoModifica;

  @NotBlank(message = "{entity.motivoModifica.notBlank}")
  @Column(name = "motivoModifica", nullable = false)
  private String motivoModifica;
  @Transient
  private StampedLock lock = new StampedLock();

  public StampedLock getLock() {
    return lock;
  }

  @Override
  public T getId() {
    return id;
  }

  @Override
  public void setId(T id) {
    this.id = id;
  }

  @Override
  public String getEstado() {
    return estado;
  }

  @Override
  public void setEstado(String estado) {
    this.estado = estado;
  }

  @Override
  public P getIdPersonaCrea() {
    return idPersonaCrea;
  }

  @Override
  public void setIdPersonaCrea(P idPersonaCrea) {
    this.idPersonaCrea = idPersonaCrea;
  }

  @Override
  public P getIdPersonaModifica() {
    return idPersonaModifica;
  }

  @Override
  public void setIdPersonaModifica(P idPersonaModifica) {
    this.idPersonaModifica = idPersonaModifica;
  }

  @Override
  public String getIpCrea() {
    return ipCrea;
  }

  @Override
  public void setIpCrea(String ipCrea) {
    this.ipCrea = ipCrea;
  }

  @Override
  public String getIpModifica() {
    return ipModifica;
  }

  @Override
  public void setIpModifica(String ipModifica) {
    this.ipModifica = ipModifica;
  }

  @Override
  public String getEquipoModifica() {
    return equipoModifica;
  }

  @Override
  public void setEquipoModifica(String equipoModifica) {
    this.equipoModifica = equipoModifica;
  }

  @Override
  public String getEquipoCrea() {
    return equipoCrea;
  }

  @Override
  public void setEquipoCrea(String equipoCrea) {
    this.equipoCrea = equipoCrea;
  }

  @Override
  public String getMotivoModifica() {
    return motivoModifica;
  }

  @Override
  public void setMotivoModifica(String motivoModifica) {
    this.motivoModifica = motivoModifica;
  }

  @Override
  public LocalDateTime getFechaCrea() {
    return fechaCrea;
  }

  @Override
  public void setFechaCrea(LocalDateTime fechaCrea) {
    this.fechaCrea = fechaCrea;
  }

  @Override
  public LocalDateTime getFechaModifica() {
    return fechaModifica;
  }

  @Override
  public void setFechaModifica(LocalDateTime fechaModifica) {
    this.fechaModifica = fechaModifica;
  }

  @PrePersist
  protected void onPrePersist() {
    // Lógica a ejecutar antes de persistir la entidad por primera vez
    setEstado(StateEnum.ACTIVO.getState());
    setIpCrea("ipcrea");
    setIpModifica("ipmodifica");
    setEquipoCrea("equipoCrea");
    setEquipoModifica("equipoModifica");
    setMotivoModifica("CREAR_TAREA_INICIAL");
    if (getIdPersonaCrea() == null) {
      setIdPersonaCrea((P) Integer.valueOf(1));
    }
    if (getIdPersonaModifica() == null) {
      setIdPersonaModifica((P) Integer.valueOf(1));
    }
  }

  @PreUpdate
  protected void onPreUpdate() {
    // Lógica a ejecutar antes de actualizar la entidad
    setIpModifica("ipmodifica");
    setEquipoModifica("equipoModifica");
  }

}
