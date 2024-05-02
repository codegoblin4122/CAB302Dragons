package com.example.cab302.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteContactDao implements IContactDAO {

    private Connection connection = SqliteConnection.getInstance();


    public SqliteContactDao() {
        createTable();
    }

    private void createTable() {
        String createQuery = "CREATE TABLE IF NOT EXISTS contacts ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "email VARCHAR NOT NULL,"
                + "password VARCHAR NOT NULL,"
                + "passwordConfirm VARCHAR NOT NULL"
                + ")";
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(createQuery);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

//    private void insertSampleData() {
//        try {
//            // Clear before inserting
//            Statement clearStatement = connection.createStatement();
//            String clearQuery = "DELETE FROM contacts";
//            clearStatement.execute(clearQuery);
//            Statement insertStatement = connection.createStatement();
//            String insertQuery = "INSERT INTO contacts (firstName, lastName, phone, email) VALUES "
//                    + "('John', 'Doe', '0423423423', 'johndoe@example.com'),"
//                    + "('Jane', 'Doe', '0423423424', 'janedoe@example.com'),"
//                    + "('Jay', 'Doe', '0423423425', 'jaydoe@example.com')";
//            insertStatement.execute(insertQuery);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void addContact(Contact contact) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts (email, password, passwordConfirm) VALUES (?, ?, ?)");
            statement.setString(1, contact.getEmail());
            statement.setString(2, contact.getPassword());
            statement.setString(3, contact.getPasswordConfirm());
            statement.executeUpdate();
            // Set the id of the new contact
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                contact.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(Contact contact) {

    }

    @Override
    public void deleteContact(Contact contact) {

    }

    @Override
    public Contact getContact(int id) {
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        return null;
    }


}
