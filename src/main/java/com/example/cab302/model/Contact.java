package com.example.cab302.model;

/**
 * Represents a contact entity with credentials and identifiers.
 * This class is used to store information about a contact including their email, password,
 * and password confirmation. It provides getters and setters for each property.
 */
public class Contact {
    private String email; // Email address of the contact
    private String password; // Password of the contact

    /**
     * Constructs a new Contact with the specified email, password, and password confirmation.
     *
     * @param email            the email address of the contact
     * @param password         the password of the contact
     */
    public Contact(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the email address of this contact.
     *
     * @return the email address of this contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of this contact.
     *
     * @param email the new email address of this contact
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of this contact.
     *
     * @return the password of this contact
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for this contact.
     *
     * @param password the new password for this contact
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
