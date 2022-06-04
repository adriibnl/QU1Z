package com.example.tfgfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.Models.DDLModel;
import com.example.tfgfinal.Models.DDLModelString;
import com.example.tfgfinal.Models.DDLModelView;
import com.example.tfgfinal.Models.Pregunta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreguntasActivity extends AppCompatActivity {
    LinearLayout layoutlist;
    Button buttonAddPreguntas;
    Button buttonSubmitPreguntas;
    ArrayList<View> ViewList = new ArrayList<View>();
    PreguntasDAO preguntasDAO = new PreguntasDAO();
    List<DDLModelView> OpcionesList = new ArrayList<DDLModelView>();
    Map<String,View> OpcionesMap = new HashMap<>();
    List<View> comboBoxListView = new ArrayList<View>();
    int idCuestionario;
    int idUsuarioResuelto;
    LinearLayout popupLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        layoutlist = findViewById(R.id.layout_list);
        buttonAddPreguntas = findViewById(R.id.añadirPreguntaBT);
        buttonSubmitPreguntas = findViewById(R.id.submitPreguntasBT);


        Bundle bundle = getIntent().getExtras();
        idCuestionario =getIntent().getExtras().getInt("IdCuestionario");
        idUsuarioResuelto = getIntent().getExtras().getInt("IdUser");
        CargarPreguntas();
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
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position,
                                               long id) {
                        //region codigo antiguo
                        /*comboBoxListView = new ArrayList<View>();
                        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = inflater.inflate(R.layout.popup_add_spinner_data,null);
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        boolean focusable = true;
                        final PopupWindow popupWindow = new PopupWindow(popupView,width,
                                height,focusable);
                        popupWindow.setTouchable(true);
                        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
                        View Layout = inflater.inflate(R.layout.popup_add_spinner_data,
                                (ViewGroup)findViewById(R.id.cbBoxPopUpElement));
                        popupLayout =
                                Layout.findViewById(R.id.popupSpinnerOptionsLayout);
                        popupLayout.addView(getLayoutInflater().inflate(R.layout.row_add_respuesta_edittext,null,false));
                        Button btnAdd = Layout.findViewById(R.id.popupSpinnerOptionsAñadirBt);
                        Button btnSave = Layout.findViewById(R.id.popupSpinnerOptionsGuardarBt);
                        //btnAdd.setOnClickListener(new View.OnClickListener() {
                        //    @Override
                        //        final View comboboxPregunta =
                        //               getLayoutInflater().inflate(R.layout
                        //               .row_add_respuesta_edittext,null,false);
                        //     comboBoxListView.add(comboboxPregunta);
                        //   popupLayout.addView(comboboxPregunta);
                        //}
                        //});
                        btnSave.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
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
                            }
                        });*/

                        //endregion
                        if (position == 2) {
                            final View optionView =
                                    getLayoutInflater().inflate(R.layout.row_add_respuesta_combobox_opcion,null,false);
                                    EditText opcionET = optionView.findViewById(R.id.cbEditTextOpcion);
                                    Button addOpcionBT = optionView.findViewById(R.id.cbAddOpcionBt);
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


                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
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
            if (!preguntasDAO.CheckPregunta(p))
            preguntasDAO.AddPregunta(p);
            if (p.Tipo == "opciones")  {
                for (DDLModelView ddlmv:
                     OpcionesList) {
                    if (ddlmv.key == enunciado) {
                        EditText etCB = (EditText) ddlmv.getView();
                        preguntasDAO.SaveOpcion(p.getId(),etCB.getText().toString());
                    }
                }
            }

        }
    }

    public void CargarPreguntas() {
        ArrayList<Pregunta> preguntasList = new ArrayList<Pregunta>();
        for (Pregunta p:
             preguntasDAO.GetAllPreguntasFromCuestionario(idCuestionario)) {
            final View preguntaView = getLayoutInflater().inflate(R.layout.row_add_pregunta,
                    null,false);
            List<String> tiposPregunta= preguntasDAO.GetAllTipoPreguntas();
            EditText enunciadoET = preguntaView.findViewById(R.id.enunciadoET);
            Spinner spinner = preguntaView.findViewById(R.id.tipoPreguntaSpinner);
            ArrayAdapter arrayAdapter = new ArrayAdapter(PreguntasActivity.this,R.layout.support_simple_spinner_dropdown_item,
                    tiposPregunta);
            spinner.setAdapter(arrayAdapter);
            enunciadoET.setText(p.Enunciado);
            spinner.setSelection(preguntasDAO.GetTipoByString(p.Tipo).id);
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

