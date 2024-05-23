package com.example.cab302.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteTrackingDAO {
    private final Connection connection = SqliteConnection.getInstance();

    public SqliteTrackingDAO() {
        createTable();
    }

    private void createTable() {
        String createQuery = "CREATE TABLE IF NOT EXISTS TimerUsage ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "userEmail VARCHAR NOT NULL,"
                + "startTime TIMESTAMP NOT NULL,"
                + "endTime TIMESTAMP NOT NULL,"
                + "FOREIGN KEY (userEmail) REFERENCES contacts(email)"
                + ")";
        try (Statement createTable = connection.createStatement()) {
            createTable.execute(createQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTimerUsage(String email, Timestamp startTime, Timestamp endTime) throws SQLException {
        String query = "INSERT INTO TimerUsage (userEmail, startTime, endTime) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setTimestamp(2, startTime);
            stmt.setTimestamp(3, endTime);
            stmt.executeUpdate();
        }
    }

    public List<TimerUsage> getTimerUsageByEmail(String email) throws SQLException {
        List<TimerUsage> usageList = new ArrayList<>();
        String query = "SELECT * FROM TimerUsage WHERE userEmail = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TimerUsage usage = new TimerUsage(
                        rs.getInt("id"),
                        rs.getString("userEmail"),
                        rs.getTimestamp("startTime"),
                        rs.getTimestamp("endTime")
                );
                usageList.add(usage);
            }
        }
        return usageList;
    }
}
