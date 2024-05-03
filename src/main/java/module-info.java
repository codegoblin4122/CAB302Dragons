module com.example.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
<<<<<<< Updated upstream
=======
    requires com.jfoenix;
    requires javafx.graphics;
>>>>>>> Stashed changes


    opens com.example.addressbook to javafx.fxml;
    exports com.example.addressbook;

    opens com.example.activitytracker to javafx.fxml;
    exports com.example.activitytracker;

    exports com.example.activitytracker.UI to javafx.graphics;
}
