package mossimo.bianco.assignment01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * INSTRUCTIONS
 * 
 * basically recreate monkeytype (kind of) in javafx
 * 
 * virtual keyboard using buttons (don't need all, but i will do all)
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
                incorrectLabel
        );
        
        Scene scene = new Scene(root, 1200, 600);

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();
    }
    
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

    public static void main(String[] args) {
        launch(args);
    }
}