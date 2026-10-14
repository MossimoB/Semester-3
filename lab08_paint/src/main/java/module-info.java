module mossimo.bianco.lab08_paint {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens mossimo.bianco.lab08_paint to javafx.fxml;
    exports mossimo.bianco.lab08_paint;
}
