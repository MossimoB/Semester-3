package mossimo.bianco.lab03;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Labels
        Label fnameLabel = new Label("First Name");
        Label lnameLabel = new Label("Last Name");
        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");

        Label messageLabel = new Label();

        // TextFields
        TextField fname = new TextField();
        TextField lname = new TextField();
        TextField email = new TextField();
        PasswordField password = new PasswordField();

        // Buttons
        Button register = new Button("Register");
        register.setDisable(true);

        Button clear = new Button("Clear");

        // Add labels and text fields to GridPane
        grid.add(fnameLabel, 0, 0);
        grid.add(fname, 1, 0);

        grid.add(lnameLabel, 0, 1);
        grid.add(lname, 1, 1);

        grid.add(emailLabel, 0, 2);
        grid.add(email, 1, 2);

        grid.add(passwordLabel, 0, 3);
        grid.add(password, 1, 3);

        grid.add(register, 0, 4);
        grid.add(clear, 1, 4);

        grid.add(messageLabel, 0, 5, 2, 1);

        // Enable or disable Register button
        fname.textProperty().addListener((a, b, c) -> {

            if (!fname.getText().isEmpty()
                    && !lname.getText().isEmpty()
                    && !email.getText().isEmpty()
                    && !password.getText().isEmpty()) {

                register.setDisable(false);

            } else {
                register.setDisable(true);
            }
        });

        lname.textProperty().addListener((a, b, c) -> {

            if (!fname.getText().isEmpty()
                    && !lname.getText().isEmpty()
                    && !email.getText().isEmpty()
                    && !password.getText().isEmpty()) {

                register.setDisable(false);

            } else {
                register.setDisable(true);
            }
        });

        email.textProperty().addListener((a, b, c) -> {

            if (!fname.getText().isEmpty()
                    && !lname.getText().isEmpty()
                    && !email.getText().isEmpty()
                    && !password.getText().isEmpty()) {

                register.setDisable(false);

            } else {
                register.setDisable(true);
            }
        });

        password.textProperty().addListener((a, b, c) -> {

            if (!fname.getText().isEmpty()
                    && !lname.getText().isEmpty()
                    && !email.getText().isEmpty()
                    && !password.getText().isEmpty()) {

                register.setDisable(false);

            } else {
                register.setDisable(true);
            }
        });

        // Register button
        register.setOnAction(e -> {

            // Email
            String emailString = email.getText();

            boolean validateEmail = emailString.contains("@")
                    && emailString.indexOf("@") < emailString.lastIndexOf(".")
                    && emailString.lastIndexOf(".") < emailString.length() - 1;

            // Password
            String passwordString = password.getText();

            boolean hasLetter = false;
            boolean hasDigit = false;


            for (int i = 0; i < passwordString.length(); i++) {

                char c = passwordString.charAt(i);

                if (Character.isLetter(c)) {
                    hasLetter = true;
                }

                if (Character.isDigit(c)) {
                    hasDigit = true;
                }
            }

            // Check email and password
            if (validateEmail && hasLetter && hasDigit) {
                messageLabel.setText(
                        "Welcome " + fname.getText() + "!"
                );

            } else {
                messageLabel.setText(
                        "Error: Invalid email or password."
                );
            }
        });

        // Clear button
        clear.setOnAction(e -> {

            fname.clear();
            lname.clear();
            email.clear();
            password.clear();

            messageLabel.setText("");

            register.setDisable(true);
        });

        // Put GridPane in the center of BorderPane
        root.setCenter(grid);

        // Scene
        Scene scene = new Scene(root, 640, 480);

        stage.setTitle("Registration Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}