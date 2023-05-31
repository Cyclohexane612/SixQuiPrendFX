package com.example.sixquiprend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IA extends AbstractPlayer{
    public IA(String name){
        super(name);
    }

    public Card chooseCardToPlay(Board board) {
        List<Card> hand = getHand();

        Card bestCard = null;
        int bestValue = Integer.MAX_VALUE; // Valeur initiale élevée pour trouver la plus faible

        for (Card card : hand) {
            int cardValue = evaluateCard(board, card);
            if (cardValue < bestValue) {
                bestCard = card;
                bestValue = cardValue;
            } else if (cardValue == bestValue) {
                // En cas d'égalité de valeur, choisissez aléatoirement entre les cartes
                if (new Random().nextBoolean()) {
                    bestCard = card;
                }
            }
        }

        return bestCard;
    }

    private int evaluateCard(Board board, Card card) {
        int cardValue = 0;

        // Évaluez la valeur de la carte en fonction de vos critères
        // Vous pouvez utiliser les informations du plateau (board) pour prendre des décisions

        // Exemple : évaluez la différence entre la valeur de la carte et les valeurs actuelles des rangées
        int minDifference = Integer.MAX_VALUE;
        for (List<Card> row : board.getRows()) {
            for (Card rowCard : row) {
                int difference = Math.abs(card.getNumber() - rowCard.getNumber());
                if (difference < minDifference) {
                    minDifference = difference;
                }
            }
        }
        cardValue += minDifference;
        cardValue += minDifference;

        // Exemple : évaluez le nombre de têtes de bœufs (tdb) que la carte ajoute aux rangées
        int tdbValue = card.getTdb();
        cardValue += tdbValue;

        return cardValue;
    }

    @Override
    public int getTdb() {
        int totalTdb = 0;
        for (Card card : getDiscard()) {
            totalTdb += card.getTdb();
        }
        setTdb(totalTdb);
        return totalTdb;
    }
}
