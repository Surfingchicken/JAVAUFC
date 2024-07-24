package com.example.demo;

import back.java.core.dto.TacheDTO;
import back.java.core.services.TacheService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ListTachesController {

    @FXML
    private TableView<TacheDTO> userTable;

    @FXML
    private TableColumn<TacheDTO, Long> idColumn;

    @FXML
    private TableColumn<TacheDTO, String> nameColumn;

    @FXML
    private TableColumn<TacheDTO, String> descriptionColumn;

    @FXML
    private TableColumn<TacheDTO, String> dateDebutColumn;

    @FXML
    private TableColumn<TacheDTO, String> dateFinColumn;

    @FXML
    private TableColumn<TacheDTO, Void> modifyColumn;

    @FXML
    private Button addTaskButton;

    private TacheService tacheService;
    private ObservableList<TacheDTO> tacheList;

    @FXML
    public void initialize() {
        tacheService = new TacheService();
        tacheList = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        loadTaches();
        userTable.setItems(tacheList);

        modifyColumn.setCellFactory(column -> new TableCell<TacheDTO, Void>() {
            private final Button modifyButton = new Button("*");
            private final Button deleteButton = new Button("-");

            {
                // Style buttons
                modifyButton.setStyle("-fx-background-color: lightblue;");
                deleteButton.setStyle("-fx-background-color: lightcoral;");

                modifyButton.setOnAction(e -> handleModify(getTableRow().getItem()));
                deleteButton.setOnAction(e -> handleDelete(getTableRow().getItem()));

                HBox hbox = new HBox(5, modifyButton, deleteButton);
                setGraphic(hbox);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : getGraphic());
            }
        });

    }

    private void handleDelete(TacheDTO tache) {
        if (tache != null) {
            tacheService.deleteTache(tache.getId());
            showAlert(Alert.AlertType.INFORMATION, "Success", "Task has been deleted successfully!");
            loadTaches();
        }
    }
    private void handleModify(TacheDTO tache) {
        if (tache != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create_tache.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                CreateTacheController controller = fxmlLoader.getController();
                controller.setTacheForUpdate(tache);
                Stage stage = new Stage();
                stage.setTitle("Update Tache");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
                loadTaches();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadTaches() {
        try {
            tacheList.setAll(tacheService.listTaches());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addTask(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create_tache.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Créer une nouvelle tâche");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            loadTaches();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
