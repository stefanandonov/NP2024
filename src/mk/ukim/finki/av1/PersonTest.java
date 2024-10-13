package mk.ukim.finki.av1;

import java.util.Objects;

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {}

    @Override
    public String toString() {
        return String.format("Name: %s Age: %d", this.name, this.age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        System.out.println(p1);
        Person p2 = new Person("Stefan");
        System.out.println(p2);
        Person p3 = new Person("Stefan", 27);
        System.out.println(p3);

        System.out.println(p2.equals(p3));

    }
}
