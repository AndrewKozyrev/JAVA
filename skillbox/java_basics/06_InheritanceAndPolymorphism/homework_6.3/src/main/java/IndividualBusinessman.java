public class IndividualBusinessman extends Client {
  private static final double COMMISSION_1 = 0.01;
  private static final double COMMISSION_2 = 0.005;

  @Override
  public void put(double amountToPut) {
    if (amountToPut < 1000) {
      super.put(amountToPut * (1 - COMMISSION_1));
    } else {
      super.put(amountToPut * (1 - COMMISSION_2));
    }
  }

  @Override
  public void info() {
    System.out.printf("Пополнение на сумму до 1000р. : %.0f%%\n", 100 * COMMISSION_1);
    System.out.printf("Пополнение на сумму от 1000р. : %.1f%%\n", 100 * COMMISSION_2);
    System.out.println("Снятие: без комиссии");
    super.info();
  }
}
