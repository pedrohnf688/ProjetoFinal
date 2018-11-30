package com.example.phnf2.projetounidadefinal.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.ListaRelatorioAdapter;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerRelatorioClickListener;
import com.example.phnf2.projetounidadefinal.modelo.RelatorioProducaoLeite;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.example.phnf2.projetounidadefinal.util.FirebaseUtil;
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
public class Fragment_AtualizarRelatorio extends Fragment {

    String id;
    DatabaseReference databaseRelatorio;
    public static List<RelatorioProducaoLeite> listRelatorio = new ArrayList<>();
    RecyclerView recyclerEditarUserRelatorio;
    private FirebaseDatabase mFirebase;

    @SuppressLint("ValidFragment")
    public Fragment_AtualizarRelatorio(String id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__atualizar_relatorio, container, false);

        recyclerEditarUserRelatorio = view.findViewById(R.id.recyclerEditarRelatorioOrdenha);


        mFirebase = FirebaseDatabase.getInstance();

        if(mFirebase == null) {
            mFirebase.setPersistenceEnabled(true);
            databaseRelatorio = mFirebase.getReference("Relatorios").child(id);

        }else{
            databaseRelatorio = mFirebase.getReference("Relatorios").child(id);
            databaseRelatorio.keepSynced(true);
        }


        recyclerEditarUserRelatorio.addOnItemTouchListener(new RecyclerRelatorioClickListener(getContext(), recyclerEditarUserRelatorio, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                RelatorioProducaoLeite ProducaoLeite = listRelatorio.get(position);
                Fragment_EditarOrdenha fragment = new Fragment_EditarOrdenha(ProducaoLeite.getIdRelatorio());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, fragment).addToBackStack(null).commit();


            }

            @Override
            public void onItemLongClick(View view, int position) {

                RelatorioProducaoLeite relatorio3 = listRelatorio.get(position);
                Toast.makeText(getContext(), "ID:"+relatorio3.getIdRelatorio(), Toast.LENGTH_SHORT).show();

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

                listRelatorio.clear();

                for(DataSnapshot dataSnapshotRelatorio: dataSnapshot.getChildren()){

                    RelatorioProducaoLeite relatorio2 = dataSnapshotRelatorio.getValue(RelatorioProducaoLeite.class);
                    listRelatorio.add(relatorio2);
                }

                recyclerEditarUserRelatorio.setAdapter(new ListaRelatorioAdapter(getContext(),listRelatorio));

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerEditarUserRelatorio.setLayoutManager(layoutManager);
                recyclerEditarUserRelatorio.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }







}
