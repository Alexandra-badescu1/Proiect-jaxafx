module com.example.controllers {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.xerial.sqlitejdbc;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.naming;
    requires junit;
    requires org.junit.jupiter.api;
    requires java.desktop;

    opens com.example.lab5 to javafx.fxml;
    exports com.example.lab5;
    exports com.example;
    opens com.example to javafx.fxml;
}