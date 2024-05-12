package com.example.cab302.controller;

import com.example.cab302.Main;
import com.example.cab302.model.Contact;
import com.example.cab302.model.SqliteContactDao;
import com.example.cab302.model.IContactDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

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

    private IContactDAO contactDAO;


    public SignupController() {
        contactDAO = new SqliteContactDao();
    }

    private void selectContact(Contact contact) {
        emailField.setText(contact.getEmail());
        enterPassword.setText((contact.getPassword()));
        enterPasswordConfirm.setText((contact.getPasswordConfirm()));
    }

        
    @FXML
    protected void SwitchtoLogin() throws IOException {
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void onClickSignUp() throws IOException {
        // Get the values from the text fields
        String email = emailField.getText();
        String password = enterPassword.getText();
        String confirmPassword = enterPasswordConfirm.getText();


        boolean emailValid = validateEmail(email);
        boolean passValid = validatePassword(password);
        boolean confPassValid = validateConfPassword(password, confirmPassword);
        if (!(emailValid && passValid && confPassValid)) {
            System.out.print(String.format("SIGNUP FAILED: email - %s, pass - %s, confPass - %s\n", emailValid, passValid, confPassValid));
            return;
        }



        // Create a new Contact object with the entered values
        Contact newContact = new Contact(email, password, confirmPassword);
        newContact.setEmail(email);
        newContact.setPassword(password);
        newContact.setPasswordConfirm(confirmPassword);

        // Update the contact in the database
        contactDAO.addContact(new Contact(email, password, confirmPassword));
    }

        @FXML
    private void onAdd() {
        // default values for a new user
        final String DEFAULT_email = "test";
        final String DEFAULT_password = "password";
        final String DEFAULT_passwordConfirm = "password";
        Contact newContact = new Contact(DEFAULT_email, DEFAULT_password, DEFAULT_passwordConfirm);
        // add user to database
        contactDAO.addContact(newContact);
    }

    private boolean validateEmail(String email) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }
    private boolean validatePassword(String pass) {
        return Pattern.matches("^(?=.*\\d)(?=.*[!@#$%^&*()\\[\\]{};':\",./<>?\\\\|~`\\-=_+])(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", pass);
    }
    private boolean validateConfPassword(String pass, String confPass) {
        return confPass.equals(pass);
    }

    private void testValidation() {

    }
}

