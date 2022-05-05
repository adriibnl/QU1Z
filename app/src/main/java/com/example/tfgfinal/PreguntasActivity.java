package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.Models.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class PreguntasActivity extends AppCompatActivity {
    LinearLayout layoutlist;
    Button buttonAddPreguntas;
    Button buttonSubmitPreguntas;
    ArrayList<View> ViewList = new ArrayList<View>();
    PreguntasDAO preguntasDAO = new PreguntasDAO();
    int idCuestionario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        layoutlist = findViewById(R.id.layout_list);
        buttonAddPreguntas = findViewById(R.id.añadirPreguntaBT);
        buttonSubmitPreguntas = findViewById(R.id.submitPreguntasBT);
        Bundle bundle = getIntent().getExtras();
        idCuestionario =getIntent().getExtras().getInt("IdCuestionario");
        //region ClickListeners
        buttonAddPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View preguntaView = getLayoutInflater().inflate(R.layout.row_add_pregunta,
                        null,false);
                List<String> tiposPregunta= preguntasDAO.GetAllTipoPreguntas();
                EditText enunciadoET = preguntaView.findViewById(R.id.enunciadoET);
                Spinner spinner = preguntaView.findViewById(R.id.tipoPreguntaSpinner);
                ArrayAdapter arrayAdapter = new ArrayAdapter(PreguntasActivity.this,R.layout.support_simple_spinner_dropdown_item,
                        tiposPregunta);
                spinner.setAdapter(arrayAdapter);
                layoutlist.addView(preguntaView);
                ViewList.add(preguntaView);
            }
        });
        //endregion
    }

    public void GuardarPreguntas(View view) {
        for (View v:
             ViewList) {
            Pregunta p = new Pregunta();
            Spinner spinner = v.findViewById(R.id.tipoPreguntaSpinner);
            String tipo = spinner.getSelectedItem().toString();
            EditText enunciadoET = v.findViewById(R.id.enunciadoET);
            String enunciado = enunciadoET.getText().toString();
            p.Tipo = tipo; p.Enunciado =enunciado; p.IdCuestionario = idCuestionario;
            preguntasDAO.AddPregunta(p);
        }
    }
}