package mk.ukim.finki.av5.math;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;

public class MathClass {

    public static void printStatistics(List<? extends Number> numbers) {
        DoubleSummaryStatistics doubleSummaryStatistics =
                numbers.stream().mapToDouble(Number::doubleValue).summaryStatistics();

        System.out.println(doubleSummaryStatistics.getMin());
        System.out.println(doubleSummaryStatistics.getMax());
        System.out.println(doubleSummaryStatistics.getAverage());
        System.out.println(doubleSummaryStatistics.getSum());

        double sum = 0;
        for (Number n : numbers) {
            sum += (n.doubleValue() - doubleSummaryStatistics.getAverage()) *
                    (n.doubleValue() - doubleSummaryStatistics.getAverage());
        }
        System.out.println(Math.sqrt(sum / numbers.size()));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = Integer.parseInt(scanner.nextLine());

        if (option == 1) {
            System.out.println(" -- test with integers -- ");
            List<Integer> integerList = new ArrayList<>();
            integerList.add(3);
            integerList.add(56);
            integerList.add(9);
            integerList.add(0);
            integerList.add(102);
            printStatistics(integerList);
        } else {
            System.out.println(" -- test with doubles -- ");
            List<Double> doubleList = new ArrayList<>();
            doubleList.add(3.67);
            doubleList.add(56.0);
            doubleList.add(9.77);
            doubleList.add(0.1981);
            doubleList.add(102.1166);
            printStatistics(doubleList);
        }
    }
}
