package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.DAO.RespuestasDAO;
import com.example.tfgfinal.Models.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class DoCuestionarioActivity extends AppCompatActivity {
CuestionariosDAO cDAO = new CuestionariosDAO();
PreguntasDAO pDAO = new PreguntasDAO();
RespuestasDAO rDAO = new RespuestasDAO();
ArrayList<View> ViewList = new ArrayList<View>();
LinearLayout layoutlist;
List<Pregunta> listPreguntas;

int idCuestionario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_cuestionario);
        layoutlist = findViewById(R.id.layoutRespuestasList);
        Bundle bundle = getIntent().getExtras();
        idCuestionario =getIntent().getExtras().getInt("IdCuestionario");
        listPreguntas = pDAO.GetAllPreguntasFromCuestionario(idCuestionario);
        for (Pregunta p:
             listPreguntas) {
            final View preguntaView = getLayoutInflater().inflate(R.layout.row_add_respuesta_enunciado,
                    null, false);
            TextView enunciadoTV = preguntaView.findViewById(R.id.respuestaEnunciadoET);
            layoutlist.addView(preguntaView);
            ViewList.add(preguntaView);
            if (p.Tipo.equals("Texto")) {
                final View respuestaView =
                        getLayoutInflater().inflate(R.layout.row_add_respuesta_edittext,
                        null, false);
                EditText respuestaET = preguntaView.findViewById(R.id.editTextRespuesta);
                layoutlist.addView(respuestaView);
                ViewList.add(respuestaView);
            }

        }
        //region ClickListeners

        //endregion
    }

    public void GuardarPreguntas(View view) {
        for (View v:
                ViewList) {
            Pregunta p = new Pregunta();
            Spinner spinner = v.findViewById(R.id.tipoPreguntaSpinner);
            String tipo = spinner.getSelectedItem().toString();
            EditText enunciadoET = v.findViewById(R.id.enunciadoET);
            String enunciado = enunciadoET.getText().toString();
            p.Tipo = tipo; p.Enunciado =enunciado; p.IdCuestionario = idCuestionario;
            pDAO.AddPregunta(p);
        }
    }

    }
