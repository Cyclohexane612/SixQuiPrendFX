module com.example.sixquiprend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;

    opens com.example.sixquiprend to javafx.fxml;
    exports com.example.sixquiprend;
    exports com.example.sixquiprend.Players;
    opens com.example.sixquiprend.Players to javafx.fxml;
    exports com.example.sixquiprend.Objects;
    opens com.example.sixquiprend.Objects to javafx.fxml;
}