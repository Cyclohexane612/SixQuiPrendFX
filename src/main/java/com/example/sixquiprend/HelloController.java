package com.example.sixquiprend;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onStartButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void setMainScene(Scene scene) {
    }
}