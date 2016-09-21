package com.bayarbogdanov.other_threads.inner_variants.lesson10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Класс вычисляющий последовательность Фибоначчи.
 */
public class Fibonacci implements Callable<List<BigInteger>> {

    private int amount;

    public Fibonacci(int amount) {
        this.amount = amount - 2;
    }

    @Override
    public List<BigInteger> call() throws Exception {
        BigInteger currentElement;
        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ONE;

        List<BigInteger> list = new ArrayList<>();
        list.add(first);
        list.add(second);

        for (int i = 0; i < amount; i++) {
            currentElement = first.add(second);
            first = second;
            second = currentElement;
            list.add(currentElement);
        }

        return list;
    }
}
