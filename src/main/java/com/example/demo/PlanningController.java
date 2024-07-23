package com.example.demo;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import back.java.core.dto.TacheDTO;
import back.java.core.services.TacheService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.io.IOException;

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


    private LocalDateTime convertToLocalDateTime(String dateString) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        return zonedDateTime.toLocalDateTime();
    }

    private void showTaskDetails(Entry<?> entry) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Task Details");
        alert.setHeaderText(entry.getTitle());
        alert.setContentText("Details about the task...");
        alert.showAndWait();
    }
}
