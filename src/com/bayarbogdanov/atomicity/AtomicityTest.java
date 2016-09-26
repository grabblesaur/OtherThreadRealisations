package com.bayarbogdanov.atomicity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {

    private int i = 2;


    @Override
    public void run() {
        while (true) {
            if (i % 2 == 0) {
                i++;
                i++;
                System.out.println(Thread.currentThread().getName() + ": " + i);
            } else {
                System.out.println("end");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exec.execute(new AtomicityTest());
        }
        exec.shutdown();
    }
}
