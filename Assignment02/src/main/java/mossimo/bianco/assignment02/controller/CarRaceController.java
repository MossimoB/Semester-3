package mossimo.bianco.assignment02.controller;

import java.lang.classfile.Label;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import mossimo.bianco.assignment02.model.Car;
import mossimo.bianco.assignment02.model.CarRaceModel;
import mossimo.bianco.assignment02.view.CarShape;
import mossimo.bianco.assignment02.view.RaceTrackView;

/**
 * Controller of the race screen. Handles the Start, Pause and Reset buttons
 * and the speed slider, animates the cars with TranslateTransition and their
 * wheels with RotateTransition, keeps the model's positions up to date,
 * determines the winner and shows the race messages and final results
 *
 * @author Mossimo Bianco
 */
public class CarRaceController {
    
    private enum RaceState {
        READY, RUNNING, PAUSED, FINISHED
    }
    
    private static final long STATUS_REFRESH_NANOS = 150_000_000L;
    
    @FXML
    private RaceTrackView raceTrack;
    @FXML
    private Button startButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Slider speedSlider;
    @FXML
    private Label speedValueLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Label messageTitleLabel;
    @FXML
    private Label messageBodyLabel;
    @FXML
    private VBox resultsBox;
    @FXML
    private Label animationInfoLabel;
    
    private CarRaceModel model;
    private final List<CarShape> carShapes = new ArrayList<>();
    private final Map<Car, TranslateTransition> carMoves = new HashMap<>();
    private final Map<Car, List<RotateTransition>> wheelSpins = new HashMap<>();
    private final Random random = new Random();
    private AnimationTimer progressTracker;
    private RaceState state = RaceState.READY;
    
    /**
     * Called by the FXMLLoader after the FXML fields are injected. Connects
     * the speed slider to the animations
     */
    @FXML
    private void initialize() {
        speedSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            speedValueLabel.setText(String.format("%.2fx", newValue.doubleValue()));
            applySimulationSpeed();
        });
        progressTracker = createProgressTracker();
    }
    
    /**
     * Gives the controller its model, then draws the track and the cars
     *
     * @param model the race model to display and update
     */
    public void setModel(CarRaceModel model) {
        this.model = model;
        raceTrack.drawTrack(model);
        raceTrack.clearCars();
        carShapes.clear();
        for (Car car : model.getCars()) {
            CarShape shape = new CarShape(car);
            carShapes.add(shape);
            raceTrack.addCar(shape);
        }
        buildAnimations();
        showReadyMessage();
        updateControls();
    }
    
    // Button Handlers
    /**
     * Starts the race, or resumes it when it is paused.
     */
    @FXML
    private void handleStart() {
        if (state == RaceState.READY) {
            playAll();
            state = RaceState.RUNNING;
            showMessage("And they're off!",
                    "Five cars, one finish line. Watch the wheels spin and the "
                    + "lead change as each car speeds up and slows down.");
        } else if (state == RaceState.PAUSED) {
            playAll();
            state = RaceState.RUNNING;
            showMessage("Race resumed", "The cars are back on the move.");
        }
        progressTracker.start();
        updateControls();
    }
    
    
}
