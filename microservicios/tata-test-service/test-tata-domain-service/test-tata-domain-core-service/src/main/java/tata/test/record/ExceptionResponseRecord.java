package tata.test.record;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ExceptionResponseRecord(
    HttpStatus httpStatus,
    String message,
    Object data
) {

}
