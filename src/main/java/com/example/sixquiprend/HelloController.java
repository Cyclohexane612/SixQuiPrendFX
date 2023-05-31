package com.example.sixquiprend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import lombok.Getter;


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
    @Getter
    private MediaPlayer mediaPlayer;

    private GameController gameController;

    public void playMusic(){
        String musicPath = getClass().getResource("/music/title.mp3").toExternalForm();
        Media musicFile = new Media(musicPath);
        mediaPlayer = new MediaPlayer(musicFile); // Utilisez la variable de classe mediaPlayer
        mediaPlayer.play();
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) {
        playMusic();
        name.setVisible(true);
        play_button.setVisible(false);
    }
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    public String confirmName(ActionEvent actionEvent) {
        String playerName = nameField.getText();
        if (!playerName.isEmpty()){
            name.setVisible(false);
            numberOfAI.setVisible(true);
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
            stopMusic();

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

            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setFullScreen(true);

        }
    }
}

