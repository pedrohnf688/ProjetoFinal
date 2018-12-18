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
import android.widget.TextView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.Principal;
import com.example.phnf2.projetounidadefinal.adapter.ListAdapter;
import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
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
    private FirebaseDatabase mFirebase;
    TextView textListarUser;

    public Fragment_ListarUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__listar_user, container, false);

        listViewUsuario = view.findViewById(R.id.ListViewUsuario);
        textListarUser = view.findViewById(R.id.textViewListarUser);

        mFirebase = FirebaseDatabase.getInstance();

        if(mFirebase == null) {

            mFirebase.setPersistenceEnabled(true);
            databaseUsuario = mFirebase.getReference("Usuarios");

        }else{

            databaseUsuario = mFirebase.getReference("Usuarios");
            databaseUsuario.keepSynced(true);
        }


        listViewUsuario.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), listViewUsuario, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Usuario user4 = listUsuarios.get(position);
                        Fragment_ListarRelatorio fragment = new Fragment_ListarRelatorio(user4.getIdUser(),user4.getNomeUser());
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment).addToBackStack(null).commit();

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        /*
                        teste
                         */
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

                if(listUsuarios.size() == 0){
                    textListarUser.setVisibility(View.VISIBLE);
                    textListarUser.setText("Não tem Usuários Cadastrados!");
                }else{
                    textListarUser.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

}
