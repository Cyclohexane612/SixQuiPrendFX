package com.example.sixquiprend.Controllers;

import com.example.sixquiprend.Players.AbstractPlayer;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RankingViewController {
    @FXML
    private TableView<AbstractPlayer> tableView;

    private ObservableList<AbstractPlayer> players = FXCollections.observableArrayList();

    private boolean columnsAdded = false;

    public void initialize() {
        // Associer la liste observable à la TableView
        tableView.setItems(players);
        tableView.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



    }

    public void setPlayers(List<AbstractPlayer> players) {
        // Spécifier le nombre maximal de lignes souhaité
        int maxRows = 10; // Remplacez cette valeur par le nombre maximal de lignes souhaité

        // Ajuster la taille de la liste en fonction du nombre maximal de lignes
        if (players.size() > maxRows) {
            players = players.subList(0, maxRows);
        }

        // Effacer la liste existante et ajouter les nouveaux joueurs
        this.players.setAll(players);

        // Ajouter les colonnes uniquement si elles n'ont pas déjà été ajoutées
        if (!columnsAdded) {
            addColumns();
        }
    }



    private void addColumns() {
        tableView.getColumns().clear();

        TableColumn<AbstractPlayer, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<AbstractPlayer, Integer> tdbColumn = new TableColumn<>("Nombre de têtes de bœuf");
        tdbColumn.setCellValueFactory(new PropertyValueFactory<>("tdb"));

        tableView.getColumns().addAll(nameColumn, tdbColumn);

        columnsAdded = true;
    }






    // Autres méthodes du contrôleur...
}


