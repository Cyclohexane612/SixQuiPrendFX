package com.example.sixquiprend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private VBox name;
    @FXML
    private Label welcomeText;
    @FXML
    protected void onStartButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void confirmName(ActionEvent actionEvent) {
        name.setVisible(false);
    }
}