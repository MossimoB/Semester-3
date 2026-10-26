package mossimo.bianco.midtermpractice;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    // a field, so the log() helper below can see it
    private VBox logBox = new VBox(5);   // 5 px between log lines

    @Override
    public void start(Stage stage) {
        
        logBox.setStyle("-fx-background-color: #eeeeee;");
        
        Label text1 = new Label("text1");
        Label text2 = new Label("text2");
        Button btn1 = new Button("btn1");
        Button btn2 = new Button("btn2");
        
        //--------------------------------------------------------------
        // CENTER PANEL
    
        // polygon test in center panel
        Polygon tri = new Polygon(100, 20, 180, 160, 20, 160);
        tri.setFill(Color.WHITE);
        tri.setStroke(Color.BLACK);
        tri.setStrokeWidth(2);
        
        // created a container for the triangle so that we
        // can center it easily
        StackPane triangleContainer = new StackPane(tri);
        triangleContainer.setAlignment(Pos.CENTER); 
        
        // add triangleContainer to the VBox
        VBox centerPanel = new VBox(10, text1, text2, btn1, btn2, triangleContainer);
        centerPanel.setPadding(new Insets(10));
        centerPanel.setStyle("-fx-background-color: lightblue;");
        
        //---------------------------------------------------------------
        // LEFT PANEL

        Label LeftPanelTitle = new Label("Left Panel");
        
        RadioButton rb1 = new RadioButton("rb1");
        RadioButton rb2  = new RadioButton("rb2");
        rb1.setUserData("User selected rb1");
        rb2.setUserData("User selected rb2");
        
        ToggleGroup tg = new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        
        // the 10 in VBox(10) means 10px of space between each child
        VBox leftPanel = new VBox(10, LeftPanelTitle, rb1, rb2);
        leftPanel.setPrefWidth(150);
        leftPanel.setStyle(
                "-fx-background-color: lightgray;" +
                "-fx-padding: 20;"
        );
        
        //----------------------------------------------------------------
        // EVENTS
        // logs everything that mouse clicks and all changes that happen
        
        btn1.setOnAction(e -> {
            text1.setText("btn1");
            log("btn1 clicked");
        });
        
        btn2.setOnAction(e -> {
            text2.setText("btn2 clicked");
            log("btn2 clicked");
        });
        
        // ov means Observable Value
        // oldToggle is the value BEFORE the change
        // newToggle is the value AFTER the change
        tg.selectedToggleProperty().addListener((ov, oldToggle, newToggle) -> {
            // this lambda is a ChangeListener (not an EventHandler)
            // JavaFX runs it every time the selected radio button in tg changes
            // newToggle = the radio button that was just selected (rb1 or rb2)

            if (newToggle != null) {   // null when nothing is selected
                log(newToggle.getUserData().toString());
                // getUserData() returns the Object we stored earlier with
                //      rb1.setUserData("User selected rb1") / rb2.setUserData("User selected rb2")
                // toString() converts that Object to a String
                // log() is a regular helper method (not an event handler):
                //      it creates a new Label with that text and adds it to logBox,
                //      the VBox inside the right panel
                // output: "User selected rb1" or "User selected rb2"
            }
        });
        
        //----------------------------------------------------------------
        // RIGHT PANEL

        // created a log section that shows up in a vbox on the right of borderpane
        Label RightPanelTitle = new Label("Activity Log");
        
        VBox logPanel = new VBox(10, RightPanelTitle, logBox);
        logPanel.setPrefWidth(150);
        logPanel.setPadding(new Insets(10));
        logPanel.setStyle("-fx-background-color: lightgray;");
        
        //--------------------------------------------------------------------------------
        // MAIN LAYOUT PANE
        // to create VBox within BorderPane, just write VBox under it and DON'T name it root
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(50, 10, 10, 10)); // sets padding (top, right, bottom, left)
        BorderPane.setMargin(leftPanel, new Insets(0, 10, 0, 0)); // 10 px gap to the right of the left panel
        BorderPane.setMargin(logPanel, new Insets(0, 0, 0, 10)); // 10 px gap to the left of the right panel

        // if you do getChildren().addAll() like i did before,
        // anything you add gets no region (left, right, etc).
        root.setLeft(leftPanel);
        root.setRight(logPanel);
        root.setCenter(centerPanel);


        Scene scene = new Scene(root, 840, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void log(String message) {
        logBox.getChildren().add(new Label(message));
    }
}