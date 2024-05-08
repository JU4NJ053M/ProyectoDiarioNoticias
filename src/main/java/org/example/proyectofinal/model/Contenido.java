package org.example.proyectofinal.model;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Contenido {

    private String idContenido;

    private String titulo;

    private OffsetDateTime fechaPublicacionOdt;

    private ZonedDateTime fechaPublicacionZdt;

    private String fechaPublicacion;

    public Contenido(String idContenido, String titulo, OffsetDateTime fechaPublicacionOdt,
                     ZonedDateTime fechaPublicacionZdt, String fechaPublicacion) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.fechaPublicacionOdt = fechaPublicacionOdt;
        this.fechaPublicacionZdt = fechaPublicacionZdt;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Contenido(String idContenido, String titulo, String fechaPublicacion) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(String idContenido) {
        this.idContenido = idContenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public OffsetDateTime getFechaPublicacionOdt() {
        return fechaPublicacionOdt;
    }

    public void setFechaPublicacionOdt(OffsetDateTime fechaPublicacionOdt) {
        this.fechaPublicacionOdt = fechaPublicacionOdt;
    }

    public ZonedDateTime getFechaPublicacionZdt() {
        return fechaPublicacionZdt;
    }

    public void setFechaPublicacionZdt(ZonedDateTime fechaPublicacionZdt) {
        this.fechaPublicacionZdt = fechaPublicacionZdt;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
