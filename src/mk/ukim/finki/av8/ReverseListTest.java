package mk.ukim.finki.av8;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class ReverseList {
    public static <T> void print (Collection<T> collection) {

        List<T> list = collection.stream().collect(Collectors.toList());

        list.reversed().forEach(el -> System.out.println(el));
    }
}

public class ReverseListTest {
    public static void main(String[] args) {
        List<String> strs = List.of("1","2","3","4");

        ReverseList.print(strs);
    }

}
