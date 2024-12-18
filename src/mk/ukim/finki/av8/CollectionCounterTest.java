package mk.ukim.finki.av8;

import java.util.Collection;
import java.util.List;

class Counter {
    public static int count(Collection<Collection<String>> collections, String str) {
        int counter = 0;
        for (Collection<String> collection : collections) {
            for (String s : collection) {
                if (s.equals(str)) {
                    ++counter;
                }
            }
        }

        return counter;
    }

    public static int countWithStream(Collection<Collection<String>> collections, String str) {
        return collections.stream()
                .mapToInt(
                        collection -> (int) collection.stream()
                                .filter(s -> s.equals(str))
                                .count()
                ).sum();

    }

    public static int countWithFlatMap(Collection<Collection<String>> collections, String str) {
        return (int) collections.stream()
                .flatMap(collection -> collection.stream())
                .filter(s -> s.equals(str))
                .count();
    }
}

public class CollectionCounterTest {
    public static void main(String[] args) {
        Collection<String> c1 = List.of("NP", "NP1");
        Collection<String> c2 = List.of("NP", "SP", "OOP");

        Collection<Collection<String>> collection = List.of(c1, c2);

        System.out.println(Counter.countWithFlatMap(collection, "NP"));

    }
}
