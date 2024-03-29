package com.example.sixquiprend.Players;

import com.example.sixquiprend.Objects.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private List<Card> hand;
    @Getter @Setter
    private List<Card> discard;
    @Setter
    private int tdb;

    public AbstractPlayer(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.discard = new ArrayList<Card>();
        this.tdb = 0;
    }
    public abstract int getTdb();


}
