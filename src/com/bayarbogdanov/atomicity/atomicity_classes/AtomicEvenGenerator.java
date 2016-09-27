package com.bayarbogdanov.atomicity.atomicity_classes;

import com.bayarbogdanov.incorrect_data_access.EvenChecker;
import com.bayarbogdanov.incorrect_data_access.IntGenerator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Атомарные классы иногда могут пригодиться в обычном коде.
 */
public class AtomicEvenGenerator extends IntGenerator {

    private AtomicInteger currentEvenValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentEvenValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
