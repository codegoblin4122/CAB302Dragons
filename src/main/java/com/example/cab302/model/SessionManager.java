package com.example.cab302.model;

public class SessionManager {
    private static SessionManager instance;
    private String currentUserEmail;
    private SessionManager() { }
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
    }
    public boolean isLoggedIn() {
        return currentUserEmail != null;
    }
}
