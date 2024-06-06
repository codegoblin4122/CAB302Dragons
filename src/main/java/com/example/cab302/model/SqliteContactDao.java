package com.example.cab302.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implements the IContactDAO interface with a SQLite database backend.
 * This class provides the database operations required to manage contacts including
 * creation, addition, and retrieval of contact information from a SQLite table.
 */
public class SqliteContactDao implements IContactDAO {
    private Connection connection = SqliteConnection.getInstance();
    /**
     * Constructs a new SqliteContactDao.
     * Ensures the necessary table for storing contacts exists upon instantiation.
     */
    public SqliteContactDao() {
        createTable();
    }
    /**
     * Creates the contacts table in the SQLite database if it does not already exist.
     * This method ensures that the application can operate with the database
     * even if starting with a new or empty database file.
     */
    private void createTable() {
        String createQuery = "CREATE TABLE IF NOT EXISTS contacts ("
                + "email VARCHAR(255) PRIMARY KEY,"
                + "password VARCHAR(255) NOT NULL"
                + ")";
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(createQuery);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new contact to the database.
     * @param contact The Contact object containing the email, password, and password confirmation.
     */
    @Override
    public void addContact(Contact contact) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts (email, password) VALUES (?, ?)");
            statement.setString(1, contact.getEmail());
            statement.setString(2, contact.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing contact in the database.
     * @param contact The Contact object containing the updated email and password.
     */
    @Override
    public void updateContact(Contact contact) {

    }

    /**
     * Deletes a contact from the database.
     * @param contact The Contact object to delete.
     */
    @Override
    public void deleteContact(Contact contact) {

    }

    /**
     * Retrieves a contact by email.
     * @param email The email of the contact to retrieve.
     * @return The Contact object, or null if not found.
     */
    @Override
    public Contact getContact(String email) {
        Contact contact = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM contacts WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                contact = new Contact(
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }


    /**
     * Retrieves all contacts from the database.
     * @return A list of Contact objects.
     */
    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
}
