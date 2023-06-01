package com.example.sixquiprend;


import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.collections.ObservableList;


import java.util.*;

public class GameController {

    @FXML
    private Button buttonRow1,buttonRow2,buttonRow3,buttonRow4;
    @FXML
    private VBox row1;
    @FXML
    private VBox row2;
    @FXML
    private VBox row3;
    @FXML
    private VBox row4;
    @FXML
    private VBox pileOptions;
    @FXML
    private VBox cardPlayersContainer;
    @FXML
    private VBox playersContainer;
    private Board board;
    private List<Label> playerLabels;
    private List<Card> cardList;
    @FXML
    private ListView<String> playerListView;
    private Boolean endgame = false;

    public void initialize() {
        board = new Board();
        playerLabels = new ArrayList<>();
        cardList = new ArrayList<>();
    }

    public void receivePlayerInformation(String playerName, int aiOpponents) throws IOException {
        System.out.println("Player name: " + playerName);
        System.out.println("Number of AI opponents: " + aiOpponents);
        // Créez le joueur humain
        Player humanPlayer = new Player(playerName);
        // Créez les joueurs IA
        List<AbstractPlayer> aiPlayers = new ArrayList<>();
        for (int i = 0; i < aiOpponents; i++) {
            String[] aiNames = {"Ontos", "Logos", "Pneuma", "Tora", "Poppi", "Rex", "Nia", "Morag", "Zyk"};
            String aiPlayerName = (i < aiNames.length) ? aiNames[i] : "AI Player " + (i + 1);
            IA aiPlayer = new IA(aiPlayerName);
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
        List<Card> playedCards = new ArrayList<Card>();
        //cardDisplayPlayers(humanPlayer, playedCards);
        // On distribue une carte sur chaque rangée
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
        // Affichage board
        updateBoard(board);
        //Jeu s'arrête quand on a déposé 10 cartes ou quand on a un joueur à 66
        System.out.println("66 :"+isAt66(board.getPlayers())+"; Empty : "+isHandEmpty(board.getPlayers()) + " Ou : " + (isAt66(board.getPlayers())||isHandEmpty(board.getPlayers())));
        //while(!isAt66(board.getPlayers()) && !isHandEmpty(board.getPlayers())) {
         //   System.out.println("boucle");
            cardDisplayPlayers(humanPlayer, playedCards);
        //}



    }
    public void displayEndScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rankingview.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Classement");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public boolean isAt66(List<AbstractPlayer> players) {
        boolean endGame = false;
        for (AbstractPlayer player : players) {
            if (player.getTdb() >= 66) {
                System.out.println("Le joueur : " + player.getName() + " a perdu car il a 66 pts");
                endGame = true;
                return endGame;
            }
        }
        return endGame;
    }

    public boolean isHandEmpty(List<AbstractPlayer> players) {
        boolean endGame = false;
        if (players.get(0).getHand().isEmpty()) {
            System.out.println("Les joueurs n'ont plus de carte");
            endGame = true;
            return endGame;
        }
        return endGame;
    }



    public void displayRanking(List<Player> players) {
        ObservableList<String> playerItems = FXCollections.observableArrayList();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            String playerInfo = (i + 1) + ". " + player.getName() + " - TdB: " + player.getTdb();
            playerItems.add(playerInfo);
        }

