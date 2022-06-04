package com.example.tfgfinal.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfgfinal.DAO.UsuariosDAO;
import com.example.tfgfinal.DoCuestionarioActivity;
import com.example.tfgfinal.MisCuestionariosActivity;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.Examen;
import com.example.tfgfinal.PreguntasActivity;
import com.example.tfgfinal.PreguntasExamenesActivity;
import com.example.tfgfinal.R;

import java.util.List;

public class ExamenListAdapter extends BaseAdapter {
    List<Examen> result;
    Context context;
    UsuariosDAO uDAO= new UsuariosDAO();
    boolean verRespuestas;
    private static LayoutInflater inflater= null;
    public ExamenListAdapter(MisCuestionariosActivity activity, List<Examen> lista,
                             boolean verRespuestas) {
        result = lista;
        context = activity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.verRespuestas = verRespuestas;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return result.get(position).getId();
    }
    public class Holder {
        TextView tvTitulo;
        TextView tvDate;
        TextView tvCreador;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
            rowView = inflater.inflate(R.layout.adapter_view_layout,null);
            holder.tvTitulo = rowView.findViewById(R.id.quizAdapterTitulo);
            holder.tvCreador = rowView.findViewById(R.id.quizAdapterCreador);
            holder.tvDate = rowView.findViewById(R.id.quizAdapterDate);
            holder.tvTitulo.setText(result.get(position).getNombre());
            holder.tvDate.setText(result.get(position).getFechaCreacion().toString());
            String creador = uDAO.GetUsuarioById(result.get(position).getIdCreador()).getUsername();
            holder.tvCreador.setText(creador);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idCuestionario = result.get(position).Id;
                    if (!verRespuestas) {
                        Intent intent = new Intent(v.getContext(), PreguntasExamenesActivity.class);
                        intent.putExtra("IdCuestionario",result.get(position).getId());
                        v.getContext().startActivity(intent);
                        Toast.makeText(context,"ole",Toast.LENGTH_SHORT);
                    }
                    else {
                        Intent intent = new Intent(v.getContext(), DoCuestionarioActivity.class);
                        intent.putExtra("IdCuestionario",result.get(position).getId());
                        intent.putExtra("verRespuestas",true);
                        v.getContext().startActivity(intent);
                    }

                }
            });
        return rowView;
    }
}
