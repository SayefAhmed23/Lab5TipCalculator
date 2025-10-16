module ahmed.lab5tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens ahmed.lab5tipcalculator to javafx.fxml;
    exports ahmed.lab5tipcalculator;
}