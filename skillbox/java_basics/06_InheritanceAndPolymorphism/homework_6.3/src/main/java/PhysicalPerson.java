public class PhysicalPerson extends Client {

  @Override
  public void info() {
    System.out.println("Пополнение: без комиссии");
    System.out.println("Снятие: без комиссии");
    super.info();
  }
}
