package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.IOException;

import back.java.core.dto.TacheDTO;
import back.java.core.services.TacheService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
    private final ObjectMapper objectMapper = new ObjectMapper();

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
            tacheDTO.setType("tache"); // Fixed value

            Long userId = tacheService.getUserId();
            if (userId == null) {
                showAlert(Alert.AlertType.ERROR, "Session Error", "Could not retrieve user ID.");
                return;
            }

            try {
                // Convert TacheDTO to JSON
                ObjectNode tacheJson = (ObjectNode) objectMapper.valueToTree(tacheDTO);
                // Replace executeurTache object with executeur ID
                tacheJson.put("executeur", userId);
                // Assuming the createur ID is also needed and retrieved similarly
                Long createurId = tacheService.getUserId(); // Modify as needed
                tacheJson.put("createur", createurId);
                // Remove the "id" field
                tacheJson.remove("id");

                String payload = tacheJson.toString();
                tacheService.createTache(payload); // Send JSON string as payload

                showAlert(Alert.AlertType.INFORMATION, "Success", "Task has been created successfully!");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Service Error", "Failed to create task: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please check your inputs and try again.");
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

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
