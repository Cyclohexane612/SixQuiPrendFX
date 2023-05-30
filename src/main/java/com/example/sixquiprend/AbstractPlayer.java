package com.example.sixquiprend;

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
    @Getter @Setter
    private int tdb;
    @Getter @Setter
    private int lastCard;

    public AbstractPlayer(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.discard = new ArrayList<Card>();
        this.tdb = 0;
        this.lastCard =0;
    }
}
