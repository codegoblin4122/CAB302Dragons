package com.example.cab302;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class that launches the JavaFX application.
 * This class is responsible for initializing and displaying the primary stage
 * with its associated scene.
 */
public class Main extends Application {

    /** Title of our Project. */
    public static final String TITLE = "CAB302 Dragons";

    /** Width of the app window. */
    public static final int WIDTH = 1200;

    /** Height of the app window. */
    public static final int HEIGHT = 800;

    /**
     * Starts the JavaFX application by setting up the stage and scene.
     * Loads the FXML layout from the specified resource and sets the scene on the primary stage.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The launch method is called to start the Screen Timer.
     *
     * @param args the command line arguments passed to the application.
     *             An application may get these parameters using the getParameters() method.
     */
    public static void main(String[] args) {
        launch();
    }
}
