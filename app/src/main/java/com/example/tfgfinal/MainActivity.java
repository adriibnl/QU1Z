package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.tfgfinal.DAO.UsuariosDAO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ImageView logOutBt;
    ImageView CreateBt;
    ImageView misCuestionariosBT;
    ImageView hacerCuestionarioBT;
    ImageView verRespuestasBT;
    String userId;
    ImageView pfpIV;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        Intent intentLog = new Intent(MainActivity.this, LogInActivity.class);
        try {
            userId = mAuth.getCurrentUser().getUid();
        } catch (Exception e) {
            startActivity(intentLog);
            }
        logOutBt = findViewById(R.id.logOutBt);
        CreateBt = findViewById(R.id.CreateBT);
        misCuestionariosBT = findViewById(R.id.MisCuestionariosMainBT);
        hacerCuestionarioBT = findViewById(R.id.mainHacerCuestionariosBt);
        verRespuestasBT = findViewById(R.id.verRespuestasMainBt);
        pfpIV = findViewById(R.id.fotoMainIv);
        try {
            StorageReference fileRef = storageReference.child("users/"+ mAuth.getCurrentUser().getUid()+
                    "/profile.jpg");
            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.with(MainActivity.this).load(uri).into(pfpIV);
                }
            });
        } catch (Exception e) {}
        Intent perfilIntent = new Intent(this,UserPageActivity.class);
        perfilIntent.putExtra("userId",userId);
        Intent createIntent = new Intent(this,CreateActivity.class);
        createIntent.putExtra("tipo",1);
        Intent createExamenIntent = new Intent(this,CreateActivity.class);
        createExamenIntent.putExtra("tipo",2);
        Intent misCuestionariosIntent = new Intent(this,MisCuestionariosActivity.class);
        misCuestionariosIntent.putExtra("tipo",1);
        Intent misExamenesIntent = new Intent(this,MisCuestionariosActivity.class);
        misExamenesIntent.putExtra("tipo",2);
        Intent hacerCuestionariosIntent = new Intent(this,AllCuestionariosActivity.class);
        hacerCuestionariosIntent.putExtra("tipo",1);
        Intent hacerExamenIntent = new Intent(this,AllCuestionariosActivity.class);
        hacerExamenIntent.putExtra("tipo",2);
        //Intent hacerExamenesIntent = new Intent(this,)
        //region ClickListeners
        logOutBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent refresh = getIntent();
                startActivity(refresh);

            }
        });
        CreateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(createIntent);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_select_type,null);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView,width,
                        height,focusable);
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                popupWindow.getContentView().findViewById(R.id.popupCrearCuestionariosBt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(createIntent);
                    }
                });
                popupWindow.getContentView().findViewById(R.id.popupCrearExamenesBt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(createExamenIntent);
                    }
                });
            }
        });
        misCuestionariosBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //misCuestionariosIntent.putExtra("verRespuestas",false);
                //startActivity(misCuestionariosIntent);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_select_edit_qu1z_type,null);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView,width,
                        height,focusable);
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                popupWindow.getContentView().findViewById(R.id.popupEditarCuestionariosBt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        misCuestionariosIntent.putExtra("verRespuestas",false);
                        startActivity(misCuestionariosIntent);
                    }
                });
                popupWindow.getContentView().findViewById(R.id.popupEditarExamenesBt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        misExamenesIntent.putExtra("verRespuestas",false);
                        startActivity(misExamenesIntent);
                    }
                });
            }
        });
        hacerCuestionarioBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(hacerCuestionariosIntent);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_select_do_qu1z_type,null);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView,width,
                        height,focusable);
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                popupWindow.getContentView().findViewById(R.id.popupEditarCuestionariosBt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(hacerCuestionariosIntent);
                    }
                });
                popupWindow.getContentView().findViewById(R.id.popupEditarExamenesBt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(hacerExamenIntent);
                    }
                });
            }
        });
        verRespuestasBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //misCuestionariosIntent.putExtra("verRespuestas",true);
             //startActivity(misCuestionariosIntent);

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_select_view_qu1z_answer_type,null);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView,width,
                        height,focusable);
                popupWindow.setTouchable(true);
                popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
                popupWindow.getContentView().findViewById(R.id.popupRespuestasCuestionariosBt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        misCuestionariosIntent.putExtra("verRespuestas",true);
                        startActivity(misCuestionariosIntent);
                    }
                });
                popupWindow.getContentView().findViewById(R.id.popupRespuestasExamenesBt).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        misExamenesIntent.putExtra("verRespuestas",true);
                        startActivity(misExamenesIntent);
                    }
                });

            }
        });
        pfpIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(perfilIntent);
            }
        });
        //endregion
        UsuariosDAO DaoUsuario = new UsuariosDAO();
        try {
            if (!DaoUsuario.CheckRegisteredUser(mAuth.getCurrentUser().getUid())) {
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {}

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