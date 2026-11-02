package mossimo.bianco.lab10;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

public class MainViewController implements Initializable {

    // Main Pane
    @FXML
    private AnchorPane mainPane;

    // File Menu Items
    @FXML
    private MenuItem newItem;

    @FXML
    private MenuItem openItem;

    @FXML
    private MenuItem closeItem;

    @FXML
    private MenuItem saveItem;


    // Edit Menu Items
    @FXML
    private CheckMenuItem gridItem;

    @FXML
    private MenuItem copyItem;


    // Search Menu Items
    @FXML
    private MenuItem findItem;

    @FXML
    private MenuItem replaceItem;


    // Settings Radio Menu Items
    @FXML
    private RadioMenuItem smallItem;

    @FXML
    private RadioMenuItem mediumItem;

    @FXML
    private RadioMenuItem largeItem;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Group RadioMenuItems
        ToggleGroup tg = new ToggleGroup();

        smallItem.setToggleGroup(tg);
        mediumItem.setToggleGroup(tg);
        largeItem.setToggleGroup(tg);

        // Set default selection
        mediumItem.setSelected(true);

        // Close is disabled until a file is opened
        closeItem.setDisable(true);


        // Keyboard Accelerators
        newItem.setAccelerator(
                KeyCombination.keyCombination("Ctrl+N")
        );
        
         openItem.setAccelerator(
                KeyCombination.keyCombination("Ctrl+O")
        );

        closeItem.setAccelerator(
                KeyCombination.keyCombination("Ctrl+W")
        );

        copyItem.setAccelerator(
                KeyCombination.keyCombination("Ctrl+C")
        );

        // Context Menu
        ContextMenu popup = new ContextMenu();

        MenuItem help = new MenuItem("Help");
        MenuItem inspect = new MenuItem("Inspect");

        popup.getItems().addAll(help, inspect);

        help.setOnAction(e ->
                System.out.println("HELP selected")
        );

        inspect.setOnAction(e ->
                System.out.println("INSPECT selected")
        );
        // Right-click Context Menu
        mainPane.setOnMouseClicked(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                popup.show(
                        mainPane,
                        e.getScreenX() - 50,
                        e.getScreenY() - 25
                );
            }
        });
    }
    
    // NEW
    @FXML
    private void handleNew() {

        System.out.println("NEW has been pressed");

        newItem.setDisable(true);
        openItem.setDisable(true);
        closeItem.setDisable(false);
    }
    
    // OPEN
    @FXML
    private void handleOpen() {
        System.out.println("OPEN has been pressed");

        newItem.setDisable(true);
        openItem.setDisable(true);
        closeItem.setDisable(false);
    }

    // CLOSE
    @FXML
    private void handleClose() {

        System.out.println("CLOSE has been pressed");

        newItem.setDisable(false);
        openItem.setDisable(false);
        closeItem.setDisable(true);
    }
    
    // USE GRIDLINES
    @FXML
    
}
