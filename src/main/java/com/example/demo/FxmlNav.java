package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FxmlNav {

    public Pane getPage(String pageName) {
        Pane view = null;
        try {
            view = FXMLLoader.load(getClass().getResource(pageName + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
