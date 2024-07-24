module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires jakarta.persistence;
    requires com.calendarfx.view;
    requires jfxtras.controls;

    opens com.example.demo to javafx.fxml;
    opens back.java.core.services to com.fasterxml.jackson.databind;
    opens back.java.core.dto to javafx.base;
    exports com.example.demo;
    exports back.java.core.dto to com.fasterxml.jackson.databind;
    exports back.java.core.datas to com.fasterxml.jackson.databind;

}