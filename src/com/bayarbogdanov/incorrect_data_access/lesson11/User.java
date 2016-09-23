package com.bayarbogdanov.incorrect_data_access.lesson11;

public class User {

    private String mail;
    private String login;
    private String password;

    public User(String mail, String login, String password) {
        this.mail = mail;
        this.login = login;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
