package mossimo.bianco.assignment02.controller;

import java.net.URL;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import mossimo.bianco.assignment02.App;
import mossimo.bianco.assignment02.model.Car;
import mossimo.bianco.assignment02.model.CarRaceModel;

/**
 * Controller of the intro slideshow. Shows one car picture at a time with a
 * greeting, switches to the next picture automatically after a delay, plays
 * background music with a MediaPlayer, and tells the application to open the
 * race screen when the slideshow ends (or when the user skips it).
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
     * Starts the slideshow and the music.
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

    /**
     * Displays the picture and information of one car with a fade-in.
     */
    private void showSlide(int index) {
        Car car = cars.get(index);
        URL imageUrl = App.class.getResource("images/car" + car.getNumber() + ".png");
        if (imageUrl != null) {
            carImageView.setImage(new Image(imageUrl.toExternalForm()));
        }
        carNameLabel.setText("Car #" + car.getNumber() + "  -  " + car.getType().getDisplayName());
        carDetailsLabel.setText("Color: " + car.getColorName() + "   |   Lane " + (index + 1));
        slideCounterLabel.setText("Racer " + (index + 1) + " of " + cars.size());

        for (int i = 0; i < dotsBox.getChildren().size(); i++) {
            dotsBox.getChildren().get(i).getStyleClass().remove("active");
            if (i == index) {
                dotsBox.getChildren().get(i).getStyleClass().add("active");
            }
        }

        FadeTransition fadeIn = new FadeTransition(FADE_TIME, carImageView);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
    }

    /**
     * Plays the intro music in a loop. If the system cannot play audio, the
     * slideshow simply continues without sound.
     */
    private void playMusic() {
        URL soundUrl = App.class.getResource("sounds/intro.wav");
        if (soundUrl == null) {
            return;
        }
        try {
            musicPlayer = new MediaPlayer(new Media(soundUrl.toExternalForm()));
            musicPlayer.setVolume(MUSIC_VOLUME);
            musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            musicPlayer.play();
        } catch (MediaException ex) {
            System.err.println("Could not play intro music: " + ex.getMessage());
            musicPlayer = null;
        }
    }

    /**
     * Skips the rest of the slideshow.
     */
    @FXML
    private void handleSkip() {
        finishSlideshow();
    }

    /**
     * Stops the timer and the music, then opens the race screen. Runs only
     * once even if the user clicks Skip at the same moment the timer ends.
     */
    private void finishSlideshow() {
        if (finished) {
            return;
        }
        finished = true;
        slideTimer.stop();
        if (musicPlayer != null) {
            musicPlayer.stop();
            musicPlayer.dispose();
        }
        onFinished.run();
    }
}
