package khu.rbecs.multithreading;

import java.time.Duration;
import java.util.concurrent.*;

public class Main1 {
    // multithreading
    // process and thread
    // parallelism and concurrency

    public static void main(String... args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.execute(() -> {
                System.out.println("Runnable is running... in thread: "
                                   + Thread.currentThread().getName());
            });
            executor.execute(new MyTask());
            executor.execute(new MyTask());
            executor.execute(new MyTask());
            executor.execute(new MyTask());
        }
        System.out.println("End of main");
    }

    public static void main2(String... args) {
        var executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            System.out.println("Runnable is running... in thread: "
                               + Thread.currentThread().getName());
        });
        executor.execute(new MyTask());
        executor.execute(new MyTask());
        executor.execute(new MyTask());
        executor.execute(new MyTask());
        executor.shutdown();
        System.out.println("End of main");
    }

    public static void main1(String... args) {
        var t1 = new MyThread("my-thread-1");
        var task = new MyTask();
        var t2 = new Thread(task);
        var t3 = new Thread(() -> {
            System.out.println("Lambda expression is running... in thread: "
                               + Thread.currentThread().getName());
        });
        t1.start();
        t2.start();
        t3.start();
        System.out.println("End of main");
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyTask is running... in thread: "
                           + (Thread.currentThread().isVirtual()
                ? "virtual"
                : Thread.currentThread().getName()));
    }
}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }
    @Override
    public void run() {
        System.out.println("MyThread is running... in thread: "
                           + Thread.currentThread().getName());
    }
}
