package com.example.phnf2.projetounidadefinal.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.Principal;
import com.example.phnf2.projetounidadefinal.adapter.ListAdapter;
import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
import com.example.phnf2.projetounidadefinal.modelo.Relatorio;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_ListarUser extends Fragment {

    public static List<Usuario> listUsuarios = new ArrayList<>();
    RecyclerView listViewUsuario;
    DatabaseReference databaseUsuario;

    public Fragment_ListarUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__listar_user, container, false);


        listViewUsuario = view.findViewById(R.id.ListViewUsuario);
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Usuarios");


        listViewUsuario.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), listViewUsuario, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Usuario user3 = listUsuarios.get(position);

                        Toast.makeText(getContext(), "ID:"+user3.getIdUser() +"Nome:"+user3.getNomeUser(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        Usuario user4 = listUsuarios.get(position);
                        Fragment_ListarRelatorio fragment = new Fragment_ListarRelatorio(user4.getIdUser(),user4.getNomeUser());

                        Toast.makeText(getContext(), "Nome:"+user4.getNomeUser(), Toast.LENGTH_SHORT).show();

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment).commit();

                    }
                })
        );

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
                listViewUsuario.setAdapter(adapter);

                RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                listViewUsuario.setLayoutManager(layout);
                listViewUsuario.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

}
