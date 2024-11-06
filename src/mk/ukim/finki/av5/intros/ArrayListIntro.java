package mk.ukim.finki.av5.intros;

import java.util.ArrayList;

class Test {

}

public class ArrayListIntro {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<String> strings2 = new ArrayList<>();
        ArrayList<Test> tests = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            strings.add("NP" + i);
            tests.add(new Test());
        }

        for (int i = 0; i < 20; i++) {
            strings2.add("Stefan" + i);
        }

        System.out.println(strings);

        System.out.println(strings.get(2));

        System.out.println(strings.contains("NP9"));

        strings.remove(2);
        System.out.println(strings);
        strings.remove("NP9");
        System.out.println(strings);

        System.out.println(strings2);

        strings.addAll(strings2);
        System.out.println(strings);

        strings.removeIf(s -> s.startsWith("Stefan"));
        System.out.println(strings);

        strings.forEach(s -> System.out.println(s));

        System.out.println(strings.isEmpty());
        System.out.println(strings.size());
    }
}
