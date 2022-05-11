package com.example.tfgfinal.Models;

import java.util.Date;

public class Respuesta {
    public int Id;
    public int IdPregunta;
    public int IdCuestionario;
    public String Texto;
    public int IdUsuario;
    public Date Fecha;

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdPregunta() {
        return IdPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        IdPregunta = idPregunta;
    }

    public int getIdCuestionario() {
        return IdCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        IdCuestionario = idCuestionario;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String texto) {
        Texto = texto;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }
}
