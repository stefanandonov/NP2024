package mk.ukim.finki.av10;

interface Strategy {
    double operation ();
}

abstract class MathematicalOperationBase implements Strategy {
    double a,b;

    public MathematicalOperationBase(double a, double b) {
        this.a = a;
        this.b = b;
    }
}

class Addition extends MathematicalOperationBase {


    public Addition(double a, double b) {
        super(a, b);
    }

    @Override
    public double operation() {
        return a+b;
    }
}

class Multiplication extends MathematicalOperationBase {

    public Multiplication(double a, double b) {
        super(a, b);
    }

    @Override
    public double operation() {
        return a*b;
    }
}

class StrategyFactory {
    static Strategy create (char op, double a, double b){
        if (op == '+'){
            return new Addition(a,b);
        } else {
            return new Multiplication(a,b);
        }
    }
}

public class StrategyTest {
    public static void main(String[] args) {
        char op = '*';
        double a = 5;
        double b = 8;

        System.out.println(StrategyFactory.create(op, a,b).operation());
    }
}
