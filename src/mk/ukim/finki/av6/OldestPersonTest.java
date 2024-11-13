package mk.ukim.finki.av6;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Person implements Comparable<Person> {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Person createPerson(String line) {
        String[] parts = line.split("\\s+");
        return new Person(parts[0], Integer.parseInt(parts[1]));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person other) {
        return Comparator.comparing(Person::getAge)
                .reversed()
                .thenComparing(Person::getName)
                .compare(this, other);
    }
}

class OldestPerson {
    public static Optional<Person> find(InputStream is) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        List<Person> people = bufferedReader.lines()
                .map(Person::createPerson)
                .toList();

        if (people.stream().max(Comparator.naturalOrder()).isPresent())
            return people.stream().max(Comparator.naturalOrder());
        else return Optional.empty();
    }
}

public class OldestPersonTest {
    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream("src/mk/ukim/finki/av6/people.txt");
            System.out.println(OldestPerson.find(is));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
