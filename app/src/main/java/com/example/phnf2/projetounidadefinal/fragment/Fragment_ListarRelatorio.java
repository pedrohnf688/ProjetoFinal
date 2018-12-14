package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phnf2.projetounidadefinal.adapter.ListaRelatorioAdapter;
import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
import com.example.phnf2.projetounidadefinal.modelo.RelatorioProducaoLeite;
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
@SuppressLint("ValidFragment")
public class Fragment_ListarRelatorio extends Fragment {

    List<RelatorioProducaoLeite> listarRelatorios = new ArrayList<>();
    RecyclerView recyclerViewRelatorio;
    DatabaseReference databaseRelatorio;
    FloatingActionButton floatingActionAddRelatorio;
    public String id;
    public String nome;
    private FirebaseDatabase mFirebase;


    @SuppressLint("ValidFragment")
    public Fragment_ListarRelatorio(String id,String nome) {
        // Required empty public constructor
        this.id = id;
        this.nome = nome;
    }

    public Fragment_ListarRelatorio() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__listar_relatorio, container, false);

        recyclerViewRelatorio = view.findViewById(R.id.recyclerViewListarRelatorio);
        floatingActionAddRelatorio = view.findViewById(R.id.fab);


            mFirebase = FirebaseDatabase.getInstance();

        if(mFirebase == null) {

            mFirebase.setPersistenceEnabled(true);
            databaseRelatorio = mFirebase.getReference("Relatorios").child(id);

        }else{
            databaseRelatorio = mFirebase.getReference("Relatorios").child(id);
            databaseRelatorio.keepSynced(true);
        }

        floatingActionAddRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment_CadastroRelatorio fragment = new Fragment_CadastroRelatorio(id,nome);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment).addToBackStack(null).commit();

            }
        });


        recyclerViewRelatorio.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerViewRelatorio, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                RelatorioProducaoLeite ProducaoLeite = listarRelatorios.get(position);
                Fragment_ListarOrdenha fragment = new Fragment_ListarOrdenha(ProducaoLeite.getIdRelatorio());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, fragment).addToBackStack(null).commit();


            }

            @Override
            public void onItemLongClick(View view, int position) {

              /*
              teste
               */

            }
        }));

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        databaseRelatorio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listarRelatorios.clear();

                for(DataSnapshot dataSnapshotRelatorio: dataSnapshot.getChildren()){

                    RelatorioProducaoLeite relatorio2 = dataSnapshotRelatorio.getValue(RelatorioProducaoLeite.class);
                    listarRelatorios.add(relatorio2);
                }

                recyclerViewRelatorio.setAdapter(new ListaRelatorioAdapter(getContext(),listarRelatorios));

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewRelatorio.setLayoutManager(layoutManager);
                recyclerViewRelatorio.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
