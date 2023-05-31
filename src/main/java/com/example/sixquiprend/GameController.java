package com.example.sixquiprend;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameController {

    @FXML
    private VBox row1;
    @FXML
    private VBox row2;
    @FXML
    private VBox row3;
    @FXML
    private VBox row4;
    @FXML
    private VBox cardPlayersContainer;
    @FXML
    private VBox playersContainer;
    private Board board;
    private List<Label> playerLabels;
    private List<Card> cardList;
    public void initialize() {
        board = new Board();
        playerLabels = new ArrayList<>();
        cardList = new ArrayList<>();
    }
    public void receivePlayerInformation(String playerName, int aiOpponents) {
        System.out.println("Player name: " + playerName);
        System.out.println("Number of AI opponents: " + aiOpponents);
        // Créez le joueur humain
        Player humanPlayer = new Player(playerName);
        Player humanPlayer2 = new Player("Aymane");
        Player humanPlayer3 = new Player("Maxence");
        humanPlayer.setTdb(10);
        humanPlayer2.setTdb(12);
        humanPlayer3.setTdb(9);
        // Créez les joueurs IA
        List<Player> aiPlayers = new ArrayList<>();
        for (int i = 0; i < aiOpponents; i++) {
            String[] aiNames = {"Ontos", "Logos", "Pneuma", "Tora", "Poppi", "Rex", "Nia", "Morag", "Zyk"};
            String aiPlayerName = (i < aiNames.length) ? aiNames[i] : "AI Player " + (i + 1);
            Player aiPlayer = new Player(aiPlayerName);
            aiPlayers.add(aiPlayer);
        }
        // initialise le board et les joueurs
        playMusic();
        board.getPlayers().add(humanPlayer);
        board.getPlayers().addAll(aiPlayers);
        displayPlayers(board.getPlayers());
        sortPlayers(board.getPlayers());
        // initialise les cartes + Mélange
        cardInitialisation(cardList);
        shuffle(cardList);
        // distribue les cartes pour tous les joueurs
        draw(cardList);
        // Montre la main du joueur humain
        cardDisplayPlayers(humanPlayer);

        // On ditribue une carte sur chaque rangées
        List<List<Card>> rows = new ArrayList<>();
        rows.add(board.getRow1());
        rows.add(board.getRow2());
        rows.add(board.getRow3());
        rows.add(board.getRow4());
        int index = 0;
        for (List<Card> row : rows) {
            List<Card> nElements = cardList.subList(index, index + 1);
            System.out.println(row + ":" + nElements.get(0).getNumber());
            row.addAll(nElements);
            index++;
        }

        updateBoard(board);


        //Jeu s'arrête quand on a déposer 10 cartes ou quand on a un joueur à 66
        while(isAt66(board.getPlayers()) || isHandEmpty(board.getPlayers())){
            // Méthode pour faire choisir une carte au joueur
            // Méthode pour faire choisir une carte à toutes les IA présentes dans la partie
            // Méthode de distribution
            // Méthode de répartition des points malus (tdb)
        }
        //Méthode pour trier les joueurs en fonction de leur tdb (classement)
        sortPlayers(board.getPlayers());
        System.out.println("le gagnant est : " + board.getPlayers().get(0).getName() + "avec " + board.getPlayers().get(0).getTdb() + "pts");


        //Méthode qui affiche classement des joueurs




    }
    public boolean isAt66(List<AbstractPlayer> players){
        boolean endGame = false;
        for (AbstractPlayer player : players) {
            if(player.getTdb() >= 66){
                System.out.println("Le joueur :" + player.getName() + "a perdu car il a 66 pts");
                return endGame = true;
            }
        }
        return endGame;
    }

    public boolean isHandEmpty(List<AbstractPlayer> players){
        boolean endGame = false;
            if(players.get(0).getHand().isEmpty()){
                System.out.println("Les joueurs n'ont plus de carte");
                return endGame = true;
        }
            return endGame;
    }


    public void playMusic(){
        String musicPath = getClass().getResource("/music/musique.mp3").toExternalForm();
        Media musicFile = new Media(musicPath);
        MediaPlayer mediaPlayer = new MediaPlayer(musicFile);
        // Réglez le volume à 50% (moitié du volume maximum)
        mediaPlayer.play();
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }


    private void cardDisplayPlayers(Player player) {
        sortCards(player.getHand());
        cardPlayersContainer.getChildren().clear();

        FlowPane cardPane = new FlowPane();
        cardPane.setPadding(new Insets(10));
        cardPane.setHgap(10);
        cardPane.setVgap(10);

        for (Card card : player.getHand()) {
            card.configureCardAppearance();
            cardPane.getChildren().add(card.getImage());
        }

        cardPlayersContainer.getChildren().add(cardPane);
    }

    private void updateBoard(Board board) {
        displayCards(board.getRow1(), row1);
        displayCards(board.getRow2(), row2);
        displayCards(board.getRow3(), row3);
        displayCards(board.getRow4(), row4);
    }

    private void displayCards(List<Card> cards, VBox row) {
        FlowPane cardPane = new FlowPane();
        cardPane.setPadding(new Insets(10));
        cardPane.setHgap(10);
        cardPane.setVgap(10);
        for (int i = 0; i < 6; i++) {
            if (i < cards.size()) {
                Card card = cards.get(i);
                card.configureCardAppearance();
                cardPane.getChildren().add(card.getImage());
            } else {
                Rectangle rectangle = new Rectangle(100, 150);
                rectangle.setFill(Color.rgb(255, 255, 255, 0.5)); // Blanc semi-transparent
                rectangle.setStroke(Color.WHITE); // Couleur de trait pour le rectangle
                cardPane.getChildren().add(rectangle);
            }
        }
        row.getChildren().add(cardPane);
    }


    private void displayPlayers(List<AbstractPlayer> players) {
        VBox vbox = new VBox(); // Créez un VBox pour contenir les joueurs
        vbox.setAlignment(Pos.TOP_RIGHT); // Alignez les joueurs en haut à droite
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);

        for (AbstractPlayer player : players) {
            Label playerLabel = new Label(player.getName());

            Rectangle rectangle = new Rectangle(100, 50);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.BLACK);

            String tdbImagePath = getClass().getResource("/image/tdb.png").toExternalForm();
            Image tdbImage = new Image(tdbImagePath);
            ImageView tdbImageView = new ImageView(tdbImage);
            tdbImageView.setFitWidth(13);
            tdbImageView.setFitHeight(13);

            Label tdbLabel = new Label(": " + player.getTdb());
            HBox tdbBox = new HBox(tdbImageView, tdbLabel);
            tdbBox.setAlignment(Pos.CENTER);
            tdbBox.setSpacing(5);

            VBox playerInfoBox = new VBox(playerLabel, tdbBox);
            playerInfoBox.setAlignment(Pos.CENTER);
            playerInfoBox.setSpacing(5);

            StackPane playerPane = new StackPane(rectangle, playerInfoBox);
            vbox.getChildren().add(playerPane); // Ajoutez chaque joueur au VBox
        }
        playersContainer.getChildren().clear();
        playersContainer.getChildren().add(vbox); // Ajoutez le VBox au conteneur des joueurs
    }












    private void addRowToGrid(List<Card> row, GridPane gridPane, int rowIndex, int maxCardsPerRow) {
        int colIndex = 0;

        for (Card card : row) {
            card.configureCardAppearance();
            BorderPane cardPane = card.getImage();

            // Ajouter la carte à la grille
            gridPane.add(cardPane, colIndex, rowIndex);

            // Mettre à jour l'index de colonne
            colIndex++;

            // Vérifier si on atteint le nombre maximal de cartes par ligne
            if (colIndex >= maxCardsPerRow) {
                break; // Arrêter d'ajouter des cartes dans cette rangée
            }
        }
    }

    private void cardInitialisation(List<Card> cardList){
        for (int i = 1; i <= 104; i++) {
            Card card = new Card(i);
            card.configureCardAppearance();
            cardList.add(card);
        }
    }

    public void distribution(List<Card> playedCards){
        sortCards(playedCards);
        for (Card card:playedCards){
            int num = card.getNumber();
            int dif1 = num - board.getRow1().get(board.getRow1().size()-1).getNumber();
            int dif2 = num - board.getRow2().get(board.getRow2().size()-1).getNumber();
            int dif3 = num - board.getRow3().get(board.getRow3().size()-1).getNumber();
            int dif4 = num - board.getRow4().get(board.getRow4().size()-1).getNumber();
            if (dif1<0 & dif2<0 & dif3<0 & dif4<0){
                //choisit la pile puis prends les cartes de cette dernière
            }
            else{
                if (dif1<dif2 & dif1<dif3 & dif1<dif4 & dif1>0){
                    if (board.getRow1().size()==4){
                        card.getPlayer().getDiscard().addAll(board.getRow1());
                        board.getRow1().clear();
                        board.getRow1().add(card);
                    }
                    else{
                        board.getRow1().add(card);
                    }
                }
                if (dif2<dif3 & dif2<dif3 & dif2<dif4 & dif2>0){
                    if (board.getRow2().size()==4){
                        card.getPlayer().getDiscard().addAll(board.getRow2());
                        board.getRow2().clear();
                        board.getRow2().add(card);
                    }
                    else{
                        board.getRow2().add(card);
                    }
                }
                if (dif3<dif2 & dif3<dif1 & dif3<dif4 & dif3>0){
                    if (board.getRow3().size()==4){
                        card.getPlayer().getDiscard().addAll(board.getRow3());
                        board.getRow3().clear();
                        board.getRow3().add(card);
                    }
                    else{
                        board.getRow3().add(card);
                    }
                }
                if (dif4<dif2 & dif4<dif3 & dif4<dif1 & dif4>0){
                    if (board.getRow4().size()==4){
                        card.getPlayer().getDiscard().addAll(board.getRow4());
                        board.getRow4().clear();
                        board.getRow4().add(card);
                    }
                    else{
                        board.getRow4().add(card);
                    }
                }
            }
        }
        playedCards.clear();
    }

    public void sortCards(List<Card> cards) {
        for (int i = 1; i < cards.size(); i++) {
            Card key = cards.get(i);
            int j = i - 1;
            while (j >= 0 && cards.get(j).getNumber() > key.getNumber()) {
                cards.set(j + 1, cards.get(j));
                j--;
            }
            cards.set(j + 1, key);
        }
    }
    public void draw(List<Card> cardList){
        // Draw 10 card from cardList and it in hand
        for (AbstractPlayer player : board.getPlayers()){
            List<Card> nElements = cardList.subList(0,10);
            player.getHand().addAll(nElements);
            cardList.removeAll(nElements);
        }
    }

    public void shuffle(List<Card> cardList) {
        //Shuffle a list of card
        Random random = new Random();
        for (int i = cardList.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = cardList.get(i);
            cardList.set(i, cardList.get(j));
            cardList.set(j, temp);
        }
        System.out.println("Liste de cartes mélangée :");
        for (Card card : cardList) {
            System.out.println(card.getNumber());
        }
    }

    public void sortPlayers(List<AbstractPlayer> players) {

        for (int i = 1; i < players.size(); i++) {
            AbstractPlayer key = players.get(i);
            int j = i - 1;
            while (j >= 0 && players.get(j).getTdb() > key.getTdb()) {
                players.set(j + 1, players.get(j));
                j--;
            }
            players.set(j + 1, key);
        }
    }
}



