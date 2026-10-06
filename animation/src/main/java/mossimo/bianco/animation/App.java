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

public class App extends Application {

    private final double WIDTH = 640;
    private final double HEIGHT = 480;

    private final double LEFT = 120;
    private final double RIGHT = 520;
    private final double TOP = 70;
    private final double BOTTOM = 280;

    private final double SPEED = 100;

    private final boolean EXIT_WHEN_FINISHED = false;

    private Pane topPane;
    private HBox bottomPane;
    private Text statusText;

    private Circle objectA;
    private Polygon objectB;

    private PathTransition pathTransition;
    private SequentialTransition sequentialTransition;
    private ParallelTransition bothAnimations;
    private SequentialTransition fullShow;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        // Main background
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #0f172a, #1e293b);"
        );

        topPane = new Pane();
        topPane.setPrefSize(WIDTH, 360);

        // Animation panel styling
        topPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #f8fafc, #e2e8f0);" +
                "-fx-border-color: #334155;" +
                "-fx-border-width: 0 0 2 0;"
        );

        Path path = createAnimationArea();
        createAnimations(path);

        bottomPane = createButtons();
        bottomPane.setPrefSize(WIDTH, 120);

        // Bottom control panel
        bottomPane.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1e293b, #0f172a);" +
                "-fx-border-color: #475569;" +
                "-fx-border-width: 2 0 0 0;"
        );

        root.setTop(topPane);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Scene-level CSS
        scene.getRoot().setStyle(
                "-fx-font-family: 'Segoe UI';"
        );

        stage.setTitle("JavaFX Path and Sequential Animation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

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

    private Path createAnimationArea() {

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

        // Status pill
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

        // Decorative subtitle
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

        // Rectangle path
        Path path = new Path(
                new MoveTo(LEFT, TOP),
                new LineTo(RIGHT, TOP),
                new LineTo(RIGHT, BOTTOM),
                new LineTo(LEFT, BOTTOM),
                new LineTo(LEFT, TOP)
        );

        path.setFill(null);
        path.setStroke(Color.web("#94a3b8"));
        path.setStrokeWidth(2.5);

        // Dashed path
        path.getStrokeDashArray().addAll(10.0, 8.0);

        // Corner labels
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

        for (Text corner : new Text[]{m, n, p, q}) {
            corner.setFont(Font.font(
                    "Segoe UI",
                    FontWeight.BOLD,
                    15
            ));

            corner.setFill(Color.web("#475569"));
        }

        // Blue moving circle
        objectA = new Circle(
                LEFT,
                TOP,
                15
        );

        objectA.setFill(Color.web("#3b82f6"));

        objectA.setStroke(Color.web("#1d4ed8"));
        objectA.setStrokeWidth(3);

        // Small highlight inside circle
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

        // Animation B title
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

        // Orange triangle
        objectB = new Polygon(
                0, -25,
                22, 20,
                -22, 20
        );

        objectB.setFill(Color.web("#f97316"));

        objectB.setStroke(Color.web("#c2410c"));
        objectB.setStrokeWidth(3);

        objectB.setLayoutX(320);
        objectB.setLayoutY(210);

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

        return path;
    }

    private void createAnimations(Path path) {

        double topSide = RIGHT - LEFT;
        double rightSide = BOTTOM - TOP;
        double perimeter = 2 * topSide + 2 * rightSide;

        Duration timeMN =
                Duration.seconds(topSide / SPEED);

        Duration timeNP =
                Duration.seconds(rightSide / SPEED);

        Duration timePQ = timeMN;
        Duration timeQM = timeNP;

        // Animation A
        pathTransition = new PathTransition(
                Duration.seconds(perimeter / SPEED),
                path,
                objectA
        );

        pathTransition.setInterpolator(
                Interpolator.LINEAR
        );

        // Animation B - Fade
        FadeTransition fade =
                new FadeTransition(
                        timeMN,
                        objectB
                );

        fade.setFromValue(1.0);
        fade.setToValue(0.3);

        fade.setOnFinished(e ->
                statusText.setText(
                        "A at N  •  B scaling"
                )
        );

        // Animation B - Scale
        ScaleTransition scale =
                new ScaleTransition(
                        timeNP,
                        objectB
                );

        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(1.8);
        scale.setToY(1.8);

        scale.setOnFinished(e ->
                statusText.setText(
                        "A at P  •  B rotating"
                )
        );

        // Animation B - Rotate
        RotateTransition rotate =
                new RotateTransition(
                        timePQ,
                        objectB
                );

        rotate.setFromAngle(0);
        rotate.setToAngle(360);

        rotate.setOnFinished(e ->
                statusText.setText(
                        "A at Q  •  B moving up"
                )
        );

        // Animation B - Move
        TranslateTransition moveUp =
                new TranslateTransition(
                        timeQM,
                        objectB
                );

        moveUp.setFromY(0);
        moveUp.setToY(-80);

        moveUp.setOnFinished(e ->
                statusText.setText(
                        "A back at M  •  done"
                )
        );

        sequentialTransition =
                new SequentialTransition(
                        fade,
                        scale,
                        rotate,
                        moveUp
                );

        bothAnimations =
                new ParallelTransition(
                        pathTransition,
                        sequentialTransition
                );

        PauseTransition endDelay =
                new PauseTransition(
                        Duration.seconds(2)
                );

        fullShow =
                new SequentialTransition(
                        bothAnimations,
                        endDelay
                );

        fullShow.setOnFinished(e -> {

            statusText.setText(
                    "Finished  •  Press Reset or Start"
            );

            if (EXIT_WHEN_FINISHED) {
                Platform.exit();
            }
        });
    }

    private HBox createButtons() {

        Button startButton =
                new Button("▶  Start");

        Button resetButton =
                new Button("↻  Reset");

        Button exitButton =
                new Button("✕  Exit");

        // Button sizing
        startButton.setPrefWidth(125);
        resetButton.setPrefWidth(125);
        exitButton.setPrefWidth(125);

        startButton.setPrefHeight(45);
        resetButton.setPrefHeight(45);
        exitButton.setPrefHeight(45);

        // Internal CSS for buttons
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

        // Hover effects
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

        startButton.setOnMouseExited(e ->
                startButton.setStyle(buttonStyle)
        );

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

        resetButton.setOnMouseExited(e ->
                resetButton.setStyle(buttonStyle)
        );

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

        exitButton.setOnMouseExited(e ->
                exitButton.setStyle(buttonStyle)
        );

        // Start
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

        // Reset
        resetButton.setOnAction(e -> {

            fullShow.stop();

            resetObjects();

            statusText.setText(
                    "Reset  •  Press Start"
            );
        });

        // Exit
        exitButton.setOnAction(e -> {

            fullShow.stop();

            Platform.exit();
        });

        HBox box =
                new HBox(
                        20,
                        startButton,
                        resetButton,
                        exitButton
                );

        box.setAlignment(Pos.CENTER);

        box.setPadding(
                new Insets(20, 20, 20, 20)
        );

        return box;
    }

    private void resetObjects() {

        objectA.setTranslateX(0);
        objectA.setTranslateY(0);

        objectB.setOpacity(1.0);

        objectB.setScaleX(1.0);
        objectB.setScaleY(1.0);

        objectB.setRotate(0);

        objectB.setTranslateY(0);
    }

    public static void main(String[] args) {
        launch();
    }
}