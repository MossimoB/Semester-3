package mossimo.bianco.assignment02;

import java.io.IOException;
import java.io.UncheckedIOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mossimo.bianco.assignment02.controller.CarRaceController;
import mossimo.bianco.assignment02.controller.SlideshowController;
import mossimo.bianco.assignment02.model.CarRaceModel;

/**
 * Car Race Simulator (Assignment 02). Starts with a slideshow of the cars,
 * then opens the race simulation. The application follows the MVC pattern:
 * CarRaceModel holds the data, the FXML files plus CarShape and RaceTrackView
 * form the view, and CarRaceController / SlideshowController handle the logic.
 *
 * @author Mossimo Bianco
 */
public class App extends Application {

    private static final String STYLESHEET = "styles.css";

    private Stage stage;
    private CarRaceModel model;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        model = new CarRaceModel();
        stage.setTitle("Car Race Simulator - Mossimo Bianco");
        stage.setResizable(false);
        showSlideshow();
        stage.show();
    }

    /**
     * Loads the slideshow screen and starts it. When it ends, the race screen
     * is shown.
     */
    private void showSlideshow() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("SlideshowFXML.fxml"));
        Parent root = load(loader);
        stage.setScene(createScene(root));
        SlideshowController controller = loader.getController();
        controller.startSlideshow(model, this::showRace);
    }

    /**
     * Loads the race screen and gives the model to its controller.
     */
    private void showRace() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("CarRaceFXML.fxml"));
        Parent root = load(loader);
        CarRaceController controller = loader.getController();
        controller.setModel(model);
        stage.setScene(createScene(root));
        stage.sizeToScene();
        stage.centerOnScreen();
    }

    /**
     * Creates a scene with the application's stylesheet.
     */
    private Scene createScene(Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().add(App.class.getResource(STYLESHEET).toExternalForm());
        return scene;
    }

    /**
     * Loads an FXML file, turning the checked IOException into an unchecked
     * one because the files are part of the application.
     */
    private static Parent load(FXMLLoader loader) {
        try {
            return loader.load();
        } catch (IOException ex) {
            throw new UncheckedIOException("Could not load " + loader.getLocation(), ex);
        }
    }

    /**
     * Launches the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
