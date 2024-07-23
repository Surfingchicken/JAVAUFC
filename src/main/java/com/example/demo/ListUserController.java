package com.example.demo;

import back.java.core.dto.UserDTO;
import back.java.core.services.AuthService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListUserController implements Initializable {

    @FXML
    private TableView<UserDTO> userTable;

    @FXML
    private TableColumn<UserDTO, String> idColumn;

    @FXML
    private TableColumn<UserDTO, String> usernameColumn;

    @FXML
    private TableColumn<UserDTO, String> emailColumn;

    @FXML
    private TableColumn<UserDTO, String> roleColumn;

    private AuthService authService;
    private ObservableList<UserDTO> userList;

    public ListUserController() {
        // No-argument constructor
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (userTable == null) {
            throw new IllegalStateException("userTable has not been injected.");
        }

        if (idColumn == null || usernameColumn == null || emailColumn == null || roleColumn == null) {
            throw new IllegalStateException("One or more table columns are not initialized.");
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getRoles() != null) {
                return new javafx.beans.property.SimpleStringProperty(cellData.getValue().getRoles().getNom());
            }
            return new javafx.beans.property.SimpleStringProperty("Unknown");
        });
        userList = FXCollections.observableArrayList();
        userTable.setItems(userList);
        loadUsersAsync();
    }
    private void loadUsersAsync() {
        Task<List<UserDTO>> task = new Task<>() {
            @Override
            protected List<UserDTO> call() {
                try {
                    // Fetch users from AuthService
                    return authService.listUsers(1, 10); // Fetch first page with 10 users
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            @Override
            protected void succeeded() {
                List<UserDTO> users = getValue();
                if (users != null) {
                    userList.setAll(users);
                }
            }

            @Override
            protected void failed() {
                Throwable exception = getException();
                exception.printStackTrace();

                // Show an alert to the user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to load users");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        };

        new Thread(task).start();
    }
}
