package com.example.cab302;

import com.example.cab302.model.SessionManager;
import com.example.cab302.model.SqliteTrackingDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;



public class RingTimer {
    private final boolean debug = false;
    private StackPane root;
    private Circle innerCircle;
    private Arc timerCircle;

    private Text statusText;
    private Polygon firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown;
    private Text tenHoursField, hoursField, tenMinutesField, minutesField ;

    private Rectangle ctrlBox, firstUpBox, secondUpBox, thirdUpBox, fourthUpBox, firstDownBox, secondDownBox, thirdDownBox, fourthDownBox, resetBox;
    private Timeline timeline;
    private Duration timeLeft;
    private CheckBox timerMode;
    private ImageView resetButton;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private final SqliteTrackingDAO timerUsageDAO;
    private final SessionManager sessionManager;

    public RingTimer() {
        this.timerUsageDAO = new SqliteTrackingDAO();
        this.sessionManager = SessionManager.getInstance();
        initStackPane();
        initEventHandlers();
    }

    private void initEventHandlers() {
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
        resetBox.setOnMouseClicked(e -> resetButtonClick());

        setupButtonIndicators(firstUpBox, firstUp);
        setupButtonIndicators(firstDownBox, firstDown);
        setupButtonIndicators(secondUpBox, secondUp);
        setupButtonIndicators(secondDownBox, secondDown);
        setupButtonIndicators(thirdUpBox, thirdUp);
        setupButtonIndicators(thirdDownBox, thirdDown);
        setupButtonIndicators(fourthUpBox, fourthUp);
        setupButtonIndicators(fourthDownBox, fourthDown);
        setupResetIndicator();

        timerMode.setOnAction(e -> handleTimerModeChange());
    }

    private void initStackPane() {
        // Create the UI components
        this.root = new StackPane();
        // Start/stop button & outer ring
        this.innerCircle = new Circle(150, Color.rgb(20,20,20));
        Circle outerCircle = new Circle(155, Color.TRANSPARENT);
        outerCircle.setStroke(Color.GREEN);
        outerCircle.setStrokeWidth(10);
        root.setPadding(new Insets(20));

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
        Pane arcPane = new StackPane(centeredArcGroup);
        arcPane.setPadding(new Insets(INSET));
        arcPane.setMinSize(0,0);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(arcPane);

        Font boldText = new Font("Roboto", 60);
        // Status text (start/stop)
        this.statusText = new Text("START");
        statusText.setId("bold-text");
        statusText.setFont(boldText);
        statusText.setTranslateY(-60);
        statusText.setFill(Color.WHITE);
        statusText.setStroke(Color.LIGHTGRAY);
        statusText.setStrokeWidth(2);

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
        Circle topDot = new Circle(3, Color.BLACK);
        Circle bottomDot = new Circle(3, Color.BLACK);
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
        this.resetBox = new Rectangle(25, 25, Color.TRANSPARENT);
        resetBox.setTranslateY(91);
        resetBox.setTranslateX(55);
        resetBox.setArcWidth(20);
        resetBox.setArcHeight(20);

        Font controlsText = new Font("Roboto", 40);
        this.tenHoursField = new Text("0");
        this.tenMinutesField = new Text("0");
        this.hoursField = new Text("0");
        this.minutesField = new Text("0");
        tenHoursField.setFont(controlsText);
        tenMinutesField.setFont(controlsText);
        hoursField.setFont(controlsText);
        minutesField.setFont(controlsText);
        tenHoursField.setTranslateY(58);
        tenMinutesField.setTranslateY(58);
        hoursField.setTranslateY(58);
        minutesField.setTranslateY(58);
        tenHoursField.setTranslateX(-50);
        tenMinutesField.setTranslateX(20);
        hoursField.setTranslateX(-20);
        minutesField.setTranslateX(50);

        Image resetImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/cab302/png/reset_small.png")));
        resetButton = new ImageView(resetImg);
        resetButton.setTranslateY(92);
        resetButton.setTranslateX(55);

        handleTimerModeChange();

        // Add everything to the stack pane
        root.getChildren().addAll(outerCircle, borderPane, innerCircle, statusText,
                ctrlBox, firstUp, secondUp, thirdUp, fourthUp, firstDown, secondDown, thirdDown, fourthDown,
                topDot, bottomDot, tenHoursField, tenMinutesField, hoursField, minutesField,
                firstUpBox, secondUpBox, thirdUpBox, fourthUpBox, firstDownBox, secondDownBox, thirdDownBox, fourthDownBox,
                timerMode, resetButton, resetBox);
    }

