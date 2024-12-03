package mk.ukim.finki.av7.benford;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class BenfordLawTest {

    public static int getFirstDigit(int num) {
        while (num >= 10) {
            num /= 10;
        }
        return num;
    }

    public static int[] printBenfordLawCounts(String fileName)
            throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        return reader.lines()
                .mapToInt(Integer::parseInt)
                .map(BenfordLawTest::getFirstDigit)
                .mapToObj(i -> {
                    int[] array = new int[10];
                    array[i]++;
                    return array;
                }).reduce(new int[10], (left, right) -> {
                    Arrays.setAll(left, i -> left[i] + right[i]);
                    return left;
                });
    }

    public static void main(String[] args) {
        try {
            int[] result =
                    printBenfordLawCounts("C:\\Users\\ana5t\\work\\teaching\\winter\\NP\\2024 - 2025\\NP2024\\src\\mk\\ukim\\finki\\av7\\benford\\library_books.txt");
            Arrays.stream(result).forEach(System.out::println);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
