public abstract class Client {
    private double sum;

    public double getAmount() {
        return sum;
    }

    public void put(double amountToPut) {
        if (amountToPut > 0) {
            sum += amountToPut;
        }
    }

    public void take(double amountToTake) {
        if (amountToTake <= sum && amountToTake > 0) {
            sum -= amountToTake;
        }
    }

    public void info() {
        System.out.println("Баланс: " + sum);
    }
}
