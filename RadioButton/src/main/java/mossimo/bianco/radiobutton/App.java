package mossimo.bianco.radiobutton;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // radio buttons
        RadioButton r1 = new RadioButton("Red");
        RadioButton r2 = new RadioButton("Blue");
        RadioButton r3 = new RadioButton("Green");

        // extra information attached to each RadioButton
        r1.setUserData("You selected Red");
        r2.setUserData("You selected Blue");
        r3.setUserData("You selected Green");

        // create ToggleGroup
        ToggleGroup group = new ToggleGroup();

        // add RadioButtons to ToggleGroup
        r1.setToggleGroup(group);
        r2.setToggleGroup(group);
        r3.setToggleGroup(group);

        // select one initially
        r1.setSelected(true);

        // Label for RadioButton result
        Label radioLabel = new Label("You selected Red");

        // listen for changes to selected RadioButton
        group.selectedToggleProperty().addListener(
            (observable, oldToggle, newToggle) -> {

                if (newToggle != null) {
                    radioLabel.setText(
                        newToggle.getUserData().toString()
                    );
                }
            }
        );

// -----------------------------------------------------------------------------

        // checkboxes
        CheckBox cb1 = new CheckBox("Email Alerts");
        CheckBox cb2 = new CheckBox("SMS Alerts");
        CheckBox cb3 = new CheckBox("Push Notifications");

        // listen for CheckBox changes
        cb1.selectedProperty().addListener(
            (observable, wasSelected, isNowSelected) -> {

                if (isNowSelected) {
                    System.out.println("Email Alerts enabled");
                } else {
                    System.out.println("Email Alerts disabled");
                }
            }
        );

        // example of checking if selected
        if (cb1.isSelected()) {
            System.out.println("Email Alerts is selected");
        }

// -----------------------------------------------------------------------------

        // slider
        Slider slider = new Slider(0.0, 50.0, 25.0);

        // explicitly set horizontal orientation
        slider.setOrientation(Orientation.HORIZONTAL);

        // show tick marks
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        slider.setMajorTickUnit(10);

        // Label for Slider
        Label sliderLabel = new Label("Slider value: 25");

        // listen for Slider value changes
        slider.valueProperty().addListener(
            (observable, oldValue, newValue) -> {

                sliderLabel.setText(
                    "Slider value: " + String.format("%.1f", newValue.doubleValue())
                );

                System.out.println(
                    "Slider changed from "
                    + oldValue
                    + " to "
                    + newValue
                );
            }
        );

// -----------------------------------------------------------------------------
        
        // text area
        TextArea textArea = new TextArea("This is the initial text.");

        // set preferred size
        textArea.setPrefColumnCount(20);
        textArea.setPrefRowCount(5);

        // enable text wrapping
        textArea.setWrapText(true);

// -----------------------------------------------------------------------------

        // layout
        VBox root = new VBox(
            10,
            r1,
            r2,
            r3,
            radioLabel,
            cb1,
            cb2,
            cb3,
            slider,
            sliderLabel,
            textArea
        );

        Scene scene = new Scene(root, 400, 500);

        stage.setTitle("JavaFX Advanced Controls");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}