package com.example.activitytracker;
import com.jfoenix.svg.SVGGlyph;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.jfoenix.controls.JFXButton;

import java.util.Objects;

public class RingTimerApplication extends Application {
    private StackPane root;
    private Scene scene;
    private Circle innerCircle, timerCircle, outerCircle, topDot, bottomDot;
    private Arc arcTimer;
    private Text statusText;
    private Polygon firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown;
    private Text tenHoursField, hoursField, tenMinutesField, minutesField ;

    private Rectangle ctrlBox, firstUpBox, secondUpBox, thirdUpBox, fourthUpBox, firstDownBox, secondDownBox, thirdDownBox, fourthDownBox;
    private Timeline timeline;
    private Duration timeLeft;
    private CheckBox timerMode;
    private ImageView resetButton;

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

        setupButtonIndicators(firstUpBox, firstUp);
        setupButtonIndicators(firstDownBox, firstDown);
        setupButtonIndicators(secondUpBox, secondUp);
        setupButtonIndicators(secondDownBox, secondDown);
        setupButtonIndicators(thirdUpBox, thirdUp);
        setupButtonIndicators(thirdDownBox, thirdDown);
        setupButtonIndicators(fourthUpBox, fourthUp);
        setupButtonIndicators(fourthDownBox, fourthDown);

        timerMode.setOnAction(e -> handleTimerModeChange());
    }



    private void initStackPane() {
        // Create the UI components
        this.root = new StackPane();
        this.scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com.example.activitytracker/mainView.css")).toExternalForm());

        // Start/stop button & outer ring
        this.innerCircle = new Circle(150, Color.rgb(20,20,20));
        this.outerCircle = new Circle(155, Color.TRANSPARENT);
        timerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setStroke(Color.GREEN);
        outerCircle.setStrokeWidth(10);
        innerCircle.setVisible(false);
        timerCircle.setStroke(Color.RED);
        timerCircle.setStrokeWidth(10);

        arcTimer = new Arc(
                innerCircle.getCenterX(),
                innerCircle.getCenterY(),
                innerCircle.getRadius(),
                innerCircle.getRadius(),
                90, // Start at 90 degrees (top of the circle)
                0 // Length of 0 to start with
        );
        arcTimer.setType(ArcType.OPEN);
        arcTimer.setStroke(Color.RED);
        arcTimer.setStrokeWidth(10);
        arcTimer.setFill(null); // Arc is only outlined, not filled
        arcTimer.setCenterX(0);
        arcTimer.setCenterY(0);

        // Status text (start/stop)
        this.statusText = new Text("START");
        statusText.setId("bold-text");
        statusText.setTranslateY(-60);
        statusText.setFill(Color.WHITE);
        statusText.setStroke(Color.LIGHTGRAY);

        // Control box
        this.ctrlBox = new Rectangle(150,100);
        ctrlBox.setFill(Color.WHITE);
        ctrlBox.setStroke(Color.LIGHTGRAY);
        ctrlBox.setStrokeWidth(4);
        ctrlBox.setTranslateY(70);
        ctrlBox.setArcHeight(15);
        ctrlBox.setArcWidth(15);

        // Control box buttons and output
        timerMode = new CheckBox("Timer mode");
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
        timerMode.setTranslateY(105);
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

        Image resetImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com.example.activitytracker/png/reset_small.png")));
        resetButton = new ImageView(resetImg);
        resetButton.setTranslateY(92);
        resetButton.setTranslateX(55);


        handleTimerModeChange();





        // Add everything to the stack pane
        root.getChildren().addAll(outerCircle, timerCircle, innerCircle, statusText,
                ctrlBox, firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown,
                topDot, bottomDot, tenHoursField, tenMinutesField, hoursField, minutesField,
                firstUpBox, secondUpBox, thirdUpBox, fourthUpBox, firstDownBox, secondDownBox, thirdDownBox, fourthDownBox,
                timerMode, resetButton, arcTimer);
    }

    private void handleTimerAnimation(int totalSeconds) {
        // Create a timeline for the animation
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000 / 60.0), // Update at 60 FPS for smoothness
                event -> {
                    double elapsedSeconds = Duration.millis(timeline.getCurrentTime().toMillis()).toSeconds();
                    double length = 360 * elapsedSeconds / totalSeconds;
                    arcTimer.setLength(-length); // Negative length to move clockwise
                }
        ));

        // Set the total duration of the timeline to the total seconds of the timer
        timeline.setCycleCount((int) (totalSeconds * 60)); // Run at 60 FPS for the number of seconds
        timeline.play();
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
    }

    private void toggleClock() {
        if (statusText.getText().equals("START")) {
            statusText.setText("STOP");
            startTimer();

        } else {
            statusText.setText("START");
            stopTimer();
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
        triangleButton.setStroke(Color.BLACK);
        triangleButton.setStrokeWidth(2);
        return triangleButton;
    }

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
        setControlVisibility(false);
        timerMode.setDisable(true);
        if (!timerMode.isSelected()) {
            // Indefinite mode, count up
            if (timeline != null) {
                timeline.play(); // Stop the timeline
                return;
            }
            timeLeft = Duration.seconds(0);
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                // This block is called every second
                timeLeft = timeLeft.add(Duration.seconds(1));
                updateTimerDisplay(timeLeft);
            }));
        } else {
            // Timer mode, count down
            int totalSeconds = getCurrentControlsValue(); // Gets the total time in seconds
            handleTimerAnimation(totalSeconds);
            timeLeft = Duration.seconds(totalSeconds);
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                // This block is called every second
                timeLeft = timeLeft.subtract(Duration.seconds(1));
                updateTimerDisplay(timeLeft);
                if (timeLeft.lessThanOrEqualTo(Duration.ZERO)) {
                    timeline.stop();
                    // Handle completion of timer, e.g., play a sound or show an alert
                    statusText.setText("START");
                    setControlVisibility(true);
                }
            }));
        }
        timeline.setCycleCount(Timeline.INDEFINITE); // Continue until we manually stop it
        timeline.play();
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
        arcTimer.setLength(0); // Reset the arc length
        if (timerMode.isSelected()) setControlVisibility(true);
        if (timeline != null) {
            timeline.stop(); // Stop the timeline
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
    private int getCurrentControlsValue() {
        int tenHours = Integer.parseInt(tenHoursField.getText()) * 10 * 3600;
        int hours = Integer.parseInt(hoursField.getText()) * 3600;
        int tenMinutes = Integer.parseInt(tenMinutesField.getText()) * 10 * 60;
        int minutes = Integer.parseInt(minutesField.getText()) * 60;
        return tenHours + hours + tenMinutes + minutes; // Return value in seconds
    }
}
