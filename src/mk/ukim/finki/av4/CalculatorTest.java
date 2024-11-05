package mk.ukim.finki.av4;

import java.util.Scanner;

interface Operation {
    double execute (double a, double b);
}

//class Addition implements Operation {
//
//    @Override
//    public double execute(double a, double b) {
//        return a+b;
//    }
//}

class Calculator {


    static Operation ADDITION = (a, b) -> a+b;

    double state = 0.0;

    boolean resultTriggered = false;

    public Calculator() {
    }

    private void printResult () {
        System.out.println(String.format("result = %.2f", state));
    }

    public boolean input (String command) throws Exception {

        if (resultTriggered) {
            if (command.equalsIgnoreCase("y")) {
                state = 0;
                resultTriggered=false;
                printResult();
                return false;
            } else {
                System.out.println("End of program");
                return true;
            }
        } else {
            if (command.equalsIgnoreCase("r")) {
                System.out.println(String.format("Final result = %.2f\nAgain? (y/n)", state));
                resultTriggered = true;
                return false;
            } else {
                double value = Double.parseDouble(command.substring(1));
                switch (command.charAt(0)){
                    case '+': state = ADDITION.execute(state,value); break;
                    case '-': state-=value; break;
                    case '*': state*=value; break;
                    case '/': state/=value; break;
                    default:
                        throw new Exception("Operation not allowed");
                }
                printResult();
                return false;
            }
        }




    }
}

public class CalculatorTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calculator = new Calculator();
        while (sc.hasNextLine()){
            String command = sc.nextLine();
            try {
                if (calculator.input(command)){
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
