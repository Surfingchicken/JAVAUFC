module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires jakarta.persistence;

    opens com.example.demo to javafx.fxml;
    opens back.java.core.services to com.fasterxml.jackson.databind;
    exports com.example.demo;
}