package com.bayarbogdanov.other_threads.inner_variants.lesson10;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FibonacciTask {

    public static List<BigInteger> run(int amount) {

        List<BigInteger> result = Collections.emptyList();

        ExecutorService service = Executors.newSingleThreadExecutor();
        try {
            result = service.submit(new Fibonacci(amount)).get();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
        } catch (ExecutionException e) {
            System.out.println("ExecutionException" + e.getMessage());
        } finally {
            service.shutdown();
        }

        return result;
    }

    public static void main(String[] args) {
        List<BigInteger> list = FibonacciTask.run(300);
        if (!list.isEmpty()) {
            System.out.println("size: " + list.size());
            for (BigInteger item : list) {
                System.out.println(item.toString());
            }
        } else {
            System.out.println("list is empty");
        }
    }
}
