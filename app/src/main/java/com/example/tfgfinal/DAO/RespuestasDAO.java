package com.example.tfgfinal.DAO;

import com.example.tfgfinal.Connection.ConSQL;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.Pregunta;
import com.example.tfgfinal.Models.Respuesta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RespuestasDAO {
    ConSQL c = new ConSQL();
    Connection con = c.conClass();



    public void AddRespuesta(Respuesta c) {

        try{
            PreparedStatement stat = con.prepareStatement("INSERT INTO RESPUESTAS(IDPREGUNTA," +
                    "IDCUESTIONARIO,TEXTO,IDUSUARIO,FECHA) VALUES (?,?,?,?,?)");
            stat.setInt(1,c.IdPregunta);
            stat.setInt(2,c.IdCuestionario);
            stat.setString(3,c.Texto);
            stat.setInt(4,c.IdUsuario);
            stat.setDate(5, (Date) c.Fecha);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Respuesta GetRespuestaById(int Id) {
        Respuesta C = new Respuesta();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM RESPUESTAS WHERE ID = '" +Id+"'");
            C.Id = rs.getInt(1);
            C.IdCuestionario = rs.getInt(3);
            C.IdPregunta = rs.getInt(2);
            C.Texto   = rs.getString(4);
            C.IdUsuario = rs.getInt(5);
        } catch (SQLException e) {}
        return C;
    }

    public List<Respuesta> GetAllRespuestasFromCuestionario(int id) {
        try {
            List<Respuesta> lista = new ArrayList<Respuesta>();

            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM RESPUESTA where idCuestionario ='" + id +
                    "'");
            while (rs.next()) {
                Respuesta C = new Respuesta();
                C.Id = rs.getInt(1);
                C.IdPregunta = rs.getInt(2);
                C.IdCuestionario = rs.getInt(3);
                C.Texto = rs.getString(4);
                C.IdUsuario = rs.getInt(5);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public List<Respuesta> GetAllRespuestasFromPregunta(int id) {
        try {
            List<Respuesta> lista = new ArrayList<Respuesta>();

            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM RESPUESTA where idPregunta ='" + id +
                            "'");
            while (rs.next()) {
                Respuesta C = new Respuesta();
                C.Id = rs.getInt(1);
                C.IdPregunta = rs.getInt(2);
                C.IdCuestionario = rs.getInt(3);
                C.Texto = rs.getString(4);
                C.IdUsuario = rs.getInt(5);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public boolean CheckRespuestasFromUser(int idUser,int idCuestionario) {
        try {
            List<Respuesta> lista = new ArrayList<Respuesta>();

            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM RESPUESTAS where idCuestionario ='" + idCuestionario +
                            "' and idUsuario = '" + idUser + "'" );
            if(rs.next()) {return true;}
            else {return false;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return false;
    }

    public List<Respuesta> GetAllRespuestasFromUserAndCuestionario(int idUser,int idCuestionario) {
        try {
            List<Respuesta> lista = new ArrayList<Respuesta>();

            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM RESPUESTAS where idCuestionario ='" + idCuestionario +
                            "' and idUsuario = '" + idUser + "'" );
            while (rs.next()) {
                Respuesta C = new Respuesta();
                C.Id = rs.getInt(1);
                C.IdPregunta = rs.getInt(2);
                C.IdCuestionario = rs.getInt(3);
                C.Texto = rs.getString(4);
                C.IdUsuario = rs.getInt(5);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public Respuesta GetRespuestaFromUserAndPregunta(int idUser,int idPregunta) {
        try {
            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM RESPUESTAS where idPregunta ='" + idPregunta +
                            "' and idUsuario = '" + idUser + "'" );
            rs.next();
                Respuesta C = new Respuesta();
                C.Id = rs.getInt(1);
                C.IdPregunta = rs.getInt(2);
                C.IdCuestionario = rs.getInt(3);
                C.Texto = rs.getString(4);
                C.IdUsuario = rs.getInt(5);


            return C;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }


    //region Tipo de preguntas
    public List<String> GetAllTipoPreguntas() {
        try {
            List<String> lista = new ArrayList<String>() {
            };

            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM TIPOPREGUNTAS");
            while (rs.next()) {
                String tipo = rs.getString(2);
                lista.add(tipo);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
    //endregion
}
