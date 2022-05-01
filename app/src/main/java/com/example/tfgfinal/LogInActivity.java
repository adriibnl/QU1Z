package com.example.tfgfinal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText usernameEt;
    EditText passwordEt;
    Button loginButton;
    Button signinButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Intent intent = new Intent(this,SignInActivity.class);
        mAuth = FirebaseAuth.getInstance();
        usernameEt = findViewById(R.id.editTextUsername);
        passwordEt = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.btnLogIn);
        signinButton = findViewById(R.id.btnSignIn);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);

            }
        });
    }

    public void UserLogIn(View view) {
        String email = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (email.isEmpty())
            Toast.makeText(LogInActivity.this,"Debes escribir un correo",Toast.LENGTH_SHORT).show();
        if (password.isEmpty())
            Toast.makeText(LogInActivity.this,"Debes escribir una contraseña",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }



}