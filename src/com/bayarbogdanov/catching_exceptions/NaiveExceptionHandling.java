package com.bayarbogdanov.catching_exceptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling {
    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
            exec.shutdown();
        } catch (RuntimeException e) {
            // это команда НЕ БУДЕТ выполняться!
            System.out.println("Exception has been handled!");
        }
    }
}
