package com.example.sixquiprend;

import java.util.ArrayList;
import java.util.List;

public class IA extends AbstractPlayer{
    public IA(String name) {
        super(name);
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

    public Card playAI(IA ia,Board board){
        Card cardplayed = ia.getHand().get(0);
        for (Card card : ia.getHand()) {
            System.out.println(card.getNumber()+" TDB:"+card.getTdb());
            List<Card> row = new ArrayList<>();
            // position dans la liste
            double position=0;
            // nombre de TdB de la pile
            double tdbpile;
            // ecart entre la carte à jouer et la dernière de la pile
            double gap;

            // definition pile dans laquelle la carte va etre jouée
            int rownum = 1;
            int num = card.getNumber();
            int dif1 = num - board.getRow1().get(board.getRow1().size() - 1).getNumber();
            int dif2 = num - board.getRow2().get(board.getRow2().size() - 1).getNumber();
            int dif3 = num - board.getRow3().get(board.getRow3().size() - 1).getNumber();
            int dif4 = num - board.getRow4().get(board.getRow4().size() - 1).getNumber();

            if (dif1 < 0 && dif2 < 0 && dif3 < 0 && dif4 < 0) {
                rownum=0;
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
                    row=board.getRow1();
                } else if (dif2 == minDiff) {
                    row=board.getRow2();
                } else if (dif3 == minDiff) {
                    row=board.getRow3();
                } else if (dif4 == minDiff) {
                    row = board.getRow4();
                }
            }

            // si la carte ne rentre dans aucune pile
            if (rownum==0){
                position = 0;

                // calcul TDB minimum
                int tdb1 = 0;
                int tdb2 = 0;
                int tdb3 = 0;
                int tdb4 = 0;
                for (Card cardTdb : board.getRow1()) {
                    tdb1 += cardTdb.getTdb();
                }
                for (Card cardTdb : board.getRow2()) {
                    tdb2 += cardTdb.getTdb();
                }
                for (Card cardTdb : board.getRow3()) {
                    tdb3 += cardTdb.getTdb();
                }
                for (Card cardTdb : board.getRow4()) {
                    tdb4 += cardTdb.getTdb();
                }

                int totalTdb = Integer.MAX_VALUE;
                if (tdb1 < totalTdb) {
                    totalTdb=tdb1;
                    row=board.getRow1();
                }
                if (tdb2 < totalTdb) {
                    totalTdb=tdb2;
                    row=board.getRow2();
                }if (tdb3 < totalTdb) {
                    totalTdb=tdb3;
                    row=board.getRow3();
                }if (tdb4 < totalTdb) {
                    totalTdb=tdb4;
                    row=board.getRow4();
                }

                if (totalTdb<=2){
                    tdbpile=1;
                } else if (totalTdb<=5) {
                    tdbpile=0.66;
                } else if (totalTdb<=8) {
                    tdbpile=0.33;
                }else{
                    tdbpile=0;
                }

                if (row.get(row.size()-1).getNumber()>=100){
                    gap=1;
                } else{
                    gap=(float)row.get(row.size()-1).getNumber()/100;
                }

            }else{
                // calcul facteur position
                if (row.size()==1){
                    position=1;
                } else if (row.size()==2) {
                    position=0.75;
                }else if (row.size()==3) {
                    position = 0.5;
                }else if (row.size()==4) {
                    position = 0.25;
                }

                // calcul facteur tdbpile
                int totalTdb = 0;
                for (Card cardTdb : row) {
                    totalTdb += cardTdb.getTdb();
                }
                if (totalTdb<=2){
                    tdbpile=1;
                } else if (totalTdb<=5) {
                    tdbpile=0.66;
                } else if (totalTdb<=8) {
                    tdbpile=0.33;
                }else{
                    tdbpile=0;
                }

                // calcul facteur gap
                int dif = card.getNumber() - row.get(row.size()-1).getNumber();;
                if (position==1 || position==0.75){
                    if (dif <= 5 ){gap= 1;}
                    else if (dif <= 10 ){gap= 0.75;}
                    else if (dif <= 15 ){gap= 0.5;}
                    else if (dif <= 25 ){gap= 0.25;}
                    else{gap= 0;}
                }else if (position==0.5 || position==0.25){
                    if (dif <= 5 ){gap= 0.75;}
                    else if (dif <= 10 ){gap= 0.25;}
                    else if (dif <= 15 ){gap= 0;}
                    else if (dif <= 25 ){gap= 0.5;}
                    else{gap= 1;}
                }else{
                    if (dif <= 5 ){gap= 0;}
                    else if (dif <= 10 ){gap= 0.25;}
                    else if (dif <= 15 ){gap= 0.5;}
                    else if (dif <= 25 ){gap= 0.75;}
                    else{gap= 1;}
                }
            }

            // calcul attractivité
            double attractivity = position * 0.4 + tdbpile * 0.4 + gap * 0.2;
            card.setAttractivity(attractivity);

            if (card.getAttractivity() > cardplayed.getAttractivity()){
                cardplayed = card;
            }

            System.out.println(attractivity);
            System.out.println(cardplayed.getNumber());
        }
        return cardplayed;
    }
}
