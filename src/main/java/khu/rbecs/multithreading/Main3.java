package khu.rbecs.multithreading;

public class Main3 {
    static int i = 0;

    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String... args) throws InterruptedException {
        var t1 = new Thread(() -> {
            for (int j = 0; j < 100_000; j++) {
//                i++;
                synchronized (LOCK2) {
                    var tmp = i;
                    tmp = tmp + 1;
                    i = tmp;
                }
            }
        });
        var t2 = new Thread(() -> {
            for (int j = 0; j < 100_000; j++) {
                synchronized (LOCK2) {
                    i--;
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("i = " + i);
    }
}
