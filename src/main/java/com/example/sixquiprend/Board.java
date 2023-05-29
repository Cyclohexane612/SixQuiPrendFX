package com.example.sixquiprend;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class    Board {
    @Getter
    private List<AbstractPlayer> players;

    @Getter
    private List<Card> row1, row2, row3, row4;

    private Boolean endTurn;

    public void Board() {
        this.players = new ArrayList<AbstractPlayer>();
        this.row1 = new ArrayList<Card>();
        this.row2 = new ArrayList<Card>();
        this.row3 = new ArrayList<Card>();
        this.row4 = new ArrayList<Card>();
        this.endTurn = endTurn;
    }
}
