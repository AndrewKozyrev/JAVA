package DAY_3_HT.exercise_16;

public class Animals {
    private String name;
    private int age;
    private double weight;
    private double height;
    private static final int AGE = 5;
    private static final double WEIGHT = 2;
    private static final double HEIGHT = 20;

    public Animals() {

    }

    public Animals(String name, int age, double weight, double heigth) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = heigth;
    }

    public void showInfo(){
        System.out.println(this.name + ", age: " + this.age +
                ", weight: " + this.weight + ", height: " + this.height);
    }

    public static boolean equals(Animals a, Animals b){
        return a.name.equals(b.name);
    }

    public void compareWithTreshold(){
        StringBuilder str = new StringBuilder();
        if (this.age < AGE)  { str.append("младше ").append(AGE).append(" лет; "); }
        else { str.append("старше ").append(AGE).append(" лет; "); }

        if (this.weight < WEIGHT) { str.append("легче ").append(WEIGHT).append(" кг; "); }
        else { str.append("тяжелее ").append(WEIGHT).append(" кг; "); }

        if (this.height < HEIGHT) { str.append("ниже ").append(HEIGHT).append(" см; "); }
        else { str.append("выше ").append(HEIGHT).append(" см; "); }

        System.out.println(str);
    }
}