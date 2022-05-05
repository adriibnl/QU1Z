package com.example.tfgfinal.Models;

import java.util.Date;

public class Cuestionario {
    public int Id;
    public String Nombre;
    public int IdCreador;
    public Date FechaCreacion;
    public String Descripcion;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getIdCreador() {
        return IdCreador;
    }

    public void setIdCreador(int idCreador) {
        IdCreador = idCreador;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
