package org.example.proyectofinal.model;

import org.example.proyectofinal.Serializacion.Serializar;
import org.example.proyectofinal.exceptions.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Empresa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private ArrayList<Administrador> listaAdmins;

    private ArrayList<Publicador> listaPublicadores;

    private ArrayList<Cliente> listaClientes;

    private ArrayList<GestorDeEnvio> listaGestoresEnvio;

    private ArrayList<GestorDeProcesamiento> listaGestoresProcesamiento;
    private ArrayList<Usuario> listaUsuarios;

    public Empresa() {
        this.listaAdmins = new ArrayList<>();
        Administrador admin = new Administrador();
        listaAdmins.add(admin);
        this.listaPublicadores = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
        this.listaGestoresProcesamiento = new ArrayList<>();
        this.listaGestoresEnvio = new ArrayList<>();
        listaUsuarios.add(admin);
    }

    public ArrayList<Administrador> getListaAdmins() {
        return listaAdmins;
    }
    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }


    public void setListaAdmins(ArrayList<Administrador> listaAdmins) {
        this.listaAdmins = listaAdmins;
    }

    public ArrayList<Publicador> getListaPublicadores() {
        return listaPublicadores;
    }

    public void setListaPublicadores(ArrayList<Publicador> listaPublicadores) {
        this.listaPublicadores = listaPublicadores;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<GestorDeEnvio> getListaGestoresEnvio() {
        return listaGestoresEnvio;
    }

    public void setListaGestoresEnvio(ArrayList<GestorDeEnvio> listaGestoresEnvio) {
        this.listaGestoresEnvio = listaGestoresEnvio;
    }

    public ArrayList<GestorDeProcesamiento> getListaGestoresProcesamiento() {
        return listaGestoresProcesamiento;
    }

    public void setListaGestoresProcesamiento(ArrayList<GestorDeProcesamiento> listaGestoresProcesamiento) {
        this.listaGestoresProcesamiento = listaGestoresProcesamiento;
    }

    public List<Articulo> obtenerArticulosPublicador(Publicador publicador) {
        return getListaPublicadores().stream().filter(publicador1 -> publicador1.equals(publicador))
                .flatMap(publicador1 -> publicador1.getArticulos().stream()).collect(Collectors.toList());
    }
    public List<Articulo> obtenerFotosPublicador(Publicador publicador) {
        return getListaPublicadores().stream().filter(publicador1 -> publicador1.equals(publicador))
                .flatMap(publicador1 -> publicador1.getArticulos().stream()).collect(Collectors.toList());
    }

    public Usuario obtenerUsuario(String idUsuario) {
        Iterator var3 = this.listaUsuarios.iterator();

        while(var3.hasNext()) {
            Usuario admin = (Usuario) var3.next();
            if (admin.getIdUsuario().equals(idUsuario)) {
                return admin;
            }
        }

        return null;
    }


    public boolean registrarCliente(Empresa empresa, Administrador administrador, String nombre, String id, String telefono, String correo, String contraseña) throws ClienteException, CarpetaException {
        administrador.registrarCliente(empresa, nombre, id, telefono, correo, contraseña);
        Serializar.serializar(this);
        return true;
    }

    public void eliminarCliente(Empresa empresa, Administrador administrador, Cliente cliente) {
        administrador.eliminarCliente(empresa, cliente);
        Serializar.serializar(empresa);
    }

    public boolean registrarPulicador(Empresa empresa, Administrador administrador, String nombre, String id, String telefono, String correo, String contraseña) throws PublicadorException, CarpetaException {
        administrador.registrarPublicador(empresa, nombre, id, telefono, correo, contraseña);
        Serializar.serializar(empresa);
        return true;
    }

    public String obtenerTipoUsuario(Usuario usuario) {
        if (usuario instanceof Administrador) {
            return "Administrador";
        } else if (usuario instanceof Cliente) {
            return "Cliente";
        } else if (usuario instanceof GestorDeEnvio) {
            return "Gestor de Envío";
        } else if (usuario instanceof GestorDeProcesamiento) {
            return "Gestor de Procesamiento";
        } else if (usuario instanceof Publicador) {
            return "Publicador";
        } else {
            return "Desconocido";
        }
    }

    public void eliminarPublicador(Empresa empresa, Administrador administrador, Publicador publicador) {
        administrador.eliminarPublicador(empresa, publicador);
        Serializar.serializar(empresa);
    }

    public boolean registrarAdministrador(Empresa empresa, Administrador administrador, String nombre, String id, String telefono, String correo,String contraseña) throws AdministradorException {
        administrador.registrarAdministrador(empresa, nombre, id, telefono, correo, contraseña);
        Serializar.serializar(empresa);
        return true;
    }

    public boolean registrarGestorEnvio(Empresa empresa, Administrador administrador, String nombre, String id, String telefono, String correo,String contraseña) throws GestorEnvioException {
        administrador.registrarGestorEnvio(empresa, nombre, id, telefono, correo, contraseña);
        Serializar.serializar(empresa);
        return true;
    }

    public void eliminarGestorEnvio(Empresa empresa, Administrador admin, GestorDeEnvio gestor) {
        admin.eliminarGestorEnvio(empresa, gestor);
        Serializar.serializar(empresa);
    }

    public boolean registrarGestorProcesamiento(Empresa empresa, Administrador administrador, String nombre, String id, String telefono, String correo,String contraseña) throws GestorProcesamientoException, CarpetaException {
        administrador.registrarGestorProcesamiento(empresa, nombre, id, telefono, correo, contraseña);
        Serializar.serializar(empresa);
        return true;
    }

    public void eliminarGestorProcesamiento(Empresa empresa, Administrador admin, GestorDeProcesamiento gestor) {
        admin.eliminarGestorProcesamiento(empresa, gestor);
        Serializar.serializar(empresa);
    }

    public void actualizarUsuario(Empresa empresa, Administrador admin, String nombre, String id, String correo, String telefono) throws Exception {
        admin.actualizarUsuario(empresa, nombre, id, telefono, correo);
        Serializar.serializar(empresa);
    }

    public void agregarContenido(Empresa empresa, Contenido contenido){

    }
}
