package com.example.demo;

import back.java.core.dto.RoleDTO;
import back.java.core.dto.UserDTO;
import back.java.core.services.AuthService;
import back.java.core.services.RoleService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListUserController implements Initializable {

    @FXML
    private TableView<UserDTO> userTable;

    @FXML
    private TableColumn<UserDTO, Long> idColumn;

    @FXML
    private TableColumn<UserDTO, String> usernameColumn;

    @FXML
    private TableColumn<UserDTO, String> emailColumn;

    @FXML
    private TableColumn<UserDTO, String> roleColumn;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> roleComboBox;

    private AuthService authService;
    private RoleService roleService;
    private ObservableList<UserDTO> userList;
    private FilteredList<UserDTO> filteredList;

    public ListUserController() {
        // No-argument constructor
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
        System.out.println("AuthService has been set.");
        checkServicesInitialized();
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
        System.out.println("RoleService has been set.");
        checkServicesInitialized();
    }

    private void checkServicesInitialized() {
        if (authService != null && roleService != null) {
            initializeData();
        }
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
                return new SimpleStringProperty(cellData.getValue().getRoles().getName());
            }
            return new SimpleStringProperty("Unknown");
        });

        userList = FXCollections.observableArrayList();
        filteredList = new FilteredList<>(userList, p -> true);
        userTable.setItems(filteredList);

        // Setup filters
        searchField.textProperty().addListener((obs, oldText, newText) -> filterBySearch());
        roleComboBox.setOnAction(event -> filterByRole());
    }

    private void initializeData() {
        loadUsersAsync();
        loadRolesAsync();
    }

    private void loadUsersAsync() {
        Task<List<UserDTO>> task = new Task<>() {
            @Override
            protected List<UserDTO> call() {
                try {
                    // Ensure authService is not null
                    if (authService == null) {
                        throw new IllegalStateException("AuthService is not initialized");
                    }
                    // Fetch users from AuthService
                    List<UserDTO> users = authService.listUsers(1, 10); // Fetch first page with 10 users
                    System.out.println("Users fetched: " + users.size()); // Log fetched users count
                    return users;
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
                    System.out.println("Users loaded into userList: " + userList.size()); // Log loaded users count
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

    private void loadRolesAsync() {
        Task<List<RoleDTO>> task = new Task<>() {
            @Override
            protected List<RoleDTO> call() {
                try {
                    if (roleService == null) {
                        throw new IllegalStateException("RoleService is not initialized");
                    }
                    List<RoleDTO> roles = roleService.listRoles();
                    System.out.println("Roles fetched: " + roles.size()); // Log fetched roles count
                    return roles;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void succeeded() {
                List<RoleDTO> roles = getValue();
                if (roles != null) {
                    roleComboBox.getItems().clear();
                    roleComboBox.getItems().add("All");
                    for (RoleDTO role : roles) {
                        roleComboBox.getItems().add(role.getName());
                    }
                    System.out.println("Roles loaded into roleComboBox: " + roleComboBox.getItems().size()); // Log loaded roles count
                }
            }

            @Override
            protected void failed() {
                Throwable exception = getException();
                exception.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to load roles");
                alert.setContentText(exception.getMessage());
                alert.showAndWait();
            }
        };

        new Thread(task).start();
    }

    private void filterBySearch() {
        String searchText = searchField.getText().toLowerCase();
        filteredList.setPredicate(user -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            return user.getUsername().toLowerCase().contains(searchText) ||
                    user.getEmail().toLowerCase().contains(searchText);
        });
    }

    private void filterByRole() {
        String selectedRole = roleComboBox.getSelectionModel().getSelectedItem();
        if (selectedRole != null && !selectedRole.equalsIgnoreCase("All")) {
            filteredList.setPredicate(user -> {
                if (user.getRoles() != null) {
                    return user.getRoles().getName().equalsIgnoreCase(selectedRole);
                }
                return false;
            });
        } else {
            filteredList.setPredicate(user -> true);
        }
    }
}
