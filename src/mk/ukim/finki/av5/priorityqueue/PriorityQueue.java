package mk.ukim.finki.av5.priorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PriorityQueue<T> {

    private List<PriorityQueueElement<T>> items;

    public PriorityQueue() {
        items = new ArrayList<>();
    }

    public void addItem(T item, int priority) {
        PriorityQueueElement<T> element = new PriorityQueueElement<>(item, priority);
        int i = 0;
        for (i = 0; i < items.size(); i++) {
            if (items.get(i).compareTo(element) < 0) break;
        }
        items.add(i, element);
    }

    public PriorityQueueElement<T> remove() throws EmptyPriorityQueueException {
        if (items.isEmpty()) throw new EmptyPriorityQueueException();
        return items.remove(0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        items.forEach(i -> stringBuilder.append(i.toString()).append("\n"));
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        if (option.equals("String")) {
            PriorityQueue<String> stringPriorityQueue = new PriorityQueue<>();
            stringPriorityQueue.addItem("A", 10);
            stringPriorityQueue.addItem("B", 0);
            stringPriorityQueue.addItem("C", 11);

            System.out.println("-- test add and print queue --");
            System.out.println(stringPriorityQueue);
            try {
                stringPriorityQueue.remove();
            } catch (EmptyPriorityQueueException exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println("-- test remove queue --");
            System.out.println(stringPriorityQueue);
        } else if (option.equals("Integer")) {
            PriorityQueue<Integer> integerPriorityQueue = new PriorityQueue<>();
            integerPriorityQueue.addItem(10, 10);
            integerPriorityQueue.addItem(90, 10000);
            integerPriorityQueue.addItem(20, 9);

            System.out.println("-- test add and print queue --");
            System.out.println(integerPriorityQueue);
            try {
                integerPriorityQueue.remove();
                integerPriorityQueue.remove();
            } catch (EmptyPriorityQueueException exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println("-- test remove queue --");
            System.out.println(integerPriorityQueue);
        } else {
            PriorityQueue<Double> doublePriorityQueue = new PriorityQueue<>();
            doublePriorityQueue.addItem(22.33, 10);
            doublePriorityQueue.addItem(34.33, 100);

            System.out.println("-- test add and print queue --");
            System.out.println(doublePriorityQueue);
            try {
                doublePriorityQueue.remove();
            } catch (EmptyPriorityQueueException exception) {
                System.out.println(exception.getMessage());
            }
            System.out.println("-- test remove queue --");
            System.out.println(doublePriorityQueue);
        }
    }
}
