module com.example.sixquiprend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens com.example.sixquiprend to javafx.fxml;
    exports com.example.sixquiprend;
}