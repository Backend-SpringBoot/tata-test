package tata.test.enumeration;

public enum StateEnum {
  TRUE("TRUE"),
  FALSE("FALSE");

  private final String state;

  StateEnum(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }
}
