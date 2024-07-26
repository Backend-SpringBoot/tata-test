package tata.test.enumeration;

public enum StateEnum {
  ACTIVO("A"),
  INCATIVO("I"),
  ELIMINADO("X");

  private final String state;

  StateEnum(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }
}
