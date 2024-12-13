package mk.ukim.finki.av9.birthdays;

import java.util.HashSet;
import java.util.Random;

class Experiment {
    static int TRIALS = 5000;
    static double run(int people) {
        int successful = 0;
        for (int i = 0; i < TRIALS; i++) {
            if (Trial.run(people)) {
                ++successful;
            }
        }
        return (float) successful / TRIALS;
    }
}

class Trial {
    static Random RANDOM = new Random();
    static boolean run(int people) {
        HashSet<Integer> birthdays = new HashSet<>();

        for (int i = 0; i < people; i++) {
            int birthday = RANDOM.nextInt(365) + 1;
            if (!birthdays.add(birthday)) {
                return true;
            }
        }
        return false;
    }
}

public class BirthdayParadoxTest {
    public static void main(String[] args) {
        System.out.println(Experiment.run(3));
    }
}
