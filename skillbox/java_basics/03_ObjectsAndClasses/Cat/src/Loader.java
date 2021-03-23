import java.util.ArrayList;
import java.util.List;

public class Loader
{
    public static void main(String[] args)
    {
        List<Cat> cats = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            cats.add(new Cat());
            System.out.println(cats.get(i).getWeight());
        }

        System.out.println("Всего кошек: " + Cat.getCOUNT());

        System.out.println("\n==================\nПокормим двух кошек\n");
        for (int i = 0; i < 2; i++) {
            System.out.printf("Кошка №%d\nБыл вес: ", i+1);
            System.out.print(cats.get(i).getWeight());
            cats.get(i).feed(50 + 100*Math.random());
            System.out.print("\tСтал вес: ");
            System.out.println(cats.get(i).getWeight());
        }

        while (!cats.get(0).getStatus().equals("Exploded")) {
            cats.get(0).feed(50 + 100*Math.random());
        }
        System.out.println("\n======================\nКошка №1: " + cats.get(0).getStatus());

        while (!cats.get(1).getStatus().equals("Dead")) {
            cats.get(1).meow();
        }

        System.out.println("\n======================\nКошка №2: " + cats.get(1).getStatus());

        System.out.println("\nКошка №3: ");
        cats.get(2).feed(150.0);
        cats.get(2).feed(100.0);

        for (int i = 0; i < 10; i++) {
            System.out.println(cats.get(2).getWeight());
            cats.get(2).pee();
        }

        System.out.println("\n=======================");
        System.out.println("Всего съедено: " + cats.get(2).getFoodAmount() + " граммов");

        //Мёртвых кошек невозможно кормить
        cats.get(1).pee();
        cats.get(0).meow();
        cats.get(0).feed(100.0);
        System.out.println("Всего кошек: " + Cat.getCOUNT());

        cats.add(getKitten());
        cats.add(getKitten());
        cats.add(getKitten());
        System.out.println("Всего кошек: " + Cat.getCOUNT());
        cats.set(3, Cat.deepCopy(cats.get(0)));
        System.out.println(cats.get(3).getStatus());
    }

    private static Cat getKitten() {
        return new Cat(1100.0);
    }
}