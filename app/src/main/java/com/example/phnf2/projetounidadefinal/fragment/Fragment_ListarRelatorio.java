package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.adapter.ListAdapter;
import com.example.phnf2.projetounidadefinal.adapter.ListaRelatorioAdapter;
import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerRelatorioClickListener;
import com.example.phnf2.projetounidadefinal.modelo.Relatorio;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
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

    List<Relatorio> listarReltorios = new ArrayList<>();
    RecyclerView recyclerViewRelatorio;
    DatabaseReference databaseRelatorio;
    FloatingActionButton floatingActionAddRelatorio;
    public String id;
    public String nome;

    @SuppressLint("ValidFragment")
    public Fragment_ListarRelatorio(String id,String nome) {
        // Required empty public constructor
        this.id = id;
        this.nome = nome;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__listar_relatorio, container, false);

        recyclerViewRelatorio = view.findViewById(R.id.recyclerViewListarRelatorio);
        floatingActionAddRelatorio = view.findViewById(R.id.fab);


        databaseRelatorio = FirebaseDatabase.getInstance().getReference("Relatorios").child(id);

        floatingActionAddRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment_CadastroRelatorio fragment = new Fragment_CadastroRelatorio(id,nome);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment).commit();

            }
        });


        recyclerViewRelatorio.addOnItemTouchListener(new RecyclerRelatorioClickListener(getContext(), recyclerViewRelatorio, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Relatorio relatorio3 = listarReltorios.get(position);

                Toast.makeText(getContext(), "ID:"+relatorio3.getIdRelatorio()+"TItulo:"+relatorio3.getTituloRelatorio(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

                //Implementar DELALHAR item do RecyclerView



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

                listarReltorios.clear();

                for(DataSnapshot dataSnapshotRelatorio: dataSnapshot.getChildren()){

                    Relatorio relatorio2 = dataSnapshotRelatorio.getValue(Relatorio.class);
                    listarReltorios.add(relatorio2);
                }

                recyclerViewRelatorio.setAdapter(new ListaRelatorioAdapter(getContext(),listarReltorios));

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
