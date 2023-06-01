package com.example.sixquiprend;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Board {
    @Getter @Setter
    private List<AbstractPlayer> players;
    @Getter
    private List<Card> row1, row2, row3, row4;

    private Boolean endTurn;

    public Board() {
        this.players = new ArrayList<>();
        this.row1 = new ArrayList<>();
        this.row2 = new ArrayList<>();
        this.row3 = new ArrayList<>();
        this.row4 = new ArrayList<>();
        this.endTurn = false; // Assuming you want to initialize it to false
    }



    public List<List<Card>> getRows() {
        List<List<Card>> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        return rows;
    }

}
