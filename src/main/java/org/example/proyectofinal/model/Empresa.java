package org.example.proyectofinal.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Empresa {

    private ArrayList<Administrador> admins;

    private ArrayList<Publicador> publicadores;

    private ArrayList<Cliente> clientes;

    public Empresa() {
        this.admins = new ArrayList<>();
        this.publicadores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public ArrayList<Administrador> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Administrador> admins) {
        this.admins = admins;
    }

    public ArrayList<Publicador> getPublicadores() {
        return publicadores;
    }

    public void setPublicadores(ArrayList<Publicador> publicadores) {
        this.publicadores = publicadores;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Articulo> obtenerArticulosPublicador(Publicador publicador) {
        return getPublicadores().stream().filter(publicador1 -> publicador1.equals(publicador))
                .flatMap(publicador1 -> publicador1.getArticulos().stream()).collect(Collectors.toList());
    }
}
