package com.bayarbogdanov.sync_for_objects;

/**
 * Синхронизация по другому объекту.
 */

class DualSync {
    private Object syncObject = new Object();

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject {
    public static void main(String[] args) {
        final DualSync ds = new DualSync();
        new Thread() {
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
}
