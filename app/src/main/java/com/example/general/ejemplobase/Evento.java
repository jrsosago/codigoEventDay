package com.example.general.ejemplobase;

/**
 * Created by sosa on 17/10/2017.
 */

public class Evento {
   // private String Key;
    private String Nombre;
    private String Categoria;
    private String Precio;
    private String Fecha;
    private String Hora;
    private String Descripcion;
    private String Direccion;
    private String Creador;
    private String Foto;


    public Evento () {

    }
    public Evento ( String nombre, String categoria, String precio, String fecha, String hora, String descripcion, String ubicacion, String creador, String foto) {
        //this.Key = key;
        this.Nombre = nombre;
        this.Categoria =categoria ;
        this.Precio = precio;
        this.Fecha = fecha;
        this.Hora = hora;
        this.Descripcion = descripcion;
        this.Direccion = ubicacion;
        this.Creador=creador;
        this.Foto=foto;
    }

  /*  public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }/
    */

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCategoria(){
        return Categoria;
    }

    public void setCategoria(String categoria){
        Categoria=categoria;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setLugar(String ubicacion) {
        Direccion = ubicacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCreador(){return Creador;}

    public void setCreador(String creador){
        Creador=creador;
    }

    public String getFoto(){return Foto;}

    public void setFoto(String foto){
        Foto=foto;
    }



}
