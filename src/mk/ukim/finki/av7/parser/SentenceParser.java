package mk.ukim.finki.av7.parser;

import java.util.Arrays;

public class SentenceParser {

    public static String sortString(String string) {
        char[] chars = string.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    public static void arrangeSentence(String sentence) {
        String[] words = sentence.split("\s+");
        Arrays.stream(words)
                .map(SentenceParser::sortString)
                .map(i -> i.charAt(0) + "" + sortString(i.substring(1)))
                .sorted()
                .forEach(i -> System.out.print(i + " "));
    }

    public static void main(String[] args) {
        String testString = "kO pSk sO";
        arrangeSentence(testString);
    }
}
