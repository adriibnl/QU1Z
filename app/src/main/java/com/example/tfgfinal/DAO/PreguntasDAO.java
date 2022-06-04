package com.example.tfgfinal.DAO;

import com.example.tfgfinal.Connection.ConSQL;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.DDLModel;
import com.example.tfgfinal.Models.Pregunta;
import com.example.tfgfinal.Models.PreguntaExamen;
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
    //region Examenes
public List<PreguntaExamen> GetAllPreguntasExamen() {
    try {
        List<PreguntaExamen> lista = null;

        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT * FROM PREGUNTASEXAMENES");
        while (rs.next()) {
            PreguntaExamen P = new PreguntaExamen();
            P.Id = rs.getInt(1);
            P.opciones = rs.getString(2);
            P.opcionCorrecta = rs.getString(3);
            P.Enunciado = rs.getString(4);
            P.IdExamen = rs.getInt(5);
            lista.add(P);
        }
        return lista;
    } catch (SQLException throwables) {
        throwables.printStackTrace();
        return null;
    }

}

    public void AddPreguntaExamen(PreguntaExamen c) {
        try{
            PreparedStatement stat = con.prepareStatement("INSERT INTO PREGUNTASEXAMENES" +
                    "(OPCIONES," +
                    "OPCIONCORRECTA,ENUNCIADO,IDEXAMEN) VALUES (?,?,?,?)");
            stat.setString(1,c.opciones);
            stat.setString(2,c.opcionCorrecta);
            stat.setString(3,c.Enunciado);
            stat.setInt(4,c.IdExamen);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public PreguntaExamen GetPreguntaExamenById(int Id) {
        PreguntaExamen C = new PreguntaExamen();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM PREGUNTASEXAMENES WHERE ID = '" +Id+
                    "'");
            C.Id = rs.getInt(1);
            C.opciones = rs.getString(2);
            C.opcionCorrecta = rs.getString(3);
            C.Enunciado = rs.getString(4);
            C.IdExamen = rs.getInt(5);
        } catch (SQLException e) {}
        return C;
    }

    public List<PreguntaExamen> GetAllPreguntasFromExamen(int id) {
        try {
            List<PreguntaExamen> lista = new ArrayList<PreguntaExamen>();

            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM PREGUNTASEXAMENES where idExamen ='" + id +
                            "'");
            while (rs.next()) {
                PreguntaExamen C = new PreguntaExamen();
                C.Id = rs.getInt(1);
                C.opciones = rs.getString(2);
                C.opcionCorrecta = rs.getString(3);
                C.Enunciado = rs.getString(4);
                C.IdExamen = rs.getInt(5);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public boolean CheckPreguntaExamen(PreguntaExamen p) {
        PreguntaExamen C = new PreguntaExamen();
        try {
            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM PREGUNTASEXAMENES WHERE IDEXAMEN = '" +p.IdExamen+
                            "'and OPCIONES = '" +p.opciones + "'");
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
