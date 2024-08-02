package tata.test.dataaccess.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tata.test.dataaccess.entities.CuentaEntity;

public interface CuentaJpaRepository extends JpaRepository<CuentaEntity, Integer> {

  Optional<CuentaEntity> findByTipoCuenta(String tipoCuenta);

}
