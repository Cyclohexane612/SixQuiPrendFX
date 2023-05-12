module com.example.sixquiprend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.sixquiprend to javafx.fxml;
    exports com.example.sixquiprend;
}