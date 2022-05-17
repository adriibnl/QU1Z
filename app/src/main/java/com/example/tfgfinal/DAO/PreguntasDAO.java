package com.example.tfgfinal.DAO;

import com.example.tfgfinal.Connection.ConSQL;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.DDLModel;
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

public class PreguntasDAO {
    ConSQL c = new ConSQL();
    Connection con = c.conClass();
    //region Preguntas
    public List<Pregunta> GetAllPreguntas() {
        try {
            List<Pregunta> lista = null;

            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM PREGUNTAS");
            while (rs.next()) {
                Pregunta P = new Pregunta();
                P.Id = rs.getInt(1);
                P.IdCuestionario = rs.getInt(2);
                P.Enunciado = rs.getString(3);
                P.Tipo = rs.getString(4);
                lista.add(P);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public void AddPregunta(Pregunta c) {
        try{
            PreparedStatement stat = con.prepareStatement("INSERT INTO PREGUNTAS(IDCUESTIONARIO," +
                    "ENUNCIADO,TIPO) VALUES (?,?,?)");
            stat.setInt(1,c.IdCuestionario);
            stat.setString(2,c.Enunciado);
            stat.setString(3,c.Tipo);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Pregunta GetPreguntaById(int Id) {
        Pregunta C = new Pregunta();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM PREGUNTAS WHERE ID = '" +Id+"'");
            C.Id = rs.getInt(1);
            C.IdCuestionario = rs.getInt(2);
            C.Enunciado = rs.getString(3);
            C.Tipo   = rs.getString(4);
        } catch (SQLException e) {}
        return C;
    }

    public List<Pregunta> GetAllPreguntasFromCuestionario(int id) {
        try {
            List<Pregunta> lista = new ArrayList<Pregunta>();

            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM PREGUNTAS where idCuestionario ='" + id +
                            "'");
            while (rs.next()) {
                Pregunta C = new Pregunta();
                C.Id = rs.getInt(1);
                C.IdCuestionario = rs.getInt(2);
                C.Enunciado = rs.getString(3);
                C.Tipo = rs.getString(4);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public boolean CheckPregunta(Pregunta p) {
        Pregunta C = new Pregunta();
        try {
            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM PREGUNTAS WHERE IDCUESTIONARIO = '" +p.IdCuestionario+
                    "'and Enunciado = '" +p.Enunciado + "'");
            if (rs.next()) {
                return true;
            }
            else return false;
        } catch (SQLException e) {}
        return true;
    }

//endregion

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

    public DDLModel GetTipoByString(String tipo) {
        DDLModel C = new DDLModel();
        try {
            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM TIPOPREGUNTAS WHERE TIPOPREGUNTAS = '" +tipo+
                            "'");
            C.id = rs.getInt(1);
            C.text = rs.getString(2);

        } catch (SQLException e) {}
        return C;
    }

    public void SaveOpcion(int idPregunta,String Opcion) {
        try{
            PreparedStatement stat = con.prepareStatement("INSERT INTO ComboBoxOpciones" +
                    "(IdPregunta," +
                    "Opcion) VALUES (?,?)");
            stat.setInt(1,idPregunta);
            stat.setString(2,Opcion);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> GetAllOpcionesFromPregunta(int Id) {
        try {
            List<String> lista = new ArrayList<String>() {
            };

            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM COMBOBOXOPCIONES");
            while (rs.next()) {
                String opciones = rs.getString(3);
                lista.add(opciones);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    //endregion
}
