package tata.test.dataaccess.adapter.cuenta.command;

import java.util.concurrent.locks.StampedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.CuentaEntity;
import tata.test.dataaccess.mappers.CuentaMapper;
import tata.test.dataaccess.repository.CuentaJpaRepository;
import tata.test.domain.application.ports.output.repository.cuenta.command.CuentaCommandRepository;
import tata.test.exception.CuentaException;
import tata.test.record.request.CuentaRequestRecord;
import tata.test.record.response.CuentaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CuentaCommandRepositoryImpl implements CuentaCommandRepository {

  private final CuentaJpaRepository cuentaJpaRepository;

  @Override
  @Transactional
  public CuentaResponseRecord createOrUpdate(CuentaRequestRecord cuentaRequestRecord) {
    CuentaEntity entity;

    if (cuentaRequestRecord.id() != null) {
      entity = updateExistingCuenta(cuentaRequestRecord);
    } else {

      entity = CuentaMapper.INSTANCE.requestRecordToEntity(cuentaRequestRecord);
    }
    return CuentaMapper.INSTANCE.entityToResponseRecord(cuentaJpaRepository.save(entity));
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    cuentaJpaRepository.deleteById(id);
  }

  private CuentaEntity updateExistingCuenta(CuentaRequestRecord requestRecord) {
    CuentaEntity existingEntity =
        cuentaJpaRepository
            .findById(requestRecord.id())
            .orElseThrow(() -> new CuentaException("Cuenta no encontrado"));

    final StampedLock lock = existingEntity.getLock();
    long stamp = lock.writeLock();
    try {
      existingEntity.setNumeroCuenta(requestRecord.numeroCuenta());
      existingEntity.setTipoCuenta(requestRecord.tipoCuenta());
      existingEntity.setSaldoInicial(requestRecord.saldoInicial());
      existingEntity.setEstado(requestRecord.estado());

    } finally {
      lock.unlockWrite(stamp);
    }
    return existingEntity;
  }
}
