module org.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.proyectofinal to javafx.fxml;
    exports org.example.proyectofinal;
    exports org.example.proyectofinal.controller;
    opens org.example.proyectofinal.controller to javafx.fxml;
    exports org.example.proyectofinal.util;
    opens org.example.proyectofinal.util to javafx.fxml;
}