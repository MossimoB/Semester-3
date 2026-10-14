// Git repository: ADD_YOUR_GIT_REPOSITORY_URL_HERE

package mossimo.bianco.lab08_paint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private RadioButton blackRadioButton;

    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private Button clearButton;

    @FXML
    private ToggleGroup colorToggleGroup;

    // Must match fx:id="drawingAreaPane" in primary.fxml
    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton greenRadioButton;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton redRadioButton;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private Button undoButton;

    private Color brushColor = Color.BLACK;
    private PenSize brushSize = PenSize.MEDIUM;

    private enum PenSize {
        SMALL(2),
        MEDIUM(4),
        LARGE(6);

        private final double radius;

        PenSize(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }
    }

    @FXML
    void initialize() {
        blackRadioButton.setUserData(Color.BLACK);
        redRadioButton.setUserData(Color.RED);
        greenRadioButton.setUserData(Color.GREEN);
        blueRadioButton.setUserData(Color.BLUE);

        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);

        blackRadioButton.setSelected(true);
        mediumRadioButton.setSelected(true);

        brushColor = Color.BLACK;
        brushSize = PenSize.MEDIUM;
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        // Draw only within the drawing pane.
        double x = event.getX();
        double y = event.getY();

        if (x < 0 || y < 0
                || x > drawingAreaPane.getWidth()
                || y > drawingAreaPane.getHeight()) {
            return;
        }

        Circle circle = new Circle(
            x,
            y,
            brushSize.getRadius(),
            brushColor
        );

        // Prevent drawn circles from intercepting mouse events.
        circle.setMouseTransparent(true);

        drawingAreaPane.getChildren().add(circle);
    }

    @FXML
    void colorRadioButtonSelected(ActionEvent event) {
        if (colorToggleGroup.getSelectedToggle() != null) {
            brushColor = (Color)
                colorToggleGroup.getSelectedToggle().getUserData();
        }
    }

    @FXML
    void sizeRadioButtonSelected(ActionEvent event) {
        if (sizeToggleGroup.getSelectedToggle() != null) {
            brushSize = (PenSize)
                sizeToggleGroup.getSelectedToggle().getUserData();
        }
    }

    @FXML
    void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();

        if (count > 0) {
            drawingAreaPane.getChildren().remove(count - 1);
        }
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
}