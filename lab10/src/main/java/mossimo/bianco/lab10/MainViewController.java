package mossimo.bianco.lab10;

import java.io.IOException;
import javafx.fxml.FXML;

public class MainViewController {

    @FXML
    private void switchToSecondary() throws IOException {
        Main.setRoot("secondary");
    }
}
