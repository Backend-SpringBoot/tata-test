package tata.test.dataaccess.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tata.test.dataaccess.entities.MovimientosEntity;

public interface MovimientosJpaRepository extends JpaRepository<MovimientosEntity, Integer> {

  List<MovimientosEntity> findByFechMovimientoBetween(LocalDateTime startDate,
      LocalDateTime endDate);

  @Query("SELECT m FROM MovimientosEntity m where m.numeroCuenta = :cuenta")
  Optional<MovimientosEntity> findByNumeroCuenta(String cuenta);
}
