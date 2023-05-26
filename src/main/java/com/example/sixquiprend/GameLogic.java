package com.example.sixquiprend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
    public class Game {
        static Scanner scanner = new Scanner(System.in);
        Player player1 = new Player("Luc");
        Player player2 = new Player("Maxence" );
        List<Card> row1 = new ArrayList<>();
        List<Card> row2 = new ArrayList<>();
        List<Card> row3 = new ArrayList<>();
        List<Card> row4 = new ArrayList<>();
        public int readInt(String prompt, int userChoices) {
            int input;
            do {
                System.out.println(prompt);
                try {
                    input = Integer.parseInt(scanner.next());
                } catch (Exception e) {
                    input = -1;
                    System.out.println("Please enter an integer");
                }
            } while (input < 1 || input > userChoices);
            return input;
        }
        public List<Card> cardInitialisation() {
            //Initialisation of a list of card
            List<Card> cardList = new ArrayList<>();
            for (int i = 1; i < 105; i++) {
                Card card = new Card(i) ;
                cardList.add(card);
            }
            return cardList;
        }
        public void play(){
            List<Card> cardList=cardInitialisation();
            List<Card> cardListShuffled = shuffle(cardList);
            int i = 1;
            for (Card card: cardListShuffled) {
                System.out.println(i+" . "+card.getNumber() + " ; " + card.getTdb());
                i += 1;
            }
            //Every player draw 10 cards
            draw(10, player1, cardListShuffled);
            //Sort every hand
            player1.setHand(sortCards(player1.getHand()));
            //Set card on each row
            setRow(row1, cardListShuffled);
            setRow(row2, cardListShuffled);
            setRow(row3, cardListShuffled);
            setRow(row4, cardListShuffled);
            showRow(row1, 1);
            showRow(row2, 2);
            showRow(row3, 3);
            showRow(row4, 4);
            //Each player choose a card
            List<Card> playedCard = new ArrayList<>();
            playedCard.add(choose(player1));
            playedCard.add(choose(player2));
        }

        public List<Card> shuffle(List<Card> cardList) {
            //Shuffle a list of card
            Random random = new Random();
            for (int i = cardList.size() - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                Card temp = cardList.get(i);
                cardList.set(i, cardList.get(j));
                cardList.set(j, temp);
            }
            return cardList;
        }
        public void draw(int n, Player player, List<Card> cardList){
            // Draw n card from cardList and it in hand
            List<Card> nElements = cardList.subList(0, n);
            player.getHand().addAll(nElements);
            cardList.removeAll(nElements);
            sortCards(cardList);
            System.out.println(player.getName()+ " draw " + n + " cards");
        }
        public Card choose(Player player){
            int i = 1;
            for (Card card: player.getHand()) {
                System.out.println(i+". "+card.getNumber() + " ; " + card.getTdb());
                i += 1;
            }
            System.out.println("Choose a Card ");
            int input = readInt("-> ", player.getHand().size());
            System.out.println("You chose " + player.getHand().get(input-1).getNumber());
            return player.getHand().get(input-1);
        }
        public void setRow(List<Card> row, List<Card> cardList){
            List<Card> nElements = cardList.subList(0, 1);
            row.addAll(nElements);
            cardList.removeAll(nElements);
        }
        public void showRow(List<Card> row, int i){
            System.out.println("row" + i + " : ");
            for (Card card: row){
                System.out.println( card.getNumber() + " ; " + card.getTdb() + "    ");
            }
        }
        public void addRow(List<Card> row, Card card){
            row.add(card);
            showRow(row1, 1);
            showRow(row2, 2);
            showRow(row3, 3);
            showRow(row4, 4);
        }
        public List<Card> sortCards(List<Card> cards) {
            for (int i = 1; i < cards.size(); i++) {
                Card key = cards.get(i);
                int j = i - 1;
                while (j >= 0 && cards.get(j).getNumber() > key.getNumber()) {
                    cards.set(j + 1, cards.get(j));
                    j--;
                }
                cards.set(j + 1, key);
            }
            return cards;
        }
    }

}
