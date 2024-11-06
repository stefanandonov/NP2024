package mk.ukim.finki.av5.box;

public class EmptyBoxException extends Exception {

    public EmptyBoxException() {
        super("The box is empty.");
    }
}
