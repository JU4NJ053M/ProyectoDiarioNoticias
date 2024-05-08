package org.example.proyectofinal.model;


import java.util.ArrayList;

import static org.example.proyectofinal.util.Constantes.RUTA_CARPETA_PUBLICADOR;

public class Publicador extends Usuario{

    private String nombrePublicador;

    private String idPublicador;

    private ArrayList<Articulo> articulos;

    private ArrayList<Foto> fotos;

    private String rutaCarpetaArticulos;
    private String rutaCarpetaFotos;

    private String urlDelSitioWeb;

    public Publicador(String correo, String contraseña, String nombrePublicador, String idPublicador,
                      ArrayList<Articulo> articulos, ArrayList<Foto> fotos, String urlDelSitioWeb) {
        super(correo, contraseña);
        this.nombrePublicador = nombrePublicador;
        this.idPublicador = idPublicador;
        this.articulos = articulos;
        this.fotos = fotos;
        this.rutaCarpetaArticulos = obtenerRutaCarpetaArticulos();
        this.rutaCarpetaFotos = obtenerRutaCarpetaFotos();
        this.urlDelSitioWeb = urlDelSitioWeb;
    }

    private String obtenerRutaCarpetaFotos() {
        return String.format("%s/%s/fotos", RUTA_CARPETA_PUBLICADOR, getNombrePublicador());
    }


    private String obtenerRutaCarpetaArticulos() {
        return String.format("%s/%s/articulos", RUTA_CARPETA_PUBLICADOR, getNombrePublicador());
    }


    public String getNombrePublicador() {
        return nombrePublicador;
    }

    public void setNombrePublicador(String nombrePublicador) {
        this.nombrePublicador = nombrePublicador;
    }

    public String getIdPublicador() {
        return idPublicador;
    }

    public void setIdPublicador(String idPublicador) {
        this.idPublicador = idPublicador;
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

    public String getUrlDelSitioWeb() {
        return urlDelSitioWeb;
    }

    public void setUrlDelSitioWeb(String urlDelSitioWeb) {
        this.urlDelSitioWeb = urlDelSitioWeb;
    }
}
