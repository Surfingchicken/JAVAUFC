package com.example.demo;

import back.java.core.services.AuthService;
import back.java.core.services.RoleService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavControllers implements Initializable {
    @FXML
    private Label identificationLabel;

    @FXML
    private SplitPane mainSplitPane;

    private final AuthService authService = new AuthService();
    private final RoleService roleService = new RoleService();

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
    public void navProfile(ActionEvent event) throws IOException {
        loadPage("profile");
    }

    @FXML
    public void navPlanning(ActionEvent event) throws IOException {
        loadPage("planning");
    }
    @FXML
    public void navListTaches(ActionEvent actionEvent) throws IOException {
        loadPage("list_taches");
    }
    private void loadPage(String pageName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pageName + ".fxml"));
        Pane view = loader.load();

        if ("list_employees".equals(pageName)) {
            ListUserController controller = loader.getController();
            controller.setAuthService(authService);
            controller.setRoleService(roleService);
        }

        mainSplitPane.getItems().set(1, view);
    }

    public void logOut(ActionEvent actionEvent) {
        AuthService authService = new AuthService();
        authService.logout();
        Platform.exit();
    }


}
