public class InsufficientFundsException extends IllegalArgumentException {

  @Override
  public String getMessage() {
    return "Impossible to make a transaction.";
  }
}
