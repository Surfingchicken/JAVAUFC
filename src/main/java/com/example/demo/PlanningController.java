package com.example.demo;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import back.java.core.dto.TacheDTO;
import back.java.core.services.TacheService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PlanningController {

    @FXML
    private CalendarView calendarView;

    private final TacheService tacheService;

    public PlanningController() {
        this.tacheService = new TacheService();
    }

    @FXML
    public void initialize() throws IOException {
        Calendar calendar = new Calendar("My Tasks");
        CalendarSource calendarSource = new CalendarSource("Tasks");
        calendarSource.getCalendars().add(calendar);
        calendarView.getCalendarSources().add(calendarSource);

        loadTasksIntoCalendar(calendar);

        calendarView.setEntryDetailsPopOverContentCallback(param -> {
            Entry<?> entry = param.getEntry();
            showTaskDetails(entry);
            return null;
        });
    }

    private void loadTasksIntoCalendar(Calendar calendar) throws IOException {
        List<TacheDTO> tasks = tacheService.listTaches();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        for (TacheDTO task : tasks) {
            Entry<String> entry = new Entry<>(task.getNom());
            LocalDateTime startDateTime = LocalDateTime.parse(task.getDateDebut(), formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(task.getDateFin(), formatter);

            entry.changeStartDate(startDateTime.toLocalDate());
            entry.changeEndDate(endDateTime.toLocalDate());
            entry.changeStartTime(startDateTime.toLocalTime());
            entry.changeEndTime(endDateTime.toLocalTime());
            entry.setId(Long.toString(task.getId()));

            calendar.addEntry(entry);
        }
    }

    private void showTaskDetails(Entry<?> entry) {
        String taskIdStr = entry.getId();
        Long taskId = Long.parseLong(taskIdStr);
        TacheDTO task = tacheService.getTache(taskId);
        if (task != null) {
            String content = String.format(
                    "Nom: %s%nDescription: %s%nDate de début: %s%nDate de fin: %s%nType: %s%nCréateur: %s",
                    task.getNom(),
                    task.getDescription(),
                    task.getDateDebut(),
                    task.getDateFin(),
                    task.getType(),
                    task.getCreateurTache().getUsername()
            );
            Alert alert = new Alert(AlertType.INFORMATION, content, ButtonType.OK);
            alert.setTitle("Détails de la tâche");
            alert.setHeaderText(task.getNom());
            alert.showAndWait();
        }
    }
}
