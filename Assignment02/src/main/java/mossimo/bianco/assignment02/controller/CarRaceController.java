package mossimo.bianco.assignment02.controller;

import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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
    
    /**
     * Pauses every car and wheel animation
     */
    @FXML
    private void handlePause() {
        if (state != RaceState.RUNNING) {
            return;
        }
        for (TranslateTransition move : carMoves.values()) {
            move.pause();
        }
        for (List<RotateTransition> spins : wheelSpins.values()) {
            for (RotateTransition spin : spins) {
                spin.pause();
            }
        }
        progressTracker.stop();
        state = RaceState.PAUSED;
        statusLabel.setText("Paused  |  Leader: " + describeShort(model.getLeader()));
        showMessage("Race paused", "Press Resume to continue or Reset to start over.");
        updateControls();
    }
    
    /**
     * Stops the race and puts everything back to its initial state with new
     * random speeds
     */
    @FXML
    private void handleReset() {
        stopAll();
        progressTracker.stop();
        model.reset();
        for (CarShape shape : carShapes) {
            shape.setTranslateX(0);
            shape.setHighlighted(false);
            for (Node wheel : shape.getWheels()) {
                wheel.setRotate(0);
            }
        }
        buildAnimations();
        state = RaceState.READY;
        showReadyMessage();
        updateControls();
    }
    
    // Animations
    /**
     * Creates a TranslateTransition for each car (start line to finish line)
     * and an endless RotateTransition for each wheel. The duration of each
     * transition comes from the car's speed in the model.
     */
    private void buildAnimations() {
        carMoves.clear();
        wheelSpins.clear();

        for (CarShape shape : carShapes) {
            Car car = shape.getCar();

            TranslateTransition move = new TranslateTransition(
                    Duration.seconds(model.getRaceTime(car)), shape);
            move.setFromX(0);
            move.setToX(model.getRaceDistance());
            move.setInterpolator(createRacingInterpolator());
            move.setOnFinished(event -> handleCarFinished(shape));
            carMoves.put(car, move);

            // One full turn of the wheel covers its circumference
            double secondsPerTurn = 2 * Math.PI * CarShape.WHEEL_RADIUS / car.getSpeed();
            List<RotateTransition> spins = new ArrayList<>();
            for (Node wheel : shape.getWheels()) {
                RotateTransition spin = new RotateTransition(Duration.seconds(secondsPerTurn), wheel);
                spin.setByAngle(360);
                spin.setCycleCount(Animation.INDEFINITE);
                spin.setInterpolator(Interpolator.LINEAR);
                spins.add(spin);
            }
            wheelSpins.put(car, spins);
        }
        applySimulationSpeed();
    }
    
    /**
     * Creates a random spline interpolator. Every car still finishes in the
     * time given by its speed, but some cars start fast and fade while others
     * start slow and finish strong, so the lead changes during the race.
     */
    private Interpolator createRacingInterpolator() {
        double earlyPace = 0.10 + random.nextDouble() * 0.35;
        double latePace = 0.55 + random.nextDouble() * 0.35;
        return Interpolator.SPLINE(0.25, earlyPace, 0.75, latePace);
    }
    
    /**
     * Plays (or resumes) every car and wheel animation that has not finished.
     */
    private void playAll() {
        applySimulationSpeed();
        for (CarShape shape : carShapes) {
            Car car = shape.getCar();
            if (car.isFinished()) {
                continue;
            }
            carMoves.get(car).play();
            for (RotateTransition spin : wheelSpins.get(car)) {
                spin.play();
            }
        }
    }
    
    /**
     * Stops every car and wheel animation
     */
    private void stopAll() {
        for (TranslateTransition move : carMoves.values()) {
            move.stop();
        }
        for (List<RotateTransition> spins : wheelSpins.values()) {
            for (RotateTransition spin : spins) {
                spin.stop();
            }
        }
    }
    
    /**
     * Applies the slider value as the playback rate of every animation
     */
    private void applySimulationSpeed() {
        double rate = speedSlider.getValue();
        for (TranslateTransition move : carMoves.values()) {
            move.setRate(rate);
        }
        for (List<RotateTransition> spins : wheelSpins.values()) {
            for (RotateTransition spin : spins) {
                spin.setRate(rate);
            }
        }
    }
    
    /**
     * Creates a timer that runs every frame while the race is running
     * It copies each car's position into the model and updates the status bar
     * with the current leader
     */
    private AnimationTimer createProgressTracker() {
        return new AnimationTimer() {
            private long lastStatusUpdate;

            @Override
            public void handle(long now) {
                for (CarShape shape : carShapes) {
                    if (!shape.getCar().isFinished()) {
                        model.updatePosition(shape.getCar(), shape.getTranslateX());
                    }
                }
                if (now - lastStatusUpdate > STATUS_REFRESH_NANOS) {
                    lastStatusUpdate = now;
                    Car leader = model.getLeader();
                    statusLabel.setText(String.format("Leader: %s  |  %d%% of the track",
                            describeShort(leader), Math.round(model.getProgress(leader) * 100)));
                }
            }
        };
    }
    
    // Race Results
    /**
     * Called when a car's TranslateTransition ends, which means its front
     * bumper reached the finish line. The first car to arrive is the winner.
     */
    private void handleCarFinished(CarShape shape) {
        Car car = shape.getCar();
        model.updatePosition(car, shape.getTranslateX());
        int place = model.recordFinish(car);
        for (RotateTransition spin : wheelSpins.get(car)) {
            spin.stop();
        }

        if (place == 1) {
            shape.setHighlighted(true);
            showMessage("Car #" + car.getNumber() + " crosses the line first!",
                    "The " + car.getDescription() + " takes the win. "
                    + "Waiting for the rest of the field...");
        }
        addResultRow(car);

        if (model.isRaceOver()) {
            progressTracker.stop();
            state = RaceState.FINISHED;
            showFinalResults();
            updateControls();
        }
    }
}
