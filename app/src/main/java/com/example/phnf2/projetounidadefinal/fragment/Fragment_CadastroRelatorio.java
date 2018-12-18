package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.adapter.ListAdapter;
import com.example.phnf2.projetounidadefinal.adapter.ListaRelatorioAdapter;
import com.example.phnf2.projetounidadefinal.modelo.RelatorioProducaoLeite;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Fragment_CadastroRelatorio extends Fragment {

    TextView nomeVisualizar;
    TextInputEditText titulo2;
    TextInputEditText tipo2;
    Button buttonaddRelatorio;
    DatabaseReference databaseRelatorios;
    String id;
    String nome;
    private FirebaseDatabase mFirebase;
    Date date;

    @SuppressLint("ValidFragment")
    public Fragment_CadastroRelatorio(String id,String nome) {
        // Required empty public constructor
        this.id = id;
        this.nome = nome;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__cadastro_relatorio, container, false);


        nomeVisualizar = view.findViewById(R.id.textViewNomeUser);
        titulo2 = view.findViewById(R.id.titulo);
        tipo2 = view.findViewById(R.id.tipo);
        buttonaddRelatorio = view.findViewById(R.id.buttonaddRelatorio);

        nomeVisualizar.setText(nome);


            mFirebase = FirebaseDatabase.getInstance();
            if(mFirebase == null) {

                mFirebase.setPersistenceEnabled(true);
                databaseRelatorios = mFirebase.getReference("Relatorios").child(id);

            }else{

                databaseRelatorios = mFirebase.getReference("Relatorios").child(id);
                databaseRelatorios.keepSynced(true);
            }

        buttonaddRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionarRelatorio();


            }
        });


        return view;
    }


    private void AdicionarRelatorio() {

        String titulo3 = titulo2.getText().toString().trim();
        String tipo3 = tipo2.getText().toString().trim();

        if (!TextUtils.isEmpty(titulo3) && !TextUtils.isEmpty(tipo3)) {

            String idR = databaseRelatorios.push().getKey();

            RelatorioProducaoLeite rel = new RelatorioProducaoLeite(idR, titulo3, tipo3, DataSistema());

            databaseRelatorios.child(idR).setValue(rel);

            titulo2.setText("");
            tipo2.setText("");

            Fragment_ListarRelatorio fragment_listarRelatorio = new Fragment_ListarRelatorio(id,nome);

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment_listarRelatorio).addToBackStack(null).commit();


        } else {
            Toast.makeText(getContext(), "Preencha Corretamente o Cadastro", Toast.LENGTH_SHORT).show();
        }

    }

    private String DataSistema() {
//        Locale locale = new Locale("pt", "BR");
//        GregorianCalendar calendar = new GregorianCalendar();
//        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();
        String dataMensagem = dateFormat.format(date);


        return dataMensagem;
    }

}
