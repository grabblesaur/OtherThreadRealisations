package com.bayarbogdanov.sync_for_objects.lesson15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Calculator {

    private int x, y;

    private Object
        obj1 = new Object(),
        obj2 = new Object(),
        obj3 = new Object();

    public Calculator(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void sum() {
        synchronized (obj1) {
            System.out.println("Calculator.sum");
            sleep(3);
            System.out.println(x + y);
        }
    }

    public void subtract() {
        synchronized (obj2) {
            System.out.println("Calculator.subtract");
            sleep(3);
            System.out.println(x - y);
        }
    }

    public void multiply() {
        synchronized (obj3) {
            System.out.println("Calculator.multiply");
            sleep(3);
            System.out.println(x * y);
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
