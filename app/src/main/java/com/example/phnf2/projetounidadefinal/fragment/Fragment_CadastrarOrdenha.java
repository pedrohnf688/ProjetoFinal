package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.modelo.Ordenha;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Fragment_CadastrarOrdenha extends Fragment {

    public String id;
    TextInputEditText Gord2;
    TextInputEditText Prot2;
    TextInputEditText Cas2;
    TextInputEditText Lact2;
    TextInputEditText St2;
    TextInputEditText Esd2;
    TextInputEditText Nu2;
    TextInputEditText Cel2;
    TextInputEditText Ccs2;
    Button CadastrarOrdenha2;
    DatabaseReference databaseOrdenha;
    FirebaseDatabase mFirebase;


    @SuppressLint("ValidFragment")
    public Fragment_CadastrarOrdenha(String id) {
        // Required empty public constructor
        this.id = id;
    }

    public Fragment_CadastrarOrdenha() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment__cadastrar_ordenha, container, false);

        Gord2 = view.findViewById(R.id.EntradaGor);
        Prot2 = view.findViewById(R.id.EntradaProt);
        Cas2 = view.findViewById(R.id.EntradaCas);
        Lact2 = view.findViewById(R.id.EntradaLact);
        St2 = view.findViewById(R.id.EntradaSt);
        Esd2 = view.findViewById(R.id.EntradaEsd);
        Nu2 = view.findViewById(R.id.EntradaNu);
        Cel2 = view.findViewById(R.id.EntradaCel);
        Ccs2 = view.findViewById(R.id.EntradaCcs);
        CadastrarOrdenha2 = view.findViewById(R.id.CadastrarOrdenha);


        mFirebase = FirebaseDatabase.getInstance();
        if(mFirebase == null) {

            mFirebase.setPersistenceEnabled(true);
            databaseOrdenha = mFirebase.getReference("Ordenhas").child(id);

        }else{

            databaseOrdenha = mFirebase.getReference("Ordenhas").child(id);
            databaseOrdenha.keepSynced(true);
        }



        CadastrarOrdenha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CadastrarOrdenha();
            }
        });


        return view;
    }




    private void CadastrarOrdenha(){


        String gord3 = Gord2.getText().toString().trim();
        String prot3 = Prot2.getText().toString().trim();
        String cas3 = Cas2.getText().toString().trim();
        String lact3 = Lact2.getText().toString().trim();
        String st3 = St2.getText().toString().trim();
        String esd3 = Esd2.getText().toString().trim();
        String nu3 = Nu2.getText().toString().trim();
        String cel3 = Cel2.getText().toString().trim();
        String ccs3 = Ccs2.getText().toString().trim();

        if(!TextUtils.isEmpty(gord3) && !TextUtils.isEmpty(prot3) && !TextUtils.isEmpty(cas3) &&
                !TextUtils.isEmpty(lact3) && !TextUtils.isEmpty(st3) && !TextUtils.isEmpty(esd3) &&
                !TextUtils.isEmpty(nu3) && !TextUtils.isEmpty(cel3) && !TextUtils.isEmpty(ccs3)){


            double Gor = Double.parseDouble(gord3);
            double Prot = Double.parseDouble(prot3);
            double Cas = Double.parseDouble(cas3);
            double Lact = Double.parseDouble(lact3);
            double St = Double.parseDouble(st3);
            double Esd = Double.parseDouble(esd3);
            double Nu = Double.parseDouble(nu3);
            double Cel = Double.parseDouble(cel3);
            double Ccs = Double.parseDouble(ccs3);

            String idOr = databaseOrdenha.push().getKey();

            Ordenha ordenha = new Ordenha(idOr,Gor,Prot,Cas,Lact,St,Esd,Nu,Cel,Ccs);

            databaseOrdenha.child(idOr).setValue(ordenha);

            Gord2.setText("");
            Prot2.setText("");
            Cas2.setText("");
            Lact2.setText("");
            St2.setText("");
            Esd2.setText("");
            Nu2.setText("");
            Cel2.setText("");
            Ccs2.setText("");


            Fragment_ListarOrdenha fragment_listarOrdenha = new Fragment_ListarOrdenha(id);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,fragment_listarOrdenha).addToBackStack(null).commit();

//            getActivity().getSupportFragmentManager().popBackStack();
        }else{
            Toast.makeText(getContext(), "Preencha Corretamente o Cadastro", Toast.LENGTH_SHORT).show();
        }

    }

}
