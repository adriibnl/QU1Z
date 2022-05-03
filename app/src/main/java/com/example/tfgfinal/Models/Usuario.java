package com.example.tfgfinal.Models;

import java.util.Date;

public class Usuario {
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public int Id;
    public String Username;
    public String Mail;
    public Date FechaCreacion;
    public String IdUser;
}
