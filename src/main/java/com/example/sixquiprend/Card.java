package com.example.sixquiprend;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lombok.Getter;
import lombok.Setter;
import org.controlsfx.control.decoration.GraphicDecoration;

public class Card {
    @FXML
    @Getter
    private BorderPane image;
    @Getter
    private final int number;
    @Getter @Setter
    private AbstractPlayer player;
    @Getter
    private int tdb;
    @FXML
    private Label numberLabel;
    @FXML
    private FlowPane tdbFlowPane;

    public Card(int number) {
        this.number = number;
        this.tdb = getTdb();

        image = new BorderPane();

        numberLabel = new Label(Integer.toString(number));
        tdbFlowPane = new FlowPane();

        // Placer le label au centre du BorderPane
        BorderPane.setAlignment(numberLabel, Pos.CENTER);

        image.setTop(tdbFlowPane); // Placer le FlowPane tout en haut
        image.setCenter(numberLabel); // Placer le label au centre

        image.setPrefSize(100, 150);
        configureCardAppearance();
        configureTdbImages();
        configureLabelAppearance();
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

    public void configureTdbImages() {
        if (tdb > 0) {
            String tdbImagePath = getClass().getResource("/image/tdb.png").toExternalForm();
            Image tdbImage = new Image(tdbImagePath);
            for (int i = 0; i < tdb; i++) {
                ImageView tdbImageView = new ImageView(tdbImage);
                tdbImageView.setPreserveRatio(true);
                tdbImageView.setFitWidth(13);
                tdbImageView.setFitHeight(13);
                tdbFlowPane.getChildren().add(tdbImageView);
            }
        }
    }
    public void configureLabelAppearance() {
        // Changer la police
        Font font = Font.font("Arial", FontWeight.BOLD, 24);
        numberLabel.setFont(font);
        // Changer la couleur
        numberLabel.setTextFill(Color.PURPLE);
    }
}
