package com.example.phnf2.projetounidadefinal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditarUsers extends RecyclerView.Adapter {

    private Context context;
    private List<Usuario> listaUsuarios;
    Usuario usuarioescolhido;

    public EditarUsers(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.editar_users, parent, false);
        EditarUsers.ViewHolderEditar holder = new EditarUsers.ViewHolderEditar(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EditarUsers.ViewHolderEditar listT = (EditarUsers.ViewHolderEditar) holder;

        usuarioescolhido = listaUsuarios.get(position);

        listT.NomeEditar.setText(usuarioescolhido.getNomeUser());
        listT.EmailEditar.setText(usuarioescolhido.getEmailUser());





    }

    @Override
    public int getItemCount() {
        return listaUsuarios == null ? 0 : listaUsuarios.size();
    }


    public class ViewHolderEditar extends RecyclerView.ViewHolder {

        final TextView NomeEditar;
        final TextView EmailEditar;


        public ViewHolderEditar(View v) {
            super(v);

            NomeEditar = v.findViewById(R.id.NomeEditarUser);
            EmailEditar = v.findViewById(R.id.EmailEditarUser);


        }
    }
}