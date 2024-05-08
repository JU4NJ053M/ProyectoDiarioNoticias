package org.example.proyectofinal.model;

public class Cliente extends Usuario{

    private String nombre;

    private String idCliente;

    private  String rutaArticulo;

    private String rutaFotos;

    public Cliente(String correo, String contraseña, String nombre, String idCliente,
                   String rutaArticulo, String rutaFotos) {
        super(correo, contraseña);
        this.nombre = nombre;
        this.idCliente = idCliente;
        this.rutaArticulo = rutaArticulo;
        this.rutaFotos = rutaFotos;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getRutaArticulo() {
        return rutaArticulo;
    }

    public void setRutaArticulo(String rutaArticulo) {
        this.rutaArticulo = rutaArticulo;
    }

    public String getRutaFotos() {
        return rutaFotos;
    }

    public void setRutaFotos(String rutaFotos) {
        this.rutaFotos = rutaFotos;
    }
}
