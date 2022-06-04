package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tfgfinal.Adapters.AllCuestionarioListAdapter;
import com.example.tfgfinal.Adapters.AllExamenListAdapter;
import com.example.tfgfinal.Adapters.CuestionarioListAdapter;
import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.Examen;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class AllCuestionariosActivity extends AppCompatActivity {
    CuestionariosDAO cDAO = new CuestionariosDAO();
    Context context;
    ListView lv;
    int tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cuestionarios);
        tipo = getIntent().getExtras().getInt("tipo");
        if (tipo == 1) {
            List<Cuestionario> lista = cDAO.GetAllCuestionarios();
            context = this;
            lv = findViewById(R.id.AllQuizList);
            lv.setAdapter(new AllCuestionarioListAdapter(this,lista));
        }
        if (tipo == 2) {
            List<Examen> lista = cDAO.GetAllExamenes();
            lv = findViewById(R.id.AllQuizList);
            lv.setAdapter(new AllExamenListAdapter(this,lista));
        }
    }
}