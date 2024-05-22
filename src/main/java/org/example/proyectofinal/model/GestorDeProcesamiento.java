package org.example.proyectofinal.model;

import org.example.proyectofinal.exceptions.CarpetaException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.Serializable;

public class GestorDeProcesamiento extends Usuario   {

    private final String rutaCarpeta;


    public GestorDeProcesamiento(String nombre, String telefono, String correo, String idUsuario, String contraseña) throws CarpetaException {
        super(nombre, telefono, correo, idUsuario, contraseña);
        this.rutaCarpeta = obtenerRutaCarpeta();
        crearCarpetaGestorDeProcesamiento();
    }

    public String obtenerRutaCarpeta() {
        return String.format("%s/%s/",
                "src\\main\\java\\org\\example\\proyectofinal\\BaseDatos\\CarpetaGestoresProcesamiento", getIdUsuario());
    }

    public void crearCarpetaGestorDeProcesamiento() throws CarpetaException {
        File creadorCarpeta = new File(rutaCarpeta);
        if (!creadorCarpeta.exists()) {
            boolean existe = creadorCarpeta.mkdirs();
            if (existe) {
                System.out.println("Se creo correctamente la carpeta");
            } else {
                throw new CarpetaException("Error: No se pudo crear la carpeta");
            }
        } else {
            throw new CarpetaException("Error: La carpeta ya existe");
        }
    }

    public void procesarArchivoXML(File archivoXML, Publicador publicadorSesion) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);

            doc.getDocumentElement().normalize();

            NodeList titleList = doc.getElementsByTagName("title");
            String titulo = titleList.item(0).getTextContent();

            NodeList dateList = doc.getElementsByTagName("date.issue");
            String fechaPublicacion = dateList.item(0).getTextContent();

            NodeList copyrightList = doc.getElementsByTagName("doc.copyright");
            String publicador = copyrightList.item(0).getTextContent();

            NodeList contentList = doc.getElementsByTagName("body.content");
            String cuerpo = contentList.item(0).getTextContent();

            Articulo nuevoArticulo = new Articulo(titulo, fechaPublicacion, cuerpo, publicador);

            publicadorSesion.agregarArticulo(nuevoArticulo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }







}
