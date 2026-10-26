module mossimo.bianco.lab09 {
    requires javafx.controls;
    requires javafx.fxml;

    opens mossimo.bianco.lab09 to javafx.fxml;
    exports mossimo.bianco.lab09;
}
