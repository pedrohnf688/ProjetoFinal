package com.example.phnf2.projetounidadefinal.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.ListAdapter;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_EditarUserOrdenha extends Fragment {


    DatabaseReference databaseUsuario;
    public static List<Usuario> listUsuarios = new ArrayList<>();
    RecyclerView recyclerEditarUserOrdenha;
    private FirebaseDatabase mFirebase;

    public Fragment_EditarUserOrdenha() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__editar_user_ordenha, container, false);

        /*
        Acessar Usuarios --> Acessar Relatorios --> Editar Ordenhas
         */

        recyclerEditarUserOrdenha = view.findViewById(R.id.recyclerEditarUserOrdenha);

        mFirebase = FirebaseDatabase.getInstance();

        if(mFirebase == null) {

            mFirebase.setPersistenceEnabled(true);
            databaseUsuario = mFirebase.getReference("Usuarios");

        }else{

            databaseUsuario = mFirebase.getReference("Usuarios");
            databaseUsuario.keepSynced(true);
        }




        recyclerEditarUserOrdenha.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerEditarUserOrdenha, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Usuario usuario2 = listUsuarios.get(position);
                Fragment_AtualizarRelatorio fragment_editarRelatorio = new Fragment_AtualizarRelatorio(usuario2.getIdUser());

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment_editarRelatorio).addToBackStack(null).commit();

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));




        return view;
    }


    @Override
    public void onStart() {
        super.onStart();


        databaseUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listUsuarios.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Usuario user3 = dataSnapshot1.getValue(Usuario.class);

                    listUsuarios.add(user3);

                }

                ListAdapter adapter = new ListAdapter(getContext(), listUsuarios);
                recyclerEditarUserOrdenha.setAdapter(adapter);

                RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerEditarUserOrdenha.setLayoutManager(layout);
                recyclerEditarUserOrdenha.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }




}

