package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.UsuariosDAO;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.Examen;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Date;
import java.time.LocalDate;

public class CreateActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button CreateBT;
    EditText Nombre;
    EditText Descripcion;
    UsuariosDAO usuariosDAO = new UsuariosDAO();
    CuestionariosDAO cuestionariosDAO = new CuestionariosDAO();
    int Tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mAuth = FirebaseAuth.getInstance();
        CreateBT = findViewById(R.id.CrearBT);
        Nombre = findViewById(R.id.nameET);
        Descripcion = findViewById(R.id.DesET);
        Tipo = getIntent().getExtras().getInt("tipo");

    }

    public void crearCuestionario(View view) {
        String n = Nombre.getText().toString();
        String d = Descripcion.getText().toString();
        if (Tipo ==1) {
            try {
                Cuestionario c = new Cuestionario();
                c.IdCreador = usuariosDAO.GetUsuarioByUserID(mAuth.getCurrentUser().getUid().toString()).Id;
                c.Nombre = n;
                c.Descripcion = d;
                c.FechaCreacion = Date.valueOf(LocalDate.now().toString());
                cuestionariosDAO.AddCuestionario(c);
                finish();
            } catch (Exception e)  {
                Toast.makeText(this,"Error al crear el cuestionario",Toast.LENGTH_SHORT);
            }
        }
        if (Tipo ==2) {
            try {
                Examen c = new Examen();
                c.IdCreador = usuariosDAO.GetUsuarioByUserID(mAuth.getCurrentUser().getUid().toString()).Id;
                c.Nombre = n;
                c.Descripcion = d;
                c.FechaCreacion = Date.valueOf(LocalDate.now().toString());
                cuestionariosDAO.AddExamen(c);
                finish();
            } catch (Exception e)  {
                Toast.makeText(this,"Error al crear el examen",Toast.LENGTH_SHORT);
            }
        }

    }
}