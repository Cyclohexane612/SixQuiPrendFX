package com.example.sixquiprend;
import com.example.sixquiprend.AbstractPlayer;
import com.example.sixquiprend.Card;

public class Player extends AbstractPlayer {
    public Player(String name){
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
}
