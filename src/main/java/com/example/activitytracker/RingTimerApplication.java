package com.example.activitytracker;
<<<<<<< Updated upstream
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
=======
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
>>>>>>> Stashed changes
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

<<<<<<< Updated upstream
//change
=======
>>>>>>> Stashed changes


public class RingTimerApplication extends Application {
    private StackPane root;
    private Scene scene;
<<<<<<< Updated upstream
    private Circle innerCircle, timerCircle, outerCircle, topDot, bottomDot;
=======
    private Circle innerCircle, outerCircle, topDot, bottomDot;
    private Arc timerCircle;
    private Pane arcPane;

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        innerCircle.setId("circle");

        // Create the outer & timer circles
        this.outerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setId("circle");
        this.timerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setStroke(Color.GREEN);
        outerCircle.setStrokeWidth(6);
=======
        this.outerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setStroke(Color.GREEN);
        outerCircle.setStrokeWidth(10);


        // Arc for timer status
        final double INSET = 10;
        final double ARC_RADIUS = 155;
        final double INITIAL_ARC_LENGTH  = 0;
        final double ARC_STROKE_WIDTH = 10;
        final double ARC_REGION_SIZE = ARC_RADIUS * 2 + ARC_STROKE_WIDTH + INSET * 2;

        timerCircle = new Arc(ARC_REGION_SIZE / 2, ARC_REGION_SIZE / 2,
                ARC_RADIUS, ARC_RADIUS,
                0, INITIAL_ARC_LENGTH);
        timerCircle.setStrokeWidth(ARC_STROKE_WIDTH);
        timerCircle.setStroke(Color.RED);
        timerCircle.setStartAngle(90);
        timerCircle.setFill(Color.TRANSPARENT);
        final double fillSize = ARC_RADIUS * 2 + timerCircle.getStrokeWidth() + INSET * 2;
        Rectangle fill = new Rectangle(fillSize, fillSize, Color.TRANSPARENT);
        Group centeredArcGroup = new Group(fill, timerCircle);
        arcPane = new StackPane(centeredArcGroup);
        arcPane.setPadding(new Insets(INSET));
        arcPane.setMinSize(0,0);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(arcPane);


>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
        root.getChildren().addAll(outerCircle, timerCircle, innerCircle, statusText,
                ctrlBox, firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown, topDot, bottomDot, tenHoursField, tenMinutesField, hoursField, minutesField, firstUpBox, secondUpBox, thirdUpBox, fourthUpBox, firstDownBox, secondDownBox, thirdDownBox, fourthDownBox);
=======
        root.getChildren().addAll(outerCircle, borderPane, innerCircle, statusText,
                ctrlBox, firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown,
                topDot, bottomDot, tenHoursField, tenMinutesField, hoursField, minutesField,
                firstUpBox, secondUpBox, thirdUpBox, fourthUpBox, firstDownBox, secondDownBox, thirdDownBox, fourthDownBox,
                timerMode, resetButton);
    }



    private void resizeControlBox(double width, double height) {
        ctrlBox.setWidth(width);
        ctrlBox.setHeight(height);
    }
    private void handleTimerModeChange() {
        if (timerMode.isSelected()) {
            ctrlBox.setTranslateY(70);
            timerMode.setTranslateX(0);
            setControlVisibility(true);
            setResetVisibility(false);
        } else {
            ctrlBox.setTranslateY(70);
            resizeControlBox(150, 75);
            timerMode.setTranslateX(-20);
            setControlVisibility(false);
            setResetVisibility(true);
        }
        root.requestLayout();
    }

    private void setResetVisibility(boolean state) {
        resetButton.setVisible(state);
        resetButton.setDisable(!state);
    }

    private void setupButtonIndicators(Rectangle box, Polygon button) {
        box.setOnMouseEntered(e -> buttonIndicator(true, button));
        box.setOnMouseExited(e -> buttonIndicator(false, button));
    }
    private void buttonIndicator(boolean mouseEntered, Polygon button) {
        if (mouseEntered) {
            button.setStroke(Color.GREY);
        } else {
            button.setStroke(Color.BLACK);
        }
        root.requestLayout();
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======

    private void setControlVisibility(boolean state) {
        if (state) {
            resizeControlBox(150, 100);
            timerMode.setTranslateY(105);
        }
        else {
            resizeControlBox(150, 75);
            timerMode.setTranslateY(90);
        }
        firstUp.setVisible(state);
        firstUpBox.setDisable(!state);
        firstDown.setVisible(state);
        firstDownBox.setDisable(!state);
        secondUp.setVisible(state);
        secondUpBox.setDisable(!state);
        secondDown.setVisible(state);
        secondDownBox.setDisable(!state);
        thirdUp.setVisible(state);
        thirdUpBox.setDisable(!state);
        thirdDown.setVisible(state);
        thirdDownBox.setDisable(!state);
        fourthUp.setVisible(state);
        fourthUpBox.setDisable(!state);
        fourthDown.setVisible(state);
        fourthDownBox.setDisable(!state);
    }

    private void startTimer() {
        System.out.println("Starting timer");
        setControlVisibility(false);
        timerMode.setDisable(true);

        if (timeline != null) {
            timeline.stop();
            System.out.println("Existing timeline stopped");
        }
        int totalSeconds = getCurrentControlsValue();
        if (totalSeconds <= 0) {
            System.out.println("Total seconds must be greater than zero to start the timer.");
            return;
        }
        timeLeft = Duration.seconds(totalSeconds);

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), e -> {
            timeLeft = timeLeft.subtract(Duration.millis(10));
            double totalMilliseconds = totalSeconds * 1000;
            double currentMillisecondsLeft = timeLeft.toMillis();
            double currentLength = 360 - ((currentMillisecondsLeft / totalMilliseconds) * 360);
            timerCircle.setLength(currentLength);

            if (currentMillisecondsLeft <= 0) {
                timeline.stop();
                timerCircle.setLength(360); // Optionally reset to full circle or clear it
                statusText.setText("START");
                setControlVisibility(true);
                timerMode.setDisable(false);
                System.out.println("Timer reached zero and stopped");
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        System.out.println("Timeline started");
    }

    private void updateTimerDisplay(Duration duration) {
        // Update your clock display based on the duration left
        long totalSeconds = (long) duration.toSeconds();
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        // Update display
        tenHoursField.setText(String.valueOf(hours / 10));
        hoursField.setText(String.valueOf(hours % 10));
        tenMinutesField.setText(String.valueOf(minutes / 10));
        minutesField.setText(String.valueOf(minutes % 10));
        root.requestLayout();
    }

    private void stopTimer() {
        timerMode.setDisable(false);
        if (timerMode.isSelected()) setControlVisibility(true);
        if (timeline != null) {
            timeline.stop(); // Stop the timeline
            timerCircle.setLength(0);
        }

        // Update the UI to show the timer has stopped
        // This could include resetting the text fields or changing the status text, etc.
    }

    private void resetTimerDisplay() {
        // Reset the text fields or labels to the initial state
        tenHoursField.setText("0");
        hoursField.setText("0");
        tenMinutesField.setText("0");
        minutesField.setText("0");
        root.requestLayout();
    }
>>>>>>> Stashed changes
    private int getCurrentControlsValue() {
        int tenHours = Integer.parseInt(tenHoursField.getText())*1000;
        int hours = Integer.parseInt(hoursField.getText())*100;
        int tenMinutes = Integer.parseInt(tenMinutesField.getText());
        int minutes = Integer.parseInt(minutesField.getText());
        return tenHours + hours + tenMinutes + minutes;
    }
}