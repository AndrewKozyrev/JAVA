public class Main {

    public static final String PATH_TO_FILE = "src/test/resources/movementList.csv";

    public static void main(String[] args) {
        Movements o = new Movements(PATH_TO_FILE);
        System.out.printf("Сумма расходов: %.2f руб.\n", o.getExpenseSum());
        System.out.printf("Сумма доходов: %.2f руб.\n", o.getIncomeSum());
        System.out.println("\nСуммы расходов по организациям:");
        o.getExpenseByOrganization()
            .forEach((key, value) -> System.out.printf("%s\t%.2f руб.\n", key, value));
    }
}
