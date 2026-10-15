module mossimo.bianco.fxml {
    requires javafx.controls;
    requires javafx.fxml;

    opens mossimo.bianco.fxml to javafx.fxml;
    exports mossimo.bianco.fxml;
}
