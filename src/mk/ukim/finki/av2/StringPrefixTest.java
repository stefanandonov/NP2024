package mk.ukim.finki.av2;

import java.util.stream.IntStream;

class StringPrefix {
    static boolean isPrefixShort (String str1, String str2){
        return str2.toLowerCase().startsWith(str1.toLowerCase());
    }

    static boolean isPrefixLong (String str1, String str2) {
        if (str1.length() > str2.length()){
            return false;
        }

        for (int i=0;i<str1.length();i++){
            if (str1.toLowerCase().charAt(i) != str2.toLowerCase().charAt(i)){
                return false;
            }
        }

        return true;
    }

    static boolean isPrefixLong2 (String str1, String str2) {
        if (str1.length() > str2.length()){
            return false;
        }

        return IntStream.range(0,str1.length())
                .allMatch(i -> str1.toLowerCase().charAt(i) == str2.toLowerCase().charAt(i));

    }


}

public class StringPrefixTest {
    public static void main(String[] args) {
        String str1 = "NaPrEdNo";
        String str2 = "Napredno programiranje 2024";

        System.out.println(StringPrefix.isPrefixShort(str1,str2));
        System.out.println(StringPrefix.isPrefixLong(str1,str2));
        System.out.println(str1);
        System.out.println(str2);
    }
}
