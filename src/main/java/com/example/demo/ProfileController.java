package com.example.demo;

import back.java.core.dto.UserDTO;
import back.java.core.services.ProfileService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ProfileController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    private Label profileLabel;

    private final ProfileService profileService = new ProfileService();


    @FXML
    private void updateProfile() {
        try {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            UserDTO updatedUser = profileService.updateUser(
                    username.isEmpty() ? null : username,
                    email.isEmpty() ? null : email,
                    password.isEmpty() ? null : password
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Profile updated successfully");
            alert.setContentText("User: " + (updatedUser.getUsername() != null ? updatedUser.getUsername() : "N/A"));
            alert.showAndWait();
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to update profile");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    private void clearFields() {
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }
}
