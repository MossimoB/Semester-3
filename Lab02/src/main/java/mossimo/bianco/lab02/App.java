package mossimo.bianco.lab02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        
        StackPane middle = new StackPane();
        // add labels
        root.setTop(new Label("Random Game"));
        root.setBottom(new Label("Waiting..."));
        Label lblImage = new Label("");
        root.setCenter(lblImage);
        
        
        Random random = new Random();
        int num = random.nextInt(20) + 101;
        
        // images path
        String path = String.format("file:src\\main\\Ressources\\%d.jpg", num);
        Image image = new Image(path);
        
        // imageview object
        lblImage.setGraphic(new ImageView(image));
        
        // setting the scene
        Scene scene = new Scene(root, 250, 300);
        // scene title
        stage.setTitle("Java Games");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}