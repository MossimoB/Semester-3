package mossimo.bianco.lesson01gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        // First row
        Button bPct = new Button("%");
        Button bCE  = new Button("CE");
        Button bC   = new Button("C");
        Button bDiv = new Button("/");

        // Second row
        Button b7 = new Button("7");
        Button b8 = new Button("8");
        Button b9 = new Button("9");
        Button bMul = new Button("*");

        // Third row
        Button b4 = new Button("4");
        Button b5 = new Button("5");
        Button b6 = new Button("6");
        Button bSub = new Button("-");

        // Fourth row
        Button b1 = new Button("1");
        Button b2 = new Button("2");
        Button b3 = new Button("3");
        Button bAdd = new Button("+");

        // Fifth row
        Button b0 = new Button("0");
        Button bDot = new Button(".");
        Button bEq = new Button("=");
        Button bPlusMinus = new Button("+/-");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        // Row 0
        grid.add(bPct, 0, 0);
        grid.add(bCE,  1, 0);
        grid.add(bC,   2, 0);
        grid.add(bDiv, 3, 0);
        
        // Row 1
        grid.add(b7,   0, 1);
        grid.add(b8,   1, 1);
        grid.add(b9,   2, 1);
        grid.add(bMul, 3, 1);
        // Row 2
        grid.add(b4,   0, 2);
        grid.add(b5,   1, 2);
        grid.add(b6,   2, 2);
        grid.add(bSub, 3, 2);
        
        // Row 3
        grid.add(b1,   0, 3);
        grid.add(b2,   1, 3);
        grid.add(b3,   2, 3);
        grid.add(bAdd, 3, 3);
        
        // Row 4
        grid.add(bPlusMinus, 0, 4);
        grid.add(b0,   1, 4);
        grid.add(bDot, 2, 4);
        grid.add(bEq,  3, 4);

        // Make buttons fill their cells
        grid.getChildren().forEach(n ->
            GridPane.setFillWidth(n, true));

        // Create a scene and add the label
        Scene scene = new Scene(grid, 300, 400);     // Pos.CENTER centers both axes
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}