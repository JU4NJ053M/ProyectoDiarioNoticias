package org.example.proyectofinal.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Foto extends Contenido implements Serializable {

    public Foto(String titulo, String fechaPublicacion, String publicador) {
        super(titulo, fechaPublicacion, "No aplica", publicador);
    }

}
