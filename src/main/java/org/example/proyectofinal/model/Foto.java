package org.example.proyectofinal.model;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Foto extends Contenido{
    byte[] cuerpo;

    public Foto(String idContenido, String titulo, OffsetDateTime fechaPublicacionOdt,
                ZonedDateTime fechaPublicacionZdt,String fechaPublicacion, byte[] cuerpo) {
        super(idContenido, titulo, fechaPublicacionOdt, fechaPublicacionZdt, fechaPublicacion);
        this.cuerpo = cuerpo;
    }

    public byte[] getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(byte[] cuerpo) {
        this.cuerpo = cuerpo;
    }
}
