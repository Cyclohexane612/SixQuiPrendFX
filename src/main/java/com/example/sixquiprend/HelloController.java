package com.example.sixquiprend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private VBox name, numberOfAI;
    @FXML
    private Button play_button;
    @FXML
    private Button confirmIA;
    @FXML
    private Label welcomeText;
    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) {
        name.setVisible(true);
        play_button.setVisible(false);
    }

    public void confirmName(ActionEvent actionEvent) {
        name.setVisible(false);
        numberOfAI.setVisible(true);
    }

    public void confirmAIOpponents(ActionEvent actionEvent) throws IOException {
        numberOfAI.setVisible(false);
        Stage stage = (Stage) confirmIA.getScene().getWindow();
        Parent root = FXMLLoader.<Parent>load(getClass().getResource("game.fxml"));
        Scene scene1 = new Scene(root, 900, 700);
        stage.setScene(scene1);
    }
}