package com.example.tfgfinal.DAO;

import com.example.tfgfinal.Connection.ConSQL;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.CuestionarioResueltoUser;
import com.example.tfgfinal.Models.DDLModel;
import com.example.tfgfinal.Models.Examen;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CuestionariosDAO {
    ConSQL c = new ConSQL();
    Connection con = c.conClass();
    //region Cuestionarios
    public List<Cuestionario> GetAllCuestionarios() {
        try {
            List<Cuestionario> lista = new ArrayList<Cuestionario>() {
            };

            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM CUESTIONARIO");
            while (rs.next()) {
            Cuestionario C = new Cuestionario();
            C.Id = rs.getInt(1);
            C.Nombre = rs.getString(2);
            C.IdCreador = rs.getInt(3);
            C.FechaCreacion = rs.getDate(4);
            C.Descripcion = rs.getString(5);
            lista.add(C);
            }
        return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public List<Cuestionario> GetAllCuestionariosFromUser(int id) {
        try {
            List<Cuestionario> lista = new ArrayList<Cuestionario>();

            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM CUESTIONARIO where idCreador ='" + id + "'");
            while (rs.next()) {
                Cuestionario C = new Cuestionario();
                C.Id = rs.getInt(1);
                C.Nombre = rs.getString(2);
                C.IdCreador = rs.getInt(3);
                C.FechaCreacion = rs.getDate(4);
                C.Descripcion = rs.getString(5);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public List<CuestionarioResueltoUser> GetAllCuestionariosFromUserRespuestas(int id) {
        try {
            List<CuestionarioResueltoUser> lista = new ArrayList<CuestionarioResueltoUser>();

            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT distinct idCuestionario,idUsuario,Fecha from" +
                    " Respuestas inner join Cuestionario on Cuestionario.Id = Respuestas.idCuestionario inner join Usuarios on Usuarios.Id = Cuestionario.idCreador where idCreador ='" + id + "'");
            while (rs.next()) {
                CuestionarioResueltoUser C = new CuestionarioResueltoUser();
                C.IdCuestionario = rs.getInt(1);
                C.IdUsuario = rs.getInt(2);
                C.fecha = rs.getDate(3);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public void AddCuestionario(Cuestionario c) {
        try{
            PreparedStatement stat = con.prepareStatement("INSERT INTO CUESTIONARIO(NOMBRE," +
                    "IDCREADOR,FECHACREACION,DESCRIPCION) VALUES (?,?,?,?)");
            stat.setString(1,c.Nombre);
            stat.setInt(2,c.IdCreador);
            stat.setDate(3, (Date) c.FechaCreacion);
            stat.setString(4,c.Descripcion);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Cuestionario GetCuestionarioById(int Id) {
        Cuestionario C = new Cuestionario();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM CUESTIONARIO WHERE ID ='" +Id +"'");
            C.Id = rs.getInt(1);
            C.Nombre = rs.getString(2);
            C.IdCreador = rs.getInt(3);
            C.FechaCreacion = rs.getDate(4);
            C.Descripcion = rs.getString(5);
        } catch (SQLException e) {}
        return C;
    }
//endregion
    //Region Examenes
public List<Examen> GetAllExamenes() {
    try {
        List<Examen> lista = new ArrayList<Examen>() {
        };

        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM EXAMENES");
        while (rs.next()) {
            Examen C = new Examen();
            C.Id = rs.getInt(1);
            C.Nombre = rs.getString(3);
            C.IdCreador = rs.getInt(2);
            C.FechaCreacion = rs.getDate(5);
            C.Descripcion = rs.getString(4);
            lista.add(C);
        }
        return lista;
    } catch (SQLException throwables) {
        throwables.printStackTrace();
        return null;
    }

}

    public List<Examen> GetAllExamenesFromUser(int id) {
        try {
            List<Examen> lista = new ArrayList<Examen>();

            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM EXAMENES where idCreador ='" + id +
                    "'");
            while (rs.next()) {
                Examen C = new Examen();
                C.Id = rs.getInt(1);
                C.Nombre = rs.getString(3);
                C.IdCreador = rs.getInt(2);
                C.FechaCreacion = rs.getDate(5);
                C.Descripcion = rs.getString(4);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public void AddExamen(Examen c) {
        try{
            PreparedStatement stat = con.prepareStatement("INSERT INTO EXAMENES(NOMBRE," +
                    "IDCREADOR,FECHACREACION,DESCRIPCION) VALUES (?,?,?,?)");
            stat.setString(1,c.Nombre);
            stat.setInt(2,c.IdCreador);
            stat.setDate(3, (Date) c.FechaCreacion);
            stat.setString(4,c.Descripcion);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Examen GetExamenById(int Id) {
        Examen C = new Examen();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM EXAMENES WHERE ID ='" +Id +"'");
            rs.next();
            C.Id = rs.getInt(1);
            C.Nombre = rs.getString(3);
            C.IdCreador = rs.getInt(2);
            C.FechaCreacion = rs.getDate(5);
            C.Descripcion = rs.getString(4);
        } catch (SQLException e) {e.printStackTrace();
        String a = e.toString();
        }
        return C;
    }
    //Endregion


}
