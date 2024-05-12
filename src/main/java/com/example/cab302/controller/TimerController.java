package com.example.cab302.controller;
import com.example.cab302.Main;
import com.example.cab302.RingTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TimerController {

    @FXML
    private StackPane timerContainer;

    @FXML
    private Button backButton;

    private RingTimer ringTimer;

    @FXML
    private Button backToLoginButton;


    @FXML
    public void initialize() {
        ringTimer = new RingTimer();
        timerContainer.getChildren().add(ringTimer.getRoot());

    }

    @FXML
    protected void switchSceneToLogin() throws IOException {
        Stage stage = (Stage) backToLoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
    }
    @FXML
    private void handleBackButtonAction() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        // Assuming login scene is set up elsewhere
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
