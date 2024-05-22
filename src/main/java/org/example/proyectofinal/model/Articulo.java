package org.example.proyectofinal.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Articulo extends Contenido implements Serializable {


    public Articulo(String titulo, String fechaPublicacion, String publicador) {
        super(titulo, fechaPublicacion,"setear",publicador);
    }
    public Articulo(String titulo, String fechaPublicacion, String cuerpo, String publicador) {
        super(titulo, fechaPublicacion,cuerpo,publicador);
    }



}
