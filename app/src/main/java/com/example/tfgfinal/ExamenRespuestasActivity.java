package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.DAO.RespuestasDAO;
import com.example.tfgfinal.Models.Examen;
import com.example.tfgfinal.Models.ExamenRespuesta;
import com.example.tfgfinal.Models.PreguntaExamen;
import com.example.tfgfinal.Models.Respuesta;

import java.util.Arrays;
import java.util.List;

public class ExamenRespuestasActivity extends AppCompatActivity {
    int idRespuesta;
    CuestionariosDAO cDao = new CuestionariosDAO();
    PreguntasDAO pDao = new PreguntasDAO();
    RespuestasDAO rDAO = new RespuestasDAO();
    LinearLayout layout;
    TextView puntuacionTV;
    String [] Respuestas;
    List<PreguntaExamen> preguntasExamen;
    Examen examen;
    ExamenRespuesta respuesta = new ExamenRespuesta();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen_respuestas);
        idRespuesta = getIntent().getExtras().getInt("idRespuesta");
        layout = findViewById(R.id.respuestaExamenLayout);
        puntuacionTV = findViewById(R.id.puntuacionRespuestaTV);
        respuesta = rDAO.GetRespuestaExamenById(idRespuesta);
        examen = cDao.GetExamenById(respuesta.IdExamen);
        preguntasExamen = pDao.GetAllPreguntasFromExamen(examen.Id);
        Respuestas = respuesta.respuestas.split("/");
        puntuacionTV.setText(respuesta.puntuacion);
        for (String r:
             Respuestas) {
            final View preguntaView = getLayoutInflater().inflate(R.layout.row_respuesta_examen,
                    null, false);
            String[] opciones = preguntasExamen.get(Arrays.asList(Respuestas).indexOf(r)).opciones.split("/");
            String opcionCorrecta = preguntasExamen.get(Arrays.asList(Respuestas).indexOf(r)).opcionCorrecta;
            TextView enunciadoTV = preguntaView.findViewById(R.id.textViewEnunciadoExamen);
            String ent = preguntasExamen.get(Arrays.asList(Respuestas).indexOf(r)).Enunciado;
            enunciadoTV.setText(ent);
            TextView opcion1TV = preguntaView.findViewById(R.id.editTextPreguntaExamen1);
            opcion1TV.setText(opciones[0]);
            if (opcion1TV.getText().toString().equals(opcionCorrecta) && opcion1TV.getText().toString().equals(r)) {
                opcion1TV.setBackgroundResource(R.drawable.border_good);
            }
            if (opcion1TV.getText().toString().equals(r) && !opcion1TV.getText().toString().equals(opcionCorrecta)) {
                opcion1TV.setBackgroundResource(R.drawable.border_fail);
            }
            TextView opcion2TV = preguntaView.findViewById(R.id.editTextPreguntaExamen2);
            opcion2TV.setText(opciones[1]);
            if (opcion2TV.getText().toString().equals(opcionCorrecta) && opcion2TV.getText().toString().equals(r)) {
                opcion2TV.setBackgroundResource(R.drawable.border_good);
            }
            if (opcion2TV.getText().toString().equals(r) && !opcion2TV.getText().toString().equals(opcionCorrecta)) {
                opcion2TV.setBackgroundResource(R.drawable.border_fail);
            }
            TextView opcion3TV = preguntaView.findViewById(R.id.editTextPreguntaExamen3);
            opcion3TV.setText(opciones[2]);
            if (opcion3TV.getText().toString().equals(opcionCorrecta) && opcion3TV.getText().toString().equals(r)) {
                opcion3TV.setBackgroundResource(R.drawable.border_good);
            }
            if (opcion3TV.getText().toString().equals(r) && !opcion3TV.getText().toString().equals(opcionCorrecta)) {
                opcion3TV.setBackgroundResource(R.drawable.border_fail);
            }
            TextView opcion4TV = preguntaView.findViewById(R.id.editTextPreguntaExamen4);
            opcion4TV.setText(opciones[3]);
            if (opcion4TV.getText().toString().equals(opcionCorrecta) && opcion4TV.getText().toString().equals(r)) {
                opcion4TV.setBackgroundResource(R.drawable.border_good);
            }
            if (opcion4TV.getText().toString().equals(r) && !opcion4TV.getText().toString().equals(opcionCorrecta)) {
                opcion4TV.setBackgroundResource(R.drawable.border_fail);
            }
            layout.addView(preguntaView);
        }
    }
}