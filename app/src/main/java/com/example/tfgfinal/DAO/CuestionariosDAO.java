package com.example.tfgfinal.DAO;

import com.example.tfgfinal.Connection.ConSQL;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.DDLModel;

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
    //region TiposCuestionario

    //endregion


}
