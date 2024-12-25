package mk.ukim.finki.av10;

interface Shape {
    double area();
}

class Circle implements Shape {
    int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.pow(radius,2) * Math.PI;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}

class Square implements Shape {
    int a;

    public Square(int a) {
        this.a = a;
    }


    @Override
    public double area() {
        return a*a;
    }

    @Override
    public String toString() {
        return "Square{" +
                "a=" + a +
                '}';
    }
}


class ShapeFactory {
    public static Shape create (char type, int dimension){
        if (type=='C'){
            return new Circle(dimension);
        } else {
            return new Square(dimension);
        }
    }
}


public class FactoryTest {
    public static void main(String[] args) {
        Shape shape1 = ShapeFactory.create('C',10);
        Shape shape2 = ShapeFactory.create('S', 10);

        System.out.println(shape1);
        System.out.println(shape1.area());
        System.out.println(shape2);
        System.out.println(shape2.area());
    }
}
