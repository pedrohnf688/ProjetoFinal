package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.EditarRelatorios;
import com.example.phnf2.projetounidadefinal.adapter.ListAdapter;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerItemClickListener;
import com.example.phnf2.projetounidadefinal.adapter.RecyclerRelatorioClickListener;
import com.example.phnf2.projetounidadefinal.modelo.RelatorioProducaoLeite;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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

        recyclerViewRelatorioEditar.addOnItemTouchListener(new RecyclerRelatorioClickListener(getContext(), recyclerViewRelatorioEditar, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final RelatorioProducaoLeite producao = relatorioProducaoList.get(position);


                AlertDialog.Builder alertAtualizar = new AlertDialog.Builder(getContext());

                final View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_atualizar_relatorio,null);
                alertAtualizar.setView(dialogView);

                alertAtualizar.setTitle("Edição do Relatório");

                final Button atuali = dialogView.findViewById(R.id.RelatorioDialogAtualizar);
                final EditText DialogTitulo  = dialogView.findViewById(R.id.tituloDialog);
                final EditText DialogTipo = dialogView.findViewById(R.id.tipoDialog);
                final Button delet = dialogView.findViewById(R.id.RelatorioDialogDeletar);

                final AlertDialog alertDialog = alertAtualizar.create();
                alertDialog.show();


                atuali.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String tituloNovo = DialogTitulo.getText().toString();
                        String tipoNovo = DialogTipo.getText().toString();

                        if(!TextUtils.isEmpty(tituloNovo) && !TextUtils.isEmpty(tipoNovo)){

                            DatabaseReference databaserelatorio = FirebaseDatabase.getInstance().getReference("Relatorios").child(idString()).child(producao.getIdRelatorio());

                            RelatorioProducaoLeite producaoLeite = new RelatorioProducaoLeite(producao.getIdRelatorio(),tituloNovo,tipoNovo,DataSistema());

                            databaserelatorio.setValue(producaoLeite);

                            alertDialog.dismiss();

                        }else {

                            Toast.makeText(getContext(), "Preencha o Novos Dados!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                delet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       DatabaseReference databaserelatorio = FirebaseDatabase.getInstance().getReference("Relatorios").child(idString()).child(producao.getIdRelatorio());
                       DatabaseReference databaseOrdenha = FirebaseDatabase.getInstance().getReference("Ordenhas").child(producao.getIdRelatorio());

                       databaserelatorio.removeValue();
                       databaseOrdenha.removeValue();

                       alertDialog.dismiss();

                    }
                });



            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));




        return view;
    }

    public static String idString(){
        return id;

    }




    private String DataSistema() {
//        Locale locale = new Locale("pt", "BR");
//        GregorianCalendar calendar = new GregorianCalendar();
//        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dataMensagem = dateFormat.format(date);

        return dataMensagem;
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
