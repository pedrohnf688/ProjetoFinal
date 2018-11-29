package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.ListaOrdenhaAdapter;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerOrdenhaClickListener;
import com.example.phnf2.projetounidadefinal.modelo.Ordenha;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.phnf2.projetounidadefinal.util.FirebaseUtil.getCurrentUserId;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Fragment_ListarOrdenha extends Fragment {

    List<Ordenha> listaOrdenha = new ArrayList<>();
    RecyclerView recyclerViewOrdenha;
    DatabaseReference databaseOrdenha;
    FloatingActionButton floatingActionAddOrdenha;
    public String id;
    private FirebaseDatabase mFirebase;


    @SuppressLint("ValidFragment")
    public Fragment_ListarOrdenha(String id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment__listar_ordenha, container, false);


        recyclerViewOrdenha = view.findViewById(R.id.recyclerOrdenha);
        floatingActionAddOrdenha = view.findViewById(R.id.fabOrdenha);


        mFirebase = FirebaseDatabase.getInstance();

        if(mFirebase == null) {

            mFirebase.setPersistenceEnabled(true);
            databaseOrdenha = mFirebase.getReference("Ordenhas").child(id);

        }else{
            databaseOrdenha = mFirebase.getReference("Ordenhas").child(id);
            databaseOrdenha.keepSynced(true);
        }


        /*
        Listar Ordenhas do Relatorio, Criar Fragment para Cadastrar Ordenha e Gerar Gráficos das ordenhas.
         */

        floatingActionAddOrdenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment_CadastrarOrdenha fragment_cadastrarOrdenha = new Fragment_CadastrarOrdenha(id);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment_cadastrarOrdenha).commit();
            }
        });


        /*
        Click's do recyclerView para visualizar o gráfico de cada ordenha do relatorio
         */

        recyclerViewOrdenha.addOnItemTouchListener(new RecyclerOrdenhaClickListener(getContext(), recyclerViewOrdenha, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Ordenha ordenha2 = listaOrdenha.get(position);


                Fragment_GraficoOrdenha fragment_graficoOrdenha = new Fragment_GraficoOrdenha(id,ordenha2.getIdOrdenha());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment_graficoOrdenha).addToBackStack(null).commit();
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


        databaseOrdenha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaOrdenha.clear();

                for(DataSnapshot dataSnapshotOrdenha: dataSnapshot.getChildren()){

                    Ordenha ordenha = dataSnapshotOrdenha.getValue(Ordenha.class);
                    listaOrdenha.add(ordenha);

                }

                recyclerViewOrdenha.setAdapter(new ListaOrdenhaAdapter(getContext(),listaOrdenha));

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewOrdenha.setLayoutManager(layoutManager);
                recyclerViewOrdenha.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
