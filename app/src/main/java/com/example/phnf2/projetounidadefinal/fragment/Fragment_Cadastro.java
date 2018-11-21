package com.example.phnf2.projetounidadefinal.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Cadastro extends Fragment {

    EditText nomeUsuario1;
    EditText emailUsuario1;
    EditText senhaUsuario1;
    Button buttonaddUsuario1;
    ListView listViewUsuario;
    DatabaseReference databaseUsuario;
    ChildEventListener mchildEventListener;
    List<Usuario> listUsuarios;


    public Fragment_Cadastro() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment__cadastro, container, false);

        nomeUsuario1 = view.findViewById(R.id.nomeUsuario);
        emailUsuario1 = view.findViewById(R.id.emailUsuario);
        senhaUsuario1 = view.findViewById(R.id.senhaUsuario);
        buttonaddUsuario1 = view.findViewById(R.id.buttonaddUsuario);
        listViewUsuario = view.findViewById(R.id.ListViewUsuario);

        databaseUsuario = FirebaseDatabase.getInstance().getReference("Usuarios");

        listUsuarios = new ArrayList<>();

        buttonaddUsuario1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionarUsuario();
            }
        });

        return view;
    }

    private void AdicionarUsuario(){

        String nome = nomeUsuario1.getText().toString().trim();
        String email = emailUsuario1.getText().toString().trim();
        String senha = senhaUsuario1.getText().toString().trim();

        if(!TextUtils.isEmpty(nome) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(senha)){

            String id = databaseUsuario.push().getKey();

            Usuario user = new Usuario(id,nome,email,senha);

            databaseUsuario.child(id).setValue(user);

            nomeUsuario1.setText("");
            emailUsuario1.setText("");
            senhaUsuario1.setText("");

            Toast.makeText(getContext(), "Usuario Cadastrado", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getContext(), "Preencha Corretamente o Formulário do Usuario", Toast.LENGTH_SHORT).show();
        }


    }



}
