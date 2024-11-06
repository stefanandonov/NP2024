package mk.ukim.finki.av5.priorityqueue;

public class PriorityQueueElement<T> implements Comparable<PriorityQueueElement<T>> {

    private T item;
    private int priority;

    public PriorityQueueElement(T item, int priority) {
        this.item = item;
        this.priority = priority;
    }

    public T getItem() {
        return item;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", item.toString(), priority);
    }

    @Override
    public int compareTo(PriorityQueueElement<T> other) {
        return Integer.compare(priority, other.priority);
    }
}
