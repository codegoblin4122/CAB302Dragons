module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
//    requires mysql.connector.j;


    opens com.example.cab302 to javafx.fxml;
    exports com.example.cab302;
    exports com.example.cab302.controller;
    exports com.example.cab302.model;
    opens com.example.cab302.controller to javafx.fxml;
}