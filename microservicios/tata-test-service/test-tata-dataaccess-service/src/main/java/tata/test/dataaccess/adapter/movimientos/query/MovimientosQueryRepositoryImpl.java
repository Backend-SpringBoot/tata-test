package tata.test.dataaccess.adapter.movimientos.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.MovimientosEntity;
import tata.test.dataaccess.mappers.MovimientosMapper;
import tata.test.dataaccess.repository.MovimientosJpaRepository;
import tata.test.domain.application.ports.output.repository.movimientos.query.MovimientosQueryRepository;
import tata.test.exception.MovimientosException;
import tata.test.record.response.MovimientosResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientosQueryRepositoryImpl implements MovimientosQueryRepository {

  private final MovimientosJpaRepository movimientosJpaRepository;

  @Override
  public MovimientosResponseRecord getTransaction(Integer id) {
    MovimientosEntity movimientosEntity =
        movimientosJpaRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new MovimientosException(
                        String.format("El movimiento con el id %d no existe.", id)));
    return MovimientosMapper.INSTANCE.entityToResponseRecord(movimientosEntity);
  }

  @Override
  public List<MovimientosResponseRecord> getTransactions() {
    List<MovimientosEntity> movimientosEntities =
        movimientosJpaRepository
            .findAll();
    return MovimientosMapper.INSTANCE.entitiesToResponseRecords(movimientosEntities);
  }
}
