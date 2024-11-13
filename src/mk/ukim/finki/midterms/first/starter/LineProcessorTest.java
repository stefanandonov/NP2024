package mk.ukim.finki.midterms.first.starter;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class LineProcessor {
    private int countCharOccurrences(String string, char character) {
        return (int) string.toLowerCase().chars().filter(c -> c == Character.toLowerCase(character)).count();
    }

    public void readLines(InputStream in, PrintStream out, char character) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());

//        int max = 0;
//        String maxLine = "";
//        for (String line : lines) {
//            if (countCharOccurrences(line, character) >= max) {
//                max = countCharOccurrences(line, character);
//                maxLine = line;
//            }
//        }

        PrintWriter printWriter = new PrintWriter(out);
        Comparator<String> comparator = (o1, o2) -> (int) (countCharOccurrences(o1, character) - countCharOccurrences(o2, character));
        String result = lines.stream().max(comparator.thenComparing(Comparator.naturalOrder())).orElse("");
        printWriter.println(result);
        printWriter.flush();
        bufferedReader.close();
    }
}

public class LineProcessorTest {
    public static void main(String[] args) {
        LineProcessor lineProcessor = new LineProcessor();

        try {
            lineProcessor.readLines(System.in, System.out, 'a');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

