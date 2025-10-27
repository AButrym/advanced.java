package khu.rbecs.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main3 {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();
    private static final Lock LOCK3 = new ReentrantLock();
    private static final ReadWriteLock LOCK4 = new ReentrantReadWriteLock();
    private static final Lock LOCK4_READER = LOCK4.readLock();
    private static final Lock LOCK4_WRITER = LOCK4.writeLock();
    static int i = 0;

    private static void increment() {
        synchronized (Main3.class) {
            i++;
        }
    }

    public static synchronized void decrement() {
        i--;
    }

    private synchronized void foo1() {
        System.out.println("Something");
    }

    private void foo2() {
        synchronized (this) {
            System.out.println("Something");
        }
    }

    public static void main(String... args) throws InterruptedException {
        var t1 = new Thread(() -> {
            for (int j = 0; j < 100_000; j++) {
                increment();
            }
        });
        var t2 = new Thread(() -> {
            for (int j = 0; j < 100_000; j++) {
                decrement();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("i = " + i);
    }
}
