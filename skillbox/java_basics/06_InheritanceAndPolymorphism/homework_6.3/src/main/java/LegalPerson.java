public class LegalPerson extends Client {
  private static final double COMMISSION = 0.01;

  @Override
  public void take(double amountToTake) {
    super.take(amountToTake * (1 + COMMISSION));
  }

  @Override
  public void info() {
    System.out.println("Пополнение: без комиссии");
    System.out.printf("Снятие: %.0f%%\n", 100 * COMMISSION);
    super.info();
  }
}
