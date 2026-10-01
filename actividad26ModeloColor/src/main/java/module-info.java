module com.mycompany.actividad26modelocolor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.actividad26modelocolor to javafx.fxml;
    exports com.mycompany.actividad26modelocolor;
}
