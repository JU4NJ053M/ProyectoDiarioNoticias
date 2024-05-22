package org.example.proyectofinal.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.example.proyectofinal.controller.ModelFactoryController;
import org.example.proyectofinal.model.Articulo;
import org.example.proyectofinal.model.Contenido;
import org.example.proyectofinal.model.Foto;
import org.example.proyectofinal.model.Publicador;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.OffsetDateTime;
import java.util.List;

public class GestionPublicController implements Serializable {

    @FXML
    public Label labelGestorPublicador;
    @FXML
    public TableView<Contenido> tableContenido;
    @FXML
    public TableColumn<Contenido, String> colTitulo;
    @FXML
    public TableColumn<Contenido, String> colFecha;
    @FXML
    public TableColumn<Contenido, String> colTipoContenido;
    @FXML
    public Button btnSubirContenido;
    @FXML
    public Button btnCerrarSesion;
    @FXML
    public Button btnBorrarContenido;
    @FXML
    public Label txtBienvenida;
    private Publicador publicadorSesion;
    private Contenido contenidoSeleccion;
    @FXML
    private Main aplicacion;

    ObservableList<Contenido> listadoContenidos = FXCollections.observableArrayList();
    ModelFactoryController mfm = ModelFactoryController.getInstance();

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        this.colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaPublicacion"));
        this.colTipoContenido.setCellValueFactory(cellData -> {
            String tipo = "";
            if (cellData.getValue() instanceof Foto) {
                tipo = "Foto (JPG)";
            } else if (cellData.getValue() instanceof Articulo) {
                tipo = "Articulo (XML)";
            }
            return new SimpleStringProperty(tipo);
        });

        this.tableContenido.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                this.contenidoSeleccion = newSelection;
            }
        });
        this.tableContenido.setRowFactory(tv -> {
            TableRow<Contenido> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Contenido rowData = row.getItem();
                    abrirArchivo(rowData);
                }
            });
            return row;
        });
        actualizarTabla();
    }


    public void setAplicacion(Main aplicacion, Publicador publicador) {
        this.aplicacion = aplicacion;
        this.publicadorSesion = publicador;
        this.txtBienvenida.setText("¡Bienvenido " + publicadorSesion.getNombre() + "!");

        actualizarTabla();
    }

    @FXML
    void cerrarSesion(ActionEvent event) {
        this.aplicacion.mostrarVentanaInicioSesion();
    }

    public Main getAplicacion() {
        return this.aplicacion;
    }

    private void abrirArchivo(Contenido contenido) {
        Path filePath = null;

        if (contenido instanceof Foto) {
            filePath = Paths.get(publicadorSesion.getRutaCarpetaFotos(), contenido.getTitulo());
        } else if (contenido instanceof Articulo) {
            filePath = Paths.get(publicadorSesion.getRutaCarpetaArticulos(), contenido.getTitulo()+".xml");
        }

        if (filePath != null && Files.exists(filePath)) {
            try {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.OPEN)) {
                    desktop.open(filePath.toFile());
                } else {
                    mostrarMensaje("Error", "No se puede abrir el archivo", "La acción de abrir archivos no es soportada en este sistema.", Alert.AlertType.ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mostrarMensaje("Error", "No se pudo abrir el archivo", "Hubo un problema al abrir el archivo.", Alert.AlertType.ERROR);
            }
        } else {
            mostrarMensaje("Archivo no encontrado", "El archivo no existe", "El archivo seleccionado no se encuentra en el sistema.", Alert.AlertType.WARNING);
        }
    }



    @FXML
    void subirContenido(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo para subir");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos XML", "*.xml"),
                new FileChooser.ExtensionFilter("Archivos JPG", "*.jpg")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String fileName = selectedFile.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            Path destinationPath = null;

            if (fileExtension.equals("xml")) {
                destinationPath = Path.of(publicadorSesion.getRutaCarpetaArticulos(), fileName);
            } else if (fileExtension.equals("jpg")) {
                destinationPath = Path.of(publicadorSesion.getRutaCarpetaFotos(), fileName);
            }

            if (destinationPath != null) {
                if (Files.exists(destinationPath)) {
                    mostrarMensaje("Error", "Archivo duplicado", "El archivo ya existe en la carpeta de destino.", Alert.AlertType.ERROR);
                } else {
                    try {
                        Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);


                        if (fileExtension.equals("xml")) {
                            Articulo nuevoContenido = procesarArchivoXML(selectedFile.toPath());
                            if (nuevoContenido != null) {
                                mfm.agregarArticulo(nuevoContenido, publicadorSesion);
                            }
                        } else {
                            Foto nuevoContenido = new Foto(fileName, OffsetDateTime.now().toString(), publicadorSesion.getNombre());
                            mfm.agregarFoto(nuevoContenido, publicadorSesion);

                        }
                        mostrarMensaje("Éxito", "Archivo subido", "El archivo se ha subido correctamente.", Alert.AlertType.INFORMATION);
                        actualizarTabla();
                    } catch (IOException e) {
                        e.printStackTrace();
                        mostrarMensaje("Error", "Error al subir archivo", "Hubo un problema al subir el archivo.", Alert.AlertType.ERROR);
                    }
                }
            } else {
                mostrarMensaje("Error", "Archivo no compatible", "Solo se permiten archivos XML y JPG.", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void eliminarContenido (ActionEvent event){
        if (contenidoSeleccion != null) {
            if (contenidoSeleccion instanceof Foto){
                mfm.eliminarFoto((Foto)contenidoSeleccion, publicadorSesion);
            }else{
                mfm.eliminarArticulo((Articulo) contenidoSeleccion, publicadorSesion);
            }
            mostrarMensaje("Éxito", "Contenido eliminado", "El contenido se ha eliminado correctamente.", Alert.AlertType.INFORMATION);
            actualizarTabla();
        } else {
            mostrarMensaje("Error", "No se ha seleccionado contenido", "Por favor, seleccione un contenido para eliminar.", Alert.AlertType.ERROR);
        }
    }


    private Articulo procesarArchivoXML(Path archivoXML) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML.toFile());

            doc.getDocumentElement().normalize();

            NodeList titleList = doc.getElementsByTagName("title");
            String titulo = titleList.item(0).getTextContent();

            NodeList dateList = doc.getElementsByTagName("date.issue");
            dateList.item(0).setTextContent(OffsetDateTime.now().toString());
            String fechaPublicacion = dateList.item(0).getTextContent();

            NodeList copyrightList = doc.getElementsByTagName("doc.copyright");
            copyrightList.item(0).setTextContent(publicadorSesion.getNombre());
            String publicador = copyrightList.item(0).getTextContent();

            NodeList contentList = doc.getElementsByTagName("body.content");
            String cuerpo = contentList.item(0).getTextContent();

            return new Articulo(titulo, fechaPublicacion, cuerpo, publicador);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private ObservableList<Contenido> getContenidos() {
        this.listadoContenidos.clear();
        if (publicadorSesion != null) {
            this.listadoContenidos.addAll(publicadorSesion.getArticulos());
            this.listadoContenidos.addAll(publicadorSesion.getFotos());
        }
        return this.listadoContenidos;
    }

    private void actualizarTabla() {
        if (publicadorSesion != null) {
            this.tableContenido.getItems().clear();
            this.tableContenido.setItems(this.getContenidos());
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
