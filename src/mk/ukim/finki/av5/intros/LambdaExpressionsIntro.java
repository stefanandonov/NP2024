package mk.ukim.finki.av5.intros;

interface Operation {
    //functional interface if it has only one method -> can be represented with lambda expression
    double execute(double num1, double num2);
}

class Addition implements Operation {
    @Override
    public double execute(double num1, double num2) {
        return num1 + num2;
    }
}

public class LambdaExpressionsIntro {
    public static void main(String[] args) {
        double a = 10, b = 3.2;

        Operation addition = new Addition();

        //anonymous classes
        Operation subtraction = new Operation() {
            @Override
            public double execute(double num1, double num2) {
                return num1 - num2;
            }
        };

        //lambda function
        Operation multiplication = (x, y) -> x * y;

        Operation complex = (x, y) -> {
            if (x > y) {
                return x / y;
            } else {
                return y / x;
            }
        };

        System.out.println(addition.execute(a, b));
        System.out.println(subtraction.execute(a, b));
        System.out.println(multiplication.execute(a, b));
        System.out.println(complex.execute(a, b));
        System.out.println(complex.execute(b, a));
    }
}
