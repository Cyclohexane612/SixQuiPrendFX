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
    }

    @Test
    void testSortCards() {

    }


    @Test
    void draw() {
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