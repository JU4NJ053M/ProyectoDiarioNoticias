package org.example.proyectofinal.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyectofinal.controller.ModelFactoryController;
import org.example.proyectofinal.exceptions.*;
import org.example.proyectofinal.model.*;

import java.net.URL;

public class GestionAdminController {

    ModelFactoryController mfm = ModelFactoryController.getInstance();



    @FXML
    public Label labelGestorAdmin;
    @FXML
    public TableView<Usuario> tableUsuarios;
    @FXML
    public TableColumn<Usuario,String> colNombre;
    @FXML
    public TableColumn<Usuario,String> colTelefono;
    @FXML
    public TableColumn<Usuario,String> colCorreo;
    @FXML
    public TableColumn<Usuario,String> colIdUsuario;
    @FXML
    public TableColumn<Usuario,String> colContraseña;
    @FXML
    public TableColumn<Usuario,String> colTipoUsuario;
    @FXML
    public Button btnCerrarSesion;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtTelefono;
    @FXML
    public TextField txtIdUsuario;
    @FXML
    public TextField txtContraseña;
    @FXML
    public Button btnLimpiar;
    @FXML
    public Button btnActualizar;
    @FXML
    public Button btnAñadir;
    @FXML
    public Button btnEliminar;
    @FXML
    public ComboBox comBoxTipoUsuario;
    @FXML
    public TextField txtCorreo;
    private Main aplicacion;
    private Administrador adminSesion;
    private Usuario usuarioSeleccion;
    @FXML
    private URL location;
    ObservableList<Usuario> listadoUsuarios = FXCollections.observableArrayList();


    public GestionAdminController() {
    }

