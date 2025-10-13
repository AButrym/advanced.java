package khu.rbecs.multithreading;

public class Main2 {
    public static void main(String... args) throws InterruptedException {
        var t1 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                System.out.println("t1: " + i);
                pause(1000);
            }
            System.out.println("t1: end");
        });
        var t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("t2: " + i);
                pause(200);
            }
            System.out.println("t2: end");
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("End of main");
    }
    static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
