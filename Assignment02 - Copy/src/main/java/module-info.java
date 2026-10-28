module mossimo.bianco.assignment02 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens mossimo.bianco.assignment02 to javafx.fxml;
    opens mossimo.bianco.assignment02.controller to javafx.fxml;
    opens mossimo.bianco.assignment02.view to javafx.fxml;
    exports mossimo.bianco.assignment02;
    exports mossimo.bianco.assignment02.model;
    exports mossimo.bianco.assignment02.view;
}
