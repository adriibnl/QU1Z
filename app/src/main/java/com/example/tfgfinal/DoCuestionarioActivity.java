package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
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
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
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
int idUsuarioResuelto;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_cuestionario);
        idPreguntas = new ArrayList<Integer>();
        layoutlist = findViewById(R.id.layoutRespuestasList);
        Bundle bundle = getIntent().getExtras();
        idCuestionario =getIntent().getExtras().getInt("IdCuestionario");
        idUsuarioResuelto = getIntent().getExtras().getInt("IdUser");
        mAuth = FirebaseAuth.getInstance();
        int iduser = uDAO.GetUsuarioByUserID(mAuth.getCurrentUser().getUid()).Id;

        try {
            verRespuestas = getIntent().getExtras().getBoolean("verRespuestas");
        } catch (Exception e) {}
        //if (!verRespuestas)
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
            switch(p.Tipo) {
                case "Texto" :
                    final View respuestaViewEt =
                            getLayoutInflater().inflate(R.layout.row_add_respuesta_edittext,
                                    null, false);
                    EditText respuestaET = respuestaViewEt.findViewById(R.id.editTextRespuesta);
                    if (verRespuestas) {
                        try{respuestaET.setEnabled(false);
                            respuestaET.setText(rDAO.GetRespuestaFromUserAndPregunta(idUsuarioResuelto,p.Id).Texto);
                        } catch (Exception e) { e.printStackTrace();}
                    }

                    layoutlist.addView(respuestaViewEt);
                    ViewList.add(respuestaViewEt);
                    break;
                case "CheckBox" :
                    final View respuestaViewCb =
                            getLayoutInflater().inflate(R.layout.row_add_respuesta_checkbox,
                                    null, false);
                    CheckBox respuestaCb = respuestaViewCb.findViewById(R.id.checkBoxRespuesta);
                    if (verRespuestas) {
                        try{respuestaCb.setEnabled(false);
                            if (rDAO.GetRespuestaFromUserAndPregunta(idUsuarioResuelto,p.Id).Texto.equals(
                                    "true"))
                            {respuestaCb.setChecked(true);}
                            else {respuestaCb.setChecked(false);}
                            respuestaCb.setText(p.Enunciado);
                        } catch (Exception e) { e.printStackTrace();}
                    }

                    layoutlist.addView(respuestaViewCb);
                    ViewList.add(respuestaViewCb);
                    break;
                case "Fechas" :
                    final View respuestaViewDP =
                            getLayoutInflater().inflate(R.layout.row_add_respuesta_date,
                                    null, false);
                    EditText respuestasDP = respuestaViewDP.findViewById(R.id.editTextDate);
                    respuestasDP.setFocusable(false);
                    Calendar c = Calendar.getInstance();
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year = c.get(Calendar.YEAR);

                    DatePickerDialog dpd = new DatePickerDialog(DoCuestionarioActivity.this);
                    dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month,
                                              int dayOfMonth) {
                            respuestasDP.setText(dayOfMonth + "/" + (month+1) +"/" + year);
                        }
                    });
                    respuestasDP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dpd.show();
                        }
                    });
                    if (verRespuestas) {
                        try{respuestasDP.setEnabled(false);
                            respuestasDP.setText(rDAO.GetRespuestaFromUserAndPregunta(idUsuarioResuelto,
                                    p.Id).Texto);
                        } catch (Exception e) { e.printStackTrace();}
                    }
                    if (layoutlist.indexOfChild(respuestaViewDP) != -1) {
                        layoutlist.removeView(respuestaViewDP);
                    }
                    layoutlist.addView(respuestaViewDP);
                    ViewList.add(respuestaViewDP);

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
            int f = 0;
            Respuesta p = new Respuesta();
            try {
                String type = view.getClass().getName();

                        EditText respuestaEt  = v.findViewById(R.id.editTextRespuesta);
                        CheckBox respuestaCb  = v.findViewById(R.id.checkBoxRespuesta);
                        EditText respuestaDt = v.findViewById(R.id.editTextDate);
                        if (respuestaEt != null) {String respuestStringEt = respuestaEt.getText().toString();
                            //EditText enunciadoET = v.findViewById(R.id.respuestaEnunciadoET);
                            //String enunciado = enunciadoET.getText().toString();
                            f = ViewList.indexOf(v);
                            p.Texto = respuestStringEt; p.IdCuestionario =idCuestionario;p.IdPregunta =
                                    listPreguntas.get(contador).Id;
                            p.IdUsuario = iduser; p.Fecha = Date.valueOf(LocalDate.now().toString());

                            rDAO.AddRespuesta(p);contador++;}

                        if (respuestaCb != null) {//EditText enunciadoET = v.findViewById(R.id.respuestaEnunciadoET);
                            //String enunciado = enunciadoET.getText().toString();
                            Boolean respuestStringCb = respuestaCb.isChecked();
                            f = ViewList.indexOf(v);
                            p.Texto = respuestStringCb.toString(); p.IdCuestionario =idCuestionario;p.IdPregunta =
                                    listPreguntas.get(contador).Id;
                            p.IdUsuario = iduser; p.Fecha = Date.valueOf(LocalDate.now().toString());

                            rDAO.AddRespuesta(p);contador++;}
                        if (respuestaDt != null) {
                            String respuestStringDt = respuestaDt.getText().toString();
                            //EditText enunciadoET = v.findViewById(R.id.respuestaEnunciadoET);
                            //String enunciado = enunciadoET.getText().toString();
                            f = ViewList.indexOf(v);
                            p.Texto = respuestStringDt; p.IdCuestionario =idCuestionario;p.IdPregunta =
                                    listPreguntas.get(contador).Id;
                            p.IdUsuario = iduser; p.Fecha = Date.valueOf(LocalDate.now().toString());

                            rDAO.AddRespuesta(p);contador++;
                        }



                }


             catch (Exception e) {
                e.printStackTrace();} } }


        finish();
    }

    }
