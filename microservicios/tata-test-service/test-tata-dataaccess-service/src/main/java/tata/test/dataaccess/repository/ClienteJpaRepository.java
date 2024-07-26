package tata.test.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tata.test.dataaccess.entities.ClienteEntity;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Integer> {

}
