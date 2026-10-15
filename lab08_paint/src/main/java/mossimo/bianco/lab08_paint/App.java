
// Git repository: https://github.com/YOUR_USERNAME/YOUR_REPOSITORY

package mossimo.bianco.lab08_paint;

import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                getClass().getResource("primary.fxml")
        );

        Scene scene = new Scene(root);

        stage.setTitle("Painter");
        stage.setScene(scene);
        stage.show();
    }
    
public static class PainterController {

    private enum PenSize {
        SMALL(2.0),
        MEDIUM(4.0),
        LARGE(7.0);

        private final double radius;

        PenSize(double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }
    }

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private ToggleGroup colorToggleGroup;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton blackRadioButton;

    @FXML
    private RadioButton redRadioButton;

    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private RadioButton greenRadioButton;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton largeRadioButton;

    private Color brushColor = Color.BLACK;

    private PenSize brushSize = PenSize.MEDIUM;

    @FXML
    private void initialize() {
        blackRadioButton.setUserData(Color.BLACK);
        redRadioButton.setUserData(Color.RED);
        blueRadioButton.setUserData(Color.BLUE);
        greenRadioButton.setUserData(Color.GREEN);

        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE);
    }

    @FXML
    private void drawingAreaMouseDragged(MouseEvent event) {
        Circle circle = new Circle(
                event.getX(),
                event.getY(),
                brushSize.getRadius(),
                brushColor
        );

        drawingAreaPane.getChildren().add(circle);
    }

    @FXML
    private void colorRadioButtonSelected(ActionEvent event) {
        Object selectedData =
                colorToggleGroup.getSelectedToggle().getUserData();

        brushColor = (Color) selectedData;
    }

    @FXML
    private void sizeRadioButtonSelected(ActionEvent event) {
        Object selectedData =
                sizeToggleGroup.getSelectedToggle().getUserData();

        brushSize = (PenSize) selectedData;
    }

    @FXML
    private void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();

        if (count > 0) {
            drawingAreaPane.getChildren().remove(count - 1);
        }
    }

    @FXML
    private void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
}


    public static void main(String[] args) {
        launch(args);
    }
}
