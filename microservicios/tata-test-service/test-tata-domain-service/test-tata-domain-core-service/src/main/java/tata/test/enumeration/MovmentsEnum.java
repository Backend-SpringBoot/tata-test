package tata.test.enumeration;

public enum MovmentsEnum {
  Deposito("Deposito"),
  Retiro("Retiro");

  private final String state;

  MovmentsEnum(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }
}
