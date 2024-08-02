package tata.test.dataaccess.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import tata.test.dataaccess.entities.MovimientosEntity;

public interface MovimientosJpaRepository extends JpaRepository<MovimientosEntity, Integer> {

  List<MovimientosEntity> findByFechMovimientoBetween(LocalDateTime startDate,
      LocalDateTime endDate);
}
