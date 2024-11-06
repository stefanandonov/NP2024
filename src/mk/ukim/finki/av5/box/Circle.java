package mk.ukim.finki.av5.box;

public class Circle implements Drawable {

    private static long ID = 1L;

    private long id;

    public Circle() {
        id = ID++;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "id=" + id +
                '}';
    }

    @Override
    public void draw() {
        System.out.printf("Circle: %d\n", id);
    }
}
