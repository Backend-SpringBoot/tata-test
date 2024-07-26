package tata.test.dataaccess.adapter.movimientos.command;

import java.time.LocalDateTime;
import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.MovimientosEntity;
import tata.test.dataaccess.mappers.MovimientosMapper;
import tata.test.dataaccess.repository.MovimientosJpaRepository;
import tata.test.domain.application.ports.output.repository.movimientos.command.MovimientosCommandRepository;
import tata.test.enumeration.MovmentsEnum;
import tata.test.exception.MovimientosException;
import tata.test.record.request.MovimientosRequestRecord;
import tata.test.record.response.MovimientosResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovimientosCommandRepositoryImpl implements MovimientosCommandRepository {

  private final MovimientosJpaRepository movimientosJpaRepository;

  @Override
  @Transactional
  public MovimientosResponseRecord createOrUpdate(
      MovimientosRequestRecord movimientosRequestRecord) {
    MovimientosEntity entity;

    if (movimientosRequestRecord.id() != null) {
      entity = updateExistingMovimiento(movimientosRequestRecord);
    } else {

      entity = MovimientosMapper.INSTANCE.requestRecordToEntity(movimientosRequestRecord);
    }
    if (entity.getFechMovimiento() == null) {
      entity.setFechMovimiento(LocalDateTime.now());
      if ("Retiro".equals(MovmentsEnum.Retiro)) {
        if (entity.getSaldo() == 0) {
          new MovimientosException("Saldo no disponible");
        } else {
          entity.setSaldo(entity.getSaldo() - movimientosRequestRecord.valor());
        }
      } else if ("Deposito".equals(MovmentsEnum.Deposito)) {
        entity.setSaldo(entity.getSaldo() + movimientosRequestRecord.valor());
      }

    }

    return MovimientosMapper.INSTANCE.entityToResponseRecord(movimientosJpaRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    movimientosJpaRepository.deleteById(id);
  }

  private MovimientosEntity updateExistingMovimiento(MovimientosRequestRecord requestRecord) {
    MovimientosEntity existingEntity =
        movimientosJpaRepository
            .findById(requestRecord.id())
            .orElseThrow(() -> new MovimientosException("Movimiento no encontrado"));

    final StampedLock lock = existingEntity.getLock();
    long stamp = lock.writeLock();
    try {
      existingEntity.setFechMovimiento(requestRecord.fechMovimiento());
      existingEntity.setTipoMovimiento(requestRecord.tipoMovimiento());
      existingEntity.setValor(requestRecord.valor());
    } finally {
      lock.unlockWrite(stamp);
    }
    return existingEntity;
  }
}
