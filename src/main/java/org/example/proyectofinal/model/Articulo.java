package org.example.proyectofinal.model;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Articulo extends Contenido{

    private String cuerpo;

    public Articulo(String idContenido, String titulo, OffsetDateTime fechaPublicacionOdt,
                    ZonedDateTime fechaPublicacionZdt, String fechaPublicacion, String cuerpo) {
        super(idContenido, titulo, fechaPublicacionOdt, fechaPublicacionZdt, fechaPublicacion);
        this.cuerpo = cuerpo;
    }

    public Articulo(String idContenido, String titulo, String fechaPublicacion, String cuerpo) {
        super(idContenido, titulo, fechaPublicacion);
        this.cuerpo = cuerpo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

}
