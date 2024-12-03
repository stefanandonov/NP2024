package mk.ukim.finki.av7.finalists;

import java.util.*;

public class RandomFinalistsPicker {

//    private static List<Integer> pickFinalists() {
//        Random random = new Random();
//        List<Integer> picked = new ArrayList<>();
//
//        while (picked.size() != 3) {
//            int pick = random.nextInt(30) + 1;
//            if (!picked.contains(pick))
//                picked.add(pick);
//        }
//        return picked;
//    }

    private static Set<Integer> pickFinalists() {
        Random random = new Random();
        Set<Integer> picked = new TreeSet<>();

        while (picked.size() != 3) {
            int pick = random.nextInt(30) + 1;
            picked.add(pick);
        }
        return picked;
    }

    public static void main(String[] args) {
        System.out.println(pickFinalists());
    }

}
