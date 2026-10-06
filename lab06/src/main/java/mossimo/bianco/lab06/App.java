package mossimo.bianco.lab06;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

// GitHub Link: https://github.com/MossimoB/Semester-3/tree/99311dbf8e365f4057b4aa890e598f2604d7c546/lab05/src/main/java/mossimo/bianco/lab06

public class App extends Application {
    
    @Override
    public void start(Stage primaryStage)
    {
        Pane task1Pane = createTask1();
        Pane task2Pane = createTask2();

        Scene scene = new Scene(task1Pane, 700, 600);

        // Button to switch between tasks
        Button switchButton = new Button("Switch to Task 2");
        switchButton.setLayoutX(550);
        switchButton.setLayoutY(20);

        task1Pane.getChildren().add(switchButton);

        switchButton.setOnAction(e ->
        {
            if (scene.getRoot() == task1Pane)
            {
                scene.setRoot(task2Pane);
                switchButton.setText("Switch to Task 1");
            }
            else
            {
                scene.setRoot(task1Pane);
                switchButton.setText("Switch to Task 2");
            }
        });

        primaryStage.setTitle("JavaFX Lab 14");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ------------------- TASK 1 -------------------

    private Pane createTask1()
    {
        final double SCENE_WIDTH = 700.0;
        final double SCENE_HEIGHT = 600.0;

        final int X1 = 10, Y1 = 10;
        final int X2 = 60, Y2 = 60;
        final int X3 = 110, Y3 = 110;

        final int WIDTH1 = 500, HEIGHT1 = 500;
        final int WIDTH2 = 400, HEIGHT2 = 400;
        final int WIDTH3 = 300, HEIGHT3 = 300;

        final int CENTER_X = 260;
        final int CENTER_Y = 260;
        final int RADIUS = 150;

        Pane pane = new Pane();

        // Create three squares
        Rectangle square1 = new Rectangle(
            X1, Y1, WIDTH1, HEIGHT1
        );

        Rectangle square2 = new Rectangle(
            X2, Y2, WIDTH2, HEIGHT2
        );

        Rectangle square3 = new Rectangle(
            X3, Y3, WIDTH3, HEIGHT3
        );

        square1.setStroke(Color.BLACK);
        square2.setStroke(Color.BLACK);
        square3.setStroke(Color.BLACK);

        square1.setFill(null);
        square2.setFill(null);
        square3.setFill(null);

        // Create four diagonal lines
        Line line1 = new Line(
            X1, Y1, X3, Y3
        );

        Line line2 = new Line(
            X1 + WIDTH1, Y1,
            X3 + WIDTH3, Y3
        );

        Line line3 = new Line(
            X1, Y1 + HEIGHT1,
            X3, Y3 + HEIGHT3
        );

        Line line4 = new Line(
            X1 + WIDTH1,
            Y1 + HEIGHT1,
            X3 + WIDTH3,
            Y3 + HEIGHT3
        );

        line1.setStroke(Color.BLACK);
        line2.setStroke(Color.BLACK);
        line3.setStroke(Color.BLACK);
        line4.setStroke(Color.BLACK);

        // Create circle
        Circle circle = new Circle(
            CENTER_X,
            CENTER_Y,
            RADIUS
        );

        circle.setStroke(Color.BLACK);
        circle.setFill(Color.BLACK);

        // Add shapes
        pane.getChildren().addAll(
            square1,
            square2,
            square3,
            line1,
            line2,
            line3,
            line4,
            circle
        );

        return pane;
    }

// ------------------- TASK 2 -------------------

    private Pane createTask2() {
        
        final double WIDTH = 700;
        final double HEIGHT = 600;

        Pane pane = new Pane();

        // Sky
        Rectangle sky = new Rectangle(
            0, 0, WIDTH, HEIGHT
        );
        sky.setFill(Color.LIGHTBLUE);

        // Grass
        Rectangle grass = new Rectangle(
            0, 470, WIDTH, 130
        );
        grass.setFill(Color.GREEN);
        grass.setStroke(Color.DARKGREEN);
        grass.setStrokeWidth(2);

        // House base
        Rectangle houseBase = new Rectangle(
            150, 280, 400, 250
        );
        houseBase.setFill(Color.LIGHTGRAY);
        houseBase.setStroke(Color.DIMGRAY);
        houseBase.setStrokeWidth(2);

        // Roof
        Polygon roof = new Polygon(
            150, 280,
            350, 90,
            550, 280
        );
        roof.setFill(Color.RED);
        roof.setStroke(Color.RED);
        roof.setStrokeWidth(2);

        // Chimney
        Rectangle chimney = new Rectangle(
            485, 145, 55, 145
        );

        chimney.setFill(Color.DARKGRAY);
        chimney.setStroke(Color.BLACK);
        chimney.setStrokeWidth(3);

        // Chimney top
        Rectangle chimneyTop = new Rectangle(
            478, 140, 69, 12
        );

        // Walkway
        Polygon walkway = new Polygon(
            315, 530,
            385, 530,
            425, 600,
            275, 600
        );

        walkway.setFill(Color.LIGHTGRAY);
        walkway.setStroke(Color.DARKGRAY);
        walkway.setStrokeWidth(2);

        // Left window
        Rectangle window1 = new Rectangle(
            190, 340, 75, 65
        );
        window1.setFill(Color.LIGHTBLUE);
        window1.setStroke(Color.LIGHTBLUE);

        // Right window
        Rectangle window2 = new Rectangle(
            435, 340, 75, 65
        );
        window2.setFill(Color.LIGHTBLUE);
        window2.setStroke(Color.LIGHTBLUE);

        // Left window panes
        Line window1Horizontal = new Line(
            190, 372.5,
            265, 372.5
        );

        Line window1Vertical = new Line(
            227.5, 340,
            227.5, 405
        );

        // Right window panes
        Line window2Horizontal = new Line(
            435, 372.5,
            510, 372.5
        );

        Line window2Vertical = new Line(
            472.5, 340,
            472.5, 405
        );

        window1Horizontal.setStroke(Color.DIMGRAY);
        window1Vertical.setStroke(Color.DIMGRAY);
        window2Horizontal.setStroke(Color.DIMGRAY);
        window2Vertical.setStroke(Color.DIMGRAY);

        window1Horizontal.setStrokeWidth(2);
        window1Vertical.setStrokeWidth(2);
        window2Horizontal.setStrokeWidth(2);
        window2Vertical.setStrokeWidth(2);

        // Door
        Rectangle door = new Rectangle(
            315, 390, 70, 140
        );
        door.setFill(Color.BROWN);
        door.setStroke(Color.BROWN);
        door.setStrokeWidth(2);

        // Sun
        Circle sun = new Circle(
            620, 95, 45
        );
        sun.setFill(Color.YELLOW);
        sun.setStroke(Color.YELLOW);

        // Sun rays
        Line ray1 = new Line(620, 35, 620, 10);
        Line ray2 = new Line(620, 155, 620, 180);
        Line ray3 = new Line(560, 95, 535, 95);
        Line ray4 = new Line(680, 95, 705, 95);

        Line ray5 = new Line(578, 53, 560, 35);
        Line ray6 = new Line(662, 53, 680, 35);
        Line ray7 = new Line(578, 137, 560, 155);
        Line ray8 = new Line(662, 137, 680, 155);

        ray1.setStroke(Color.YELLOW);
        ray2.setStroke(Color.YELLOW);
        ray3.setStroke(Color.YELLOW);
        ray4.setStroke(Color.YELLOW);
        ray5.setStroke(Color.YELLOW);
        ray6.setStroke(Color.YELLOW);
        ray7.setStroke(Color.YELLOW);
        ray8.setStroke(Color.YELLOW);

        ray1.setStrokeWidth(2);
        ray2.setStrokeWidth(2);
        ray3.setStrokeWidth(2);
        ray4.setStrokeWidth(2);
        ray5.setStrokeWidth(2);
        ray6.setStrokeWidth(2);
        ray7.setStrokeWidth(2);
        ray8.setStrokeWidth(2);

        // Add everything to the scene
        pane.getChildren().addAll(
            sky,
            grass,
            chimney,
            houseBase,
            roof,
            window1,
            window2,
            window1Horizontal,
            window1Vertical,
            window2Horizontal,
            window2Vertical,
            chimneyTop,
            walkway,
            door,
            sun,
            ray1,
            ray2,
            ray3,
            ray4,
            ray5,
            ray6,
            ray7,
            ray8
        );

        return pane;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}