package org.example.proyectofinal.controller;

import org.example.proyectofinal.model.Articulo;
import org.example.proyectofinal.model.Empresa;
import org.example.proyectofinal.model.Publicador;
import org.example.proyectofinal.util.ArchivosUtil;
import org.example.proyectofinal.util.EmpresaUtil;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("All")
public class ModelFactoryController {

    private Empresa empresa;

    public ModelFactoryController() {
        this.empresa = cargarDatosPrueba();
    }

    private Empresa cargarDatosPrueba() {
        return EmpresaUtil.CargarDatosPrueba();
    }

    public void agregarPublicador(Publicador publicador) throws IOException {
        empresa.getPublicadores().add(publicador);
        ArchivosUtil.crearCarpetaPublicador(publicador.getNombrePublicador());
        ArchivosUtil.crearCsv();
    }

    public List<Articulo> obtenerArticulosPublicador(Publicador publicador){
        return empresa.obtenerArticulosPublicador(publicador);
    }

    public void agregarArticulo(Articulo articulo, Publicador publicador){
        List<Articulo> listaArticulos = empresa.obtenerArticulosPublicador(publicador);
        listaArticulos.add(articulo);
        crearArticuloXml(publicador.getNombrePublicador(), articulo);
        agregarArticuloCsv(publicador.getNombrePublicador(), articulo);

    }



    private void crearArticuloXml(String nombre, Articulo articulo) {
        try {
            ArchivosUtil.crearArticuloXml(nombre, articulo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void crearArticuloCsv() {
        try {
            ArchivosUtil.crearCsv();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void agregarArticuloCsv(String nombrePublicador, Articulo articulo) {
        try {
            ArchivosUtil.agregarArticuloCsv(nombrePublicador, articulo);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
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
