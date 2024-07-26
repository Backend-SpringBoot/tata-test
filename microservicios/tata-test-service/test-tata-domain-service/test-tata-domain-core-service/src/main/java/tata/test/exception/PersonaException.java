package tata.test.exception;

import tata.test.exception.global.GlobalException;

public class PersonaException extends GlobalException {

  public PersonaException(String message) {
    super(message);
  }

  public PersonaException(String message, Throwable cause) {
    super(message, cause);
  }
}
