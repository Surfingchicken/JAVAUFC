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

import back.java.core.dto.TacheDTO;
import back.java.core.services.TacheService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.stage.Stage;

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
    private TacheDTO tacheToUpdate;

    @FXML
    private void handleSave(ActionEvent event) {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        String dateDebut = dateDebutField.getText();
        String dateFin = dateFinField.getText();

        if (validateInputs(name, description, dateDebut, dateFin)) {
            TacheDTO tacheDTO = tacheToUpdate != null ? tacheToUpdate : new TacheDTO();
            tacheDTO.setNom(name);
            tacheDTO.setDescription(description);
            tacheDTO.setDateDebut(parseDate(dateDebut));
            tacheDTO.setDateFin(parseDate(dateFin));
            tacheDTO.setType("tache");

            Long userId = tacheService.getUserId();
            if (userId == null) {
                showAlert(Alert.AlertType.ERROR, "Session Error", "Could not retrieve user ID.");
                return;
            }

            try {
                if (tacheToUpdate != null) {
                    // Update existing task
                    tacheService.updateTache(tacheToUpdate.getId(), tacheDTO);
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Task has been updated successfully!");
                } else {
                    // Create new task
                    ObjectNode tacheJson = objectMapper.createObjectNode();
                    tacheJson.put("nom", tacheDTO.getNom());
                    tacheJson.put("description", tacheDTO.getDescription());
                    tacheJson.put("date_debut", tacheDTO.getDateDebut());
                    tacheJson.put("date_fin", tacheDTO.getDateFin());
                    tacheJson.put("type", tacheDTO.getType());
                    tacheJson.put("executeur", userId);
                    Long createurId = tacheService.getUserId();
                    tacheJson.put("createur", createurId);

                    tacheService.createTache(tacheJson.toString());
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Task has been created successfully!");
                }
                closeWindow();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Service Error", "Failed to save task: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please check your inputs and try again.");
        }
    }

    public void setTacheForUpdate(TacheDTO tache) {
        this.tacheToUpdate = tache;
        nameField.setText(tache.getNom());
        descriptionArea.setText(tache.getDescription());
        dateDebutField.setText(formatDate(tache.getDateDebut()));
        dateFinField.setText(formatDate(tache.getDateFin()));
    }

    private boolean validateInputs(String name, String description, String dateDebut, String dateFin) {
        return name != null && !name.trim().isEmpty() &&
                description != null && !description.trim().isEmpty() &&
                isValidDateTime(dateDebut) &&
                isValidDateTime(dateFin);
    }

    private boolean isValidDateTime(String dateTime) {
        try {
            parseDate(dateTime);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private String parseDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        // Parse the input date-time string to a LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, inputFormatter);

        // Convert LocalDateTime to ZonedDateTime with UTC offset
        ZonedDateTime zonedDateTime = localDateTime.atZone(java.time.ZoneOffset.UTC);

        // Format the ZonedDateTime to ISO 8601 format
        return zonedDateTime.format(outputFormatter);
    }

    private String formatDate(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Parse the ISO 8601 date-time string to a ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime, inputFormatter);

        // Format the ZonedDateTime to the desired format
        return zonedDateTime.format(outputFormatter);
    }
    private void closeWindow() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
