package com.example.tfgfinal.Models;

import com.google.type.DateTime;

import java.util.Date;

public class ExamenRespuesta {
    public int Id;
    public int IdExamen;
    public String respuestas;
    public int idUser;
    public Date fecha;
    public String puntuacion;

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdExamen() {
        return IdExamen;
    }

    public void setIdExamen(int idExamen) {
        IdExamen = idExamen;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
