package DAY_3_HT;

interface Base {
    public double square();
}

class Rectangle implements Base {
    protected double a, b;

    Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double square() {
        return this.a * this.b;
    }
}

class Circle implements Base {
    private double r;

    Circle(double r) {
        this.r = r;
    }

    @Override
    public double square() {
        return Math.pow(r, 2) * Math.round(Math.PI*10000)/10000.0;
    }
}

class Triangle extends Rectangle  implements Base{
    Triangle(double a, double b) {
        super(a, b);
    }
    @Override
    public double square(){
        return this.a * this.b / 2.0;
    }
}

class Trapeze extends Triangle implements Base {
    protected double h;

    Trapeze(double a, double b, double h) {
        super(a, b);
        this.h = h;
    }

    @Override
    public double square() {
        return super.square()*h;
    }
}

public class exercise_17 {
    public static void main(String[] args){
        Base[] shapes = new Base[4];
        shapes[0] = new Rectangle(4, 5);
        shapes[1] = new Circle(100);
        shapes[2] = new Triangle(7, 3);
        shapes[3] = new Trapeze(4, 5, 8);
        for (Base e : shapes){
            System.out.println("Area: " + e.square());
        }
    }
}
