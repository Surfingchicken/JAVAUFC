package com.example.demo;

import back.java.core.services.AuthService;
import back.java.core.services.RoleService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavControllers implements Initializable {
    @FXML
    private Label identificationLabel;

    @FXML
    private SplitPane mainSplitPane;

    private final AuthService authService = new AuthService(); // Initialisation de AuthService
    private final RoleService roleService = new RoleService(); // Initialisation de RoleService

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSession session = UserSession.getInstance();
        String username = session.getUsername();
        identificationLabel.setText(username);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pageName + ".fxml"));
        Pane view = loader.load();

        if ("list_employees".equals(pageName)) {
            ListUserController controller = loader.getController();
            controller.setAuthService(authService); // Injecter AuthService dans le contrôleur
            controller.setRoleService(roleService); // Injecter RoleService dans le contrôleur
        }

        mainSplitPane.getItems().set(1, view);
    }
}
