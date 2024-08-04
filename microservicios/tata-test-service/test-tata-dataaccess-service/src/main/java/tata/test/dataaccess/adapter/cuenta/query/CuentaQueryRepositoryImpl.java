package tata.test.dataaccess.adapter.cuenta.query;

import java.util.List;
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
    return cuentaJpaRepository.findByNumeroCuenta(id)
        .map(this::getCuentaResponse)
        .orElseGet(() -> createErrorResponse("No se encontró ningún registro"));
  }

  @Override
  public ResponseEntity<List<ExceptionResponseRecord>> getAccounts() {
    List<CuentaEntity> cuentaEntities = cuentaJpaRepository.findAll();
    List<CuentaResponseRecord> data = CuentaMapper.INSTANCE.entitiesToResponseRecords(
        cuentaEntities);
    return new ResponseEntity<>(List.of(CreateException("Correcto", data)), HttpStatus.OK);
  }

  private ResponseEntity<ExceptionResponseRecord> getCuentaResponse(CuentaEntity cuenta) {
    CuentaResponseRecord data = CuentaMapper.INSTANCE.entityToResponseRecord(cuenta);
    return createSuccessResponse("Correcto!", data);
  }

  private ExceptionResponseRecord CreateException(String message, Object o) {
    return ExceptionResponseRecord.builder()
        .httpStatus(HttpStatus.ACCEPTED)
        .message(message)
        .data(o)
        .build();
  }

  private ResponseEntity<ExceptionResponseRecord> createErrorResponse(String message) {
    return new ResponseEntity<>(CreateException(message, null), HttpStatus.OK);
  }

  private ResponseEntity<ExceptionResponseRecord> createSuccessResponse(String message,
      CuentaResponseRecord data) {
    return new ResponseEntity<>(CreateException(message, data), HttpStatus.OK);
  }
}
