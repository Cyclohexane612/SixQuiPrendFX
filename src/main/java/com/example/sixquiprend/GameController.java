package com.example.sixquiprend;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    @FXML
    private VBox playersContainer;
    private Board board;
    private List<Label> playerLabels;
    public void initialize() {
        board = new Board();
        playerLabels = new ArrayList<>();
    }
    public void receivePlayerInformation(String playerName, int aiOpponents) {
        System.out.println("Player name: " + playerName);
        System.out.println("Number of AI opponents: " + aiOpponents);
        // Créez le joueur humain
        Player humanPlayer = new Player(playerName);
        // Créez les joueurs IA
        List<Player> aiPlayers = new ArrayList<>();
        for (int i = 0; i < aiOpponents; i++) {
            String[] aiNames = {"Ontos", "Logos", "Pneuma", "Tora", "Poppi", "Rex", "Nia", "Morag", "Zyk"};
            String aiPlayerName = (i < aiNames.length) ? aiNames[i] : "AI Player " + (i + 1);
            Player aiPlayer = new Player(aiPlayerName);
            aiPlayers.add(aiPlayer);
        }
        board.getPlayers().add(humanPlayer);
        board.getPlayers().addAll(aiPlayers);
        displayPlayers(board.getPlayers());

    }

    private void displayPlayers(List<AbstractPlayer> players) {
        playersContainer.getChildren().clear();

        HBox hbox = new HBox(); // Créez un HBox pour contenir les joueurs
        hbox.setAlignment(Pos.TOP_LEFT); // Alignez les joueurs en haut à gauche
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(5);

        for (AbstractPlayer player : players) {
            Label playerLabel = new Label(player.getName());

            Rectangle rectangle = new Rectangle(100, 50);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);

            StackPane playerPane = new StackPane(rectangle, playerLabel);
            hbox.getChildren().add(playerPane); // Ajoutez chaque joueur au HBox
        }

        playersContainer.getChildren().add(hbox); // Ajoutez le HBox au conteneur des joueurs
    }


}


