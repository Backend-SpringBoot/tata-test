package tata.test.exception;

import tata.test.exception.global.GlobalException;

public class CuentaException extends GlobalException {

  public CuentaException(String message) {
    super(message);
  }

  public CuentaException(String message, Throwable cause) {
    super(message, cause);
  }
}
