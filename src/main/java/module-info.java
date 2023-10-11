module com.example.itss_rentbike {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rentbike to javafx.fxml;
    opens com.example.rentbike.controllers.test to javafx.fxml;
    opens com.example.rentbike.controllers.test.station to javafx.fxml;
    opens com.example.rentbike.controllers.test.home to javafx.fxml;
    opens com.example.rentbike.controllers.test.bike to javafx.fxml;
    opens com.example.rentbike.controllers.test.payment to javafx.fxml;
    exports com.example.rentbike;

    requires static lombok;
    requires java.sql;
    requires mysql.connector.j;
}