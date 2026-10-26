package mossimo.bianco.lab08_paint;

<<<<<<< HEAD
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
=======
import javafx.application.Application;
>>>>>>> 8f20526f568a14d6910c9d8517ecaeb9658e245b
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
<<<<<<< HEAD
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
=======

import java.io.IOException;
>>>>>>> 8f20526f568a14d6910c9d8517ecaeb9658e245b

public class App extends Application {

    private static Scene scene;

    @Override
<<<<<<< HEAD
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("primary"));

        stage.setTitle("Painter");
=======
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
>>>>>>> 8f20526f568a14d6910c9d8517ecaeb9658e245b
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
<<<<<<< HEAD
        return FXMLLoader.load(App.class.getResource(fxml + ".fxml"));
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
=======
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
>>>>>>> 8f20526f568a14d6910c9d8517ecaeb9658e245b
