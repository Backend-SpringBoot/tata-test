package tata.test.exception;

import tata.test.exception.global.GlobalException;

public class MovimientosException extends GlobalException {

  public MovimientosException(String message) {
    super(message);
  }

  public MovimientosException(String message, Throwable cause) {
    super(message, cause);
  }
}
