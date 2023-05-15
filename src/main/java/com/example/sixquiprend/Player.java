package com.example.sixquiprend;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Player {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int numberTdb;
    @Getter @Setter
    private List<Card> hand;
    public Player(String name){
        this.name = name;
        this.numberTdb = 0;
        this.hand = new ArrayList<Card>();
    }
    public int Tdb(List<Card> listOfCard){
        int numberOfTdb = 0;
        for(int i = 0; i < listOfCard.size(); i++){
            numberOfTdb = numberOfTdb + listOfCard.get(i).getTdb();
        }
        return numberOfTdb;
    }
}
