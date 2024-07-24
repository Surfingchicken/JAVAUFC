package com.example.demo;

import com.example.demo.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class FxmlNav {
    private AnchorPane view;
    public Pane getPage(String fileName){
        try{
            URL fileUrl = Application.class.getResource(fileName+".fxml");
            if (fileUrl==null){
                fileUrl = Application.class.getResource("error.fxml");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (IOException e) {
            System.out.println("No page " +  fileName);
        }
        return view;
    }
}
