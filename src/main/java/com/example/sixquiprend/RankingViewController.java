package com.example.sixquiprend;

import com.example.sixquiprend.Players.AbstractPlayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class RankingViewController {
    @FXML
    private TableView<AbstractPlayer> tableView;

    @FXML
    private TableColumn<AbstractPlayer, String> nameColumn;

    @FXML
    private TableColumn<AbstractPlayer, Integer> tdbColumn;

    private ObservableList<AbstractPlayer> players = FXCollections.observableArrayList();

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tdbColumn.setCellValueFactory(new PropertyValueFactory<>("tdb"));

        tableView.setItems(players);
    }

    public void setPlayers(List<AbstractPlayer> players) {
        this.players.clear();
        this.players.addAll(players);
    }
}





