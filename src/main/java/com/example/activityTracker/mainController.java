package com.example.activityTracker;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class signUpController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private void handleSignUp() {
        // Handle sign-up action
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Add your sign-up logic here
        System.out.println("Signing up...");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }
}
