package com.example.tfgfinal.Models;

public class Respuesta {
    public int Id;
    public int IdPregunta;
    public int IdCuestionario;
    public String Texto;

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
}
