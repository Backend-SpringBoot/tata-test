package tata.test.exception;

import tata.test.exception.global.GlobalException;

public class ClienteException extends GlobalException {

  public ClienteException(String message) {
    super(message);
  }

  public ClienteException(String message, Throwable cause) {
    super(message, cause);
  }
}
