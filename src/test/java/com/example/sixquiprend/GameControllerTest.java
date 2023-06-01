package com.example.sixquiprend;

import javafx.scene.media.MediaPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    @Test
    void initialize() {
    }

    @Test
    void receivePlayerInformation() {
    }

    @Test
    void isAt66() {
    }

    @Test
    void isHandEmpty() {
    }

    @Test
    void playMusic() {
    }

    @Test
    void distribution() {
        Board board = new Board();
        List<AbstractPlayer> players = new ArrayList<>();
        List<Card> playersCards = new ArrayList<>();
        Player humanPlayer = new Player("Bob");
        board.getPlayers().add(humanPlayer);
        players.add(humanPlayer);

        Card card1 = new Card(1);
        Card card2 = new Card(10);
        Card card3 = new Card(100);
        playersCards.add(card1);
        playersCards.add(card3);
        playersCards.add(card2);
    }

    @Test
    void testSortCards() {

    }


    @Test
    void draw() {
        Board board = new Board();
        List<AbstractPlayer> players = new ArrayList<>();
        List<Card> CardList = new ArrayList<>();
        Player humanPlayer = new Player("Bob");
        board.getPlayers().add(humanPlayer);
        players.add(humanPlayer);

        Card card1 = new Card(1);
        Card card2 = new Card(10);
        Card card3 = new Card(100);
        Card card4 = new Card(12);
        Card card5 = new Card(78);
        Card card6 = new Card(98);
        Card card7 = new Card(3);
        Card card8 = new Card(43);
        Card card9 = new Card(4);
        Card card10 = new Card(26);
        Card card11 = new Card(104);
        CardList.add(card1);
        CardList.add(card2);
        CardList.add(card3);
        CardList.add(card4);
        CardList.add(card5);
        CardList.add(card6);
        CardList.add(card7);
        CardList.add(card8);
        CardList.add(card9);
        CardList.add(card10);
        CardList.add(card11);

        for (AbstractPlayer player : board.getPlayers()) {
            List<Card> nElements = CardList.subList(0, 10);
            player.getHand().addAll(nElements);
            for (Card card : player.getHand()) {
                card.setPlayer(player);
            }
            CardList.removeAll(nElements);
        }
        Assertions.assertEquals(card1, humanPlayer.getHand().get(0));
        Assertions.assertEquals(card2, humanPlayer.getHand().get(1));
        Assertions.assertEquals(card3, humanPlayer.getHand().get(2));
        Assertions.assertEquals(card4, humanPlayer.getHand().get(3));
        Assertions.assertEquals(card5, humanPlayer.getHand().get(4));
        Assertions.assertEquals(card6, humanPlayer.getHand().get(5));
        Assertions.assertEquals(card7, humanPlayer.getHand().get(6));
        Assertions.assertEquals(card8, humanPlayer.getHand().get(7));
        Assertions.assertEquals(card9, humanPlayer.getHand().get(8));
        Assertions.assertEquals(card10, humanPlayer.getHand().get(9));



    }

    @Test
    void shuffle() {
    }

    @Test
    void sortPlayers() {
        List<AbstractPlayer> players = new ArrayList<>();
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Player player3 = new Player("Charlie");
        player1.setTdb(10);
        player2.setTdb(12);
        player3.setTdb(8);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        // Perform the sorting
        for (int i = 1; i < players.size(); i++) {
            AbstractPlayer key = players.get(i);
            int j = i - 1;
            while (j >= 0 && players.get(j).getTdb() > key.getTdb()) {
                players.set(j + 1, players.get(j));
                j--;
            }
            players.set(j + 1, key);
        }
        // Verify the sorted order
        Assertions.assertEquals("Bob", players.get(2).getName());
        Assertions.assertEquals("Charlie", players.get(0).getName());
        Assertions.assertEquals("Alice", players.get(1).getName());
    }
}