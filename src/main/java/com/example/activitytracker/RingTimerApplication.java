package com.example.activitytracker;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class RingTimerApplication extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Create the UI components
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com.example.activitytracker/mainView.css")).toExternalForm());



        // Create the inner circle
        Circle innerCircle = new Circle(150, Color.DARKGREY);
        innerCircle.setId("circle");

        // Create the outer & timer circles
        Circle outerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setId("circle");
        Circle timerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setStroke(Color.GREEN);
        outerCircle.setStrokeWidth(6);

        // Status text (start/stop)
        Text statusText = new Text("START");
        statusText.setId("bold-text");
        statusText.setTranslateY(-60);


        // Control box
        Rectangle ctrlBox = new Rectangle(150,80);
        ctrlBox.setFill(Color.LIGHTGRAY);
        ctrlBox.setStroke(Color.BLACK);
        ctrlBox.setStrokeWidth(4);
        ctrlBox.setTranslateY(60);
        ctrlBox.setArcHeight(15);
        ctrlBox.setArcWidth(15);

        Circle topDot = new Circle(3, Color.BLACK);
        Circle bottomDot = new Circle(3, Color.BLACK);
        Polygon firstUp = createTriangleButton(true);
        Polygon secondUp = createTriangleButton(true);
        Polygon thirdUp = createTriangleButton(true);
        Polygon fourthUp = createTriangleButton(true);
        Polygon firstDown = createTriangleButton(false);
        Polygon secondDown = createTriangleButton(false);
        Polygon thirdDown = createTriangleButton(false);
        Polygon fourthDown = createTriangleButton(false);

        topDot.setTranslateY(50);
        bottomDot.setTranslateY(70);

        firstUp.setTranslateY(35);
        firstUp.setTranslateX(-50);
        firstDown.setTranslateY(85);
        firstDown.setTranslateX(-50);
        secondUp.setTranslateY(35);
        secondUp.setTranslateX(-20);
        secondDown.setTranslateY(85);
        secondDown.setTranslateX(-20);
        thirdUp.setTranslateY(35);
        thirdUp.setTranslateX(20);
        thirdDown.setTranslateY(85);
        thirdDown.setTranslateX(20);
        fourthUp.setTranslateY(35);
        fourthUp.setTranslateX(50);
        fourthDown.setTranslateY(85);
        fourthDown.setTranslateX(50);


        Text tenHoursField = new Text("0");
        Text tenMinutesField = new Text("0");
        Text hoursField = new Text("0");
        Text minutesField = new Text("0");
        tenHoursField.setId("controls-text");
        tenMinutesField.setId("controls-text");
        hoursField.setId("controls-text");
        minutesField.setId("controls-text");

        tenHoursField.setTranslateY(58);
        tenMinutesField.setTranslateY(58);
        hoursField.setTranslateY(58);
        minutesField.setTranslateY(58);
        tenHoursField.setTranslateX(-50);
        tenMinutesField.setTranslateX(20);
        hoursField.setTranslateX(-20);
        minutesField.setTranslateX(50);



        // Add everything to the stack pane
        root.getChildren().addAll(innerCircle, outerCircle, timerCircle, ctrlBox, statusText, firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown, topDot, bottomDot, tenHoursField, tenMinutesField, hoursField, minutesField);

        primaryStage.setScene(scene);
        primaryStage.show();

        // Define the rotate transition for the timer circle
        RotateTransition rotate = new RotateTransition(Duration.seconds(5), outerCircle);
        rotate.setByAngle(360);
        rotate.setCycleCount(RotateTransition.INDEFINITE);

        // Start the animation when the button is clicked
        innerCircle.setOnMouseClicked(event -> {
            if (statusText.getText().equals("START")) {
                statusText.setText("STOP");
            } else {
                statusText.setText("START");
            }
            root.requestLayout();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
    private Polygon createTriangleButton(boolean facingUp) {
        Polygon triangleButton = new Polygon();
        if (facingUp) {
            triangleButton.getPoints().addAll(new Double[]{
                    0.0, 15.0,
                    10.0, 0.0,
                    20.0, 15.0
            });
        } else {
            triangleButton.getPoints().addAll(new Double[]{
                    0.0, 0.0,
                    10.0, 15.0,
                    20.0, 0.0
            });
        }

        triangleButton.setFill(Color.BLACK);
        return triangleButton;
    }
}
