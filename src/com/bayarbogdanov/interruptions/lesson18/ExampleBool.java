package com.bayarbogdanov.interruptions.lesson18;

/**
 * Created by qqq on 03.10.2016.
 */
public class ExampleBool {
    private boolean isTrue;

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public static void main(String[] args) {
        ExampleBool eb = new ExampleBool();
        System.out.println(eb.isTrue());
    }
}
