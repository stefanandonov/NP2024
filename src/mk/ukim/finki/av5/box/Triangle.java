package mk.ukim.finki.av5.box;

public class Triangle implements Drawable {

    private static long ID = 1L;

    private long id;

    public Triangle() {
        id = ID++;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "id=" + id +
                '}';
    }

    @Override
    public void draw() {
        System.out.printf("Triangle: %d\n", id);
    }
}
