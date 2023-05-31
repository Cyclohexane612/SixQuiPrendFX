package com.example.sixquiprend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private VBox name;
    @FXML
    private VBox numberOfAI;
    @FXML
    private Button play_button;
    @FXML
    private Button confirmIA;
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<Integer> aiComboBox;

    private GameController gameController;

    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) {
        name.setVisible(true);
        play_button.setVisible(false);
    }
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    public String confirmName(ActionEvent actionEvent) {
        name.setVisible(false);
        numberOfAI.setVisible(true);
        String playerName = nameField.getText();
        System.out.println("Player name: " + playerName);
        return playerName;
        }else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Empty Name");
        alert.setContentText("Please enter your name");
        alert.showAndWait();
        }
        return playerName;
    }

    @FXML
    public void confirmAIOpponents(ActionEvent actionEvent) throws IOException {
        Integer aiOpponents = aiComboBox.getValue();
        if (aiOpponents == null || aiOpponents < 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty AI");
            alert.setContentText("Please select at least one AI");
            alert.showAndWait();
        } else {
            numberOfAI.setVisible(false);
            Stage stage = (Stage) confirmIA.getScene().getWindow();

            String playerName = nameField.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root = loader.load();
            GameController gameController = loader.getController();

            gameController.receivePlayerInformation(playerName, aiOpponents);
            System.out.println("Number of AI opponents: " + aiOpponents);

            Scene scene = new Scene(root, 900, 700);
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.setFullScreen(true); // Ajout de cette ligne pour mettre en plein écran

        }
    }
}

