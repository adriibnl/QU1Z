package com.example.tfgfinal.DAO;

import com.example.tfgfinal.Connection.ConSQL;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.Examen;
import com.example.tfgfinal.Models.ExamenRespuesta;
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

    public int GetCuestionariosResueltosFromUser(int idUser) {
        try {
            Statement stat = con.createStatement();
            int count = -1;
            ResultSet rs =
                    stat.executeQuery("SELECT idCuestionario FROM RESPUESTAS where idusuario ='" + idUser +
                            "' group by idCuestionario order by idCuestionario" );
            while (rs.next()){

                count = rs.getRow();
            }


            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }

    }

    public int GetRespuestasDeMisCuestionarios(int idUser) {
        try {
            Statement stat = con.createStatement();
            int count = -1;
            ResultSet rs =
                    stat.executeQuery("SELECT COUNT(RESPUESTAS.ID) FROM RESPUESTAS INNER JOIN " +
                            "CUESTIONARIO ON RESPUESTAS.IDCUESTIONARIO = CUESTIONARIO.ID WHERE " +
                            "CUESTIONARIO.IDCREADOR = '" +idUser +"' GROUP BY IDCUESTIONARIO" );
            while (rs.next()){

                count = rs.getRow();
            }


            return count;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }

    }
    //region Examenes
    public void AddRespuestaExamen(ExamenRespuesta c) {

        try{
            PreparedStatement stat = con.prepareStatement("INSERT INTO EXAMENRESPUESTAS" +
                    "(IDEXAMEN," +
                    "RESPUESTAS,IDUSER,FECHA,PUNTUACION) VALUES (?,?,?,?,?)");
            stat.setInt(1,c.IdExamen);
            stat.setString(2,c.respuestas);
            stat.setInt(3,c.idUser);
            stat.setString(5,c.puntuacion);
            stat.setDate(4, (Date) c.fecha);
            stat.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ExamenRespuesta GetRespuestasByExamenAndUser(int IdExamen,int IdUser) {
        ExamenRespuesta C = new ExamenRespuesta();
        try {
            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM EXAMENRESPUESTAS WHERE ID = '" +IdExamen+"' " +
                            "AND IDUSER ='" +IdUser + "'");
            C.Id = rs.getInt(1);
            C.IdExamen = rs.getInt(2);
            C.respuestas = rs.getString(2);
            C.idUser   = rs.getInt(4);
            C.fecha = rs.getDate(5);
            C.puntuacion = rs.getString(6);
        } catch (SQLException e) {}
        return C;
    }

    public List<ExamenRespuesta> GetAllRespuestasFromExamen(int id) {
        try {
            List<ExamenRespuesta> lista = new ArrayList<ExamenRespuesta>();

            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM EXAMENRESPUESTAS where idExamen ='" + id +
                            "'");
            while (rs.next()) {
                ExamenRespuesta C = new ExamenRespuesta();
                C.Id = rs.getInt(1);
                C.IdExamen = rs.getInt(2);
                C.respuestas = rs.getString(3);
                C.idUser = rs.getInt(4);
                C.fecha = rs.getDate(5);
                C.puntuacion = rs.getString(6);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public ExamenRespuesta GetRespuestaExamenById(int Id) {
        ExamenRespuesta C = new ExamenRespuesta();
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM EXAMENRESPUESTAS WHERE ID = '" +Id+"'");
            rs.next();
            C.Id = rs.getInt(1);
            C.IdExamen = rs.getInt(2);
            C.respuestas = rs.getString(3);
            C.idUser = rs.getInt(4);
            C.fecha = rs.getDate(5);
            C.puntuacion = rs.getString(6);
        } catch (SQLException e) {}
        return C;
    }

    public List<ExamenRespuesta> GetAllRespuestasFromUserExams(int id) {
        try {
            List<ExamenRespuesta> lista = new ArrayList<ExamenRespuesta>();

            Statement stat = con.createStatement();
            ResultSet rs =
                    stat.executeQuery("SELECT * FROM EXAMENRESPUESTAS inner join Examenes on " +
                            "ExamenRespuestas.idExamen = Examenes.id " +
                            "  where Examenes.idcreador ='" + id +
                            "'");
            while (rs.next()) {
                ExamenRespuesta C = new ExamenRespuesta();
                C.Id = rs.getInt(1);
                C.IdExamen = rs.getInt(2);
                C.respuestas = rs.getString(3);
                C.idUser = rs.getInt(4);
                C.fecha = rs.getDate(5);
                C.puntuacion = rs.getString(6);
                lista.add(C);
            }
            return lista;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

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
    //endregion
}
