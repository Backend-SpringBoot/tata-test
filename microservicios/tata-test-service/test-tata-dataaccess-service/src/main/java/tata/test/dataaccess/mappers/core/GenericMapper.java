package tata.test.dataaccess.mappers.core;

import org.springframework.data.domain.Page;
import tata.test.record.response.PaginatedDataRecord;

public interface GenericMapper<E, R> {

  R entityToResponseRecord(E entity);

  R recordPageToRecordPage(R recordPage);

  /**
   * Permite convertir entre Page<Entity> a PaginatedDataRecord<Response>
   *
   * @param entityPage Entidad a convertir
   * @return PaginatedDataRecord como respuesta.
   */
  default PaginatedDataRecord<R> entityPageToRecordPage(Page<E> entityPage) {
    return PaginatedDataRecord.<R>builder()
        .data(entityPage.getContent().stream().map(this::entityToResponseRecord).toList())
        .totalElements(entityPage.getTotalElements())
        .totalPages(entityPage.getTotalPages())
        .number(entityPage.getNumber())
        .size(entityPage.getSize())
        .build();
  }

  default PaginatedDataRecord<R> recordPageToRecordPage(Page<R> recordPage) {
    return PaginatedDataRecord.<R>builder()
        .data(recordPage.getContent())
        .totalElements(recordPage.getTotalElements())
        .totalPages(recordPage.getTotalPages())
        .number(recordPage.getNumber())
        .size(recordPage.getSize())
        .build();
  }
}

