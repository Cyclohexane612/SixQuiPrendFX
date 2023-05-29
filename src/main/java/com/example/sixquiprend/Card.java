package com.example.sixquiprend;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class Card {
    @FXML
    @Getter
    private Pane image;
    @Getter
    private final int number;
    @Getter
    private int tdb;
    public Card(int number){
        this.number = number;
        this.tdb = getTdb();

        // Créez les labels et configurez-les
        numberLabel = new Label(String.valueOf(number));
        tdbLabel = new Label(String.valueOf(tdb));

        // Ajoutez les labels à l'image StackPane
        image.getChildren().addAll(tdbLabel, numberLabel);

        // Positionnez les labels correctement
        StackPane.setAlignment(numberLabel, Pos.CENTER);
        StackPane.setAlignment(tdbLabel, Pos.TOP_CENTER);
    }

    public int getTdb(){
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
