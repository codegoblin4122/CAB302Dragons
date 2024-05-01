package com.example.cab302;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SignupController {

    @FXML
    private TitledPane SignupTitlePane;

    @FXML
    private AnchorPane Scene2AnchorPane;

    @FXML
    void SwitchToLogin(ActionEvent e) throws IOException {
        new SceneSwitch(Scene2AnchorPane, "view/Signup.fxml");
    }

}
