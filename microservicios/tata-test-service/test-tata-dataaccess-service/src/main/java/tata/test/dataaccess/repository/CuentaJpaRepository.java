package tata.test.dataaccess.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tata.test.dataaccess.entities.CuentaEntity;

public interface CuentaJpaRepository extends JpaRepository<CuentaEntity, Integer> {

  Optional<CuentaEntity> findByTipoCuenta(String tipoCuenta);

  Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);

  @Query("SELECT c FROM CuentaEntity c "
      + "JOIN c.cliente p WHERE p.identificacion = :identificacion "
      + "AND c.tipoCuenta = :tipoCuenta AND c.numeroCuenta = :numeroCuenta  ")
  Optional<CuentaEntity> findCuenta(String identificacion, String tipoCuenta, String numeroCuenta);

  @Query("SELECT c FROM CuentaEntity c "
      + "JOIN c.cliente p WHERE p.identificacion = :identificacion "
      + "AND c.numeroCuenta = :numeroCuenta  ")
  Optional<CuentaEntity> findCuentaByUsuarioAndNumCuenta(String identificacion,
      String numeroCuenta);

}
