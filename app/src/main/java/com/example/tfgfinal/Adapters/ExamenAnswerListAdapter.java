package com.example.tfgfinal.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfgfinal.DAO.CuestionariosDAO;
import com.example.tfgfinal.DAO.PreguntasDAO;
import com.example.tfgfinal.DAO.RespuestasDAO;
import com.example.tfgfinal.DAO.UsuariosDAO;
import com.example.tfgfinal.ExamenRespuestasActivity;
import com.example.tfgfinal.MisCuestionariosActivity;
import com.example.tfgfinal.Models.Examen;
import com.example.tfgfinal.Models.ExamenRespuesta;
import com.example.tfgfinal.Models.Respuesta;
import com.example.tfgfinal.PreguntasActivity;
import com.example.tfgfinal.R;

import java.util.List;

public class ExamenAnswerListAdapter extends BaseAdapter {
    List<ExamenRespuesta> result;
    Context context;
    UsuariosDAO uDAO= new UsuariosDAO();
    PreguntasDAO pDAO = new PreguntasDAO();
    RespuestasDAO rDAO = new RespuestasDAO();
    CuestionariosDAO cDAO = new CuestionariosDAO();
    private static LayoutInflater inflater= null;
    public ExamenAnswerListAdapter(MisCuestionariosActivity activity, List<ExamenRespuesta> lista) {
        result = lista;
        context = activity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        ImageView ivPfp;
        TextView tvTitulo;
        TextView tvUsuario;
        TextView tvFecha;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
            rowView = inflater.inflate(R.layout.adapter_examen_respuesta_view_layout,null);
            holder.ivPfp = rowView.findViewById(R.id.quizAdapterFoto);
            holder.tvUsuario = rowView.findViewById(R.id.quizAdapterUsuario);
            holder.tvFecha = rowView.findViewById(R.id.quizAdapterDate);
            holder.tvTitulo = rowView.findViewById(R.id.quizAdapterTitulo);
            holder.tvTitulo.setText(cDAO.GetExamenById(result.get(position).IdExamen).Nombre);
            holder.tvFecha.setText(result.get(position).getFecha().toString());
            holder.tvUsuario.setText(uDAO.GetUsuarioById(result.get(position).getIdUser()).Username);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idCuestionario = result.get(position).Id;
                    Intent intent = new Intent(v.getContext(), ExamenRespuestasActivity.class);
                    intent.putExtra("idRespuesta",result.get(position).getId());
                    v.getContext().startActivity(intent);
                    Toast.makeText(context,"ole",Toast.LENGTH_SHORT);
                }
            });
        return rowView;
    }
}
