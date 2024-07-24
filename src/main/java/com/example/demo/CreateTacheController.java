package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import back.java.core.dto.TacheDTO;
import back.java.core.dto.UserDTO;
import back.java.core.services.TacheService;

public class CreateTacheController {

    @FXML
    private TextField nameField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField dateDebutField; // Assuming this is a TextField for date input
    @FXML
    private TextField dateFinField;   // Assuming this is a TextField for date input

    private final TacheService tacheService = new TacheService();

    @FXML
    private void handleSave(ActionEvent event) {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        String dateDebut = dateDebutField.getText();
        String dateFin = dateFinField.getText();

        if (validateInputs(name, description, dateDebut, dateFin)) {
            TacheDTO tacheDTO = new TacheDTO();
            tacheDTO.setNom(name);
            tacheDTO.setDescription(description);
            tacheDTO.setDateDebut(parseDate(dateDebut)); // Convert to ISO 8601 format
            tacheDTO.setDateFin(parseDate(dateFin));     // Convert to ISO 8601 format
            tacheDTO.setType("task"); // Fixed value
            tacheDTO.setExecuteurTache(new UserDTO(2L, null, null, null, null, null, null, null)); // Fixed user ID

            // Optionally set the creator if needed
            // tacheDTO.setCreateurTache(new UserDTO(creatorId, null, null, null, null, null, null, null));

            tacheService.createTache(tacheDTO);

            showAlert(AlertType.INFORMATION, "Success", "Task has been created successfully!");
        } else {
            showAlert(AlertType.ERROR, "Validation Error", "Please check your inputs and try again.");
        }
    }

    private boolean validateInputs(String name, String description, String dateDebut, String dateFin) {
        return name != null && !name.trim().isEmpty() &&
                description != null && !description.trim().isEmpty() &&
                isValidDateTime(dateDebut) &&
                isValidDateTime(dateFin);
    }

    private boolean isValidDateTime(String dateTime) {
        try {
            parseDate(dateTime); // Use custom parser for validation
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private String parseDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        // Parse the input date-time string to a LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, inputFormatter);

        // Convert LocalDateTime to ZonedDateTime with UTC offset
        ZonedDateTime zonedDateTime = localDateTime.atZone(java.time.ZoneOffset.UTC);

        // Format the ZonedDateTime to ISO 8601 format
        return zonedDateTime.format(outputFormatter);
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
