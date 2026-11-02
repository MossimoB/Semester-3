package mossimo.bianco.lab10;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
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
        
        
    }
}
