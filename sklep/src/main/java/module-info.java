module com.example.sklep {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires javafx.graphics;
    opens com.example.sklep to javafx.fxml;
    opens com.example.sklep.database to javafx.fxml, org.hibernate.orm.core;
    opens com.example.sklep.controlers to javafx.fxml;
    exports com.example.sklep;
    exports com.example.sklep.controlers;
    exports com.example.sklep.database;
}