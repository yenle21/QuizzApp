module com.lby.quizzapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires lombok;
    requires java.sql;


    opens com.lby.quizzapp to javafx.fxml;
    exports com.lby.quizzapp;
}
