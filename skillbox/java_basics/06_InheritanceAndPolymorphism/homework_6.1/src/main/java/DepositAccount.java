import java.time.LocalDate;

public class DepositAccount extends BankAccount {
  private LocalDate lastIncome;

  @Override
  public void put(double amountToPut) {
    if (amountToPut > 0) {
      lastIncome = LocalDate.now();
    }
    super.put(amountToPut);
  }

  @Override
  public void take(double amountToTake) {
    LocalDate currentDate = LocalDate.now();
    if (lastIncome != null && lastIncome.plusMonths(1).isBefore(currentDate)) {
      super.take(amountToTake);
    }
  }

}
