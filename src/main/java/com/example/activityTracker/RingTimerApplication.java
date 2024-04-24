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
    private StackPane root;
    private Scene scene;
    private Circle innerCircle, timerCircle, outerCircle, topDot, bottomDot;
    private Text statusText;
    private Polygon firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown;
    private Text tenHoursField, hoursField, tenMinutesField, minutesField ;

    private Rectangle ctrlBox, firstUpBox, secondUpBox, thirdUpBox, fourthUpBox, firstDownBox, secondDownBox, thirdDownBox, fourthDownBox;


    @Override
    public void start(Stage primaryStage) {

        initStackPane();

        primaryStage.setScene(scene);
        primaryStage.show();


        // Start the animation when the button is clicked
        innerCircle.setOnMouseClicked(event -> toggleClock());
        statusText.setOnMouseClicked(event -> toggleClock());
        firstUpBox.setOnMouseClicked(event -> incrementControls(true, 1));
        firstDownBox.setOnMouseClicked(event -> incrementControls(false,1));
        secondUpBox.setOnMouseClicked(event -> incrementControls(true,2));
        secondDownBox.setOnMouseClicked(event -> incrementControls(false,2));
        thirdUpBox.setOnMouseClicked(event -> incrementControls(true,3));
        thirdDownBox.setOnMouseClicked(event -> incrementControls(false,3));
        fourthUpBox.setOnMouseClicked(event -> incrementControls(true,4));
        fourthDownBox.setOnMouseClicked(event -> incrementControls(false,4));
    }

    private void initStackPane() {
        // Create the UI components
        this.root = new StackPane();
        this.scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com.example.activitytracker/mainView.css")).toExternalForm());

        // Create the inner circle
        this.innerCircle = new Circle(150, Color.rgb(20,20,20));
        innerCircle.setId("circle");

        // Create the outer & timer circles
        this.outerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setId("circle");
        this.timerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setStroke(Color.GREEN);
        outerCircle.setStrokeWidth(6);

        // Status text (start/stop)
        this.statusText = new Text("START");
        statusText.setId("bold-text");
        statusText.setTranslateY(-60);
        statusText.setFill(Color.WHITE);
        statusText.setStroke(Color.LIGHTGRAY);

        // Control box
        this.ctrlBox = new Rectangle(150,80);
        ctrlBox.setFill(Color.WHITE);
        ctrlBox.setStroke(Color.LIGHTGRAY);
        ctrlBox.setStrokeWidth(4);
        ctrlBox.setTranslateY(60);
        ctrlBox.setArcHeight(15);
        ctrlBox.setArcWidth(15);

        // Control box buttons and output
        this.topDot = new Circle(3, Color.BLACK);
        this.bottomDot = new Circle(3, Color.BLACK);
        this.firstUp = createTriangleButton(true);
        this.secondUp = createTriangleButton(true);
        this.thirdUp = createTriangleButton(true);
        this.fourthUp = createTriangleButton(true);
        this.firstDown = createTriangleButton(false);
        this.secondDown = createTriangleButton(false);
        this.thirdDown = createTriangleButton(false);
        this.fourthDown = createTriangleButton(false);
        topDot.setTranslateY(50);
        bottomDot.setTranslateY(70);
        firstUp.setTranslateY(35);
        firstUp.setTranslateX(-50);
        secondUp.setTranslateY(35);
        secondUp.setTranslateX(-20);
        thirdUp.setTranslateY(35);
        thirdUp.setTranslateX(20);
        fourthUp.setTranslateY(35);
        fourthUp.setTranslateX(50);
        firstDown.setTranslateY(85);
        firstDown.setTranslateX(-50);
        secondDown.setTranslateY(85);
        secondDown.setTranslateX(-20);
        thirdDown.setTranslateY(85);
        thirdDown.setTranslateX(20);
        fourthDown.setTranslateY(85);
        fourthDown.setTranslateX(50);

        this.firstUpBox = new Rectangle(28,40, Color.TRANSPARENT);
        firstUpBox.setTranslateY(40);
        firstUpBox.setTranslateX(-50);
        this.firstDownBox = new Rectangle(28,40, Color.TRANSPARENT);
        firstDownBox.setTranslateY(80);
        firstDownBox.setTranslateX(-50);
        this.secondUpBox = new Rectangle(28,40, Color.TRANSPARENT);
        secondUpBox.setTranslateY(40);
        secondUpBox.setTranslateX(-20);
        this.secondDownBox = new Rectangle(28,40, Color.TRANSPARENT);
        secondDownBox.setTranslateY(80);
        secondDownBox.setTranslateX(-20);
        this.thirdUpBox = new Rectangle(28,40, Color.TRANSPARENT);
        thirdUpBox.setTranslateY(40);
        thirdUpBox.setTranslateX(20);
        this.thirdDownBox = new Rectangle(28,40, Color.TRANSPARENT);
        thirdDownBox.setTranslateY(80);
        thirdDownBox.setTranslateX(20);
        this.fourthUpBox = new Rectangle(28,40, Color.TRANSPARENT);
        fourthUpBox.setTranslateY(40);
        fourthUpBox.setTranslateX(50);
        this.fourthDownBox = new Rectangle(28,40, Color.TRANSPARENT);
        fourthDownBox.setTranslateY(80);
        fourthDownBox.setTranslateX(50);

        this.tenHoursField = new Text("0");
        this.tenMinutesField = new Text("0");
        this.hoursField = new Text("0");
        this.minutesField = new Text("0");
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
        root.getChildren().addAll(outerCircle, timerCircle, innerCircle, statusText,
                ctrlBox, firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown, topDot, bottomDot, tenHoursField, tenMinutesField, hoursField, minutesField, firstUpBox, secondUpBox, thirdUpBox, fourthUpBox, firstDownBox, secondDownBox, thirdDownBox, fourthDownBox);
    }

    private void toggleClock() {
        if (statusText.getText().equals("START")) {

            statusText.setText("STOP");
        } else {
            statusText.setText("START");
        }
        root.requestLayout();
    }
    // Inc or dec the first field in clock controls
    private void incrementControls(boolean inc, int field) {
        System.out.println(String.valueOf(inc)+" inc for field: "+String.valueOf(field));
        int currentValue;
        if (field == 1) {
            currentValue = Integer.parseInt(tenHoursField.getText());
            if (inc && currentValue < 9) {
                this.tenHoursField.setText(String.valueOf(currentValue+1));
            } else if (!inc && currentValue > 0) {
                this.tenHoursField.setText(String.valueOf(currentValue-1));
            }
        } else if (field == 2) {
            currentValue = Integer.parseInt(hoursField.getText());
            if (inc && currentValue < 9) {
                this.hoursField.setText(String.valueOf(currentValue+1));
            } else if (!inc && currentValue > 0) {
                this.hoursField.setText(String.valueOf(currentValue-1));
            }
        } else if (field == 3) {
            currentValue = Integer.parseInt(tenMinutesField.getText());
            if (inc && currentValue < 9) {
                this.tenMinutesField.setText(String.valueOf(currentValue+1));
            } else if (!inc && currentValue > 0) {
                this.tenMinutesField.setText(String.valueOf(currentValue-1));
            }
        } else if (field == 4) {
            currentValue = Integer.parseInt(minutesField.getText());
            if (inc && currentValue < 9) {
                this.minutesField.setText(String.valueOf(currentValue+1));
            } else if (!inc && currentValue > 0) {
                this.minutesField.setText(String.valueOf(currentValue-1));
            }
        }
        root.requestLayout();
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
    private int getCurrentControlsValue() {
        int tenHours = Integer.parseInt(tenHoursField.getText())*1000;
        int hours = Integer.parseInt(hoursField.getText())*100;
        int tenMinutes = Integer.parseInt(tenMinutesField.getText());
        int minutes = Integer.parseInt(minutesField.getText());
        return tenHours + hours + tenMinutes + minutes;
    }
}