    private void resizeControlBox(double height) {
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
            resizeControlBox(75);
            timerMode.setTranslateX(-20);
            setControlVisibility(false);
            setResetVisibility(true);
            resetTimerDisplay(); // Reset the timer display when switching to !timerMode
        }
        root.requestLayout();
    }

    private void setResetVisibility(boolean state) {
        resetButton.setVisible(state);
        resetButton.setDisable(!state);
        resetBox.setVisible(state);
        resetBox.setDisable(!state);
    }

    private void setupButtonIndicators(Rectangle box, Polygon button) {
        box.setOnMouseEntered(e -> buttonIndicator(true, button));
        box.setOnMouseExited(e -> buttonIndicator(false, button));
    }

    private void setupResetIndicator() {
        resetBox.setOnMouseEntered(e -> resetBox.setFill(Color.rgb(105, 105, 105, 0.2)));
        resetBox.setOnMouseExited(e -> resetBox.setFill(Color.TRANSPARENT));
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
            startTimer();
        } else {
            stopTimer();
        }
        root.requestLayout();
    }

    // Inc or dec the first field in clock controls
    private void incrementControls(boolean inc, int field) {
        if (debug) System.out.println(String.valueOf(inc) + " inc for field: " + String.valueOf(field));
        int currentValue;
        switch(field) {
            case 1:
                currentValue = Integer.parseInt(tenHoursField.getText());
                if (inc && currentValue < 9) {
                    this.tenHoursField.setText(String.valueOf(currentValue + 1));
                } else if (!inc && currentValue > 0) {
                    this.tenHoursField.setText(String.valueOf(currentValue - 1));
                }
                break;
            case 2:
                currentValue = Integer.parseInt(hoursField.getText());
                if (inc && currentValue < 9) {
                    this.hoursField.setText(String.valueOf(currentValue + 1));
                } else if (!inc && currentValue > 0) {
                    this.hoursField.setText(String.valueOf(currentValue - 1));
                }
                break;
            case 3:
                currentValue = Integer.parseInt(tenMinutesField.getText());
                if (inc && currentValue < 9) {
                    this.tenMinutesField.setText(String.valueOf(currentValue + 1));
                } else if (!inc && currentValue > 0) {
                    this.tenMinutesField.setText(String.valueOf(currentValue - 1));
                }
                break;
            case 4:
                currentValue = Integer.parseInt(minutesField.getText());
                if (inc && currentValue < 9) {
                    this.minutesField.setText(String.valueOf(currentValue + 1));
                } else if (!inc && currentValue > 0) {
                    this.minutesField.setText(String.valueOf(currentValue - 1));
                }
                break;
            default:
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
            resizeControlBox(100);
            timerMode.setTranslateY(105);
        } else {
            resizeControlBox(75);
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

    public void startTimer() {
        statusText.setText("STOP");
        if (debug) System.out.println("Starting timer");
        setControlVisibility(false);
        timerMode.setDisable(true);

        if (sessionManager.isLoggedIn()) startTime = LocalDateTime.now();

        // Ensure that timeline is initialized here before using it.
        if (timeline != null) {
            timeline.stop();
            if (debug) System.out.println("Existing timeline stopped");
        }
        timeline = new Timeline();  // Ensure timeline is initialized before any possible use

        int totalSeconds = getCurrentControlsValue();

        if (!timerMode.isSelected()) {
            if (debug) System.out.println("Timer mode: Indefinite (count up)");
            if (timeLeft == null) {
                timeLeft = Duration.seconds(0);
            }
            timerCircle.setLength(360); // Set arc to full circle when counting up starts
            KeyFrame keyFrame = new KeyFrame(Duration.millis(10), e -> {
                timeLeft = timeLeft.add(Duration.millis(10));
                updateTimerDisplay(timeLeft); // Display the current elapsed time
            });
            timeline.getKeyFrames().add(keyFrame);
        } else {
            if (totalSeconds <= 0) {
                if (debug) System.out.println("Total seconds must be greater than zero to start the timer.");
                timerMode.setDisable(false);
                setControlVisibility(true);
                return;
            }
            timeLeft = Duration.seconds(totalSeconds);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(10), e -> {
                timeLeft = timeLeft.subtract(Duration.millis(10));
                double totalMilliseconds = totalSeconds * 1000;
                double currentMillisecondsLeft = timeLeft.toMillis();
                double currentLength = 360 - ((currentMillisecondsLeft / totalMilliseconds) * 360);
                timerCircle.setLength(currentLength);

                if (currentMillisecondsLeft <= 0) {
                    timeline.stop();
                    timerCircle.setLength(0);
                    statusText.setText("START");
                    setControlVisibility(true);
                    timerMode.setDisable(false);
                    if (debug) System.out.println("Timer reached zero and stopped");
                }
                updateTimerDisplay(timeLeft);
            });
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        if (debug) System.out.println("Timeline started");
    }

    private void updateTimerDisplay(Duration duration) {
        // Update your clock display based on the duration left
        long totalSeconds = (long) duration.toSeconds();
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;

        // Update display
        tenHoursField.setText(String.valueOf(hours / 10));
        hoursField.setText(String.valueOf(hours % 10));
        tenMinutesField.setText(String.valueOf(minutes / 10));
        minutesField.setText(String.valueOf(minutes % 10));
        root.requestLayout();
    }

    public void stopTimer() {
        statusText.setText("START");
        if (timeline != null) {
            timeline.stop(); // Stop the timeline regardless of the timer mode
        }
        timerCircle.setLength(0); // Clear the arc when countdown stops
        if (timerMode.isSelected()) {
            timeLeft = Duration.ZERO; // Reset time to 0
            updateTimerDisplay(timeLeft); // Update display to show 0
        }

        if (sessionManager.isLoggedIn()) {
            endTime = LocalDateTime.now();
            logTimerUsage();
        }
        statusText.setText("START");
        setControlVisibility(timerMode.isSelected());
        timerMode.setDisable(false);
        if (debug) System.out.println("Timer stopped");
    }

    private void logTimerUsage() {
        String email = sessionManager.getCurrentUserEmail();
        Timestamp startTimestamp = Timestamp.valueOf(startTime);
        Timestamp endTimestamp = Timestamp.valueOf(endTime);
        try {
            timerUsageDAO.addTimerUsage(email, startTimestamp, endTimestamp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resetTimerDisplay() {
        // Reset the text fields or labels to the initial state
        tenHoursField.setText("0");
        hoursField.setText("0");
        tenMinutesField.setText("0");
        minutesField.setText("0");
        root.requestLayout();
    }

    private void resetButtonClick() {
        if (timerMode.isSelected()) return;
        if (statusText.getText().equals("STOP")) {
            stopTimer();
        }
        timeLeft = Duration.ZERO;
        updateTimerDisplay(timeLeft);
        if (debug) System.out.println("Timer reset to zero");
    }

    private int getCurrentControlsValue() {
        int tenHours = Integer.parseInt(tenHoursField.getText()) * 10 * 3600;
        int hours = Integer.parseInt(hoursField.getText()) * 3600;
        int tenMinutes = Integer.parseInt(tenMinutesField.getText()) * 10 * 60;
        int minutes = Integer.parseInt(minutesField.getText()) * 60;
        return tenHours + hours + tenMinutes + minutes; // Return value in seconds
    }

    public StackPane getRoot() {
        return root;
    }

    public boolean getRunning() {
        return statusText.getText().equals("STOP");
    }
}
