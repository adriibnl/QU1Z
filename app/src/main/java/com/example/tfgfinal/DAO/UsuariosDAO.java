package com.example.tfgfinal.DAO;

import com.example.tfgfinal.Connection.ConSQL;
import com.example.tfgfinal.Models.Usuario;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UsuariosDAO {
    ConSQL c = new ConSQL();
    Connection con = c.conClass();
    public List<Usuario> getAllUsuarios() {
        List<Usuario> list = null;
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select * from USUARIOS");
            while (rs.next()) {
                Usuario user = new Usuario();
                user.Id = rs.getInt(1);
                user.Username = rs.getString(2);
                user.FechaCreacion = rs.getDate(3);
                user.IdUser = rs.getString(4);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    return list;
    }

    public Usuario getUsuarioById(int id) {
        Usuario user = new Usuario();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select * from USUARIOS where id =" +id);
            user.Id = rs.getInt(1);
            user.Username = rs.getString(2);
            user.FechaCreacion = rs.getDate(3);
            user.IdUser = rs.getString(4);
        } catch (SQLException e) {}
        return user;
    }

    public Usuario getUsuarioByUsername(String username) {
        Usuario user = new Usuario();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select * from USUARIOS where Username =" +username);
            user.Id = rs.getInt(1);
            user.Username = rs.getString(2);
            user.FechaCreacion = rs.getDate(3);
            user.IdUser = rs.getString(4);
        } catch (SQLException e) {}
        return user;
    }

    public boolean checkRegisteredUser(String IdUser) {
        Usuario user = new Usuario();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("Select * from USUARIOS where IdUser =" +IdUser);
            if (rs.next() == false) { return false;}
            else {return true;}
        } catch (SQLException e) {return false;}

    }

    public void addUsuario(Usuario user) {
        try {
            PreparedStatement stat = con.prepareStatement("INSERT INTO USUARIOS(USERNAME," +
                    "FECHACREACION,IDUSER) VALUES (?,?,?)");
            stat.setString(1,user.Username);
            stat.setDate(2, (Date) user.FechaCreacion);
            stat.setString(3, user.IdUser);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
