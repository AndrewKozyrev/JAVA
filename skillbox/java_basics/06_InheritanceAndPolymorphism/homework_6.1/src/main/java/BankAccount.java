public class BankAccount {

  private double amount;

  public double getAmount() {
    return amount;
  }

  public void put(double amountToPut) {
    if (amountToPut > 0) {
      amount += amountToPut;
    }
  }

  public void take(double amountToTake) {
    if (amountToTake <= amount && amountToTake > 0) {
      amount -= amountToTake;
    }
  }

  public boolean send(BankAccount receiver, double amount) {

    // Нельзя отправлять себе
    if (receiver.equals(this)) {
      return false;
    }

    double oldAmount = this.amount;
    take(amount);

    // Если сумма не поменялась
    if (oldAmount == this.amount) {
      return false;
    }

    receiver.put(amount);
    return true;
  }

}
