package mossimo.bianco.lab09;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorChooserController {

    @FXML
    private Slider redSlider;

    @FXML
    private Slider greenSlider;

    @FXML
    private Slider blueSlider;

    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField redTextField;

    @FXML
    private TextField greenTextField;

    @FXML
    private TextField blueTextField;

    @FXML
    private TextField alphaTextField;

    @FXML
    private Rectangle colorRectangle;

    private int red;
    private int green;
    private int blue;
    private double alpha;

    @FXML
    public void initialize() {

        redTextField.textProperty().bind(
                redSlider.valueProperty().asString("%.0f"));

        greenTextField.textProperty().bind(
                greenSlider.valueProperty().asString("%.0f"));

        blueTextField.textProperty().bind(
                blueSlider.valueProperty().asString("%.0f"));

        alphaTextField.textProperty().bind(
                alphaSlider.valueProperty().asString("%.2f"));

        ChangeListener<Number> colorListener = (observable, oldValue, newValue) -> {
            red = (int) redSlider.getValue();
            green = (int) greenSlider.getValue();
            blue = (int) blueSlider.getValue();
            alpha = alphaSlider.getValue();

            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
        };

        redSlider.valueProperty().addListener(colorListener);
        greenSlider.valueProperty().addListener(colorListener);
        blueSlider.valueProperty().addListener(colorListener);
        alphaSlider.valueProperty().addListener(colorListener);

        red = (int) redSlider.getValue();
        green = (int) greenSlider.getValue();
        blue = (int) blueSlider.getValue();
        alpha = alphaSlider.getValue();

        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
    }
}