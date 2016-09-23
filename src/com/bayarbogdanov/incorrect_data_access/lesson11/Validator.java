package com.bayarbogdanov.incorrect_data_access.lesson11;

import java.util.Collections;
import java.util.Map;

public class Validator implements Runnable {

    private FakeDB fakeDB;

    private String guessedLogin;
    private String guessedPassword;

    public Validator(String guessedLogin, String guessedPassword) {
        this.guessedLogin = guessedLogin;
        this.guessedPassword = guessedPassword;
    }

    @Override
    public void run() {

        if (fakeDB == null) {
            fakeDB = new FakeDB();
            if (validate(guessedLogin, guessedPassword)) {
                System.out.println("auth completed");
            } else {
                System.out.println("incorrect input");
            }
        }

    }

    public boolean validate(String login, String password) {
        Map<String, String> existedUsers = fakeDB.getUsers();
        for (Map.Entry<String, String> pair : existedUsers.entrySet()) {
            if (login.equals(pair.getKey())
                    && password.equals(pair.getValue())) {
                return true;
            }
        }
        return false;
    }
}
