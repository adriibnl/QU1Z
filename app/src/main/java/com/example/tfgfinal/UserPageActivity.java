package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.DAO.RespuestasDAO;
import com.example.tfgfinal.DAO.UsuariosDAO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

public class UserPageActivity extends AppCompatActivity {
    TextView NombreTV;
    TextView CuestionariosCompletadosTV;
    TextView RespuestasATusCuestionariosTV;
    TextView CuestionariosCreadosTV;
    UsuariosDAO Udao = new UsuariosDAO();
    PreguntasDAO Pdao = new PreguntasDAO();
    RespuestasDAO Rdao = new RespuestasDAO();
    CuestionariosDAO Cdao = new CuestionariosDAO();
    ImageView pfp;
    private FirebaseAuth mAuth;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        NombreTV = findViewById(R.id.NombrePerfilTv);
        CuestionariosCompletadosTV = findViewById(R.id.cuestionariosCompletadosPerfilTV);
        RespuestasATusCuestionariosTV = findViewById(R.id.RespuestasACuestionariosPerfilTv);
        CuestionariosCreadosTV = findViewById(R.id.CuestionariosCreadosPerfilTv);
        pfp = findViewById(R.id.pfpImageView);
        String userId = getIntent().getStringExtra("userId");
        int idUsuario = Udao.GetUsuarioByUserID(userId).Id;
        String userName = Udao.GetUsuarioByUserID(userId).Username;
        NombreTV.setText(userName);
        int cuestionariosCreados = Cdao.GetAllCuestionariosFromUser(idUsuario).toArray().length;
        CuestionariosCreadosTV.setText("" +cuestionariosCreados);
        int cuestionariosCompletados = Rdao.GetCuestionariosResueltosFromUser(idUsuario);
        CuestionariosCompletadosTV.setText("" +cuestionariosCompletados);
        int respuestasAMisCuestionarios = Rdao.GetRespuestasDeMisCuestionarios(idUsuario);
        RespuestasATusCuestionariosTV.setText("" +respuestasAMisCuestionarios);
        //region ClickListeners
        pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });
        //endregion

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,
                                    @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1000) {
            Uri imageUri = data.getData();
            pfp.setImageURI(imageUri);

            uploadImageToFirebase(imageUri);
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {

        StorageReference fileRef = storageReference.child("profile.jpg");
        fileRef.putFile(imageUri);
    }
}