package tata.test.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.test.dataaccess.entities.CuentaEntity;

public interface CuentaJpaRepository extends JpaRepository<CuentaEntity, Integer> {

}
