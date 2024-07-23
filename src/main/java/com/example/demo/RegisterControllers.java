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

public class RegisterControllers implements Initializable {
    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField passwordInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerButton.setOnAction(this::handleRegisterAction);
    }

    private void handleRegisterAction(ActionEvent event) {
        AuthService authService = new AuthService();
        try {
            String token = authService.signup(usernameInput.getText(), emailInput.getText(), passwordInput.getText());
        } catch (Exception e) {
            showAlert("Registration Error", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void goToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            showAlert("Loading Error", "Unable to load the login page. Please try again later.");
        }
    }
}
