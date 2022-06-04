package com.example.tfgfinal.Models;

public class PreguntaExamen {
    public int Id;
    public int IdExamen;
    public String Enunciado;
    public String opciones;
    public String opcionCorrecta;

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }

    public String getOpcionCorrecta() {
        return opcionCorrecta;
    }

    public void setOpcionCorrecta(String opcionCorrecta) {
        this.opcionCorrecta = opcionCorrecta;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }


    public String getEnunciado() {
        return Enunciado;
    }

    public void setEnunciado(String enunciado) {
        Enunciado = enunciado;
    }
}
