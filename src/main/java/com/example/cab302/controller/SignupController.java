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
import org.mindrot.jbcrypt.*;

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

        if (!validateEmail(email)) {
            loginMessageLabel.setText("Please enter a valid email.");
            return;
        }
        if (!validatePassword(password)) {
            loginMessageLabel.setText("Please enter a valid password. At least 8 digits, at least one number and one uppercase letter.");
            return;
        }
        if (!validateConfPassword(password, confirmPassword)) {
            loginMessageLabel.setText("Confirm password does not match password.");
            return;
        }
        if (contactDAO.getContact(email) != null) {
            System.out.println("User already exists.");
            return;
        }

        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Create a new Contact object with the entered values
        Contact newContact = new Contact(email, hashedPassword);

        // Update the contact in the database
        contactDAO.addContact(newContact);
        // Implementation of onClickSignUp
        loginMessageLabel.setText("Signup Successful");
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
        Contact newContact = new Contact(DEFAULT_email, DEFAULT_password);
        // add user to database
        contactDAO.addContact(newContact);
        // Implementation of onAdd
    }
}
