package mk.ukim.finki.av6;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;


class LineConsumer implements Consumer<String> {

    int lines = 0, words = 0, chars = 0;

    @Override
    public void accept(String line) {
        ++lines;
        words += line.split("\\s+").length;
        chars += line.length();
    }

    @Override
    public String toString() {
        return "LineConsumer{" +
                "lines=" + lines +
                ", words=" + words +
                ", chars=" + chars +
                '}';
    }
}

class WordCounter {
    public static void count(InputStream is) {
        int lines = 0, words = 0, chars = 0;
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            ++lines;
            String[] parts = line.split("\\s+");
            words += parts.length;
            chars += line.length();
        }
        sc.close();
        System.out.println(String.format("Lines: %d Words: %d Chars: %d", lines, words, chars));
    }

    public static void countBR(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        LineConsumer lineConsumer = new LineConsumer();
        br.lines().forEach(lineConsumer);
        br.close();
        System.out.println(lineConsumer);
    }

    public static void countStreams(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        List<String> lines = br.lines().collect(Collectors.toList());
        System.out.println(
                String.format(
                        "Lines: %d Words: %d Chars: %d",
                        lines.size(),
                        lines.stream()
                                .mapToInt(line -> line.split("\\s+").length)
                                .sum(),
                        lines.stream()
                                .mapToInt(line -> line.length())
                                .sum()
                )
        );

        br.close();
    }
}

public class WordCounterTest {
    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream("src/mk/ukim/finki/exercise5/files/source.txt");
            WordCounter.countBR(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
