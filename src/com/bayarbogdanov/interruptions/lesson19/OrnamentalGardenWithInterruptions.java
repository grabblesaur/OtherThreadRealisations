package com.bayarbogdanov.interruptions.lesson19;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class Count {
    private int count = 0;
    private Random rand = new Random(47);

    public synchronized int increment() {
        return ++count;
    }

    public synchronized int value() {
        return count;
    }
}

class Entrance implements Runnable {

    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<>();
    private int number;

    // Для чтения синхрониазация не нужна
    private final int id;
    private static volatile boolean canceled = false;

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    public static void cancel() {
        canceled = true;
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance e : entrances) {
            sum += e.getValue();
        }
        return sum;
    }
}

public class OrnamentalGardenWithInterruptions {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        f.cancel(true); // Прервать если выполняется
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            test(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting psvm with the System.exit(0)");
        System.exit(0);
    }
}
