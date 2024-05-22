package org.example.proyectofinal.model;


import java.io.Serial;
import java.io.Serializable;

public class Usuario implements Serializable {



    private String nombre;
    private String telefono;
    private String correo;
    private String idUsuario;
    private String contraseña;

    public Usuario(String nombre, String telefono, String correo, String idUsuario, String contraseña) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.idUsuario = idUsuario;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setUsuario(String usuario) {
        this.idUsuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}
