package mossimo.bianco.assignment01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
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
 * FIve big quacking zephyrs jolt my wax bed.
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

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}