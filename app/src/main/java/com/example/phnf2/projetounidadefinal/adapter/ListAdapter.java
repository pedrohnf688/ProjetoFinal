package com.example.phnf2.projetounidadefinal.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.example.phnf2.projetounidadefinal.util.FirebaseUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Usuario> listaUsuarios;
    Usuario usuarioescolhido;

    public ListAdapter(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_users, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

       final ViewHolder listT = (ViewHolder) holder;

       usuarioescolhido = listaUsuarios.get(position);
       boolean fotoX = usuarioescolhido.getPhotoUser() != null;

        listT.textViewNome2.setText(usuarioescolhido.getNomeUser());
        listT.textViewEmail2.setText(usuarioescolhido.getEmailUser());
        if(fotoX){

            FirebaseUtil.loadProfileIcon(usuarioescolhido.getPhotoUser(),listT.fotoUsuario);
        }else{

            listT.fotoUsuario.setImageResource(R.mipmap.ic_launcher);
        }





    }

    @Override
    public int getItemCount() {
        return listaUsuarios == null ? 0 : listaUsuarios.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView textViewNome2;
        final TextView textViewEmail2;
        final CircleImageView fotoUsuario;

        public ViewHolder(View v) {
            super(v);

            textViewNome2 = v.findViewById(R.id.textViewNome);
            textViewEmail2 = v.findViewById(R.id.textViewEmail);
            fotoUsuario = v.findViewById(R.id.fotoUsuarioList);
        }
    }
}
