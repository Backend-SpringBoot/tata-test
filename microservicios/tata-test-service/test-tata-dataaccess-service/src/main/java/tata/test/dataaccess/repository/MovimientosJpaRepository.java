package tata.test.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.test.dataaccess.entities.MovimientosEntity;

public interface MovimientosJpaRepository extends JpaRepository<MovimientosEntity, Integer> {

}
