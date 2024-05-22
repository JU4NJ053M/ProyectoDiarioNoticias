package org.example.proyectofinal.model;

import org.example.proyectofinal.exceptions.*;
import org.example.proyectofinal.util.ArchivosUtil;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class Administrador extends Usuario implements Serializable {


    public Administrador(String nombre, String telefono, String correo, String idUsuario, String contraseña) {
        super(nombre, telefono, correo, idUsuario, contraseña);
    }

    public Administrador() {
        super("Juan José Mesa Osorio","3202693300","Juanj.mesao@uqvirtual.edu.co",
                "123", "123");
    }

    public boolean registrarAdministrador(Empresa empresa, String nombre, String id, String telefono, String correo, String contraseña) throws AdministradorException {
        Usuario usuario = this.obtenerUsuario(empresa.getListaUsuarios(), id);
        if (usuario != null) {
            throw new AdministradorException("Error: La persona ya se encuentra registrada");
        } else {
            Administrador administrador = new Administrador(nombre, telefono, correo, id, contraseña);
            empresa.getListaUsuarios().add(administrador);
            empresa.getListaAdmins().add(administrador);
            return true;
        }
    }

    public boolean registrarCliente(Empresa empresa, String nombre, String id, String telefono, String correo, String contraseña) throws ClienteException, CarpetaException {
        Usuario usuario = this.obtenerUsuario(empresa.getListaUsuarios(), id);
        if (usuario != null) {
            throw new ClienteException("Error: La persona ya se encuentra registrada");
        } else {
            Cliente cliente = new Cliente(nombre, telefono, correo, id, contraseña);
            empresa.getListaClientes().add(cliente);
            empresa.getListaUsuarios().add(cliente);
            return true;
        }
    }

    public void eliminarCliente(Empresa empresa, Cliente cliente) {
        String carpeta = cliente.obtenerRutaCarpeta();
        ArchivosUtil.eliminarCarpeta(carpeta);
        empresa.getListaClientes().remove(cliente);
        empresa.getListaUsuarios().remove(cliente);
    }

    public boolean registrarPublicador(Empresa empresa, String nombre, String id, String telefono, String correo, String contraseña) throws CarpetaException, PublicadorException {
        Usuario usuario = this.obtenerUsuario(empresa.getListaUsuarios(), id);
        if (usuario != null) {
            throw new PublicadorException("Error: La persona ya se encuentra registrado");
        } else {
            Publicador publicador = new Publicador(nombre, telefono, correo, id, contraseña);
            empresa.getListaPublicadores().add(publicador);
            empresa.getListaUsuarios().add(publicador);
            return true;
        }
    }

    public void eliminarPublicador(Empresa empresa, Publicador publicador) {
        String carpeta = publicador.getRutaCarpeta();
        ArchivosUtil.eliminarCarpeta(carpeta);
        empresa.getListaPublicadores().remove(publicador);
        empresa.getListaUsuarios().remove(publicador);
    }

    public boolean registrarGestorEnvio(Empresa empresa, String nombre, String id, String telefono, String correo, String contraseña) throws GestorEnvioException {
        Usuario usuario = this.obtenerUsuario(empresa.getListaUsuarios(), id);
        if (usuario != null) {
            throw new GestorEnvioException("Error: La persona ya se encuentra registrado");
        } else {
            GestorDeEnvio gestor = new GestorDeEnvio(nombre, id, telefono, correo, contraseña);
            empresa.getListaGestoresEnvio().add(gestor);
            empresa.getListaUsuarios().add(gestor);
            return true;
        }
    }

    public void eliminarGestorEnvio(Empresa empresa, GestorDeEnvio gestor) {
        empresa.getListaUsuarios().remove(gestor);
        empresa.getListaGestoresEnvio().remove(gestor);
    }

    public boolean registrarGestorProcesamiento(Empresa empresa, String nombre, String id, String telefono ,String correo, String contraseña) throws GestorProcesamientoException, CarpetaException {
        Usuario usuario = this.obtenerUsuario(empresa.getListaUsuarios(), id);
        if (usuario != null) {
            throw new GestorProcesamientoException("Error: La persona ya se encuentra registrado");
        } else {
            GestorDeProcesamiento gestor = new GestorDeProcesamiento(nombre, telefono, correo, id, contraseña);
            empresa.getListaGestoresProcesamiento().add(gestor);
            empresa.getListaUsuarios().add(gestor);
            return true;
        }
    }

    public void eliminarGestorProcesamiento(Empresa empresa, GestorDeProcesamiento gestor) {
        String carpeta = gestor.obtenerRutaCarpeta();
        ArchivosUtil.eliminarCarpeta(carpeta);
        empresa.getListaGestoresProcesamiento().remove(gestor);
        empresa.getListaUsuarios().remove(gestor);
    }


    public Usuario obtenerUsuario(List<Usuario> listaUsuarios, String id) {
        Iterator var4 = listaUsuarios.iterator();

        while(var4.hasNext()) {
            Usuario usuario = (Usuario) var4.next();
            if (usuario.getIdUsuario().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean actualizarUsuario(Empresa empresa, String nombre, String id, String telefono, String correo) throws Exception {
        Usuario usuario = this.obtenerUsuario(empresa.getListaUsuarios(), id);
        if (usuario == null) {
            throw new Exception("La identificacion ingresada no corresponde a ninguna persona registrada");
        } else {
            usuario.setNombre(nombre);
            usuario.setTelefono(telefono);
            usuario.setCorreo(correo);
            return true;
        }
    }

}
