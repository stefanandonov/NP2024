package mk.ukim.finki.av8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Eratosthenes {

    private static boolean isPrimeNumber(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> filter(List<Integer> numbers) {

        List<Integer> results = new ArrayList<>();
        results.addAll(numbers);

        for (int n1 : numbers) {

            for (int n2 : numbers) {
                if (n1!=n2 && n2%n1==0){
                    System.out.println(String.format("Deleting the number %d because it's divisible with %d", n2, n1));
                    results.remove((Integer) n2);
                }
            }
        }

        return results;
    }
}

public class EratosthenesTest {
    public static void main(String[] args) {
        List<Integer> elements = IntStream.range(2,1001)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(Eratosthenes.filter(elements));

//        System.out.println(elements);
    }
}
