package org.example.proyectofinal.model;

import org.example.proyectofinal.controller.ModelFactoryController;
import org.example.proyectofinal.exceptions.CarpetaException;
import org.example.proyectofinal.util.ArchivosUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Publicador extends Usuario implements Serializable {

    private ArrayList<Articulo> articulos;
    private ArrayList<Foto> fotos;

    private final String rutaCarpeta;
    private String rutaCarpetaArticulos;
    private String rutaCarpetaFotos;

    public Publicador(String nombre, String telefono, String correo, String idUsuario, String contraseña) throws CarpetaException {
        super(nombre, telefono, correo, idUsuario, contraseña);
        this.articulos = new ArrayList<>();
        this.fotos = new ArrayList<>();
        this.rutaCarpeta = "src\\main\\java\\org\\example\\proyectofinal\\BaseDatos\\CarpetasPublicadores\\" + getIdUsuario();
        crearCarpetaPublicador();
    }

    public String getRutaCarpeta() {
        return rutaCarpeta;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }

    public ArrayList<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<Foto> fotos) {
        this.fotos = fotos;
    }

    public String getRutaCarpetaArticulos() {
        return rutaCarpetaArticulos;
    }

    public void setRutaCarpetaArticulos(String rutaCarpetaArticulos) {
        this.rutaCarpetaArticulos = rutaCarpetaArticulos;
    }

    public String getRutaCarpetaFotos() {
        return rutaCarpetaFotos;
    }

    public void setRutaCarpetaFotos(String rutaCarpetaFotos) {
        this.rutaCarpetaFotos = rutaCarpetaFotos;
    }

    public void setFoto(Foto foto) {
        this.fotos.add(foto);
    }
    public void setArticulo(Articulo articulo) {
        this.articulos.add(articulo);
    }

    public void crearCarpetaPublicador() throws CarpetaException {
        rutaCarpetaArticulos = String.format(
                "%s/%s",
                rutaCarpeta,
                "articulos"
        );

        rutaCarpetaFotos = String.format(
                "%s/%s",
                rutaCarpeta,
                "fotos"
        );

        File creadorCarpetaArticulos = new File(rutaCarpetaArticulos);
        File creadorCarpetaFotos = new File(rutaCarpetaFotos);

        if (!creadorCarpetaArticulos.exists() && !creadorCarpetaFotos.exists()) {
            boolean existe = creadorCarpetaArticulos.mkdirs() && creadorCarpetaFotos.mkdirs();
            if (existe) {
                System.out.println("Se creo correctamente la carpeta");
            } else {
                throw new CarpetaException("Error: No se pudo crear la carpeta");
            }
        } else {
            System.out.println("La carpeta ya existe");
        }
    }


    public void agregarArticulo(Articulo articulo) {
        this.articulos.add(articulo);
        ModelFactoryController.getInstance().actualizarPublicadorSesion(this);
    }


    public void agregarFoto(Foto foto) {
        this.fotos.add(foto);
        ModelFactoryController.getInstance().actualizarPublicadorSesion(this);
    }

    public void eliminarFoto(Foto foto) {
        Path filePath = Paths.get(rutaCarpetaFotos, foto.getTitulo());
        try {
            Files.deleteIfExists(filePath);
            this.fotos.remove(foto);
            ModelFactoryController.getInstance().actualizarPublicadorSesion(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarArticulo(Articulo articulo) {
        Path filePath = Paths.get(rutaCarpetaArticulos, articulo.getTitulo());
        try {
            Files.deleteIfExists(filePath);
            this.articulos.remove(articulo);
            ModelFactoryController.getInstance().actualizarPublicadorSesion(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
