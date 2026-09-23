package mossimo.bianco.listviewcontrols.practiceexample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        TextField textField = new TextField();
        textField.setPrefSize(100, 30);
        textField.setTranslateX(20);
        textField.setTranslateY(50);

        ListView<String> listView = new ListView<>();
        listView.setPrefSize(120, 130);
        listView.setTranslateX(260);
        listView.setTranslateY(50);

        Button addButton = new Button("Add Item");
        addButton.setPrefSize(100, 30);
        addButton.setTranslateX(140);
        addButton.setTranslateY(50);

        Button removeButton = new Button("Remove Selected Item");
        removeButton.setPrefSize(220, 30);
        removeButton.setTranslateX(20);
        removeButton.setTranslateY(100);

        Button removeAllButton = new Button("Remove All Items");
        removeAllButton.setPrefSize(220, 30);
        removeAllButton.setTranslateX(20);
        removeAllButton.setTranslateY(150);

        Label counterLabel = new Label("Total items = 0");
        counterLabel.setPrefSize(120, 30);
        counterLabel.setTranslateX(20);
        counterLabel.setTranslateY(200);

        // ======================================================
        // INTERNAL CSS
        // ======================================================

        textField.setStyle(
                "-fx-background-color: white;"
                + "-fx-background-radius: 8;"
                + "-fx-border-color: #cbd5e1;"
                + "-fx-border-radius: 8;"
                + "-fx-border-width: 1;"
                + "-fx-padding: 5 10;"
                + "-fx-font-size: 13px;"
        );

        addButton.setStyle(
                "-fx-background-color: #2563eb;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 8;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 12px;"
                + "-fx-cursor: hand;"
        );

        removeButton.setStyle(
                "-fx-background-color: #334155;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 8;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 12px;"
                + "-fx-cursor: hand;"
        );

        removeAllButton.setStyle(
                "-fx-background-color: #ef4444;"
                + "-fx-text-fill: white;"
                + "-fx-background-radius: 8;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 12px;"
                + "-fx-cursor: hand;"
        );

        listView.setStyle(
                "-fx-background-color: white;"
                + "-fx-background-radius: 8;"
                + "-fx-border-color: #cbd5e1;"
                + "-fx-border-radius: 8;"
                + "-fx-border-width: 1;"
        );

        counterLabel.setStyle(
                "-fx-text-fill: #334155;"
                + "-fx-font-size: 13px;"
                + "-fx-font-weight: bold;"
        );

        Group root = new Group();

        root.getChildren().add(textField);
        root.getChildren().add(addButton);
        root.getChildren().add(removeButton);
        root.getChildren().add(removeAllButton);
        root.getChildren().add(listView);
        root.getChildren().add(counterLabel);

        Scene scene = new Scene(root, 400, 250);

        // Background
        root.setStyle(
                "-fx-background-color: linear-gradient("
                + "to bottom right, #f8fafc, #e2e8f0"
                + ");"
        );

        // TODO 1: Add Item
        addButton.setOnAction(e -> {
            addItem(textField, listView, counterLabel);
        });

        // TODO 2: Remove Selected Item
        removeButton.setOnAction(e -> {
            removeSelectedItem(listView, counterLabel);
        });

        // TODO 3: Remove All Items
        removeAllButton.setOnAction(e -> {
            removeAllItems(listView, counterLabel);
        });

        stage.setTitle("JavaFX ListView");
        stage.setScene(scene);
        stage.show();
    }

    // Add Item Method
    public void addItem(TextField textField, ListView<String> listView,
            Label counterLabel) {

        String item = textField.getText().trim();

        if (item.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Item");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an item.");
            alert.showAndWait();

            return;
        }

        listView.getItems().add(item);

        int newIndex = listView.getItems().size() - 1;

        listView.getSelectionModel().select(newIndex);

        updateCounter(listView, counterLabel);

        textField.clear();
    }

    // Remove Selected Item Method
    public void removeSelectedItem(ListView<String> listView,
            Label counterLabel) {

        int selectedIndex = listView.getSelectionModel().getSelectedIndex();

        if (selectedIndex != -1) {

            listView.getItems().remove(selectedIndex);

            updateCounter(listView, counterLabel);

        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Item Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an item to remove.");
            alert.showAndWait();
        }
    }

    // Remove All Items Method
    public void removeAllItems(ListView<String> listView,
            Label counterLabel) {

        listView.getItems().clear();

        updateCounter(listView, counterLabel);
    }

    // Update Counter Method
    public void updateCounter(ListView<String> listView,
            Label counterLabel) {

        int totalItems = listView.getItems().size();

        counterLabel.setText("Total items = " + totalItems);
    }

    public static void main(String[] args) {
        launch();
    }
}