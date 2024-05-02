package com.example.cab302.model;

public class Contact {
    private int id;
    private String email;
    private String password;
    private String passwordConfirm;

    public Contact(String email, String password, String passwordConfirm) {
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) {this.password = password; }

    public String getPasswordConfirm() { return passwordConfirm; }

    public void setPasswordConfirm(String passwordConfirm) {this.passwordConfirm = passwordConfirm; }

}