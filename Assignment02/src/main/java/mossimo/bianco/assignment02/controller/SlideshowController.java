package mossimo.bianco.assignment02.controller;

import java.util.List;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import mossimo.bianco.assignment02.model.Car;
import mossimo.bianco.assignment02.model.CarRaceModel;

/**
 * Controller of the intro slideshow. Shows one car picture at a time with a
 * greeting, switches to the next picture automatically after a delay, plays
 * background music with a MediaPlayer, and tells the application to open the
 * race screen when the slideshow ends (or when the user skips it)
 *
 * @author Mossimo Bianco
 */
public class SlideshowController {
    
    private static final Duration SLIDE_DELAY = Duration.seconds(2.5);
    private static final Duration FADE_TIME = Duration.millis(600);
    private static final double MUSIC_VOLUME = 0.6;
    
    @FXML
    private ImageView carImageView;
    @FXML
    private Label carNameLabel;
    @FXML
    private Label carDetailsLabel;
    @FXML
    private Label slideCounterLabel;
    @FXML
    private HBox dotsBox;

    private List<Car> cars;
    private Runnable onFinished;
    private Timeline slideTimer;
    private MediaPlayer musicPlayer;
    private int currentSlide;
    private boolean finished;
    
    /**
     * Starts the slideshow and the music
     *
     * @param model      the race model holding the cars to present
     * @param onFinished action to run when the slideshow is over
     */
    public void startSlideshow(CarRaceModel model, Runnable onFinished) {
        
    }
    
}
