package HeadFirst.Patterns.Strategy;

public class DecoyDuck implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Decoy quack >>");
    }
}
