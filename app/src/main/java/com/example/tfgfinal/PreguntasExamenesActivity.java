package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.Models.DDLModelView;
import com.example.tfgfinal.Models.Pregunta;
import com.example.tfgfinal.Models.PreguntaExamen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreguntasExamenesActivity extends AppCompatActivity {
    LinearLayout layoutlist;
    Button buttonAddPreguntas;
    Button buttonSubmitPreguntas;
    ArrayList<View> ViewList = new ArrayList<View>();
    PreguntasDAO preguntasDAO = new PreguntasDAO();
    List<DDLModelView> OpcionesList = new ArrayList<DDLModelView>();
    Map<String,View> OpcionesMap = new HashMap<>();
    List<View> comboBoxListView = new ArrayList<View>();
    int idCuestionario;
    LinearLayout popupLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_examenes);
        layoutlist = findViewById(R.id.layout_listExamen);
        buttonAddPreguntas = findViewById(R.id.añadirPreguntaBT);
        buttonSubmitPreguntas = findViewById(R.id.submitPreguntasBT);


        Bundle bundle = getIntent().getExtras();
        idCuestionario =getIntent().getExtras().getInt("IdCuestionario");
        CargarPreguntas();
        //region ClickListeners
        buttonAddPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View preguntaView = getLayoutInflater().inflate(R.layout.row_preguntas_examen,
                        null,false);
                EditText enunciadoET = preguntaView.findViewById(R.id.textViewEnunciadoExamen);
                EditText pregunta1 = preguntaView.findViewById(R.id.editTextPreguntaExamen1);
                EditText pregunta2 = preguntaView.findViewById(R.id.editTextPreguntaExamen2);
                EditText pregunta3 = preguntaView.findViewById(R.id.editTextPreguntaExamen3);
                EditText pregunta4 = preguntaView.findViewById(R.id.editTextPreguntaExamen4);
                Spinner opcionCorrecta = preguntaView.findViewById(R.id.opcionCorrectaSpinner);
                final String[] choices = {"1","2","3","4"};
                ArrayAdapter<String> a =new ArrayAdapter<String>(PreguntasExamenesActivity.this, android.R.layout.simple_spinner_item, choices);
                a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                opcionCorrecta.setAdapter(a);
                layoutlist.addView(preguntaView);
                ViewList.add(preguntaView);
            }
        });

        //endregion
    }

    public void GuardarPreguntas(View view) {
        for (View v:
                ViewList) {
            PreguntaExamen p = new PreguntaExamen();
            EditText enunciadoET = v.findViewById(R.id.textViewEnunciadoExamen);
            EditText opcion1 = v.findViewById(R.id.editTextPreguntaExamen1);
            EditText opcion2 = v.findViewById(R.id.editTextPreguntaExamen2);
            EditText opcion3 = v.findViewById(R.id.editTextPreguntaExamen3);
            EditText opcion4 = v.findViewById(R.id.editTextPreguntaExamen4);
            String enunciado = enunciadoET.getText().toString();
            Spinner opcionCorrectaSp = v.findViewById(R.id.opcionCorrectaSpinner);
            int opcion = opcionCorrectaSp.getSelectedItemPosition();
            String preguntas =
                    opcion1.getText().toString().concat("/" + opcion2.getText().toString().concat("/" + opcion3.getText().toString().concat("/" +opcion4.getText().toString()) ));
            String[] arrayPreguntas = preguntas.split("/");
            String opcionCorrecta = arrayPreguntas[opcion];
            p.opciones = preguntas; p.Enunciado =enunciado; p.IdExamen = idCuestionario; p.opcionCorrecta = opcionCorrecta;
            if (!preguntasDAO.CheckPreguntaExamen(p))
                preguntasDAO.AddPreguntaExamen(p);
        }
    }

    public void CargarPreguntas() {
        ArrayList<PreguntaExamen> preguntasList = new ArrayList<PreguntaExamen>();
        for (PreguntaExamen p:
                preguntasDAO.GetAllPreguntasFromExamen(idCuestionario)) {
            final View preguntaView = getLayoutInflater().inflate(R.layout.row_preguntas_examen,
                    null,false);
            EditText enunciadoET = preguntaView.findViewById(R.id.textViewEnunciadoExamen);
            EditText opcion1 = preguntaView.findViewById(R.id.editTextPreguntaExamen1);
            EditText opcion2 = preguntaView.findViewById(R.id.editTextPreguntaExamen2);
            EditText opcion3 = preguntaView.findViewById(R.id.editTextPreguntaExamen3);
            EditText opcion4 = preguntaView.findViewById(R.id.editTextPreguntaExamen4);
            Spinner opcionCorrecta = preguntaView.findViewById(R.id.opcionCorrectaSpinner);
            enunciadoET.setText(p.Enunciado);
            opcion1.setText(p.opciones.split("/")[0]);
            opcion2.setText(p.opciones.split("/")[1]);
            opcion3.setText(p.opciones.split("/")[2]);
            opcion4.setText(p.opciones.split("/")[3]);
            String ops[] = p.opciones.split("/");
            int ind = -1;
            for(int i=0;i<=ops.length;i++) {
                if (ops[i].equals(p.opcionCorrecta)) {
                    ind = i;
                    break;
                }
            }
            final String[] choices = {"1","2","3","4"};
            ArrayAdapter<String> a =new ArrayAdapter<String>(PreguntasExamenesActivity.this, android.R.layout.simple_spinner_item, choices);
            a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            opcionCorrecta.setAdapter(a);
            if (ind != -1)
            opcionCorrecta.setSelection(ind);
            layoutlist.addView(preguntaView);
            ViewList.add(preguntaView);
        }
    }



    /*public void AñadirET(View view) {
        final View optionView =
                getLayoutInflater().inflate(R.layout.row_add_respuesta_combobox_opcion,null,false);
        layoutlist.addView(optionView);
        OpcionesList.add(new DDLModelView(){
            @Override
            public void setKey(String key) {
                super.setKey(enunciadoET.getText().toString());
            }

            @Override
            public void setView(View view) {
                super.setView(optionView);
            }
        });

    }*/

    /*public void SaveOpcion(View view) {
        String Lista = "";
        for (View cbV:
                comboBoxListView) {
            EditText etCB = cbV.findViewById(R.id.editTextRespuesta);
            Lista += etCB.getText().toString() + "__";


        }
        String finalLista = Lista;
        OpcionesList.add(new DDLModel(){
            @Override
            public void setId(int id) {
                super.setId(OpcionesList.size()-1);
                super.setText(finalLista);
            }
        });
    }*/
}
