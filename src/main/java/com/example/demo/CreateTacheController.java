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
                    ObjectNode tacheJson = (ObjectNode) objectMapper.valueToTree(tacheDTO);
                    tacheJson.put("executeur", userId);
                    Long createurId = tacheService.getUserId();
                    tacheJson.put("createur", createurId);
                    tacheJson.remove("id");
                    tacheService.createTache(tacheJson.toString());
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Task has been created successfully!");
                }
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
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, inputFormatter);
        return localDateTime.format(outputFormatter);
    }

    private String formatDate(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, inputFormatter);
        return localDateTime.format(outputFormatter);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
