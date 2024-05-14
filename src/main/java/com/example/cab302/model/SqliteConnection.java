package com.example.cab302.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the database connection to SQLite using the Singleton pattern.
 * This class ensures that only one connection instance is created and reused throughout
 * the application lifecycle, providing access to the SQLite database.
 */
public class SqliteConnection {
    private static Connection instance = null;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the connection to the SQLite database.
     */
    private SqliteConnection() {
        String url = "jdbc:sqlite:contacts.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println("Failed to create the SQLite database connection: " + sqlEx);
        }
    }

    /**
     * Provides the global point of access to the single instance of the Connection.
     * If the instance does not exist yet, it will be created.
     *
     * @return The single instance of the database Connection.
     */
    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}
