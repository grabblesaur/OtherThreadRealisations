package com.bayarbogdanov.sync_for_objects.lesson16;

import com.bayarbogdanov.sync_for_objects.lesson15.Calculator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * То же самое что и lesson15, но с использованием
 * явныз блокировок Lock
 */
public class LockCalculator {
    private int x, y;
    private Lock lock = new ReentrantLock();

    private Object
            obj1 = new Object(),
            obj2 = new Object(),
            obj3 = new Object();

    public LockCalculator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void sum() {
        lock.lock();
        try {
            System.out.println("Calculator.sum");
            sleep(3);
            System.out.println(x + y);
        } finally {
            lock.unlock();
        }
    }

    public void subtract() {
        lock.lock();
        try {
            System.out.println("Calculator.subtract");
            sleep(3);
            System.out.println(x - y);
        } finally {
            lock.unlock();
        }
    }

    public void multiply() {
        lock.lock();
        try {
            System.out.println("Calculator.multiply");
            sleep(3);
            System.out.println(x * y);
        } finally {
          lock.unlock();
        }
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Calculator c = new Calculator(5, 4);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(c::sum);
        exec.execute(c::subtract);
        exec.execute(c::multiply);
        exec.shutdown();
    }
}
