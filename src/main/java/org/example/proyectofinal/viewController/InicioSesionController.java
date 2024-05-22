package org.example.proyectofinal.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.proyectofinal.controller.ModelFactoryController;
import org.example.proyectofinal.model.*;


public class InicioSesionController {

    ModelFactoryController mfm = ModelFactoryController.getInstance();

    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField contraseñaField;
    @FXML
    private Main aplicacion;

    public void setAplicacion(Main aplicacion) {
        this.aplicacion = aplicacion;
    }

    @FXML
    void login(ActionEvent event) {
        String idUsuario= this.usuarioField.getText();
        String contraseña = this.contraseñaField.getText();
        usuarioField.clear();
        contraseñaField.clear();
        if (this.validarDatos(idUsuario, contraseña)) {
            Usuario usuario = this.mfm.obtenerUsuario(idUsuario);
            if (usuario != null) {
                if (usuario instanceof Administrador) {
                    if (((Administrador)usuario).getContraseña().equals(contraseña)) {
                        this.aplicacion.mostrarVentanaGestionAdmin(event,(Administrador) usuario);
                    } else {
                        this.mostrarMensaje("Inicio de Sesion", "La accion no se puede completar", "Contraseña incorrecta", Alert.AlertType.INFORMATION);
                    }
                } else if (usuario instanceof Cliente) {
                    if (((Cliente)usuario).getContraseña().equals(contraseña)) {
                        System.out.println("Eres un cliente "); //mientras tanto
                    } else {
                        this.mostrarMensaje("Inicio de Sesion", "La accion no se puede completar", "Contraseña incorrecta", Alert.AlertType.INFORMATION);
                    }
                } else if (usuario instanceof Publicador) {
                    if (((Publicador)usuario).getContraseña().equals(contraseña)) {
                        this.aplicacion.mostrarVentanaGestionPublic(event,(Publicador) usuario);
                    } else {
                        this.mostrarMensaje("Inicio de Sesion", "La accion no se puede completar", "Contraseña incorrecta", Alert.AlertType.INFORMATION);
                    }
                } else if (usuario instanceof GestorDeProcesamiento) {
                    if (((GestorDeProcesamiento) usuario).getContraseña().equals(contraseña)) {
                        this.aplicacion.mostrarVentanaGestionProcesamiento(event,(GestorDeProcesamiento) usuario);                    } else {
                        this.mostrarMensaje("Inicio de Sesion", "La accion no se puede completar", "Contraseña incorrecta", Alert.AlertType.INFORMATION);
                    }
                } else if (usuario instanceof GestorDeEnvio) {
                    if (((GestorDeEnvio) usuario).getContraseña().equals(contraseña)) {
                        System.out.println("Eres un Gestor De Envio"); //mientras tanto
                    } else {
                        this.mostrarMensaje("Inicio de Sesion", "La accion no se puede completar", "Contraseña incorrecta", Alert.AlertType.INFORMATION);
                    }
                }
            } else {
                    this.mostrarMensaje("Inicio de Sesion", "La accion no se puede completar", "Debe ingresar un usuario registrado", Alert.AlertType.INFORMATION);
                }
        }
    }

    private boolean validarDatos(String identificacion, String contrasenia) {
        String mensaje = "";
        if (identificacion.equals("") || identificacion == null) {
            mensaje = mensaje + "La identificacion";
        }

        if (contrasenia.equals("") || contrasenia == null) {
            mensaje = mensaje + "La contrasenia";
        }

        if (mensaje.equals("")) {
            return true;
        } else {
            this.mostrarMensaje("Inicio de Sesion","La accion no se puede completar", "Para continuar, asegurese de que todos los campos del formulario estén completos", Alert.AlertType.INFORMATION);
            return false;
        }
    }

    public void mostrarMensaje(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}

