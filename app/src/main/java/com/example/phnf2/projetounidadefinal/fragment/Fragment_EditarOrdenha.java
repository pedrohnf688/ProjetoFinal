package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
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
import com.example.phnf2.projetounidadefinal.adapter.EditarOrdenhas;
import com.example.phnf2.projetounidadefinal.adapter.ListaOrdenhaAdapter;
import com.example.phnf2.projetounidadefinal.modelo.Ordenha;
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
public class Fragment_EditarOrdenha extends Fragment {

    static String id;

    DatabaseReference databaseOrdenha;
    public static List<Ordenha> ordenhaList = new ArrayList<>();
    RecyclerView recycler;
    private FirebaseDatabase mFirebase;



    @SuppressLint("ValidFragment")
    public Fragment_EditarOrdenha(String id) {
        this.id = id;
    }

    public Fragment_EditarOrdenha() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__editar_ordenha, container, false);

        /*
        Editar Ordenha
         */

        recycler = view.findViewById(R.id.recyclerviewEditarOrdenha);

        mFirebase = FirebaseDatabase.getInstance();

        if(mFirebase == null) {

            mFirebase.setPersistenceEnabled(true);
            databaseOrdenha = mFirebase.getReference("Ordenhas").child(id);

        }else{
            databaseOrdenha = mFirebase.getReference("Ordenhas").child(id);
            databaseOrdenha.keepSynced(true);
        }






        return view;
    }


    public static String getOrdenhaID(){
        return id;
    }



    @Override
    public void onStart() {
        super.onStart();


        databaseOrdenha.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ordenhaList.clear();

                for(DataSnapshot dataSnapshotOrdenha: dataSnapshot.getChildren()){

                    Ordenha ordenha = dataSnapshotOrdenha.getValue(Ordenha.class);
                    ordenhaList.add(ordenha);

                }

                recycler.setAdapter(new EditarOrdenhas(getContext(),ordenhaList));

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recycler.setLayoutManager(layoutManager);
                recycler.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





}
