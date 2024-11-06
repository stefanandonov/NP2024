package mk.ukim.finki.av5.intros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfacesTest {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        strings.add("MAkedonija");
        strings.add("FINKI");
        strings.add("Stefan");
        strings.add("Ana");
        strings.add("Gjorgji");

        System.out.println(strings);

        //3. Consumer<IN>
        Consumer<String> printer = s -> System.out.println(s);

        //1. Function<IN,OUT>
        //Used for stream operator .map
        Function<String, Integer> locationOfLetterA = s -> s.toLowerCase().indexOf("a");

        strings.stream()
                .map(locationOfLetterA)
                .forEach(location -> System.out.println(location));

        //2. Predicate<IN>
        //Used for stream operator .filter
        Predicate<String> containsA = s -> s.toLowerCase().contains("a");
        strings.stream()
                .filter(containsA)
                .forEach(printer);


        //4. Supplier<OUT>
        Supplier<Integer> randomGenerator = () -> new Random().nextInt(10);

        for (int i = 0; i < 10; i++) {
            System.out.println(randomGenerator.get());
        }
    }
}
