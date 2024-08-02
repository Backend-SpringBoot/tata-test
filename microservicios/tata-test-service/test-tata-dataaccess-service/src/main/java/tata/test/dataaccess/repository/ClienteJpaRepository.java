package tata.test.dataaccess.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tata.test.dataaccess.entities.ClienteEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Integer> {

  Optional<ClienteEntity> findByIdentificacion(String identificacion);

  void deleteByIdentificacion(String identificacion);
}
