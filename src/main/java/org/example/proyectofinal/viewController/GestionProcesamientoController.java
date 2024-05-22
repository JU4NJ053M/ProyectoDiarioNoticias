package org.example.proyectofinal.viewController;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.proyectofinal.controller.ModelFactoryController;
import org.example.proyectofinal.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GestionProcesamientoController {

    @FXML
    public Button btnCerrarSesion;
    @FXML
    public Button btnProcesar;
    @FXML
    public Label txtBienvenida;
    @FXML
    public TableView<Contenido> tableContenido;
    @FXML
    public TableColumn<Contenido, String> colTitulo;
    @FXML
    public TableColumn<Contenido, String> colFecha;
    @FXML
    public TableColumn<Contenido, String> colCuerpo;
    @FXML
    public TableColumn<Contenido, String> colPublicador;
    private  GestorDeProcesamiento gestorSesion;
    private Contenido contenidoSeleccion;
    @FXML
    private Main aplicacion;
    ObservableList<Contenido> listadoContenidos = FXCollections.observableArrayList();


    ModelFactoryController mfm = ModelFactoryController.getInstance();


    @FXML
    public void initialize() {
        this.colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colCuerpo.setCellValueFactory(new PropertyValueFactory<>("cuerpo"));
        this.colPublicador.setCellValueFactory(new PropertyValueFactory<>("publicador"));

    }

    public void setAplicacion(Main aplicacion, GestorDeProcesamiento gestor) {
        this.gestorSesion = gestor;
        this.aplicacion = aplicacion;
        this.txtBienvenida.setText("¡Bienvenido " + gestorSesion.getNombre() + "!");
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        this.aplicacion.mostrarVentanaInicioSesion();
    }

    private void actualizarTabla() {
        listadoContenidos.clear();

        List<Publicador> publicadores = mfm.getEmpresa().getListaPublicadores();
        for (Publicador publicador : publicadores) {
            for (Articulo articulo : publicador.getArticulos()) {
                listadoContenidos.add(new Articulo(
                        articulo.getTitulo(),
                        articulo.getFechaPublicacion(),
                        articulo.getCuerpo(),
                        articulo.getPublicador()
                ));
            }
            for (Foto foto : publicador.getFotos()) {
                listadoContenidos.add(new Foto(
                        foto.getTitulo(),
                        foto.getFechaPublicacion(),
                        foto.getPublicador()
                ));
            }
        }

        tableContenido.setItems(listadoContenidos);
    }


    @FXML
    void procesarContenido(ActionEvent event) {
        actualizarTabla();
        String ruta = gestorSesion.obtenerRutaCarpeta();
        mfm.crearArticuloCsv(ruta);
        List<Publicador> listaPublicadores = mfm.obtenerListadoPublicadores();
        final boolean[] archivosEliminados = {false};

        for (Contenido contenido : listadoContenidos){
            mfm.agregarArticuloCsv(contenido.getPublicador(), contenido, ruta);
        }

        for(Publicador publicador : listaPublicadores){
            String rutaCarpetaArticulos = publicador.getRutaCarpetaArticulos();
            if (Files.exists(Paths.get(rutaCarpetaArticulos))) {
                try {
                    Files.list(Paths.get(rutaCarpetaArticulos))
                            .forEach(file -> {
                                try {
                                    Files.delete(file);
                                    archivosEliminados[0] = true; // Modificar el valor del array
                                    System.out.println("Eliminado: " + file);
                                } catch (IOException e) {
                                    System.out.println("Error al eliminar archivo: " + e.getMessage());
                                }
                            });
                } catch (IOException e) {
                    System.out.println("Error al listar archivos: " + e.getMessage());
                }
            }
        }

        if (archivosEliminados[0]) {
            mostrarMensaje("Éxito", "Archivos eliminados", "Los archivos de los publicadores han sido eliminados correctamente", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("Advertencia", "No se encontraron archivos", "No se encontraron archivos para eliminar", Alert.AlertType.WARNING);
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