        playerListView.setItems(playerItems);
    }



    public void playMusic() {
        String musicPath = getClass().getResource("/music/musique.mp3").toExternalForm();
        Media musicFile = new Media(musicPath);
        MediaPlayer mediaPlayer = new MediaPlayer(musicFile);
        // Réglez le volume à 50% (moitié du volume maximum)
        mediaPlayer.play();
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }


    private void cardDisplayPlayers(Player player, List<Card> playedCards){
        sortCards(player.getHand());

        FlowPane cardPane = new FlowPane();
        cardPane.setPadding(new Insets(10));
        cardPane.setHgap(10);
        cardPane.setVgap(10);
        for (Card card : player.getHand()) {
            card.configureCardAppearance();
            cardPane.getChildren().add(card.getImage());

            // Gestionnaire d'événements pour l'effet de levée de la carte
            card.getImage().setOnMouseEntered(event -> {
                TranslateTransition liftTransition = new TranslateTransition(Duration.millis(50), card.getImage());
                liftTransition.setToY(-10);
                liftTransition.play();
            });

            // Gestionnaire d'événements pour annuler l'effet de levée de la carte
            card.getImage().setOnMouseExited(event -> {
                card.getImage().setTranslateY(0); // Réinitialisation de la translation verticale
            });

            card.getImage().setOnMouseClicked(event -> {
                playedCards.add(card);
                player.getHand().remove(card);
                cardPane.getChildren().remove(card.getImage());

                // Méthode pour faire choisir une carte à toutes les IA présentes dans la partie (Maxence)
                playAI(board.getPlayers(), playedCards);
                // Méthode de distribution (Maxence)
                distribution(board.getPlayers(), playedCards);
                if(isAt66(board.getPlayers()) || isHandEmpty(board.getPlayers())) {
                   System.out.println("end");
                    try {
                        displayEndScreen(); // Cette méthode peut potentiellement lancer une IOException
                    } catch (IOException e) {
                        // Traitement de l'exception IOException
                        e.printStackTrace(); // Affichage de la trace de la pile pour le débogage
                    }
                }
                // Réaffichage des cartes restantes dans la main du joueur
                cardPane.getChildren().clear();
                for (Card updatedCard : player.getHand()) {
                    updatedCard.configureCardAppearance();
                    cardPane.getChildren().add(updatedCard.getImage());
                }
            });
        }

        cardPlayersContainer.getChildren().clear();
        cardPlayersContainer.getChildren().add(cardPane);
    }




    private void updateBoard(Board board) {
        displayCards(board.getRow1(), row1);
        displayCards(board.getRow2(), row2);
        displayCards(board.getRow3(), row3);
        displayCards(board.getRow4(), row4);
    }

    private void displayCards(List<Card> cards, VBox row) {
        // Supprimer les cartes précédentes affichées
        row.getChildren().clear();
        FlowPane cardPane = new FlowPane();
        cardPane.setPadding(new Insets(10));
        cardPane.setHgap(10);
        cardPane.setVgap(10);
        for (int i = 0; i < 6; i++) {
            if (i < cards.size()) {
                Card card = cards.get(i);
                card.configureCardAppearance();
                cardPane.getChildren().add(card.getImage());
            } else if (i == 5) { // Correction : remplacer "else-if" par "else if" et modifier la condition de "i == 6" à "i == 5"
                Rectangle rectangle = new Rectangle(100, 150);
                rectangle.setFill(Color.rgb(255, 0, 0, 0.5)); // Blanc semi-transparent
                rectangle.setStroke(Color.RED); // Couleur de trait pour le rectangle
                cardPane.getChildren().add(rectangle);
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

    private void updatePlayerTdb(List<AbstractPlayer> players) {
        VBox vbox = (VBox) playersContainer.getChildren().get(0); // Récupérez le VBox existant contenant les joueurs

        for (Node node : vbox.getChildren()) {
            if (node instanceof StackPane) {
                StackPane playerPane = (StackPane) node;
                VBox playerInfoBox = (VBox) playerPane.getChildren().get(1); // Récupérez le VBox contenant les informations du joueur

                if (playerInfoBox.getChildren().size() > 1) {
                    HBox tdbBox = (HBox) playerInfoBox.getChildren().get(1); // Récupérez le HBox contenant le nombre de tdb

                    if (tdbBox.getChildren().size() > 1) {
                        Label tdbLabel = (Label) tdbBox.getChildren().get(1); // Récupérez le Label affichant le nombre de tdb

                        AbstractPlayer player = players.get(vbox.getChildren().indexOf(playerPane));
                        tdbLabel.setText(": " + player.getTdb()); // Mettez à jour le texte du Label avec le nouveau nombre de tdb
                    }
                }
            }
        }
    }
    private void cardInitialisation(List<Card> cardList) {
        for (int i = 1; i <= 104; i++) {
            Card card = new Card(i);
            card.configureCardAppearance();
            cardList.add(card);
        }
    }

    public void distribution(List<AbstractPlayer> players, List<Card> playedCards) {
        sortCards(playedCards);
        for (Card card : playedCards) {
            int num = card.getNumber();
            int dif1 = num - board.getRow1().get(board.getRow1().size() - 1).getNumber();
            int dif2 = num - board.getRow2().get(board.getRow2().size() - 1).getNumber();
            int dif3 = num - board.getRow3().get(board.getRow3().size() - 1).getNumber();
            int dif4 = num - board.getRow4().get(board.getRow4().size() - 1).getNumber();

            if (dif1 < 0 && dif2 < 0 && dif3 < 0 && dif4 < 0) {
                if (card.getPlayer()==players.get(0)) {
                    Platform.runLater(() -> showPileOptions(card));
                }else{
                    int tdb1 = 0;
                    for (Card cardfortdb : board.getRow1()) {
                        tdb1 += cardfortdb.getTdb();
                    }
                    int tdb2 = 0;
                    for (Card cardfortdb : board.getRow2()) {
                        tdb2 += cardfortdb.getTdb();
                    }
                    int tdb3 = 0;
                    for (Card cardfortdb : board.getRow3()) {
                        tdb3 += cardfortdb.getTdb();
                    }
                    int tdb4 = 0;
                    for (Card cardfortdb : board.getRow4()) {
                        tdb4 += cardfortdb.getTdb();
                    }

                    int tdbDiff = Integer.MAX_VALUE;
                    int lastCard = 0;
                    int pileAI = 0;
                    // prend la pile avec le moins de Tdb, si ce nombre est égal entre 2 piles, prend celle avec la derniere carte la plus haute
                    if (tdb1 < tdbDiff || (tdb1==tdbDiff && board.getRow1().get(board.getRow1().size() - 1).getNumber() > lastCard)) {
                        tdbDiff = tdb1;
                        pileAI = 1;
                        lastCard = board.getRow1().get(board.getRow1().size() - 1).getNumber();
                    }
                    if (tdb2 < tdbDiff || (tdb2==tdbDiff && board.getRow2().get(board.getRow2().size() - 1).getNumber() > lastCard)) {
                        tdbDiff = tdb2;
                        pileAI = 2;
                        lastCard = board.getRow2().get(board.getRow2().size() - 1).getNumber();
                    }
                    if (tdb3 < tdbDiff || (tdb3==tdbDiff && board.getRow3().get(board.getRow3().size() - 1).getNumber() > lastCard)) {
                        tdbDiff = tdb3;
                        pileAI = 3;
                        lastCard = board.getRow3().get(board.getRow3().size() - 1).getNumber();
                    }
                    if (tdb4 < tdbDiff || (tdb4==tdbDiff && board.getRow4().get(board.getRow4().size() - 1).getNumber() > lastCard)) {
                        tdbDiff = tdb4;
                        pileAI = 4;
                        lastCard = board.getRow4().get(board.getRow4().size() - 1).getNumber();
                    }

                    if (pileAI == 1) {
                        card.getPlayer().getDiscard().addAll(board.getRow1());
                        board.getRow1().clear();
                        board.getRow1().add(card);
                        System.out.println(card.getPlayer().getName()+" pile 1");
                    } else if (pileAI == 2) {
                        card.getPlayer().getDiscard().addAll(board.getRow2());
                        board.getRow2().clear();
                        board.getRow2().add(card);
                        System.out.println(card.getPlayer().getName()+" pile 2");
                    } else if (pileAI == 3) {
                        card.getPlayer().getDiscard().addAll(board.getRow3());
                        board.getRow3().clear();
                        board.getRow3().add(card);
                        System.out.println(card.getPlayer().getName()+" pile 3");
                    } else if (pileAI == 4) {
                        card.getPlayer().getDiscard().addAll(board.getRow4());
                        board.getRow4().clear();
                        board.getRow4().add(card);
                        System.out.println(card.getPlayer().getName()+" pile 4");
                    }
                }
            } else {
                int minDiff = Integer.MAX_VALUE;
                if (dif1 < minDiff && dif1 > 0) {
                    minDiff = dif1;
                }
                if (dif2 < minDiff && dif2 > 0) {
                    minDiff = dif2;
                }
                if (dif3 < minDiff && dif3 > 0) {
                    minDiff = dif3;
                }
                if (dif4 < minDiff && dif4 > 0) {
                    minDiff = dif4;
                }

                // Ajouter la carte à la pile correspondante avec la différence la plus petite
                if (dif1 == minDiff) {
                    if (board.getRow1().size() == 5) {
                        card.getPlayer().getDiscard().addAll(board.getRow1());
                        board.getRow1().clear();
                    }
                    board.getRow1().add(card);
                } else if (dif2 == minDiff) {
                    if (board.getRow2().size() == 5) {
                        card.getPlayer().getDiscard().addAll(board.getRow2());
                        board.getRow2().clear();
                    }
                    board.getRow2().add(card);
                } else if (dif3 == minDiff) {
                    if (board.getRow3().size() == 5) {
                        card.getPlayer().getDiscard().addAll(board.getRow3());
                        board.getRow3().clear();
                    }
                    board.getRow3().add(card);
                } else if (dif4 == minDiff) {
                    if (board.getRow4().size() == 5) {
                        card.getPlayer().getDiscard().addAll(board.getRow4());
                        board.getRow4().clear();
                    }
                    board.getRow4().add(card);
                }
            }
        }
        updateBoard(board);
        playedCards.clear();
        updatePlayerTdb(board.getPlayers());
    }

    private void showPileOptions(Card card) {
        pileOptions.setVisible(true);
        buttonRow1.setOnAction(event -> selectPile(card, 1));
        buttonRow2.setOnAction(event -> selectPile(card, 2));
        buttonRow3.setOnAction(event -> selectPile(card, 3));
        buttonRow4.setOnAction(event -> selectPile(card, 4));
    }


    private void selectPile(Card card, int pileNumber) {
        AbstractPlayer player = card.getPlayer();
        List<Card> selectedPile = switch (pileNumber) {
            case 1 -> board.getRow1();
            case 2 -> board.getRow2();
            case 3 -> board.getRow3();
            case 4 -> board.getRow4();
            default -> Collections.emptyList();
        };

        player.getDiscard().addAll(selectedPile);
        selectedPile.clear();
        selectedPile.add(card);

        pileOptions.setVisible(false);

        // Autres mises à jour nécessaires
        updateBoard(board);
        updatePlayerTdb(board.getPlayers());
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

    public void draw(List<Card> cardList) {
        // Draw 10 card from cardList and it in hand
        for (AbstractPlayer player : board.getPlayers()) {
            List<Card> nElements = cardList.subList(0, 10);
            player.getHand().addAll(nElements);
            for (Card card : player.getHand()) {
                card.setPlayer(player);
            }
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

    public void playAI(List<AbstractPlayer> players, List<Card> playedCards) {
        for (AbstractPlayer AI : players.subList(1, players.size())) {
            sortCards(AI.getHand());
            //int num = ThreadLocalRandom.current().nextInt(0, AI.getHand().size());
            Card cardplayed = ((IA) AI).playAI((IA) AI, board);
            playedCards.add(cardplayed);
            AI.getHand().remove(cardplayed);
        }
    }
}