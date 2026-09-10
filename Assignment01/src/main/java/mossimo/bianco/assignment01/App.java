package mossimo.bianco.assignment01;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Typing Tutor
 *
 * A simple typing tutor application with:
 * - Virtual keyboard
 * - Physical keyboard input
 * - Shift support
 * - Backspace support
 * - Correct/incorrect keystroke tracking
 * - Accuracy tracking
 * - Next and Reset controls
 */
public class App extends Application {
    // Sample typing exercises
    private final String[] texts = {
        "Try typing this text. Do it as quickly and as accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog.",
        "Five big quacking zephyrs jolt my wax bed.",
        "Sympathizing would fix Quaker objectives.",
        "A large fawn jumped quickly over the white zinc boxes."
    };

    // Current exercise
    private int currentTextIndex = 0;

    // Accuracy tracking
    private int correctKeyStrokes = 0;
    private int incorrectKeyStrokes = 0;

    // UI
    private Label expectedTextLabel;
    private TextField responseField;
    private Label progressLabel;
    private Label pressedKeyLabel;
    private Label correctLabel;
    private Label incorrectLabel;
    private Label accuracyLabel;
    private Label statusLabel;
    private Button nextButton;
    private Button resetButton;

    // Virtual keyboard
    private final Map<KeyCode, Button> virtualKeys = new HashMap<>();

    // Shift state
    private boolean shiftPressed = false;

    // Accent colour
    private static final String ACCENT_COLOR = "#7CFF6B";

    @Override
    public void start(Stage stage) {
        expectedTextLabel = new Label();
        expectedTextLabel.setWrapText(true);
        expectedTextLabel.setMaxWidth(1050);
        expectedTextLabel.setStyle(
            "-fx-font-size: 25px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #F5F5F5;"
        );

        responseField = new TextField();
        responseField.setEditable(false);
        responseField.setFocusTraversable(false);
        responseField.setPrefWidth(900);
        responseField.setPrefHeight(52);
        responseField.setStyle(
            "-fx-font-size: 19px; " +
            "-fx-text-fill: #181818; " +
            "-fx-padding: 8px 16px; " +
            "-fx-background-color: white; " +
            "-fx-border-color: white; " +
            "-fx-border-radius: 10px; " +
            "-fx-background-radius: 10px;"
        );

        progressLabel = new Label();
        progressLabel.setStyle(
            "-fx-font-size: 13px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: #858585;"
        );

        pressedKeyLabel = new Label("Last key pressed: ");
        pressedKeyLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-text-fill: #FFFFFF;"
        );

