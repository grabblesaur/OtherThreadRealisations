package com.bayarbogdanov.other_threads.inner_variants.lesson10;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Измените упражнение 5 по образцу класса ThreadMethod, чтобы
 * метод runTask() получал аргумент с количеством суммиремых чисел
 * Фибоначчи и при каждом вызове runTask() возвращал объект Future,
 * полученный при вызове submit().
 */
public class FibonacciDemo {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<List<BigInteger>> result = exec.submit(new Fibonacci(15));

        try {
            // get() blocks until completion
            List<BigInteger> fibSequence = result.get();
            System.out.println("fibSequence: ");
            for (BigInteger item : fibSequence) {
                System.out.print(item.toString() + ", ");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }

    }

}
