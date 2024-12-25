package mk.ukim.finki.av10;

import java.util.ArrayList;
import java.util.List;

class NumberPrinter extends Thread {
    int number;

    public NumberPrinter(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(number);
    }
}

public class ThreadsTesting {
    public static void main(String[] args) {

//        List<NumberPrinter> printers = new ArrayList<>();
//
//        for (int i=0;i<1000;i++){
//            printers.add(new NumberPrinter(i+1));
//        }
//
//        printers.forEach(Thread::start);
//        printers.forEach(p -> {
//            try {
//                p.join();
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//        });

        List<Thread> threads = new ArrayList<>();


        for (int i=0;i<1000;i++){
            int copyOfI = i;
            threads.add(new Thread(()-> System.out.println(copyOfI +1)));
        }

        threads.forEach(Thread::start);
        threads.forEach(p -> {
            try {
                p.join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        });

    }
}
