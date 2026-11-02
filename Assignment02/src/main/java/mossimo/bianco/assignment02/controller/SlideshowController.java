package mossimo.bianco.assignment02.controller;

import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
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
                this.cars = model.getCars();
        this.onFinished = onFinished;

        for (int i = 0; i < cars.size(); i++) {
            Circle dot = new Circle(5);
            dot.getStyleClass().add("slide-dot");
            dotsBox.getChildren().add(dot);
        }

        currentSlide = 0;
        showSlide(currentSlide);

        // Every SLIDE_DELAY, move to the next car; after the last car, finish
        slideTimer = new Timeline(new KeyFrame(SLIDE_DELAY, event -> nextSlide()));
        slideTimer.setCycleCount(Animation.INDEFINITE);
        slideTimer.play();

        playMusic();
    }
    
    /**
     * Moves to the next car, or ends the slideshow after the last one.
     */
    private void nextSlide() {
        currentSlide++;
        if (currentSlide < cars.size()) {
            showSlide(currentSlide);
        } else {
            finishSlideshow();
        }
    }
}
