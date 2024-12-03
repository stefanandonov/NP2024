package mk.ukim.finki.av7.books;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookCollection {

    private final Comparator<Book> titleAndPriceComparator =
            Comparator.comparing(Book::getTitle)
                    .thenComparing(Book::getPrice);

    private final Comparator<Book> priceAndTitleComparator =
            Comparator.comparing(Book::getPrice)
                    .thenComparing(Book::getTitle);

    private final List<Book> books;

    public BookCollection() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printByCategory(String category) {
        books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .sorted(titleAndPriceComparator)
                .forEach(System.out::println);
    }

    public List<Book> getCheapestN(int n) {
        return books.stream()
                .sorted(priceAndTitleComparator)
                .limit(n)
                .collect(Collectors.toList());
    }
}
