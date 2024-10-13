package mk.ukim.finki.av1;

class Operator {

    public static int times = 0;
    public static int sum (int a, int b){
        ++times;
        return a+b;
    }
}


public class FirstClass {

    public static void main(String[] args) {
        Operator operator = new Operator();
        Operator operator2 = new Operator();
        Operator operator3 = new Operator();
        System.out.println("Welcome to NP 2024");
        System.out.println(Operator.sum(5,6));
        System.out.println(Operator.sum(10,12));
        System.out.println(Operator.sum(15,20));
        System.out.println(Operator.times);

    }
}
