package com.example.sixquiprend.Players;
import com.example.sixquiprend.Objects.Card;

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
