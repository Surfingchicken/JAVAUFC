package com.example.demo;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import back.java.core.dto.TacheDTO;
import back.java.core.dto.UserDTO;
import back.java.core.services.TacheService;
import com.example.demo.UserSession;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class PlanningController {

    @FXML
    private CalendarView calendarView;

    private final TacheService tacheService;
    private UserDTO currentUser;

    public PlanningController() {
        this.tacheService = new TacheService();
    }

    @FXML
    public void initialize() {
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


    private void loadTasksIntoCalendar(Calendar calendar) {
        List<TacheDTO> tasks = tacheService.listTaches();
        for (TacheDTO task : tasks) {
            Entry<String> entry = new Entry<>(task.getNom());
            LocalDateTime startDateTime = convertToLocalDateTime(task.getDateDebut());
            LocalDateTime endDateTime = convertToLocalDateTime(task.getDateFin());
            entry.changeStartDate(startDateTime.toLocalDate());
            entry.changeEndDate(endDateTime.toLocalDate());
            entry.changeStartTime(startDateTime.toLocalTime());
            entry.changeEndTime(endDateTime.toLocalTime());
            calendar.addEntry(entry);
        }
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private void showTaskDetails(Entry<?> entry) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Task Details");
        alert.setHeaderText(entry.getTitle());
        alert.setContentText("Details about the task...");
        alert.showAndWait();
    }
}

