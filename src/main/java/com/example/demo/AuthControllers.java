package com.example.demo;

import back.java.core.services.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthControllers implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Button goToRegisterButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(this::handleLoginAction);
        goToRegisterButton.setOnAction(this::handleGoToRegisterAction);
    }

    private void handleLoginAction(ActionEvent event) {
        AuthService authService = new AuthService();
        String token = "";
        try {
            token = authService.login(usernameInput.getText(), passwordInput.getText());

        } catch (Exception e) {
            showAlert("Login Error", e.getMessage());
            return;
        }
        UserSession.getInstance().setUsername(usernameInput.getText());
        UserSession.getInstance().setToken(token);
        loadMainMenu(event);
    }

    private void handleGoToRegisterAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
            Stage stage = new Stage();
            stage.setTitle("UQC Manager");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Navigation Error", "Unable to load the registration page. Please try again later.");
        }
    }

    private void loadMainMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_menu.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Loading Error", "Unable to load the main menu. Please try again later.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
