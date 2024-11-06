package mk.ukim.finki.av5.box;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Box<E extends Drawable> {

    private List<E> items;

    public Box() {
        items = new ArrayList<>();
    }

    public void addItem(E item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public E getItemFromBox() throws EmptyBoxException {
        if (isEmpty()) throw new EmptyBoxException();

        Random random = new Random();
        int position = random.nextInt(items.size());
        return items.get(position);
    }

    public void drawBox() throws EmptyBoxException {
        if (isEmpty()) throw new EmptyBoxException();

        for (E item : items) {
            item.draw();
        }
//        items.stream().forEach(i -> i.draw());
//        items.stream().forEach(Drawable::draw);
//        items.forEach(Drawable::draw);
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        items.forEach(i -> stringBuilder.append(i.toString()).append("\n"));
        return stringBuilder.toString();
    }
}
