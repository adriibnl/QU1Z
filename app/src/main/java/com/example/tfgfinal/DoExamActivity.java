package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.DAO.RespuestasDAO;
import com.example.tfgfinal.DAO.UsuariosDAO;
import com.example.tfgfinal.Models.Examen;
import com.example.tfgfinal.Models.ExamenRespuesta;
import com.example.tfgfinal.Models.PreguntaExamen;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class DoExamActivity extends AppCompatActivity {
    UsuariosDAO uDAO = new UsuariosDAO();
    CuestionariosDAO cuestionariosDAO = new CuestionariosDAO();
    PreguntasDAO preguntasDAO = new PreguntasDAO();
    RespuestasDAO rDAO = new RespuestasDAO();
    int idExamen;
    private FirebaseAuth mAuth;
    List<PreguntaExamen> ListaPreguntas;
    int Contador = 0;
    int ContadorAciertos =0;
    String [] ListaRespuestas;
    String stringRespuestas = "";
    String puntuacion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_exam);
        //region Inicializar vistas
        final TextView EnunciadoTV = findViewById(R.id.enunciadoDoExamenTV);
        final TextView Opcion1 = findViewById(R.id.Opcion1TV);
        final TextView Opcion2 = findViewById(R.id.Opcion2TV);
        final TextView Opcion3 = findViewById(R.id.Opcion3TV);
        final TextView Opcion4 = findViewById(R.id.Opcion4TV);
        final LinearLayout L1 = findViewById(R.id.Opcion1Layout);
        final LinearLayout L2 = findViewById(R.id.Opcion2Layout);
        final LinearLayout L3 = findViewById(R.id.Opcion3Layout);
        final LinearLayout L4 = findViewById(R.id.Opcion4Layout);
        //endregion
        mAuth = FirebaseAuth.getInstance();
        int IdUser = uDAO.GetUsuarioByUserID(mAuth.getCurrentUser().getUid()).Id;
        idExamen =getIntent().getExtras().getInt("IdExamen");
        ListaPreguntas = preguntasDAO.GetAllPreguntasFromExamen(idExamen);
        CargarPregunta();
        //region OnClickListeners
        L1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Opcion1.getText().toString().equals(ListaPreguntas.get(Contador).opcionCorrecta)) {
                    L1.setBackgroundResource(R.drawable.border_good);
                    ContadorAciertos++;
                    stringRespuestas =stringRespuestas.concat(Opcion1.getText().toString().concat(
                            "/"));

                }
                else {
                    L1.setBackgroundResource(R.drawable.border_fail);
                    stringRespuestas =stringRespuestas.concat(Opcion1.getText().toString().concat("/"));

                }
                Contador++;

                if (Contador > ListaPreguntas.size()-1) {
                    puntuacion = ContadorAciertos + "/" + Contador;
                    ExamenRespuesta r = new ExamenRespuesta();
                    r.IdExamen = idExamen;
                    r.puntuacion = puntuacion;
                    r.respuestas = stringRespuestas;
                    r.fecha = Date.valueOf(LocalDate.now().toString());
                    r.idUser = IdUser;
                    rDAO.AddRespuestaExamen(r);
                    onBackPressed();
                }
                CargarPregunta();
            }
        });
        L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Opcion2.getText().toString().equals(ListaPreguntas.get(Contador).opcionCorrecta)) {
                    L2.setBackgroundResource(R.drawable.border_good);
                    stringRespuestas =
                            stringRespuestas.concat(Opcion2.getText().toString().concat("/"));
                    ContadorAciertos++;
                }
                else {
                    L2.setBackgroundResource(R.drawable.border_fail);
                    stringRespuestas =
                            stringRespuestas.concat(Opcion2.getText().toString().concat("/"));
                }
                Contador++;
                if (Contador > ListaPreguntas.size()-1) {
                    puntuacion = ContadorAciertos + "/" + Contador;
                    ExamenRespuesta r = new ExamenRespuesta();
                    r.IdExamen = idExamen;
                    r.puntuacion = puntuacion;
                    r.respuestas = stringRespuestas;
                    r.fecha = Date.valueOf(LocalDate.now().toString());
                    r.idUser = IdUser;
                    rDAO.AddRespuestaExamen(r);
                    onBackPressed();
                }
                CargarPregunta();
            }
        });
        L3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Opcion3.getText().toString().equals(ListaPreguntas.get(Contador).opcionCorrecta)) {
                     L3.setBackgroundResource(R.drawable.border_good);
                    stringRespuestas =
                            stringRespuestas.concat(Opcion3.getText().toString().concat("/"));
                    ContadorAciertos++;
                }
                else {
                    L3.setBackgroundResource(R.drawable.border_fail);
                    stringRespuestas =
                            stringRespuestas.concat(Opcion3.getText().toString().concat("/"));
                }
                Contador++;
                if (Contador > ListaPreguntas.size()-1) {
                    puntuacion = ContadorAciertos + "/" + Contador;
                    ExamenRespuesta r = new ExamenRespuesta();
                    r.IdExamen = idExamen;
                    r.puntuacion = puntuacion;
                    r.respuestas = stringRespuestas;
                    r.fecha = Date.valueOf(LocalDate.now().toString());
                    r.idUser = IdUser;
                    rDAO.AddRespuestaExamen(r);
                    onBackPressed();
                }
                CargarPregunta();
            }
        });
        L4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Opcion4.getText().toString().equals(ListaPreguntas.get(Contador).opcionCorrecta)) {
                    L4.setBackgroundResource(R.drawable.border_good);
                    ContadorAciertos++;
                    stringRespuestas =
                            stringRespuestas.concat(Opcion4.getText().toString().concat("/"));
                }
                else {
                    L4.setBackgroundResource(R.drawable.border_fail);
                    stringRespuestas =
                            stringRespuestas.concat(Opcion4.getText().toString().concat("/"));
                }
                Contador++;
                if (Contador > ListaPreguntas.size()-1) {
                    puntuacion = ContadorAciertos + "/" + Contador;
                    ExamenRespuesta r = new ExamenRespuesta();
                    r.IdExamen = idExamen;
                    r.puntuacion = puntuacion;
                    r.respuestas = stringRespuestas;
                    r.fecha = Date.valueOf(LocalDate.now().toString());
                    r.idUser = IdUser;
                    rDAO.AddRespuestaExamen(r);
                    onBackPressed();
                }
                CargarPregunta();
            }
        });
        //endregion
    }

    public void CargarPregunta() {
        final TextView EnunciadoTV = findViewById(R.id.enunciadoDoExamenTV);
        final TextView Opcion1 = findViewById(R.id.Opcion1TV);
        final TextView Opcion2 = findViewById(R.id.Opcion2TV);
        final TextView Opcion3 = findViewById(R.id.Opcion3TV);
        final TextView Opcion4 = findViewById(R.id.Opcion4TV);
        final LinearLayout L1 = findViewById(R.id.Opcion1Layout);
        final LinearLayout L2 = findViewById(R.id.Opcion2Layout);
        final LinearLayout L3 = findViewById(R.id.Opcion3Layout);
        final LinearLayout L4 = findViewById(R.id.Opcion4Layout);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Contador < ListaPreguntas.size()) {
                    ListaRespuestas = ListaPreguntas.get(Contador).opciones.split("/");
                    EnunciadoTV.setText(ListaPreguntas.get(Contador).Enunciado);
                    Opcion1.setText(ListaRespuestas[0]);
                    Opcion2.setText(ListaRespuestas[1]);
                    Opcion3.setText(ListaRespuestas[2]);
                    Opcion4.setText(ListaRespuestas[3]);
                    L1.setBackgroundResource(R.drawable.border);
                    L2.setBackgroundResource(R.drawable.border);
                    L3.setBackgroundResource(R.drawable.border);
                    L4.setBackgroundResource(R.drawable.border);
                }

            }
        }, 3000);
    }

}