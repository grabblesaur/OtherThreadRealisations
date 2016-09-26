package com.bayarbogdanov.atomicity;

public class Atomicity {

    public static void main(String[] args) {
        int i = 0;
        System.out.println(i++);
        System.out.println(i += 3);
    }
}
