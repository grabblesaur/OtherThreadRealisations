package com.bayarbogdanov.catching_exceptions;

abstract class AClass {
    void printHello(String name) {
        System.out.println("hello " + name);
    }

    abstract void bye(String name);
}

public class Example extends AClass {

    @Override
    void bye(String name) {
        System.out.println("bye " + name);
    }

    public static void main(String[] args) {
        Example e = new Example();
        e.printHello("Alex");
        e.bye("Alex");
    }
}
