package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavControllers implements Initializable {

    @FXML
    private SplitPane mainSplitPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization code can go here if needed
    }

    @FXML
    public void navListEmployees(ActionEvent event) throws IOException {
        loadPage("list_employees");
    }

    @FXML
    public void navMeetings(ActionEvent event) throws IOException {
        loadPage("meetings");
    }

    @FXML
    public void navProfile(ActionEvent event) throws IOException {
        loadPage("profile");
    }

    @FXML
    public void navPlanning(ActionEvent event) throws IOException {
        loadPage("planning");
    }

    @FXML
    public void navSurveys(ActionEvent event) throws IOException {
        loadPage("surveys");
    }
    @FXML
    public void navModifyProfile(ActionEvent actionEvent) throws IOException {
        loadPage("modify_profile");
    }

    private void loadPage(String pageName) throws IOException {
        FxmlNav object = new FxmlNav();
        Pane view = object.getPage(pageName);
        mainSplitPane.getItems().set(1, view);
    }


}
