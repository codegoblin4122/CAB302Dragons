module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    requires org.junit.jupiter.api;



//    requires mysql.connector.j;


    exports com.example.cab302;
    opens com.example.cab302 to javafx.fxml, org.junit.jupiter.api;
    exports com.example.cab302.controller;
    exports com.example.cab302.model;
    opens com.example.cab302.controller to javafx.fxml;
}