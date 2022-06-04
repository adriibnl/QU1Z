package com.example.tfgfinal.Models;

import java.util.Date;

public class CuestionarioResueltoUser {
    public int IdCuestionario;
    public int IdUsuario;
    public Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCuestionario() {
        return IdCuestionario;
    }

    public void setIdCuestionario(int idCuestionario) {
        IdCuestionario = idCuestionario;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }
}
