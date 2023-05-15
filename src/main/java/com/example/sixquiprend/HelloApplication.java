package com.example.tp6;

import com.example.sixquiprend.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("title_screen.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        HelloController controller = loader.getController();
        controller.setMainScene(scene); // Définir la scène principale pour le contrôleur

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}