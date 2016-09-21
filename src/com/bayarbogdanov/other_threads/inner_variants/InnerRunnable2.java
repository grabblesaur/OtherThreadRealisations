package com.bayarbogdanov.other_threads.inner_variants;

import java.util.concurrent.TimeUnit;

/**
 * Использование анонимной реализаци Runnable.
 */
public class InnerRunnable2 {
    private int countDown = 5;
    private Thread t;

    public InnerRunnable2(String name) {
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(this);
                        if (--countDown == 0) {
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }

            @Override
            public String toString() {
                return Thread.currentThread().getName() +
                        ": " + countDown;
            }

        }, name);
        t.start();
    }
}
