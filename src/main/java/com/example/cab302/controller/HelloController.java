package com.example.cab302.controller;

import com.example.cab302.Main;
import com.example.cab302.RingTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 * Controller for the main application login view.
 * Handles user interactions for login, signup, password reset, and exiting the application.
 */
public class HelloController {
    @FXML
    private Button signUpButton;

    @FXML
    private BorderPane LoginPane;

    @FXML
    private Button cancelButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button resetPasswordButton;
    /**
     * Closes the application window when the cancel button is clicked.
     */
    @FXML
    protected void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

// Used with DatabaseConnection - switch to SqliteConnection
//    public void loginButtonOnAction() {
//        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
//            //loginMessageLabel.setText("You tried to login!");
//            validateLogin();
//        } else {
//            loginMessageLabel.setText("Please enter your username and password");
//        }
//    }
//
//    public void validateLogin() {
//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectDB = connectNow.getConnection();
//
//        String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE username = '" + usernameTextField.getText() + "' AND password = '" + passwordPasswordField.getText() + "';";
//
//        try {
//            Statement statement = connectDB.createStatement();
//            ResultSet queryResult = statement.executeQuery(verifyLogin);
//
//            while(queryResult.next()) {
//                if (queryResult.getInt(1) == 1) {
//                    loginMessageLabel.setText("Welcome!");
//                } else {
//                    loginMessageLabel.setText("Invalid Login. Please try again");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * Navigates to the signup view when the signup button is clicked.
     * @throws IOException if the Signup.fxml file cannot be loaded.
     */
    @FXML
    protected void SwitchToSignUp() throws IOException {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    /**
     * Navigates to the reset password view when the reset password button is clicked.
     * @throws IOException if the ResetPassword.fxml file cannot be loaded.
     */
    protected void switchToResetPassword() throws IOException {
        Stage stage = (Stage) resetPasswordButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/ResetPassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }
    /**
     * Handles user login actions and navigates to the main application view upon successful login.
     * Currently, this method does not perform any actual authentication.
     */
    @FXML
    protected void loginButtonOnAction() {
        System.out.print("Logging in");
        try {
            // Get the current stage from the login button
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // Load the timer scene
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/TimerView.fxml"));
            if (fxmlLoader.getLocation() == null) {
                System.out.println("Error loading the FXML file.");
                return;
            }
            Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);

            // Set the new scene on the current stage
            stage.setScene(scene);
            stage.setTitle("Ring Timer");
            System.out.print("login successful");
        } catch (IOException e) {
            System.out.print("login failed");
        }
    }


}