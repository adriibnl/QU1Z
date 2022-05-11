package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.DAO.RespuestasDAO;
import com.example.tfgfinal.DAO.UsuariosDAO;
import com.example.tfgfinal.Models.Pregunta;
import com.example.tfgfinal.Models.Respuesta;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoCuestionarioActivity extends AppCompatActivity {
CuestionariosDAO cDAO = new CuestionariosDAO();
PreguntasDAO pDAO = new PreguntasDAO();
RespuestasDAO rDAO = new RespuestasDAO();
UsuariosDAO uDAO = new UsuariosDAO();
ArrayList<View> ViewList = new ArrayList<View>();
LinearLayout layoutlist;
List<Pregunta> listPreguntas;
ArrayList<Integer> idPreguntas;
boolean verRespuestas = false;
int idCuestionario;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_cuestionario);
        idPreguntas = new ArrayList<Integer>();
        layoutlist = findViewById(R.id.layoutRespuestasList);
        Bundle bundle = getIntent().getExtras();
        idCuestionario =getIntent().getExtras().getInt("IdCuestionario");
        mAuth = FirebaseAuth.getInstance();
        int iduser = uDAO.GetUsuarioByUserID(mAuth.getCurrentUser().getUid()).Id;
        try {
            verRespuestas = getIntent().getExtras().getBoolean("verRespuestas");
        } catch (Exception e) {}
        listPreguntas = pDAO.GetAllPreguntasFromCuestionario(idCuestionario);
        mAuth = FirebaseAuth.getInstance();
        for (Pregunta p:
             listPreguntas) {
            idPreguntas.add(p.Id);
            final View preguntaView = getLayoutInflater().inflate(R.layout.row_add_respuesta_enunciado,
                    null, false);
            TextView enunciadoTV = preguntaView.findViewById(R.id.respuestaEnunciadoET);
            enunciadoTV.setText(p.Enunciado);
            if (verRespuestas)
                enunciadoTV.setEnabled(false);
            layoutlist.addView(preguntaView);
            ViewList.add(preguntaView);
            if (p.Tipo.equals("Texto")) {
                final View respuestaView =
                        getLayoutInflater().inflate(R.layout.row_add_respuesta_edittext,
                        null, false);
                EditText respuestaET = respuestaView.findViewById(R.id.editTextRespuesta);
                if (verRespuestas) {
                    try{respuestaET.setEnabled(false);
                        respuestaET.setText(rDAO.GetRespuestaFromUserAndPregunta(iduser,p.Id).Texto);
                    } catch (Exception e) { e.printStackTrace();}
                }

                layoutlist.addView(respuestaView);
                ViewList.add(respuestaView);
            }

        }
        //region ClickListeners

        //endregion
    }

    public void GuardarRespuestas(View view) {
        int iduser = uDAO.GetUsuarioByUserID(mAuth.getCurrentUser().getUid()).Id; int contador = 0;
        if (!rDAO.CheckRespuestasFromUser(iduser,idCuestionario)) {
        for (View v:
                ViewList) {
            Respuesta p = new Respuesta();
            try {
                EditText respuesta  = v.findViewById(R.id.editTextRespuesta);
                String respuestString = respuesta.getText().toString();
                //EditText enunciadoET = v.findViewById(R.id.respuestaEnunciadoET);
                //String enunciado = enunciadoET.getText().toString();
                int f = ViewList.indexOf(v);
                p.Texto = respuestString; p.IdCuestionario =idCuestionario;p.IdPregunta =
                        listPreguntas.get(contador).Id;
                p.IdUsuario = iduser; p.Fecha = Date.valueOf(LocalDate.now().toString());

                rDAO.AddRespuesta(p);contador++;}

             catch (Exception e) {e.printStackTrace();} } }


        finish();
    }

    }
