package com.example.demo;

import back.java.core.dto.TacheDTO;
import back.java.core.services.TacheService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<TacheDTO, Button> modifyColumn;

    @FXML
    private Button addTaskButton;

    private TacheService tacheService;
    private ObservableList<TacheDTO> tacheList;

    @FXML
    public void initialize() {
        tacheService = new TacheService();
        tacheList = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        loadTaches();
        userTable.setItems(tacheList);


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

            // Reload tasks after closing the form
            loadTaches();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
