package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.tfgfinal.Adapters.CuestionarioAnswerListAdapter;
import com.example.tfgfinal.Adapters.CuestionarioListAdapter;
import com.example.tfgfinal.Adapters.ExamenAnswerListAdapter;
import com.example.tfgfinal.Adapters.ExamenListAdapter;
import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.RespuestasDAO;
import com.example.tfgfinal.DAO.UsuariosDAO;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.CuestionarioResueltoUser;
import com.example.tfgfinal.Models.Examen;
import com.example.tfgfinal.Models.ExamenRespuesta;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MisCuestionariosActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    UsuariosDAO uDAO = new UsuariosDAO();
    CuestionariosDAO cDAO = new CuestionariosDAO();
    RespuestasDAO rDAO = new RespuestasDAO();
    Context context;
    ListView  lv;
    int Tipo;
    List<Cuestionario> listaC;
    List<Examen> listaE;
    List<ExamenRespuesta>listaR;
    List<CuestionarioResueltoUser> listaCR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_cuestionarios);
        boolean verRespuestas = getIntent().getExtras().getBoolean("verRespuestas");
        mAuth = FirebaseAuth.getInstance();
        int idCreador = uDAO.GetUsuarioByUserID(mAuth.getCurrentUser().getUid()).getId();
        Tipo = getIntent().getExtras().getInt("tipo");
        if (Tipo == 1 && !verRespuestas) {
            listaC = cDAO.GetAllCuestionariosFromUser(idCreador);
            context = this;
            lv = findViewById(R.id.myQuizList);
            lv.setAdapter(new CuestionarioListAdapter(this,listaC,verRespuestas));
        }
        if (Tipo ==1 && verRespuestas) {
            listaCR = cDAO.GetAllCuestionariosFromUserRespuestas(idCreador);
            lv = findViewById(R.id.myQuizList);
            lv.setAdapter(new CuestionarioAnswerListAdapter(this,listaCR));
        }
        if (Tipo == 2 && !verRespuestas) {
            listaE = cDAO.GetAllExamenesFromUser(idCreador);
            context = this;
            lv = findViewById(R.id.myQuizList);
            lv.setAdapter(new ExamenListAdapter(this,listaE,verRespuestas));
        }
        if (Tipo == 2 && verRespuestas) {
            listaR = rDAO.GetAllRespuestasFromUserExams(idCreador);
            context = this;
            lv = findViewById(R.id.myQuizList);
            lv.setAdapter(new ExamenAnswerListAdapter(this,listaR));
        }
    }


}