package com.example.sixquiprend;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

public class Card {
    @FXML
    @Getter
    private StackPane image;
    @Getter
    private final int number;
    @Getter @Setter
    private AbstractPlayer player;
    @Getter
    private int tdb;
    @FXML
    private Label numberLabel;
    @FXML
    private ImageView tdbImageView;

    public Card(int number) {
        this.number = number;
        this.tdb = getTdb();

        image = new StackPane();

        numberLabel = new Label(Integer.toString(number));
        tdbImageView = new ImageView();

        // Ajouter le numéro de tdb au centre de la StackPane
        StackPane.setAlignment(numberLabel, Pos.CENTER);
        image.getChildren().addAll(numberLabel, tdbImageView);

        image.setPrefSize(100, 150);
        configureCardAppearance();
        configureTdbImage();
    }

    public int getTdb() {
        if (number % 11 == 0) {
            if (number == 55) {
                return 7;
            }
            return 5;
        } else if (number % 10 == 0) {
            return 3;
        } else if (number % 5 == 0) {
            return 2;
        } else {
            return 1;
        }
    }

    public void configureCardAppearance() {
        if (tdb == 7) {
            image.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        } else if (tdb == 5) {
            image.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));
        } else if (tdb == 3) {
            image.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
        } else if (tdb == 2) {
            image.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        } else {
            image.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        }
    }

    public void configureTdbImage() {
            String tdbImageFile = "src/main/resources/image/tdb.png";
            String tdbImagePath = getClass().getResource(tdbImageFile).toExternalForm();
            Image tdbImage = new Image(tdbImagePath);
            tdbImageView.setImage(tdbImage);
            tdbImageView.setPreserveRatio(true);
            tdbImageView.setFitWidth(30);
            tdbImageView.setFitHeight(30);
    }
}
