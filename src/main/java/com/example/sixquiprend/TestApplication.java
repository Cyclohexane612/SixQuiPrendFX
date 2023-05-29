package com.example.sixquiprend;

import com.example.sixquiprend.Card;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TestApplication extends Application{
    private static final int NUM_CARDS = 104;

    @Override
    public void start(Stage primaryStage) {
        // Créez une disposition pour afficher les cartes
        FlowPane cardPane = new FlowPane();
        cardPane.setPadding(new Insets(10));
        cardPane.setHgap(10);
        cardPane.setVgap(10);

        // Générez les cartes et ajoutez-les à la disposition
        for (int i = 1; i <= NUM_CARDS; i++) {
            Card card = new Card(i);
            card.configureCardAppearance();
            cardPane.getChildren().add(card.getImage());
        }

        // Créez une scène et affichez-la
        Scene scene = new Scene(cardPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
