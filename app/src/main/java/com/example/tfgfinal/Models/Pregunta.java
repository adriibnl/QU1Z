package com.example.tfgfinal.Models;

public class Pregunta {
    public int Id;
    public int IdCuestionario;
    public String Enunciado;

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String Tipo;
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdCuestionario() {
        return IdCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        IdCuestionario = idCuestionario;
    }

    public String getEnunciado() {
        return Enunciado;
    }

    public void setEnunciado(String enunciado) {
        Enunciado = enunciado;
    }
}
