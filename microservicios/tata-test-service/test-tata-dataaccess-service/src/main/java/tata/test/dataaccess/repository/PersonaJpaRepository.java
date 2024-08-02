package tata.test.dataaccess.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tata.test.dataaccess.entities.PersonaEntity;

public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Integer> {

  @Query("""
      SELECT p FROM PersonaEntity p WHERE p.identificacion = :identificacion AND p.estado = 'True'""")
  Optional<PersonaEntity> findByIdentificacion(String identificacion);

}
