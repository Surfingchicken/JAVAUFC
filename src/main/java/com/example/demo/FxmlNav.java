package com.example.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class FxmlNav {
    private AnchorPane view;

    public Pane getPage(String fileName) throws IOException {
            URL fileUrl = Application.class.getResource(fileName+".fxml");
            if (fileUrl==null){
                fileUrl = Application.class.getResource("error.fxml");
            }
            view = new FXMLLoader().load(fileUrl);
            return view;
    }
}
