module com.example.cab302 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
//    requires mysql.connector.j;


    opens com.example.cab302 to javafx.fxml;
    exports com.example.cab302;
}