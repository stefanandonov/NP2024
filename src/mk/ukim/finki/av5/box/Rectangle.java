package mk.ukim.finki.av5.box;

public class Rectangle implements Drawable {

    private static long ID = 1L;

    private long id;

    public Rectangle() {
        id = ID++;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "id=" + id +
                '}';
    }

    @Override
    public void draw() {
        System.out.printf("Rectangle: %d\n", id);
    }
}
