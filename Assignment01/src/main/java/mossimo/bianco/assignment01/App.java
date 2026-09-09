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
 * INSTRUCTIONS
 * 
 * basically recreate monkeytype (kind of) in javafx
 * 
 * virtual keyboard using buttons (don't need all)
 * 
 * textfield to display text to type
 * textfield to display typed response (matched to physical keyboard)
 * 
 * need key_pressed/released (key inputs)
 * must show key being pressed/released when it is pressed/released (change opacity or background)
 * if user presses external key (not shown in window) then error message
 * 
 * next button to move to next displayed text
 * when pressed, show new text, clear user text field, show 2 of counter
 * 
 * reset button to return to input 1 of 6
 * method to track correct/incorrect keystrokes
 * 
 * allow backspace
 * 
 */

/**
 * SAMPLE TEXT FOR TESTING
 * 
 * Try typing this text. Do it as quickly and as accurately as you can.
 * 
 * Next type another line of input data.
 * 
 * The quick brown fox jumps over the lazy dog.
 * 
 * Five big quacking zephyrs jolt my wax bed.
 * 
 * Sympathizing would fix Quaker objectives.
 * 
 * A large fawn jumped quickly over the white zinc boxes.
 * 
 */


/**
 * JavaFX App
 */
public class App extends Application {
        // the texts in an array
        private final String[] texts = {
            "Try typing this text. Do it as quickly and as accurately as you can.",
            "Next type another line of input data.",
            "The quick brown fox jumps over the lazy dog.",
            "Five big quacking zephyrs jolt my wax bed.",
            "Sympathizing would fix Quaker objectives.",
            "A large fawn jumped quickly over the white zinc boxes."
        };
        
        // current excercise
        private int currentTextIndex = 0;
        
        // accuracy
        private int correctKeyStrokes = 0;
        private int incorrectKeyStrokes = 0;
        
        // UI
        private Label expectedTextLabel;
        private TextField responseField;
        private Label progressLabel;
        private Label pressedKeyLabel;
        private Label correctLabel;
        private Label incorrectLabel;
        
        // virtaul key matches keyboard key
        private final Map<KeyCode, Button> virtualKeys = new HashMap();
        
        // shift key
        private boolean shiftPressed = false;
        
    @Override
    public void start(Stage stage) {
        // UI
        expectedTextLabel = new Label();
        responseField = new TextField();
        progressLabel = new Label();
        pressedKeyLabel = new Label();
        correctLabel = new Label();
        incorrectLabel = new Label();
        
        // first excercise
        updateDisplay();
        
        // main scene
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        
        // add components
        root.getChildren().addAll(
                expectedTextLabel,
                responseField,
                progressLabel,
                pressedKeyLabel,
                correctLabel,
                incorrectLabel,
                
                createKeyboard()
        );
        
        Scene scene = new Scene(root, 1200, 600);
        
        scene.setOnKeyPressed(e -> {
            handleKeyPressed(e.getCode());
        });
        
        scene.setOnKeyReleased(e -> {
            handleKeyReleased(e.getCode());
        });

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Updates the information displayed by the application
     */
    private void updateDisplay() {
        expectedTextLabel.setText(texts[currentTextIndex]);
        
        progressLabel.setText(
                (currentTextIndex + 1) + " of " + texts.length
        );
        
        pressedKeyLabel.setText("Last key pressed: ");
        
        correctLabel.setText(
                "Correct: " + correctKeyStrokes
        );
        
        incorrectLabel.setText(
                "Incorrect: " + incorrectKeyStrokes
        );
    }
        
    /**
     * Creates the virtual keyboard
     * 
     * @return the virtual keyboard layout
     */
    private VBox createKeyboard() {
        // making the keyboard
        VBox keyboard = new VBox(5);
        keyboard.setAlignment(Pos.CENTER);
        
        HBox topRow = new HBox(5);
        topRow.setAlignment(Pos.CENTER);
        
        HBox bottomRow = new HBox(5);
        bottomRow.setAlignment(Pos.CENTER);
        
        HBox middleRow = new HBox(5);
        middleRow.setAlignment(Pos.CENTER);
        
        HBox spaceRow = new HBox(5);
        spaceRow.setAlignment(Pos.CENTER);
        
        // adding all keys
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
        
        addKey(spaceRow, "Space", KeyCode.SPACE);
        
        // make keys show
        keyboard.getChildren().addAll(
                topRow,
                middleRow,
                bottomRow,
                spaceRow
        );
        
        return keyboard;
    }
    
    /**
     * Creates a virtual keyboard button and adds it to a keyboard row
     * 
     * @param row the row where the button should be placed
     * @param text the text displayed on the button
     * @param KeyCode the physical keyboard key represented by the button
     */
    private void addKey(HBox row, String text, KeyCode keyCode) {
        Button button = new Button(text);
        
        // make space and shift wider
        if (keyCode == KeyCode.SPACE) {
            button.setPrefWidth(400);
        } else if (keyCode == KeyCode.SHIFT) {
            button.setPrefWidth(100);
        } else {
            button.setPrefWidth(50);
        }
        
        button.setPrefHeight(45);
        
        virtualKeys.put(keyCode, button);
        
        row.getChildren().add(button);
    }
    
    /**
     * Handles a key when it is pressed on the physical keyboard
     * 
     * @param keyCode the key that was released
     */
    private void handleKeyPressed(KeyCode keyCode) {
        Button virtualKey = virtualKeys.get(keyCode);
        
        if (virtualKey != null) {
            virtualKey.setStyle("-fx-background-color: lightgray;");
            
            pressedKeyLabel.setText("Last key pressed: " + keyCode);
            pressedKeyLabel.setStyle("");
            
            if (keyCode == KeyCode.SHIFT) {
                shiftPressed = true;
                return;
            }
            
            if (keyCode == KeyCode.BACK_SPACE) {
                handleBackspace();
                return;
            }
            
            String character = getCharacter(keyCode);
            
            if (character != null) {
                addCharacter(character);
            }
            
        } else {
            pressedKeyLabel.setText("Not handled.");
            pressedKeyLabel.setStyle("-fx-text-fill: red;");
        }
    }
    
    /**
     * Handles a key when it is released on the physical keyboard
     * 
     * @param keyCode the key that was released
     */
    private void handleKeyReleased(KeyCode keyCode) {
        Button virtualKey = virtualKeys.get(keyCode);
        
        if (virtualKey != null) {
            virtualKey.setStyle("");
        }
    }
    
    /**
     * Converts a physical keyboard key into the character it represents
     * 
     * @param keyCode the physical keyboard key
     * @return the right character or null if not a character
     */
    private String getCharacter(KeyCode keyCode) {
        if (keyCode == KeyCode.SPACE) {
            return " ";
        }
        
        if (keyCode.isLetterKey()) {
            String character = keyCode.toString().toLowerCase();
            
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
     * @param keyCode the character types by user
     */
    private void addCharacter(String character) {
        String currentResponse = responseField.getText();
        int position = currentResponse.length();
        
        if (position < texts[currentTextIndex].length()) {
            char expectedCharacter = texts[currentTextIndex].charAt(position);
            
            if (character.charAt(0) == expectedCharacter) {
                correctKeyStrokes++;
            } else {
                incorrectKeyStrokes++;
            }
            
            responseField.appendText(character);
            
            updateDisplay();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}