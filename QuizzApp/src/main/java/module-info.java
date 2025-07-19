module com.lby.quizzapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    requires java.sql;


    opens com.lby.quizzapp to javafx.fxml;
    exports com.lby.quizzapp;
    exports com.lby.pojo;
}
