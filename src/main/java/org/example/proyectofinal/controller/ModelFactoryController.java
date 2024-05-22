package org.example.proyectofinal.controller;

import org.example.proyectofinal.Serializacion.Serializar;
import org.example.proyectofinal.exceptions.*;
import org.example.proyectofinal.model.*;
import org.example.proyectofinal.util.ArchivosUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@SuppressWarnings("All")
public class ModelFactoryController {


    Empresa empresa = null;
    public ModelFactoryController() {
        System.out.println("invocacion clase singleton");
        this.inicializarDatos();
    }

    private void inicializarDatos() {
        if (Serializar.deserializar() != null) {
            this.empresa = Serializar.deserializar();
            List<Usuario> listaUsuarios = empresa.getListaUsuarios();
            if (listaUsuarios != null) {
                listaUsuarios.forEach(usuario -> System.out.println("idUsuario: " + usuario.getIdUsuario() +
                        ", contraseña: " + usuario.getContraseña() + ", " + empresa.obtenerTipoUsuario(usuario)));
            }
        } else {
            this.empresa = new Empresa();
            Serializar.serializar(empresa);
        }

    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Usuario> obtenerListadoUsuarios() {
        return this.empresa.getListaUsuarios();
    }

    public List<Publicador> obtenerListadoPublicadores(){
        return this.empresa.getListaPublicadores();
    }

    public String obtenerTipoUsuario(Usuario usuario){
        return this.empresa.obtenerTipoUsuario(usuario);
    }

    public void eliminarCliente(Administrador administrador, Cliente usuarioSeleccion) {
        this.empresa.eliminarCliente(this.empresa, administrador, usuarioSeleccion);
    }

    public void eliminarGestorEnvio(Administrador adminSesion, GestorDeEnvio usuarioSeleccion) {
        this.empresa.eliminarGestorEnvio(this.empresa, adminSesion, usuarioSeleccion);
    }

    public void eliminarGestorProcesamiento(Administrador adminSesion, GestorDeProcesamiento usuarioSeleccion) {
        this.empresa.eliminarGestorProcesamiento(this.empresa, adminSesion, usuarioSeleccion);
    }

    public void eliminarPublicador(Administrador adminSesion, Publicador usuarioSeleccion) {
        this.empresa.eliminarPublicador(this.empresa, adminSesion, usuarioSeleccion);
    }

    public boolean registrarCliente(Administrador adminSesion, String nombre, String id, String telefono, String correo, String contraseña) throws ClienteException, CarpetaException {
        return this.empresa.registrarCliente(this.empresa, adminSesion, nombre, id, telefono, correo, contraseña);
    }

    public boolean registrarPublicador(Administrador administrador, String nombre, String id, String telefono, String correo, String contraseña) throws PublicadorException, CarpetaException {
        return this.empresa.registrarPulicador(this.empresa, administrador, nombre, id, telefono, correo, contraseña);
    }

    public boolean registrarAdministrador(Administrador administrador, String nombre,  String id, String telefono, String correo, String contraseña) throws AdministradorException {
        return this.empresa.registrarAdministrador(this.empresa, administrador, nombre, id, telefono, correo, contraseña);
    }

    public boolean registrarGestorEnvio(Administrador administrador, String nombre, String id, String telefono, String correo, String contraseña) throws GestorEnvioException {
        return this.empresa.registrarGestorEnvio(this.empresa, administrador, nombre, id, telefono, correo, contraseña);
    }

    public boolean registrarGestorProcesamiento(Administrador administrador, String nombres , String identificacion, String telefono, String correo, String contraseña) throws GestorProcesamientoException, CarpetaException {
        return this.empresa.registrarGestorProcesamiento(this.empresa, administrador, nombres , identificacion, telefono, correo, contraseña);
    }


    public void actualizarUsuario(Administrador adminSesion, String nombre , String id, String telefono, String correo) throws Exception {
        this.empresa.actualizarUsuario(this.empresa, adminSesion, nombre, id, telefono, correo);
    }

    public Usuario obtenerUsuario(String idUsuario) {
        return this.empresa.obtenerUsuario(idUsuario);
    }

    private void crearArticuloXml(String nombre, Articulo articulo, String ruta) {
        try {
            ArchivosUtil.crearArticuloXml(nombre, articulo, ruta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void crearArticuloCsv(String ruta) {
        try {
            ArchivosUtil.crearCsv(ruta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void agregarArticuloCsv(String nombrePublicador, Contenido contenido, String ruta) {
        try {
            ArchivosUtil.agregarArticuloCsv(nombrePublicador, contenido, ruta);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void agregarArticulo(Articulo articulo, Publicador publicadorSesion) {
        publicadorSesion.agregarArticulo(articulo);
    }

    public void agregarFoto(Foto foto, Publicador publicadorSesion) {
        publicadorSesion.agregarFoto(foto);
    }
    public void eliminarFoto(Foto foto, Publicador publicadorSesion) {
        publicadorSesion.eliminarFoto(foto);
    }

    public void eliminarArticulo(Articulo articulo, Publicador publicadorSesion) {
        publicadorSesion.eliminarArticulo(articulo);
    }

    public void eliminarCarpeta (String carpeta){
        ArchivosUtil.eliminarCarpeta(carpeta);
    }

    public void actualizarPublicadorSesion(Publicador publicadorSesion) {
        Publicador publicadorOriginal = (Publicador) obtenerUsuario(publicadorSesion.getIdUsuario());
        publicadorOriginal.setArticulos(publicadorSesion.getArticulos());
        publicadorOriginal.setFotos(publicadorSesion.getFotos());
        Serializar.serializar(empresa);
    }

    /*
    -----------------------------------------------------------------------------------------------------------
    --------------------------------------GET INSTANCE--------------------------------------------------------------
    */
    public static ModelFactoryController getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    private static class SingletonHolder {
        private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
    }

}
