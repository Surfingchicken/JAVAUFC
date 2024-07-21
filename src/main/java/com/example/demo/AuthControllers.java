package com.example.demo;

import back.java.core.services.AuthService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();

                // Debugging lines
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

                AuthService authService = new AuthService();
                String token = authService.login(username, password);

                // Debugging line
                System.out.println("Token: " + token);

                if (token == null || token.isEmpty()) {
                    System.out.println("Combinaison Incorrecte");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Combinaison Incorrecte");
                    alert.show();
                } else {
                    System.out.println("Token: " + token);
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_menu.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });
    }
}
