package tata.test.dataaccess.adapter.cuenta.query;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tata.test.dataaccess.entities.CuentaEntity;
import tata.test.dataaccess.mappers.CuentaMapper;
import tata.test.dataaccess.repository.CuentaJpaRepository;
import tata.test.domain.application.ports.output.repository.cuenta.query.CuentaQueryRepository;
import tata.test.record.ExceptionResponseRecord;
import tata.test.record.response.CuentaResponseRecord;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CuentaQueryRepositoryImpl implements CuentaQueryRepository {

  private final CuentaJpaRepository cuentaJpaRepository;

  @Override
  public ResponseEntity<ExceptionResponseRecord> getAccount(String id) {
    Optional<CuentaEntity> cuentaOptional =
        cuentaJpaRepository
            .findByNumeroCuenta(id);
    if (cuentaOptional.isEmpty()) {
      return new ResponseEntity<>(CreateException("No existe registros", null), HttpStatus.OK);
    }
    CuentaEntity cliente = cuentaOptional.get();
    CuentaResponseRecord data = CuentaMapper.INSTANCE.entityToResponseRecord(cliente);
    return new ResponseEntity<>(CreateException("Correcto !", data), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getAccounts() {
    List<CuentaEntity> cuentaEntities =
        cuentaJpaRepository
            .findAll();

    List<CuentaResponseRecord> data = CuentaMapper.INSTANCE.entitiesToResponseRecords(
        cuentaEntities);
    return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }
}
