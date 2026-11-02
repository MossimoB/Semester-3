module mossimo.bianco.lab10 {
    requires javafx.controls;
    requires javafx.fxml;

    opens mossimo.bianco.lab10 to javafx.fxml;
    exports mossimo.bianco.lab10;
}