        correctLabel = new Label();
        correctLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-text-fill: #FFFFFF;"
        );

        incorrectLabel = new Label();
        incorrectLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-text-fill: #FFFFFF;"
        );

        accuracyLabel = new Label();
        accuracyLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-text-fill: #FFFFFF;"
        );

        HBox statistics = new HBox(35);
        statistics.setAlignment(Pos.CENTER);
        statistics.getChildren().addAll(
            correctLabel,
            incorrectLabel,
            accuracyLabel
        );
        
        statusLabel = new Label();
        statusLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-text-fill: " + ACCENT_COLOR + ";"
        );

        nextButton = new Button("Next");
        nextButton.setFocusTraversable(false);
        nextButton.setPrefWidth(105);
        nextButton.setPrefHeight(38);
        nextButton.setStyle(
            "-fx-background-color: " + ACCENT_COLOR + "; " +
            "-fx-text-fill: #181818; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 8px; " +
            "-fx-cursor: hand;"
        );
        nextButton.setOnAction(e -> nextText());

        resetButton = new Button("Reset");
        resetButton.setFocusTraversable(false);
        resetButton.setPrefWidth(105);
        resetButton.setPrefHeight(38);
        resetButton.setStyle(
            "-fx-background-color: #2A2A2A; " +
            "-fx-text-fill: #FFFFFF; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-border-color: #444444; " +
            "-fx-border-width: 1px; " +
            "-fx-border-radius: 8px; " +
            "-fx-background-radius: 8px; " +
            "-fx-cursor: hand;"
        );
        resetButton.setOnAction(e -> reset());

        HBox controls = new HBox(10);

        controls.setAlignment(Pos.CENTER);
        controls.getChildren().addAll(
            nextButton,
            resetButton
        );

        updateDisplay();

        VBox root = new VBox(14);

        root.setPadding(new Insets(25, 35, 25, 35));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #181818;");
        root.setFocusTraversable(true);

        // Typing area
        VBox typingArea = new VBox(10);

        typingArea.setAlignment(Pos.CENTER);
        typingArea.getChildren().addAll(
            expectedTextLabel,
            responseField,
            progressLabel
        );
        
        // Add everything
        root.getChildren().addAll(
            typingArea,
            statistics,
            pressedKeyLabel,
            statusLabel,
            controls,
            createKeyboard()
        );

        Scene scene = new Scene(root, 1200, 600);

        // Physical keyboard - key pressed
        scene.setOnKeyPressed(e -> {
            handleKeyPressed(e.getCode());
        });

        // Physical keyboard - key released
        scene.setOnKeyReleased(e -> {
            handleKeyReleased(e.getCode());
        });

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        // Make sure the application receives keyboard input
        root.requestFocus();
    }

    /**
     * Updates all information displayed by the application.
     */
    private void updateDisplay() {
        expectedTextLabel.setText(
            texts[currentTextIndex]
        );

        progressLabel.setText(
            (currentTextIndex + 1) + " of " + texts.length
        );

        correctLabel.setText(
            "Correct: " + correctKeyStrokes
        );

        incorrectLabel.setText(
            "Incorrect: " + incorrectKeyStrokes
        );

        int totalKeyStrokes =
            correctKeyStrokes + incorrectKeyStrokes;
        
        if (totalKeyStrokes == 0) {
            accuracyLabel.setText(
                "Accuracy: 0%"
            );
        } else {
            double accuracy =
                (double) correctKeyStrokes
                / totalKeyStrokes
                * 100;

            accuracyLabel.setText(
                String.format(
                    "Accuracy: %.1f%%",
                    accuracy
                )
            );
        }

        // Completion indicator
        if (
            responseField.getText().length()
            == texts[currentTextIndex].length()
        ) {
            statusLabel.setText("Complete!");
        } else {
            statusLabel.setText("");
        }
    }

    /**
     * Creates the virtual keyboard.
     *
     * @return the virtual keyboard layout
     */
    private VBox createKeyboard() {
        VBox keyboard = new VBox(6);
        keyboard.setAlignment(Pos.CENTER);

        HBox topRow = new HBox(6);
        topRow.setAlignment(Pos.CENTER);
        
        HBox middleRow = new HBox(6);
        middleRow.setAlignment(Pos.CENTER);

        HBox bottomRow = new HBox(6);
        bottomRow.setAlignment(Pos.CENTER);
        
        HBox spaceRow = new HBox(6);
        spaceRow.setAlignment(Pos.CENTER);

        addKey(topRow, "Q", KeyCode.Q);
        addKey(topRow, "W", KeyCode.W);
        addKey(topRow, "E", KeyCode.E);
        addKey(topRow, "R", KeyCode.R);
        addKey(topRow, "T", KeyCode.T);
        addKey(topRow, "Y", KeyCode.Y);
        addKey(topRow, "U", KeyCode.U);
        addKey(topRow, "I", KeyCode.I);
        addKey(topRow, "O", KeyCode.O);
        addKey(topRow, "P", KeyCode.P);
        addKey(topRow, "Backspace", KeyCode.BACK_SPACE);

        addKey(middleRow, "A", KeyCode.A);
        addKey(middleRow, "S", KeyCode.S);
        addKey(middleRow, "D", KeyCode.D);
        addKey(middleRow, "F", KeyCode.F);
        addKey(middleRow, "G", KeyCode.G);
        addKey(middleRow, "H", KeyCode.H);
        addKey(middleRow, "J", KeyCode.J);
        addKey(middleRow, "K", KeyCode.K);
        addKey(middleRow, "L", KeyCode.L);

        addKey(bottomRow, "Shift", KeyCode.SHIFT);
        addKey(bottomRow, "Z", KeyCode.Z);
        addKey(bottomRow, "X", KeyCode.X);
        addKey(bottomRow, "C", KeyCode.C);
        addKey(bottomRow, "V", KeyCode.V);
        addKey(bottomRow, "B", KeyCode.B);
        addKey(bottomRow, "N", KeyCode.N);
        addKey(bottomRow, "M", KeyCode.M);
        addKey(bottomRow, ".", KeyCode.PERIOD);
        
        addKey(spaceRow, "Space", KeyCode.SPACE);

        keyboard.getChildren().addAll(
            topRow,
            middleRow,
            bottomRow,
            spaceRow
        );

        return keyboard;
    }

    /**
     * Creates a virtual keyboard button.
     *
     * @param row the row where the key belongs
     * @param text the text displayed on the key
     * @param keyCode the physical key represented
     */
    private void addKey(
        HBox row,
        String text,
        KeyCode keyCode
    ) {
        Button button = new Button(text);

        if (keyCode == KeyCode.BACK_SPACE) {
            button.setPrefWidth(100);
        } else if (keyCode == KeyCode.SPACE) {
            button.setPrefWidth(400);
        } else if (keyCode == KeyCode.SHIFT) {
            button.setPrefWidth(100);
        } else {
            button.setPrefWidth(50);
        }

        button.setPrefHeight(42);
        button.setMinHeight(42);
        button.setMaxHeight(42);
        button.setFocusTraversable(false);
        button.setStyle(
            "-fx-background-color: #F2F2F2; " +
            "-fx-text-fill: #181818; " +
            "-fx-font-size: 13px; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 7px; " +
            "-fx-border-radius: 7px; " +
            "-fx-border-color: #D6D6D6; " +
            "-fx-border-width: 1px; " +
            "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e -> {
            if (!button.getStyle().contains("#555555")) {
                button.setStyle(
                    "-fx-background-color: #FFFFFF; " +
                    "-fx-text-fill: #181818; " +
                    "-fx-font-size: 13px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-background-radius: 7px; " +
                    "-fx-border-radius: 7px; " +
                    "-fx-border-color: #FFFFFF; " +
                    "-fx-border-width: 1px; " +
                    "-fx-cursor: hand;"
                );
            }
        });

        button.setOnMouseExited(e -> {
            if (!button.getStyle().contains("#555555")) {
                button.setStyle(
                    "-fx-background-color: #F2F2F2; " +
                    "-fx-text-fill: #181818; " +
                    "-fx-font-size: 13px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-background-radius: 7px; " +
                    "-fx-border-radius: 7px; " +
                    "-fx-border-color: #D6D6D6; " +
                    "-fx-border-width: 1px; " +
                    "-fx-cursor: hand;"
                );
            }
        });

        virtualKeys.put(keyCode, button);

        row.getChildren().add(button);
    }

    /**
     * Handles a physical key press.
     *
     * @param keyCode the key pressed
     */
    private void handleKeyPressed(KeyCode keyCode) {
        // ENTER = NEXT
        if (keyCode == KeyCode.ENTER) {
            nextText();
            return;
        }
        
        Button virtualKey =
            virtualKeys.get(keyCode);

        if (virtualKey != null) {
            // Highlight pressed key
            virtualKey.setStyle(
                "-fx-background-color: " + ACCENT_COLOR + "; " +
                "-fx-text-fill: #181818; " +
                "-fx-font-size: 13px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 7px; " +
                "-fx-border-radius: 7px; " +
                "-fx-border-color: " + ACCENT_COLOR + "; " +
                "-fx-border-width: 1px;"
            );

            pressedKeyLabel.setText(
                "Last key pressed: " + keyCode
            );

            pressedKeyLabel.setStyle(
                "-fx-font-size: 14px; " +
                "-fx-text-fill: #FFFFFF;"
            );

            // SHIFT
            if (keyCode == KeyCode.SHIFT) {
                shiftPressed = true;
                return;
            }

            // BACKSPACE
            if (keyCode == KeyCode.BACK_SPACE) {
                handleBackspace();
                return;
            }

            String character =
                getCharacter(keyCode);

            if (character != null) {
                addCharacter(character);
            }
        } else {
            // Unsupported key
            pressedKeyLabel.setText(
                "Key not handled!"
            );

            pressedKeyLabel.setStyle(
                "-fx-text-fill: red; " +
                "-fx-font-size: 14px;"
            );
        }
    }

    /**
     * Handles a physical key release.
     *
     * @param keyCode the key released
     */
    private void handleKeyReleased(KeyCode keyCode) {
        Button virtualKey =
            virtualKeys.get(keyCode);

        if (virtualKey != null) {
            virtualKey.setStyle(
                "-fx-background-color: #F2F2F2; " +
                "-fx-text-fill: #181818; " +
                "-fx-font-size: 13px; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 7px; " +
                "-fx-border-radius: 7px; " +
                "-fx-border-color: #D6D6D6; " +
                "-fx-border-width: 1px; " +
                "-fx-cursor: hand;"
            );
        }

        if (keyCode == KeyCode.SHIFT) {
            shiftPressed = false;
        }
    }

    /**
     * Converts a physical key into a character.
     *
     * @param keyCode the physical keyboard key
     * @return the corresponding character
     */
    private String getCharacter(KeyCode keyCode) {
        if (keyCode == KeyCode.SPACE) {
            return " ";
        }

        if (keyCode == KeyCode.PERIOD) {
            return ".";
        }

        if (keyCode.isLetterKey()) {
            String character =
                keyCode.toString().toLowerCase();
            if (shiftPressed) {
                return character.toUpperCase();
            }

            return character;
        }

        return null;
    }

    /**
     * Adds a character to the response field and checks accuracy
     *
     * @param character the character typed
     */
    private void addCharacter(String character) {
        String currentResponse =
            responseField.getText();

        int position =
            currentResponse.length();

        if (position < texts[currentTextIndex].length()) {
            char expectedCharacter =
                texts[currentTextIndex].charAt(position);
            if (character.charAt(0) == expectedCharacter) {
                correctKeyStrokes++;
            } else {
                incorrectKeyStrokes++;
            }

            responseField.appendText(character);

            updateDisplay();
        }
    }

    /**
     * Removes the last character from the response
     */
    private void handleBackspace() {
        String currentResponse =
            responseField.getText();

        if (!currentResponse.isEmpty()) {
            responseField.deleteText(
                currentResponse.length() - 1,
                currentResponse.length()
            );

            updateDisplay();
        }
    }

    /**
     * Moves to the next exercise
     */
    private void nextText() {
        if (currentTextIndex < texts.length - 1) {
            currentTextIndex++;
        } else {
            currentTextIndex = 0;
        }

        responseField.clear();

        correctKeyStrokes = 0;
        incorrectKeyStrokes = 0;


        pressedKeyLabel.setText(
            "Last key pressed: "
        );

        pressedKeyLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-text-fill: #FFFFFF;"
        );

        shiftPressed = false;

        updateDisplay();

        requestKeyboardFocus();
    }

    /**
     * Resets the application to the first exercise.
     */
    private void reset() {

        currentTextIndex = 0;

        correctKeyStrokes = 0;
        incorrectKeyStrokes = 0;

        responseField.clear();


        pressedKeyLabel.setText(
            "Last key pressed: "
        );

        pressedKeyLabel.setStyle(
            "-fx-font-size: 14px; " +
            "-fx-text-fill: #FFFFFF;"
        );

        shiftPressed = false;

        updateDisplay();

        requestKeyboardFocus();
    }

    /**
     * Returns keyboard focus to the application.
     */
    private void requestKeyboardFocus() {
        expectedTextLabel
            .getScene()
            .getRoot()
            .requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}