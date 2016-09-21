package com.bayarbogdanov.responsive_ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputExample {

    public static void main(String[] args) {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        String inputString = null;

        System.out.print("Enter message: ");
        try {
            inputString = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(inputString);
    }
}
