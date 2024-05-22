package org.example.proyectofinal.model;

import org.example.proyectofinal.exceptions.CarpetaException;

import java.io.File;
import java.io.Serializable;

public class Cliente extends Usuario  {

    private final String rutaCarpeta;

    public Cliente(String nombre, String telefono, String correo, String idUsuario, String contraseña) throws CarpetaException {
        super(nombre, telefono, correo, idUsuario, contraseña);
        this.rutaCarpeta = obtenerRutaCarpeta();
        crearCarpetaCliente();

    }

    public String obtenerRutaCarpeta() {
        return String.format("%s/%s/",
                "src\\main\\java\\org\\example\\proyectofinal\\BaseDatos\\CarpetasClientes", getIdUsuario());
    }

    public void crearCarpetaCliente() throws CarpetaException {
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

}
