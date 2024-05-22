module org.example.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens org.example.proyectofinal.model to javafx.base;
    opens org.example.proyectofinal.viewController to javafx.fxml;
    exports org.example.proyectofinal.controller;
    opens org.example.proyectofinal.controller to javafx.fxml;
    exports org.example.proyectofinal.util;
    exports org.example.proyectofinal.viewController;

}