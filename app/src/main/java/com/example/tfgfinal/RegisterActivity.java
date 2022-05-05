package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tfgfinal.DAO.UsuariosDAO;
import com.example.tfgfinal.Models.Usuario;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameText;
    Button registerBt;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        usernameText = findViewById(R.id.usernameText);
        registerBt = findViewById(R.id.RegisterButton);
        registerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = usernameText.getText().toString();
                UsuariosDAO userDAO = new UsuariosDAO();
                Usuario user = new Usuario();
                user.Username = name;
                user.FechaCreacion = Date.valueOf(LocalDate.now().toString());
                user.IdUser =  mAuth.getCurrentUser().getUid();
                user.Mail = mAuth.getCurrentUser().getEmail();
                userDAO.AddUsuario(user);
                finish();
            }
        });
    }


}