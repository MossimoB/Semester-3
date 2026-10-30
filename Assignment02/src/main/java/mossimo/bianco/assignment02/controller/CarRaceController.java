package mossimo.bianco.assignment02.controller;

import java.lang.classfile.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import mossimo.bianco.assignment02.model.CarRaceModel;
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
    
    
}
