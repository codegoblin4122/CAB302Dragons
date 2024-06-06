package com.example.cab302.controller;

import com.example.cab302.Main;
import com.example.cab302.RingTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for managing the timer interface in the application.
 * This class handles the initialization and interaction logic for the timer screen,
 * including switching back to the login scene.
 */
public class TimerController {

    @FXML
    private StackPane timerContainer;
    @FXML
    private Button showStatsButton;
    private RingTimer ringTimer;

    @FXML
    private Button backToLoginButton;

    /**
     * Initializes the controller and sets up the RingTimer within the timer container.
     */
    @FXML
    public void initialize() {
        ringTimer = new RingTimer();
        timerContainer.getChildren().add(ringTimer.getRoot());
    }

    /**
     * Switches scene on button press to the Login Screen
     * @throws IOException if the FXML file for the login scene cannot be loaded.
     */
    @FXML
    protected void switchSceneToLogin() throws IOException {
        if (ringTimer.getRunning()) {
            ringTimer.stopTimer();
        }

        Stage stage = (Stage) backToLoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void handleShowStatsAction() throws IOException {
        if (ringTimer.getRunning()) {
            ringTimer.stopTimer();
        }
        Stage stage = (Stage) showStatsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/StatsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
