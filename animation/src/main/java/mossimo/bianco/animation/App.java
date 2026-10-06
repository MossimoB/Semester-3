// Git repository: https://github.com/YOUR-USERNAME/YOUR-REPO-NAME
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

/**
 * Animation A: PathTransition. A circle travels around the rectangle M-N-P-Q-M.
 * Animation B: SequentialTransition. A triangle fades, scales, rotates, then moves up.
 * Both run inside one ParallelTransition so they start and end at the same time.
 * Each step of B lasts exactly as long as A takes to travel one side.
 */
public class App extends Application {

    private final double WIDTH = 640;
    private final double HEIGHT = 480;

    // corners of the rectangular path
    private final double LEFT = 120;
    private final double RIGHT = 520;
    private final double TOP = 70;
    private final double BOTTOM = 280;

    // speed of object A in pixels per second (same on every side)
    private final double SPEED = 100;

    // change to true if the window should close by itself after the animation
    private final boolean EXIT_WHEN_FINISHED = false;

    private Pane topPane;
    private HBox bottomPane;
    private Text statusText;

    private Circle objectA;
    private Polygon objectB;

    private PathTransition pathTransition;
    private SequentialTransition sequentialTransition;
    private ParallelTransition bothAnimations;
    private SequentialTransition fullShow; // both animations + end delay

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        topPane = new Pane();
        topPane.setPrefSize(WIDTH, 360);
        paintBox(topPane, Color.rgb(244, 244, 244));

        Path path = createAnimationArea();
        createAnimations(path);

        bottomPane = createButtons();
        bottomPane.setPrefSize(WIDTH, 120);
        paintBox(bottomPane, Color.rgb(221, 221, 221));

        root.setTop(topPane);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("JavaFX Path and Sequential Animation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // background color + border without CSS
    private void paintBox(Region box, Color color) {
        box.setBackground(new Background(
                new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
        box.setBorder(new Border(new BorderStroke(Color.rgb(51, 51, 51),
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    private Path createAnimationArea() {
        Text title = new Text(20, 25, "Animation A: Path Transition");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        statusText = new Text(330, 25, "Press Start");
        statusText.setFont(Font.font("Arial", 13));

        // M -> N -> P -> Q -> M
        Path path = new Path(
                new MoveTo(LEFT, TOP),       // M
                new LineTo(RIGHT, TOP),      // N
                new LineTo(RIGHT, BOTTOM),   // P
                new LineTo(LEFT, BOTTOM),    // Q
                new LineTo(LEFT, TOP)        // back to M
        );
        path.setFill(null);
        path.setStroke(Color.GRAY);
        path.getStrokeDashArray().addAll(10.0, 10.0);

        Text m = new Text(LEFT - 20, TOP - 10, "M");
        Text n = new Text(RIGHT + 10, TOP - 10, "N");
        Text p = new Text(RIGHT + 10, BOTTOM + 20, "P");
        Text q = new Text(LEFT - 20, BOTTOM + 20, "Q");

        objectA = new Circle(LEFT, TOP, 15);
        objectA.setFill(Color.DODGERBLUE);

        Text objectBLabel = new Text(20, 345, "Animation B: Sequential Transition");
        objectBLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        objectB = new Polygon(0, -25, 22, 20, -22, 20); // triangle
        objectB.setFill(Color.ORANGE);
        objectB.setLayoutX(320);
        objectB.setLayoutY(210);

        topPane.getChildren().addAll(title, statusText, path, m, n, p, q,
                objectA, objectBLabel, objectB);

        return path;
    }

    private void createAnimations(Path path) {
        double topSide = RIGHT - LEFT;      // M->N and P->Q (400 px)
        double rightSide = BOTTOM - TOP;    // N->P and Q->M (210 px)
        double perimeter = 2 * topSide + 2 * rightSide;

        Duration timeMN = Duration.seconds(topSide / SPEED);   // 4.0 s
        Duration timeNP = Duration.seconds(rightSide / SPEED); // 2.1 s
        Duration timePQ = timeMN;
        Duration timeQM = timeNP;

        // Animation A
        pathTransition = new PathTransition(
                Duration.seconds(perimeter / SPEED), path, objectA);
        // LINEAR = constant speed, so A reaches each corner exactly on time
        pathTransition.setInterpolator(Interpolator.LINEAR);

        // Animation B, one step per side of the rectangle
        FadeTransition fade = new FadeTransition(timeMN, objectB);
        fade.setFromValue(1.0);
        fade.setToValue(0.3);
        fade.setOnFinished(e -> statusText.setText("A at N  |  B scaling"));

        ScaleTransition scale = new ScaleTransition(timeNP, objectB);
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(1.8);
        scale.setToY(1.8);
        scale.setOnFinished(e -> statusText.setText("A at P  |  B rotating"));

        RotateTransition rotate = new RotateTransition(timePQ, objectB);
        rotate.setFromAngle(0);
        rotate.setToAngle(360);
        rotate.setOnFinished(e -> statusText.setText("A at Q  |  B moving up"));

        TranslateTransition moveUp = new TranslateTransition(timeQM, objectB);
        moveUp.setFromY(0);
        moveUp.setToY(-80);
        moveUp.setOnFinished(e -> statusText.setText("A back at M  |  done"));

        sequentialTransition = new SequentialTransition(fade, scale, rotate, moveUp);

        // A and B start and end together
        bothAnimations = new ParallelTransition(pathTransition, sequentialTransition);

        // short delay so the final state stays on screen
        PauseTransition endDelay = new PauseTransition(Duration.seconds(2));

        fullShow = new SequentialTransition(bothAnimations, endDelay);
        fullShow.setOnFinished(e -> {
            statusText.setText("Finished. Press Reset or Start.");
            if (EXIT_WHEN_FINISHED) {
                Platform.exit();
            }
        });
    }

    private HBox createButtons() {
        Button startButton = new Button("Start");
        Button resetButton = new Button("Reset");
        Button exitButton = new Button("Exit");

        startButton.setPrefWidth(100);
        resetButton.setPrefWidth(100);
        exitButton.setPrefWidth(100);

        startButton.setOnAction(e -> {
            if (fullShow.getStatus() != Animation.Status.RUNNING) {
                resetObjects();
                statusText.setText("A: M to N  |  B fading");
                fullShow.playFromStart();
            }
        });

        resetButton.setOnAction(e -> {
            fullShow.stop();
            resetObjects();
            statusText.setText("Reset. Press Start");
        });

        exitButton.setOnAction(e -> {
            fullShow.stop();
            Platform.exit();
        });

        HBox box = new HBox(20, startButton, resetButton, exitButton);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    // puts both objects back to their starting state
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