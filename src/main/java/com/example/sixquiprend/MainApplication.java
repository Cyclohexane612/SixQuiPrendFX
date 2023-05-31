package com.example.sixquiprend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public void start(Stage primaryStage) throws IOException {
        // Charger le fichier "title_screen.fxml"
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("title_screen.fxml"));
        StackPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 320, 240);
        primaryStage.setTitle("Six qui prend");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Lancer la vidéo "titlescreen.mp4"
        String videoFile = getClass().getResource("/video/titlescreen.mp4").toExternalForm(); // Chemin d'accès au fichier "titlescreen.mp4"
        Media media = new Media(videoFile);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        Stage videoStage = new Stage();
        StackPane videoRoot = new StackPane();
        videoRoot.getChildren().add(mediaView);
        Scene videoScene = new Scene(videoRoot);

        videoStage.setFullScreen(true);
        videoStage.setScene(videoScene);
        videoStage.show();

        // Masquer la fenêtre principale pendant la lecture de la vidéo
        primaryStage.hide();

        // Écouter l'événement de fin de lecture de la vidéo
        mediaPlayer.setOnEndOfMedia(() -> {
            // Afficher à nouveau la fenêtre principale
            primaryStage.show();

            // Fermer la fenêtre de la vidéo
            videoStage.close();
        });

        // Lancer la lecture de la vidéo
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
