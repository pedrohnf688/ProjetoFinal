package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.ListaOrdenhaAdapter;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
import com.example.phnf2.projetounidadefinal.modelo.Ordenha;
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
    TextView textEditarOrdenha;


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
        textEditarOrdenha = view.findViewById(R.id.textViewEditarOrdenha);

        mFirebase = FirebaseDatabase.getInstance();

        if(mFirebase == null) {

            mFirebase.setPersistenceEnabled(true);
            databaseOrdenha = mFirebase.getReference("Ordenhas").child(id);

        }else{
            databaseOrdenha = mFirebase.getReference("Ordenhas").child(id);
            databaseOrdenha.keepSynced(true);
        }


        recycler.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                final Ordenha ordenhaEcolhida = ordenhaList.get(position);

                AlertDialog.Builder alertAtualizar = new AlertDialog.Builder(getContext());

                final View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_atualizar_ordenha,null);
                alertAtualizar.setView(dialogView);

                alertAtualizar.setTitle("Edição da Ordenha");

                final TextInputEditText gordEditar = dialogView.findViewById(R.id.EntradaGorDialog);
                final TextInputEditText protEditar = dialogView.findViewById(R.id.EntradaProtDialog);
                final TextInputEditText casEditar = dialogView.findViewById(R.id.EntradaCasDialog);
                final TextInputEditText lactEditar = dialogView.findViewById(R.id.EntradaLactDialog);
                final TextInputEditText stEditar = dialogView.findViewById(R.id.EntradaStDialog);
                final TextInputEditText esdEditar = dialogView.findViewById(R.id.EntradaEsdDialog);
                final TextInputEditText nuEditar = dialogView.findViewById(R.id.EntradaNuDialog);
                final TextInputEditText celEditar = dialogView.findViewById(R.id.EntradaCelDialog);
                final TextInputEditText ccsEditar = dialogView.findViewById(R.id.EntradaCcsDialog);
                final Button EditarOrdenha1 = dialogView.findViewById(R.id.EditarOrdenha);
                final Button DeletarOrdenha1 = dialogView.findViewById(R.id.DeletarOrdenha);

                final AlertDialog alertDialog = alertAtualizar.create();
                alertDialog.show();

                EditarOrdenha1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String gord4 = gordEditar.getText().toString().trim();
                        String prot4 = protEditar.getText().toString().trim();
                        String cas4 = casEditar.getText().toString().trim();
                        String lact4 = lactEditar.getText().toString().trim();
                        String st4 = stEditar.getText().toString().trim();
                        String esd4 = esdEditar.getText().toString().trim();
                        String nu4 = nuEditar.getText().toString().trim();
                        String cel4 = celEditar.getText().toString().trim();
                        String ccs4 = ccsEditar.getText().toString().trim();


                        if(!TextUtils.isEmpty(gord4) && !TextUtils.isEmpty(prot4) && !TextUtils.isEmpty(cas4) &&
                                !TextUtils.isEmpty(lact4) && !TextUtils.isEmpty(st4) && !TextUtils.isEmpty(esd4) &&
                                !TextUtils.isEmpty(nu4) && !TextUtils.isEmpty(cel4) && !TextUtils.isEmpty(ccs4)) {

                            double GorSaida = Double.parseDouble(gord4);
                            double ProtSaida = Double.parseDouble(prot4);
                            double CasSaida = Double.parseDouble(cas4);
                            double LactSaida = Double.parseDouble(lact4);
                            double StSaida = Double.parseDouble(st4);
                            double EsdSaida = Double.parseDouble(esd4);
                            double NuSaida = Double.parseDouble(nu4);
                            double CelSaida = Double.parseDouble(cel4);
                            double CcsSaida = Double.parseDouble(ccs4);


                            final DatabaseReference databaseordenha = FirebaseDatabase.getInstance().getReference("Ordenhas").child(getOrdenhaID()).child(ordenhaEcolhida.getIdOrdenha());

                            final Ordenha ordenhaNova = new Ordenha(ordenhaEcolhida.getIdOrdenha(),GorSaida,ProtSaida,CasSaida,LactSaida,StSaida,EsdSaida,NuSaida,CelSaida,CcsSaida);

                            MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                                    .title("Atualizar")
                                    .content("Tem certeza que deseja atualizar os dados da ordenha!")
                                    .positiveText("Sim")
                                    .negativeText("Não")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            databaseordenha.setValue(ordenhaNova);
                                            alertDialog.dismiss();

                                            Toast.makeText(getContext(), "Ordenha Atualizada com Sucesso!", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                            alertDialog.dismiss();
                                        }
                                    })
                                    .build();
                            dialog.show();


                        }else{
                            Toast.makeText(getContext(), "Preencha o Novos Dados!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                DeletarOrdenha1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final DatabaseReference databaseordenha = FirebaseDatabase.getInstance().getReference("Ordenhas").child(getOrdenhaID()).child(ordenhaEcolhida.getIdOrdenha());

                        MaterialDialog dialog = new MaterialDialog.Builder(getContext())
                                .title("Deletar")
                                .content("Tem certeza que deseja deletar os dados da ordenha!")
                                .positiveText("Sim")
                                .negativeText("Não")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        databaseordenha.removeValue();

                                        alertDialog.dismiss();

                                        Toast.makeText(getContext(), "Ordenha Deletada com Sucesso!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .onNegative(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                      alertDialog.dismiss();
                                    }
                                })
                                .build();
                        dialog.show();


                    }
                });

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));


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

                recycler.setAdapter(new ListaOrdenhaAdapter(getContext(),ordenhaList));

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recycler.setLayoutManager(layoutManager);
                recycler.setItemAnimator(new DefaultItemAnimator());

                if(ordenhaList.size() == 0){
                    textEditarOrdenha.setVisibility(View.VISIBLE);
                    textEditarOrdenha.setText("Não tem Ordenhas Cadastradas!");
                }else{
                    textEditarOrdenha.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
