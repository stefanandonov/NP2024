package mk.ukim.finki.av5.priorityqueue;

public class EmptyPriorityQueueException extends Exception {
    public EmptyPriorityQueueException() {
        super("The queue is empty.");
    }
}
