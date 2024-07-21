package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        Scene sceneLogin = new Scene(fxmlLoader.load());
        stage.setTitle("UQC Manager");
        stage.setScene(sceneLogin);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}