    @FXML
    public void initialize() {

        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        this.colContraseña.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        this.colTipoUsuario.setCellValueFactory(cellData -> {
            String tipo = mfm.obtenerTipoUsuario(cellData.getValue());
            return new SimpleStringProperty(tipo);
        });

        getUsuarios();
        tableUsuarios.setItems(listadoUsuarios);

        this.tableUsuarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.usuarioSeleccion = newSelection;
                this.mostrarInformacion();
            }
        });
        actualizarTabla();
    }


    public void setAplicacion(Main aplicacion, Administrador administrador) {
        this.aplicacion = aplicacion;
        this.adminSesion = administrador;
        System.out.println("Usuarios a agregar a la tabla: " + getUsuarios());
        actualizarTabla();
    }
    @FXML
    void cerrarSesion(ActionEvent event) {
        this.aplicacion.mostrarVentanaInicioSesion();
    }


    public Main getAplicacion() {
        return this.aplicacion;
    }

    @FXML
    void registrarUsuario(ActionEvent event) throws ClienteException, CarpetaException, PublicadorException, AdministradorException, GestorEnvioException, GestorProcesamientoException {
        String nombre = this.txtNombre.getText();
        String idUsuario = this.txtIdUsuario.getText();
        String telefono = this.txtTelefono.getText();
        String contraseña = this.txtContraseña.getText();
        String correo = this.txtCorreo.getText();
        String tipoPersona = (String)this.comBoxTipoUsuario.getValue();
        if (this.validarDatosCompletos(nombre, idUsuario, telefono, correo, contraseña, tipoPersona)) {
            if (tipoPersona.equals("Administrador")) {
                this.registrarAdministrador(nombre, idUsuario, telefono, correo, contraseña);
                this.actualizarTabla();
            } else if (tipoPersona.equals("Cliente")) {
                this.registrarCliente(nombre, idUsuario, telefono, correo, contraseña);
                this.actualizarTabla();
            } else if (tipoPersona.equals("Gestor de Envío")) {
                this.registrarGestorEnvio(nombre, idUsuario, telefono, correo, contraseña);
                this.actualizarTabla();
            } else if (tipoPersona.equals("Gestor de Procesamiento")) {
                this.registrarGestorProcesamiento(nombre, idUsuario, telefono, correo, contraseña);
                this.actualizarTabla();
            } else {
                this.registrarPublicador(nombre, idUsuario, telefono, correo, contraseña);
                this.actualizarTabla();
            }

            this.limpiarDatos(event);
        }

    }

    @FXML
    void limpiarDatos(ActionEvent event) {
        this.txtNombre.clear();
        this.txtIdUsuario.clear();
        this.txtIdUsuario.setDisable(false);
        this.txtTelefono.clear();
        this.comBoxTipoUsuario.getSelectionModel().clearSelection();
        this.txtContraseña.setDisable(false);
        this.txtContraseña.clear();
        this.txtCorreo.clear();
        actualizarTabla();

    }

    @FXML
    void eliminarUsuario(ActionEvent event) {
        if (this.usuarioSeleccion != null) {
            if (this.usuarioSeleccion instanceof Administrador) {
                this.mostrarMensaje("Advertencia", "Error al eliminar un administrador", "No se pueden eliminar administradores", Alert.AlertType.WARNING);
            } else {
                if (this.usuarioSeleccion instanceof Cliente) {
                    this.mfm.eliminarCliente(this.adminSesion, (Cliente)this.usuarioSeleccion);
                } else if (this.usuarioSeleccion instanceof GestorDeEnvio) {
                    this.mfm.eliminarGestorEnvio(this.adminSesion, (GestorDeEnvio)this.usuarioSeleccion);
                } else if (this.usuarioSeleccion instanceof GestorDeProcesamiento) {
                    this.mfm.eliminarGestorProcesamiento(this.adminSesion, (GestorDeProcesamiento)this.usuarioSeleccion);
                } else if (this.usuarioSeleccion instanceof Publicador) {
                    this.mfm.eliminarPublicador(this.adminSesion, (Publicador)this.usuarioSeleccion);
                }

                this.actualizarTabla();
                this.mostrarMensaje("Éxito", "Eliminacion de Usuario", "Se ha eliminado el usuario correctamente", Alert.AlertType.INFORMATION);
                this.limpiarDatos(event);
            }
        } else {
            this.mostrarMensaje("Error", "Eliminación fallida", "No se ha podido eliminar Usuarios", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void actualizarUsuario(ActionEvent event) {
        String nombre = this.txtNombre.getText();
        String idUsuario = this.txtIdUsuario.getText();
        String telefono = this.txtTelefono.getText();
        String correo = this.txtCorreo.getText();
        if (this.validarCamposTextoAP(nombre, idUsuario, telefono, correo)) {
            this.actualizarUsuario(nombre, idUsuario, telefono, correo);
            this.actualizarTabla();
        }

    }

    private boolean validarCamposTextoAP(String nombre, String id, String telefono, String correo ) {
        String msj = "Para poder continuar se necesita completar los siguientes campos:\n";
        if (nombre == null || nombre.isEmpty()) {
            msj = msj + "El nombre.\n";
        }

        if (id == null || id.isEmpty()) {
            msj = msj + "La identificación.\n";
        }

        if (telefono == null || telefono.isEmpty()) {
            msj = msj + "El teléfono.\n";
        }

        if (correo == null || correo.isEmpty()) {
            msj = msj + "El correo.\n";
        }

        if (msj.equals("Para poder continuar se necesita completar los siguientes campos:\n")) {
            return true;
        } else {
            this.mostrarMensaje("Error", "No se pudo actualizar el usuario", "Por favor, complete los campos correctamente", Alert.AlertType.ERROR);
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

    private void mostrarInformacion() {
        this.txtNombre.setText(this.usuarioSeleccion.getNombre());
        this.txtIdUsuario.setText(this.usuarioSeleccion.getIdUsuario());
        this.txtIdUsuario.setDisable(true);
        this.txtTelefono.setText(this.usuarioSeleccion.getTelefono());
        this.txtContraseña.setText(this.usuarioSeleccion.getContraseña());
        this.txtContraseña.setDisable(true);
        this.txtCorreo.setText(this.usuarioSeleccion.getCorreo());
    }

    private ObservableList<Usuario> getUsuarios() {
        this.listadoUsuarios.clear();
        this.listadoUsuarios.addAll(this.mfm.obtenerListadoUsuarios());
        return this.listadoUsuarios;
    }

    private void actualizarTabla() {
        this.tableUsuarios.getItems().clear();
        this.tableUsuarios.setItems(this.getUsuarios());
    }

    private boolean validarDatosCompletos(String nombre, String id, String telefono, String correo, String contraseña, String tipoPersona) {
        String msj = "Para poder continuar se necesita completar los siguientes campos:\n";
        if (nombre == null || nombre.isEmpty()) {
            msj = msj + "El nombre.\n";
        }

        if (id == null || id.isEmpty()) {
            msj = msj + "La identificación.\n";
        }

        if (telefono == null || telefono.isEmpty()) {
            msj = msj + "El teléfono.\n";
        }

        if (correo == null || correo.isEmpty()) {
            msj = msj + "El correo.\n";
        }

        if (contraseña == null || contraseña.isEmpty()) {
            msj = msj + "La contraseña.\n";
        }

        if (tipoPersona == null || tipoPersona.isEmpty()) {
            msj = msj + "El tipo de persona.\n";
        }

        if (msj.equals("Para poder continuar se necesita completar los siguientes campos:\n")) {
            return true;
        } else {
            this.mostrarMensaje("Error", "No se pudo añadir un usuario", "Por favor, complete los campos correctamente", Alert.AlertType.ERROR);
            return false;
        }
    }

    private void registrarPublicador(String nombre, String id, String telefono, String correo, String contraseña) throws CarpetaException, PublicadorException {
        try {
            this.mfm.registrarPublicador(this.adminSesion, nombre, id, telefono, correo, contraseña);
            this.mostrarMensaje("Éxito", "Publicador registrado", "El publicador se ha registrado correctamente", Alert.AlertType.INFORMATION);
        } catch (CarpetaException | PublicadorException var8) {
            this.mostrarMensaje("Error", "Publicador no registrado", "El publicador no ha sido registrado", Alert.AlertType.ERROR);
        }

    }

    private void registrarCliente(String nombre, String id, String telefono, String correo, String contraseña) throws ClienteException, CarpetaException {
        try {
            this.mfm.registrarCliente(this.adminSesion, nombre, id, telefono, correo, contraseña);
            this.mostrarMensaje("Éxito", "Cliente registrado", "El cliente se ha registrado correctamente", Alert.AlertType.INFORMATION);
        } catch (CarpetaException | ClienteException var8) {
            this.mostrarMensaje("Error", "Cliente no registrado", "El cliente no ha sido registrado", Alert.AlertType.ERROR);
        }

    }

    private void registrarAdministrador(String nombre, String id, String telefono, String correo, String contraseña) throws AdministradorException {
        try {
            this.mfm.registrarAdministrador(this.adminSesion, nombre, id, telefono, correo, contraseña);
            this.mostrarMensaje("Éxito", "Administrador registrado", "El Administrador se ha registrado correctamente", Alert.AlertType.INFORMATION);
        } catch (AdministradorException var8) {
            this.mostrarMensaje("Error", "Administrador no registrado", "El Administrador no ha sido registrado", Alert.AlertType.ERROR);
        }

    }

    private void registrarGestorEnvio(String nombre, String id, String telefono, String correo, String contraseña) {
        try {
            this.mfm.registrarGestorEnvio(this.adminSesion, nombre, id, telefono, correo, contraseña);
            this.mostrarMensaje("Éxito", "Gestor de envío registrado registrado", "El Gestor de envío se ha registrado correctamente", Alert.AlertType.INFORMATION);
        } catch (GestorEnvioException var8) {
            this.mostrarMensaje("Error", "Gestor de envío no registrado", "El Gestor de envío no ha sido registrado", Alert.AlertType.ERROR);
        }

    }

    private void registrarGestorProcesamiento(String nombre, String id, String telefono, String correo, String contraseña) throws GestorProcesamientoException {
        try {
            this.mfm.registrarGestorProcesamiento(this.adminSesion, nombre, id, telefono, correo, contraseña);
            this.mostrarMensaje("Éxito", "Gestor de Procesamiento registrado", "El Gestor de Procesamiento registrado se ha registrado correctamente", Alert.AlertType.INFORMATION);
        } catch (GestorProcesamientoException | CarpetaException var8) {
            this.mostrarMensaje("Error", "Gestor de Procesamiento registrado no registrado", "El Gestor de Procesamiento registrado no ha sido registrado", Alert.AlertType.ERROR);
        }

    }

    private void actualizarUsuario(String nombre, String id, String telefono, String correo) {
        try {
            this.mfm.actualizarUsuario(this.adminSesion, nombre, id, correo, telefono);
            this.mostrarMensaje("Éxito", "Se actualizaron los datos", "Datos del Usuario actualizados correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception var7) {
            this.mostrarMensaje("Error", "No se actualizaron los datos", "No se pudo actualizar los datos", Alert.AlertType.ERROR);
        }

    }


}
