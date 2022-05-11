package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.tfgfinal.Adapters.CuestionarioListAdapter;
import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.UsuariosDAO;
import com.example.tfgfinal.Models.Cuestionario;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MisCuestionariosActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    UsuariosDAO uDAO = new UsuariosDAO();
    CuestionariosDAO cDAO = new CuestionariosDAO();
    Context context;
    ListView  lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_cuestionarios);
        boolean verRespuestas = getIntent().getExtras().getBoolean("verRespuestas");
        mAuth = FirebaseAuth.getInstance();
        int idCreador = uDAO.GetUsuarioByUserID(mAuth.getCurrentUser().getUid()).getId();
        List<Cuestionario> lista = cDAO.GetAllCuestionariosFromUser(idCreador);
        context = this;
        lv = findViewById(R.id.myQuizList);
        lv.setAdapter(new CuestionarioListAdapter(this,lista,verRespuestas));
    }


}