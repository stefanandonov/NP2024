package mk.ukim.finki.av4;

import java.util.Scanner;

interface Operation {
    double execute(double a, double b);
}

class Addition implements Operation {
    @Override
    public double execute(double a, double b) {
        return a + b;
    }
}

class Subraction implements Operation {
    @Override
    public double execute(double a, double b) {
        return a - b;
    }
}

class Multiplication implements Operation {
    @Override
    public double execute(double a, double b) {
        return a * b;
    }
}

class Division implements Operation {
    @Override
    public double execute(double a, double b) {
        return a / b;
    }
}


class Calculator {

    //    static Operation ADDITION = (a, b) -> a + b;
    private Operation operation;
    double state = 0.0;
    boolean resultTriggered = false;

    public Calculator() {
        operation = new Addition();
    }

    private void printResult() {
        System.out.println(String.format("result = %.2f", state));
    }

    public boolean input(String command) throws Exception {

        if (resultTriggered) {
            if (command.equalsIgnoreCase("y")) {
                state = 0;
                resultTriggered = false;
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
                switch (command.charAt(0)) {
                    case '+':
//                        state = ADDITION.execute(state, value);
                        operation = new Addition();
                        break;
                    case '-':
//                        state -= value;
                        operation = new Subraction();
                        break;
                    case '*':
//                        state *= value;
                        operation = new Multiplication();
                        break;
                    case '/':
//                        state /= value;
                        operation = new Division();
                        break;
                    default:
                        throw new Exception("Operation not allowed");
                }
                state = operation.execute(state, value);
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
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            try {
                if (calculator.input(command)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
