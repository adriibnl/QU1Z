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
import com.example.tfgfinal.DoCuestionarioActivity;
import com.example.tfgfinal.MisCuestionariosActivity;
import com.example.tfgfinal.Models.Cuestionario;
import com.example.tfgfinal.Models.CuestionarioResueltoUser;
import com.example.tfgfinal.Models.Respuesta;
import com.example.tfgfinal.PreguntasActivity;
import com.example.tfgfinal.R;

import java.util.List;

public class CuestionarioAnswerListAdapter extends BaseAdapter {
    List<CuestionarioResueltoUser> result;
    Context context;
    UsuariosDAO uDAO= new UsuariosDAO();
    PreguntasDAO pDAO = new PreguntasDAO();
    RespuestasDAO rDAO = new RespuestasDAO();
    CuestionariosDAO cDAO = new CuestionariosDAO();
    private static LayoutInflater inflater= null;
    public CuestionarioAnswerListAdapter(MisCuestionariosActivity activity, List<CuestionarioResueltoUser> lista) {
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
        return 0;
    }
    public class Holder {
        TextView tvTitulo;
        TextView tvDate;
        TextView tvUsuario;
        ImageView ivFoto;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
            rowView = inflater.inflate(R.layout.adapter_examen_respuesta_view_layout,null);
            holder.tvTitulo = rowView.findViewById(R.id.quizAdapterTitulo);
            holder.tvUsuario = rowView.findViewById(R.id.quizAdapterUsuario);
            holder.tvDate = rowView.findViewById(R.id.quizAdapterDate);
            holder.ivFoto = rowView.findViewById(R.id.quizAdapterFoto);
            holder.tvTitulo.setText(cDAO.GetCuestionarioById(result.get(position).IdCuestionario).Nombre);
            //holder.tvDate.setText(result.get(position).getFecha().toString());
            holder.tvDate.setText(result.get(position).fecha.toString());
            String usuario = uDAO.GetUsuarioById(result.get(position).getIdUsuario()).getUsername();
            holder.tvUsuario.setText(usuario);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int idCuestionario = result.get(position).IdCuestionario;
                    Intent intent = new Intent(v.getContext(), DoCuestionarioActivity.class);
                    intent.putExtra("IdCuestionario",result.get(position).IdCuestionario);
                    intent.putExtra("IdUser",result.get(position).getIdUsuario());
                    intent.putExtra("verRespuestas",true);
                    v.getContext().startActivity(intent);
                    Toast.makeText(context,"ole",Toast.LENGTH_SHORT);
                }
            });
        return rowView;
    }
}
