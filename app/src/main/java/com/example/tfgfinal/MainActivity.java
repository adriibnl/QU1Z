package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tfgfinal.DAO.UsuariosDAO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button logOutBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        logOutBt = findViewById(R.id.logOutBt);
        logOutBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent refresh = getIntent();
                startActivity(refresh);

            }
        });
        UsuariosDAO DaoUsuario = new UsuariosDAO();
        if (!DaoUsuario.checkRegisteredUser(mAuth.getCurrentUser().getUid())) {
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected  void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this,currentUser.getEmail().toString(),Toast.LENGTH_SHORT).show();
        }
    }



}