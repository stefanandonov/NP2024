package mk.ukim.finki.av7.finalists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFinalistsPicker {

    public static List<Integer> pickFinalists() {
        Random random = new Random();
        List<Integer> chosen = new ArrayList<>();

        while (chosen.size() < 3) {
            int finalist = random.nextInt(30) + 1;
            if (!chosen.contains(finalist))
                chosen.add(finalist);
        }
        return chosen;
    }

    public static void main(String[] args) {
        System.out.println(pickFinalists());
    }
}
