package com.example.cab302.controller;

import com.example.cab302.Main;
import com.example.cab302.model.SessionManager;
import com.example.cab302.model.SqliteConnection;
import com.example.cab302.model.SqliteTrackingDAO;
import com.example.cab302.model.TimerUsage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class StatsController {
    @FXML
    private ComboBox<String> timePeriodComboBox;

    @FXML
    private PieChart statsPieChart;

    private SqliteTrackingDAO trackingDAO;

    public StatsController() {
        trackingDAO = new SqliteTrackingDAO();
    }

    @FXML
    public void initialize() {
        timePeriodComboBox.setValue("1 Day"); // Default value

        // Add listener to ComboBox to update stats on selection change
        timePeriodComboBox.valueProperty().addListener((observable, oldValue, newValue) -> showStats());

        // Show stats for the default value
        showStats();
    }

    public void showStats() {
        String selectedPeriod = timePeriodComboBox.getValue();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime;

        switch (selectedPeriod) {
            case "1 Day":
                startTime = now.minusDays(1);
                break;
            case "1 Week":
                startTime = now.minusWeeks(1);
                break;
            case "1 Month":
                startTime = now.minusMonths(1);
                break;
            case "1 Year":
                startTime = now.minusYears(1);
                break;
            default:
                startTime = now.minusDays(1);
        }

        Timestamp startTimestamp = Timestamp.valueOf(startTime);
        String currentUserEmail = SessionManager.getInstance().getCurrentUserEmail();

        try {
            List<TimerUsage> timerUsages = trackingDAO.getTimerUsageByEmail(currentUserEmail);
            Duration totalTimeOn = Duration.ZERO;
            Duration totalTimeOff = Duration.between(startTime, now);

            for (TimerUsage usage : timerUsages) {
                if (usage.getStartTime().after(startTimestamp)) {
                    Duration usageDuration = usage.getDuration();
                    totalTimeOn = totalTimeOn.plus(usageDuration);
                }
            }
            totalTimeOff = totalTimeOff.minus(totalTimeOn);

            updatePieChart(totalTimeOn, totalTimeOff);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePieChart(Duration totalTimeOn, Duration totalTimeOff) {
        long totalMinutes = totalTimeOn.toMinutes() + totalTimeOff.toMinutes();
        double timeOnPercentage = ((double) totalTimeOn.toMinutes() / totalMinutes) * 100;
        double timeOffPercentage = ((double) totalTimeOff.toMinutes() / totalMinutes) * 100;

        PieChart.Data timeOnData = new PieChart.Data("Time On (" + String.format("%.2f", timeOnPercentage) + "%)", totalTimeOn.toMinutes());
        PieChart.Data timeOffData = new PieChart.Data("Time Off (" + String.format("%.2f", timeOffPercentage) + "%)", totalTimeOff.toMinutes());

        statsPieChart.getData().clear();
        statsPieChart.getData().add(timeOnData);
        statsPieChart.getData().add(timeOffData);
    }

    @FXML
    private void handleBackToTimerAction() throws IOException {
        Stage stage = (Stage) statsPieChart.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/TimerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
