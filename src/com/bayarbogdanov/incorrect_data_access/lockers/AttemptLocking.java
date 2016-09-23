package com.bayarbogdanov.incorrect_data_access.lockers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Объекты Lock из библиотеки concurrent позволяют
 * отказаться от попыток получения блокировки
 * по тайм-ауту.
 */
public class AttemptLocking {

    private ReentrantLock lock = new ReentrantLock();

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;

        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): "
                    + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }
}
