// Git repository: https://github.com/MossimoB/Semester-3/blob/main/animation/src/main/java/mossimo/bianco/animation/App.java
package mossimo.bianco.animation;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

// Main JavaFX application
public class App extends Application {

    // Size of the window
    private final double WIDTH = 640;
    private final double HEIGHT = 480;

    // Coordinates of the rectangle's four corners
    private final double LEFT = 120;
    private final double RIGHT = 520;
    private final double TOP = 70;
    private final double BOTTOM = 280;

    // Speed of the circle moving around the path
    private final double SPEED = 100;

    // Determines whether the application closes after the animation
    private final boolean EXIT_WHEN_FINISHED = false;

    // Main UI containers and status text
    private Pane topPane;
    private HBox bottomPane;
    private Text statusText;

    // The two animated objects
    private Circle objectA;
    private Polygon objectB;

    // Animation objects
    private PathTransition pathTransition;
    private SequentialTransition sequentialTransition;
    private ParallelTransition bothAnimations;
    private SequentialTransition fullShow;

    @Override
    public void start(Stage stage) {

        // Main layout containing the animation area and buttons
        BorderPane root = new BorderPane();

        // Main background
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #0f172a, #1e293b);"
        );

        // Create the top animation area
        topPane = new Pane();
        topPane.setPrefSize(WIDTH, 360);

