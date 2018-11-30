package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.EditarRelatorios;
import com.example.phnf2.projetounidadefinal.adapter.ListAdapter;
import com.example.phnf2.projetounidadefinal.modelo.RelatorioProducaoLeite;
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
public class Fragment_EditarRelatorio extends Fragment {

    static String id;

    RecyclerView recyclerViewRelatorioEditar;
    DatabaseReference databaseRelatorioEditar;
    DatabaseReference databaseOrdenhaEditar;
    List<RelatorioProducaoLeite> relatorioProducaoList = new ArrayList<>();
    DatabaseReference drOrdenha;

    @SuppressLint("ValidFragment")
    public Fragment_EditarRelatorio(String id) {
        // Required empty public constructor
        this.id = id;
    }

    public Fragment_EditarRelatorio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__editar_relatorio, container, false);


        recyclerViewRelatorioEditar = view.findViewById(R.id.recyclerViewListarRelatorios);

        databaseRelatorioEditar = FirebaseDatabase.getInstance().getReference("Relatorios").child(id);





        return view;
    }

    public static String idString(){
        return id;

    }



    @Override
    public void onStart() {
        super.onStart();


            databaseRelatorioEditar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                relatorioProducaoList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    RelatorioProducaoLeite user3 = dataSnapshot1.getValue(RelatorioProducaoLeite.class);

                    relatorioProducaoList.add(user3);

                }

                EditarRelatorios adapter = new EditarRelatorios(getContext(), relatorioProducaoList);
                recyclerViewRelatorioEditar.setAdapter(adapter);

                RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                recyclerViewRelatorioEditar.setLayoutManager(layout);
                recyclerViewRelatorioEditar.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }





}
