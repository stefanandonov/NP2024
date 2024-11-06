package mk.ukim.finki.av5.box;

import java.util.Scanner;
import java.util.stream.IntStream;

public class BoxTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Box<Circle> circleBox = new Box<>();
        Box<Rectangle> rectangleBox = new Box<>();
        Box<Triangle> triangleBox = new Box<>();

        IntStream.range(0, 3).forEach(i -> circleBox.addItem(new Circle()));
        IntStream.range(0, 4).forEach(i -> rectangleBox.addItem(new Rectangle()));

        int option = Integer.parseInt(scanner.nextLine());

        if (option == 1) {
            System.out.println(" -- test isEmpty() -- ");
            System.out.println(circleBox.isEmpty());
            System.out.println(rectangleBox.isEmpty());
            System.out.println(triangleBox.isEmpty());
        } else if (option == 2) {
            System.out.println(" -- test getItemFromBox() --");
            try {
                System.out.println(circleBox.getItemFromBox());
                System.out.println(rectangleBox.getItemFromBox());
                System.out.println(triangleBox.getItemFromBox());
            } catch (EmptyBoxException exception) {
                System.out.println(exception.getMessage());
            }
        } else {
            System.out.println(" -- test drawBox() --");
            try {
                circleBox.drawBox();
                rectangleBox.drawBox();
                triangleBox.drawBox();
            } catch (EmptyBoxException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
