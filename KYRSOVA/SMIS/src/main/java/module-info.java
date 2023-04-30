module com.example.smis {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.smis to javafx.fxml;
    exports com.example.smis;
    exports com.example.smis.ADMIN_DATA;
    opens com.example.smis.ADMIN_DATA to javafx.fxml;
}