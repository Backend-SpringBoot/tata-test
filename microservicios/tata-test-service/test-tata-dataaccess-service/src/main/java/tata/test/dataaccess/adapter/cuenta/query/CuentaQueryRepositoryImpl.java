package tata.test.dataaccess.adapter.cuenta.query;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.CuentaEntity;
import tata.test.dataaccess.mappers.CuentaMapper;
import tata.test.dataaccess.repository.CuentaJpaRepository;
import tata.test.domain.application.ports.output.repository.cuenta.query.CuentaQueryRepository;
import tata.test.exception.CuentaException;
import tata.test.record.response.CuentaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CuentaQueryRepositoryImpl implements CuentaQueryRepository {

  private final CuentaJpaRepository cuentaJpaRepository;

  @Override
  public CuentaResponseRecord getAccount(Integer id) {
    CuentaEntity cuentaEntity =
        cuentaJpaRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new CuentaException(String.format("La cuenta con el id %d no existe.", id)));
    return CuentaMapper.INSTANCE.entityToResponseRecord(cuentaEntity);
  }

  @Override
  public List<CuentaResponseRecord> getAccounts() {
    List<CuentaEntity> cuentaEntities =
        cuentaJpaRepository
            .findAll();
    return CuentaMapper.INSTANCE.entitiesToResponseRecords(cuentaEntities);
  }
}
