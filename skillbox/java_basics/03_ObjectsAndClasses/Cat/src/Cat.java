
public class Cat
{
    private double originWeight;
    private double foodAmount;
    private double weight;
    private boolean isAlive;
    private Color color;

    // CONSTANTS
    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;
    public static final int EYES_COUNT = 2;

    public static int COUNT;


    public Cat()
    {
        COUNT++;
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        isAlive = true;
    }

    public Cat(double weight) {
        this();
        this.weight = weight;
        originWeight = weight;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static Cat deepCopy(Cat original) {
        Cat copycat = new Cat();
        copycat.originWeight = original.originWeight;
        copycat.foodAmount = original.foodAmount;
        copycat.weight = original.weight;
        copycat.isAlive = original.isAlive;
        if (!copycat.isAlive) COUNT--;
        copycat.color = original.color;

        return copycat;
    }

    public Color getColor() {
        return color;
    }

    public void meow()
    {
        if (!isAlive)
            return;
        weight = weight - 2;
        System.out.println("Meow");
    }

    public void feed(Double amount)
    {
        if (!isAlive)
            return;
        foodAmount += amount;
        weight = weight + amount;
    }

    public double getFoodAmount() {
        return foodAmount;
    }

    public void pee() {
        if (!isAlive)
            return;
        System.out.println("Пора сходить в лоток...");
        weight -= 10*Math.random();
    }

    public void drink(Double amount)
    {
        if (!isAlive)
            return;
        foodAmount += amount;
        weight = weight + amount;
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            if (isAlive)
            {
                COUNT--;
                isAlive = false;
            }
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            if (isAlive)
            {
                COUNT--;
                isAlive = false;
            }
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public static int getCOUNT() {
        return COUNT;
    }
}