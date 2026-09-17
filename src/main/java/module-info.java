module pl.kalendarz.kalendarz {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;
    requires java.sql;

    opens pl.kalendarz.kalendarz to javafx.fxml;
    exports pl.kalendarz.kalendarz;
}