module com.example.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.example.addressbook to javafx.fxml;
    exports com.example.addressbook;

    opens com.example.activitytracker to javafx.fxml;
    exports com.example.activitytracker;
}
