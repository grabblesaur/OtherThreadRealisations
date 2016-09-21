package com.bayarbogdanov.other_threads;

/**
 * Прямое наследование от класса Thread.
 */
public class SimpleThread extends Thread{
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        // Сохранение имени потока
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return "#" + getName() + "(" + countDown + "), ";
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(this);
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread();
        }
    }
}
