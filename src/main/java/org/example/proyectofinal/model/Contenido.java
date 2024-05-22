package org.example.proyectofinal.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Contenido implements Serializable {

    private String titulo;
    private String cuerpo;
    private String publicador;


    private String fechaPublicacion;

    public Contenido(String titulo, String fechaPublicacion, String cuerpo, String publicador) {
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
        this.publicador = publicador;
        this.cuerpo = cuerpo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }


    public String getPublicador() {
        return publicador;
    }

    public void setPublicador(String publicador) {
        this.publicador = publicador;
    }

    public String getRuta (){
        return "src\\main\\java\\org\\example\\proyectofinal\\BaseDatos\\CarpetasPublicadores\\";
    }
}
