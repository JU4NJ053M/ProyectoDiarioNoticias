package org.example.proyectofinal.viewController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.proyectofinal.model.Administrador;
import org.example.proyectofinal.model.GestorDeProcesamiento;
import org.example.proyectofinal.model.Publicador;

public class Main extends Application {

    private Stage primaryStage;

    public Main() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.mostrarVentanaInicioSesion();
    }

    public void mostrarVentanaInicioSesion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/org/example/proyectofinal/viewController/InicioSesionView.fxml"));
            AnchorPane anchorPane = loader.load();

            InicioSesionController controller = loader.getController();
            controller.setAplicacion(this);

            Scene scene = new Scene(anchorPane);
            this.primaryStage.setScene(scene);
            this.primaryStage.centerOnScreen();

            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void mostrarVentanaGestionAdmin(ActionEvent event, Administrador administrador) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/org/example/proyectofinal/viewController/GestionAdminView.fxml"));
            AnchorPane anchorPane = loader.load();

            GestionAdminController controller = loader.getController();
            controller.setAplicacion(this,administrador);

            Scene scene = new Scene(anchorPane);
            this.primaryStage.setScene(scene);
            this.primaryStage.centerOnScreen();
            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    public void mostrarVentanaGestionPublic(ActionEvent event, Publicador publicador) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/org/example/proyectofinal/viewController/GestionPublicView.fxml"));
            AnchorPane anchorPane = loader.load();

            GestionPublicController controller = loader.getController();
            controller.setAplicacion(this,publicador);

            Scene scene = new Scene(anchorPane);
            this.primaryStage.setScene(scene);
            this.primaryStage.centerOnScreen();
            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    public void mostrarVentanaGestionProcesamiento(ActionEvent event, GestorDeProcesamiento gestor) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/org/example/proyectofinal/viewController/GestionProcesamientoView.fxml"));
            AnchorPane anchorPane = loader.load();

            GestionProcesamientoController controller = loader.getController();
            controller.setAplicacion(this,gestor);

            Scene scene = new Scene(anchorPane);
            this.primaryStage.setScene(scene);
            this.primaryStage.centerOnScreen();
            this.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}