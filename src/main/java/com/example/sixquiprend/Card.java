package com.example.sixquiprend;

import lombok.Getter;

public class Card {
    @Getter
    private final int number;
    @Getter
    private int tdb;
    public Card(int number){
        this.number=number;
    }
    public int getTdb(){
            //Define the number of beef's head
            if(number%11 == 0 ){
                if(number == 55 ){
                    return 7;
                }
                return 5;
            }else if(number%10 == 0){
                return 3;
            }else if(number%5 == 0){
                return 2;
            }else{
                return 1;
            }
    }
}
