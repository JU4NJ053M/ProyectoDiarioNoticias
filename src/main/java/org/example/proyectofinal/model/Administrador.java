package org.example.proyectofinal.model;

import java.util.ArrayList;

public class Administrador extends Usuario{

    private String nombre;

    private String idAdministrador;

    private ArrayList<Publicador> publicadores;

    private ArrayList<Cliente> clientes;

    private ArrayList<Administrador> administradores;

    public Administrador(String correo, String contraseña, String nombre, String idAdministrador) {
        super(correo, contraseña);
        this.nombre = nombre;
        this.idAdministrador = idAdministrador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }


}
