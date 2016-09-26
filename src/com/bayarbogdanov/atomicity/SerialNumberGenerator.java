package com.bayarbogdanov.atomicity;

public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public synchronized static int nextSerialNumber() {
        return serialNumber++; // Небезопасно для потоков
    }
}
