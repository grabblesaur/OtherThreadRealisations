package com.bayarbogdanov.incorrect_data_access.lesson11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            service.execute(new Validator("Crild", "ghjcnjnfr219532"));
        }
        service.shutdown();
    }
}
