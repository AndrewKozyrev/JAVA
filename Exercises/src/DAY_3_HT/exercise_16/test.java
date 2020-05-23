package DAY_3_HT.exercise_16;

public class test {
    public static void main(String[] args){
        Animals dog = new Animals("васик", 5, 20, 70);
        Animals cat = new Animals("ёся", 2, 4, 30);
        dog.showInfo();
        cat.showInfo();
        System.out.println(Animals.equals(dog, cat));
        dog.compareWithTreshold();
        cat.compareWithTreshold();
    }
}