        // Style the animation area
        topPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #f8fafc, #e2e8f0);" +
                "-fx-border-color: #334155;" +
                "-fx-border-width: 0 0 2 0;"
        );

        // Create the path and animations
        Path path = createAnimationArea();
        createAnimations(path);

        // Create the bottom button area
        bottomPane = createButtons();
        bottomPane.setPrefSize(WIDTH, 120);

        // Style the button area
        bottomPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1e293b, #0f172a);" +
                "-fx-border-color: #475569;" +
                "-fx-border-width: 2 0 0 0;"
        );

        // Put the animation and buttons into the BorderPane
        root.setTop(topPane);
        root.setBottom(bottomPane);

        // Create the scene
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Set the default font
        scene.getRoot().setStyle(
                "-fx-font-family: 'Segoe UI';"
        );

        // Configure and display the window
        stage.setTitle("JavaFX Path and Sequential Animation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // Adds a background and border to a JavaFX region
    private void paintBox(Region box, Color color) {
        box.setBackground(new Background(
                new BackgroundFill(
                        color,
                        new CornerRadii(12),
                        Insets.EMPTY
                )
        ));

        box.setBorder(new Border(
                new BorderStroke(
                        Color.rgb(51, 51, 51),
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(12),
                        BorderWidths.DEFAULT
                )
        ));
    }

    // Creates the visual objects shown in the animation area
    private Path createAnimationArea() {

        // Title for Animation A
        Text title = new Text(
                20,
                30,
                "PATH TRANSITION"
        );

        title.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                17
        ));

        title.setFill(Color.web("#1e293b"));

        // Text that tells the user what the animation is doing
        statusText = new Text(
                390,
                30,
                "Press Start"
        );

        statusText.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                13
        ));

        statusText.setFill(Color.web("#2563eb"));

        // Small description under the title
        Text subtitle = new Text(
                20,
                52,
                "Animation A • Rectangle Path"
        );

        subtitle.setFont(Font.font(
                "Segoe UI",
                FontWeight.NORMAL,
                12
        ));

        subtitle.setFill(Color.web("#64748b"));

        // Create the rectangular path: M -> N -> P -> Q -> M
        Path path = new Path(
                new MoveTo(LEFT, TOP),
                new LineTo(RIGHT, TOP),
                new LineTo(RIGHT, BOTTOM),
                new LineTo(LEFT, BOTTOM),
                new LineTo(LEFT, TOP)
        );

        // Make the path visible but not filled
        path.setFill(null);
        path.setStroke(Color.web("#94a3b8"));
        path.setStrokeWidth(2.5);

        // Make the path dashed
        path.getStrokeDashArray().addAll(10.0, 8.0);

        // Labels for the four corners
        Text m = new Text(
                LEFT - 25,
                TOP - 10,
                "M"
        );

        Text n = new Text(
                RIGHT + 12,
                TOP - 10,
                "N"
        );

        Text p = new Text(
                RIGHT + 12,
                BOTTOM + 22,
                "P"
        );

        Text q = new Text(
                LEFT - 25,
                BOTTOM + 22,
                "Q"
        );

        // Apply the same font styling to all corner labels
        for (Text corner : new Text[]{m, n, p, q}) {
            corner.setFont(Font.font(
                    "Segoe UI",
                    FontWeight.BOLD,
                    15
            ));

            corner.setFill(Color.web("#475569"));
        }

        // Circle used for Animation A
        objectA = new Circle(
                LEFT,
                TOP,
                15
        );

        objectA.setFill(Color.web("#3b82f6"));

        objectA.setStroke(Color.web("#1d4ed8"));
        objectA.setStrokeWidth(3);

        // Small white highlight on the circle
        Circle circleHighlight = new Circle(
                LEFT - 5,
                TOP - 5,
                4
        );

        circleHighlight.setFill(Color.rgb(
                255,
                255,
                255,
                0.7
        ));

        // Title for Animation B
        Text objectBLabel = new Text(
                20,
                345,
                "SEQUENTIAL TRANSITION"
        );

        objectBLabel.setFont(Font.font(
                "Segoe UI",
                FontWeight.BOLD,
                17
        ));

        objectBLabel.setFill(Color.web("#1e293b"));

        // Triangle used for Animation B
        objectB = new Polygon(
                0, -25,
                22, 20,
                -22, 20
        );

        objectB.setFill(Color.web("#f97316"));

        objectB.setStroke(Color.web("#c2410c"));
        objectB.setStrokeWidth(3);

        // Starting position of the triangle
        objectB.setLayoutX(320);
        objectB.setLayoutY(210);

        // Add all visual elements to the animation pane
        topPane.getChildren().addAll(
                title,
                subtitle,
                statusText,
                path,
                m,
                n,
                p,
                q,
                objectA,
                circleHighlight,
                objectBLabel,
                objectB
        );

        // Return the path so it can be used by PathTransition
        return path;
    }

    // Creates and connects all of the animations
    private void createAnimations(Path path) {

        // Calculate the lengths of the rectangle sides
        double topSide = RIGHT - LEFT;
        double rightSide = BOTTOM - TOP;
        double perimeter = 2 * topSide + 2 * rightSide;

        // Calculate how long the circle takes to travel each side
        Duration timeMN =
                Duration.seconds(topSide / SPEED);

        Duration timeNP =
                Duration.seconds(rightSide / SPEED);

        Duration timePQ = timeMN;
        Duration timeQM = timeNP;

        // Animation A: move the circle around the rectangle
        pathTransition = new PathTransition(
                Duration.seconds(perimeter / SPEED),
                path,
                objectA
        );

        // Linear interpolation keeps the circle moving at a constant speed
        pathTransition.setInterpolator(
                Interpolator.LINEAR
        );

        // Animation B, step 1: fade the triangle
        FadeTransition fade =
                new FadeTransition(
                        timeMN,
                        objectB
                );

        fade.setFromValue(1.0);
        fade.setToValue(0.3);

        // Update the status after fading
        fade.setOnFinished(e ->
                statusText.setText(
                        "A at N  •  B scaling"
                )
        );

        // Animation B, step 2: make the triangle larger
        ScaleTransition scale =
                new ScaleTransition(
                        timeNP,
                        objectB
                );

        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(1.8);
        scale.setToY(1.8);

        // Update the status after scaling
        scale.setOnFinished(e ->
                statusText.setText(
                        "A at P  •  B rotating"
                )
        );

        // Animation B, step 3: rotate the triangle
        RotateTransition rotate =
                new RotateTransition(
                        timePQ,
                        objectB
                );

        rotate.setFromAngle(0);
        rotate.setToAngle(360);

        // Update the status after rotating
        rotate.setOnFinished(e ->
                statusText.setText(
                        "A at Q  •  B moving up"
                )
        );

        // Animation B, step 4: move the triangle upward
        TranslateTransition moveUp =
                new TranslateTransition(
                        timeQM,
                        objectB
                );

        moveUp.setFromY(0);
        moveUp.setToY(-80);

        // Update the status when the movement is complete
        moveUp.setOnFinished(e ->
                statusText.setText(
                        "A back at M  •  done"
                )
        );

        // Run the four triangle animations one after another
        sequentialTransition =
                new SequentialTransition(
                        fade,
                        scale,
                        rotate,
                        moveUp
                );

        // Run Animation A and Animation B at the same time
        bothAnimations =
                new ParallelTransition(
                        pathTransition,
                        sequentialTransition
                );

        // Wait two seconds after both animations finish
        PauseTransition endDelay =
                new PauseTransition(
                        Duration.seconds(2)
                );

        // Play the animations first, then the delay
        fullShow =
                new SequentialTransition(
                        bothAnimations,
                        endDelay
                );

        // What happens when everything is finished
        fullShow.setOnFinished(e -> {

            statusText.setText(
                    "Finished  •  Press Reset or Start"
            );

            // Close the program if this option is enabled
            if (EXIT_WHEN_FINISHED) {
                Platform.exit();
            }
        });
    }

    // Creates the Start, Reset, and Exit buttons
    private HBox createButtons() {

        Button startButton =
                new Button("▶  Start");

        Button resetButton =
                new Button("↻  Reset");

        Button exitButton =
                new Button("✕  Exit");

        // Set the button sizes
        startButton.setPrefWidth(125);
        resetButton.setPrefWidth(125);
        exitButton.setPrefWidth(125);

        startButton.setPrefHeight(45);
        resetButton.setPrefHeight(45);
        exitButton.setPrefHeight(45);

        // Basic button styling using inline CSS
        String buttonStyle =
                "-fx-background-color: #334155;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #64748b;" +
                "-fx-border-width: 1;" +
                "-fx-cursor: hand;";

        startButton.setStyle(buttonStyle);
        resetButton.setStyle(buttonStyle);
        exitButton.setStyle(buttonStyle);

        // Change Start button appearance when the mouse is over it
        startButton.setOnMouseEntered(e ->
                startButton.setStyle(
                        "-fx-background-color: #2563eb;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #60a5fa;" +
                        "-fx-border-width: 2;" +
                        "-fx-cursor: hand;"
                )
        );

        // Return Start button to its normal style
        startButton.setOnMouseExited(e ->
                startButton.setStyle(buttonStyle)
        );

        // Change Reset button appearance when hovered
        resetButton.setOnMouseEntered(e ->
                resetButton.setStyle(
                        "-fx-background-color: #7c3aed;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #a78bfa;" +
                        "-fx-border-width: 2;" +
                        "-fx-cursor: hand;"
                )
        );

        // Return Reset button to its normal style
        resetButton.setOnMouseExited(e ->
                resetButton.setStyle(buttonStyle)
        );

        // Change Exit button appearance when hovered
        exitButton.setOnMouseEntered(e ->
                exitButton.setStyle(
                        "-fx-background-color: #dc2626;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #f87171;" +
                        "-fx-border-width: 2;" +
                        "-fx-cursor: hand;"
                )
        );

        // Return Exit button to its normal style
        exitButton.setOnMouseExited(e ->
                exitButton.setStyle(buttonStyle)
        );

        // Start the animation from the beginning
        startButton.setOnAction(e -> {

            if (fullShow.getStatus() !=
                    Animation.Status.RUNNING) {

                resetObjects();

                statusText.setText(
                        "A: M → N  •  B fading"
                );

                fullShow.playFromStart();
            }
        });

        // Stop the animation and restore the starting positions
        resetButton.setOnAction(e -> {

            fullShow.stop();

            resetObjects();

            statusText.setText(
                    "Reset  •  Press Start"
            );
        });

        // Stop the animation and close the application
        exitButton.setOnAction(e -> {

            fullShow.stop();

            Platform.exit();
        });

        // Place the buttons horizontally with spacing
        HBox box =
                new HBox(
                        20,
                        startButton,
                        resetButton,
                        exitButton
                );

        // Center the buttons
        box.setAlignment(Pos.CENTER);

        // Add space around the buttons
        box.setPadding(
                new Insets(20, 20, 20, 20)
        );

        return box;
    }

    // Returns both animated objects to their original state
    private void resetObjects() {

        // Reset circle position
        objectA.setTranslateX(0);
        objectA.setTranslateY(0);

        // Reset triangle opacity
        objectB.setOpacity(1.0);

        // Reset triangle size
        objectB.setScaleX(1.0);
        objectB.setScaleY(1.0);

        // Reset triangle rotation
        objectB.setRotate(0);

        // Reset triangle vertical movement
        objectB.setTranslateY(0);
    }

    // Program entry point
    public static void main(String[] args) {
        launch();
    }
}