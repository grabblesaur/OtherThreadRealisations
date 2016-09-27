package com.bayarbogdanov.atomicity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {

    private volatile int i = 0;

    public synchronized int getValue() {
        return i;
    }

    public synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        for (int i = 0; i < 10; i++) {
            exec.execute(at);
            boolean flag = true;
            while (flag) {
                int val = at.getValue();
                if (val % 2 != 0) {
                    System.out.println(val);
                    flag = false;
                }
            }
        }
        exec.shutdown();
    }
}
