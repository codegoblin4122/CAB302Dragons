package com.example.cab302.controller;

import com.example.cab302.Main;
import com.example.cab302.model.Contact;
import com.example.cab302.model.SqliteContactDao;
import com.example.cab302.model.IContactDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import static com.example.cab302.utils.InputValidation.*;

/**
 * Controller for handling the user signup process.
 * This class manages user inputs from the signup form, validates them, and interacts with the DAO
 * to add new users to the database.
 */
public class SignupController {

    @FXML
    private Button LoginButton;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField enterPassword;

    @FXML
    private PasswordField enterPasswordConfirm;

    @FXML
    private Button signUpConfirm;

    @FXML
    private BorderPane signupPane;

    @FXML
    private Label loginMessageLabel;

    private IContactDAO contactDAO;

    /**
     * Constructs the SignupController and initializes the contact DAO.
     */
    public SignupController() {
        contactDAO = new SqliteContactDao();
    }

    /**
     * Fills the form fields with data from an existing contact, typically for editing.
     * @param contact The contact whose information is to be displayed in the form.
     */
    private void selectContact(Contact contact) {
        emailField.setText(contact.getEmail());
        enterPassword.setText(contact.getPassword());
        enterPasswordConfirm.setText(contact.getPasswordConfirm());
    }

    /**
     * Switches scene on button press to the Login Screen
     * @throws IOException If the FXML file for the login view cannot be loaded.
     */
    @FXML
    protected void SwitchtoLogin() throws IOException {
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Handles the signup process when the sign up button is clicked.
     * Validates the email and password, and adds a new contact to the database if valid.
     * @throws IOException If an I/O error occurs during the process.
     */

    @FXML
    private void onClickSignUp() throws IOException {
        // Get the values from the text fields

        String email = emailField.getText();
        String password = enterPassword.getText();
        String confirmPassword = enterPasswordConfirm.getText();

//        boolean emailValid = validateEmail(email);
//        boolean passValid = validatePassword(password);
//        boolean confPassValid = validateConfPassword(password, confirmPassword);
//        if (!(emailValid && passValid && confPassValid)) {
//            System.out.print(String.format("SIGNUP FAILED: email - %s, pass - %s, confPass - %s\n", emailValid, passValid, confPassValid));
//            return;
//        }

        // Check if password and confirmPassword match
        if (!password.equals(confirmPassword)) {
            // Handle password mismatch (optional)
            System.out.println("Password and confirm password do not match.");
            return;
        }

        // Create a new Contact object with the entered values
        Contact newContact = new Contact(email, password, confirmPassword);
        newContact.setEmail(email);
        newContact.setPassword(password);
        newContact.setPasswordConfirm(confirmPassword);

        // Update the contact in the database
        contactDAO.addContact(new Contact(email, password, confirmPassword));
        // Implementation of onClickSignUp
    }

    /**
     * Adds a new user with default values to the database.
     * This method is primarily for testing and demonstration purposes.
     */
    @FXML
    private void onAdd() {
        // default values for a new user
        final String DEFAULT_email = "test";
        final String DEFAULT_password = "password";
        final String DEFAULT_passwordConfirm = "password";
        Contact newContact = new Contact(DEFAULT_email, DEFAULT_password, DEFAULT_passwordConfirm);
        // add user to database
        contactDAO.addContact(newContact);
        // Implementation of onAdd
    }
}
