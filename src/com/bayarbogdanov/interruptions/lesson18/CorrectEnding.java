package com.bayarbogdanov.interruptions.lesson18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CorrectEnding implements Runnable {

    public void sleepMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(100);
    }

    @Override
    public void run() {
        System.out.println("starting CorrectEnding.run");
        try {
            sleepMethod();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("exiting CorrectEnding.run");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new CorrectEnding());
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}
