package com.bayarbogdanov.interruptions;

import java.util.concurrent.TimeUnit;

/**
 * Общая идиома прерывания задачи.
 */

class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup() {
        System.out.println("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {

    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // точка1
                NeedsCleanup n1 = new NeedsCleanup(1);
                // try-finally начинается сразу же за определением
                // n1, чтобы гарантировать освобождение n1:
                try {
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    // точка2
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    // гарантирует правильное
                    // освобождение n2:
                    try {
                        System.out.println("Calculating");
                        // Продолжительная неблокирующая операция:
                        for (int i = 0; i < 2500000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("Finished time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while() test");
        } catch (InterruptedException e) {
            System.out.println("Exiting via InterruptedException");
        }
    }
}

public class InterruptingIdiom {

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("usage: java InterruptingIdiom delay-in-ms");
            System.exit(1);
        }
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(args[0]));
        t.interrupt();
    }

}




















