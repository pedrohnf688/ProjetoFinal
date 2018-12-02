package com.example.phnf2.projetounidadefinal.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_EditarOrdenha;
import com.example.phnf2.projetounidadefinal.modelo.Ordenha;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditarOrdenhas extends RecyclerView.Adapter {

    private Context context;
    private List<Ordenha> listaOrdenha;
    private Ordenha ordenhaescolhida;


    public EditarOrdenhas(Context context, List<Ordenha> listaOrdenha) {
        this.context = context;
        this.listaOrdenha = listaOrdenha;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.editar_ordenhas, parent,false);

        EditarOrdenhas.ViewHolderOrdenhaEditar viewHolderOrdenha = new EditarOrdenhas.ViewHolderOrdenhaEditar(view);
        return viewHolderOrdenha;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EditarOrdenhas.ViewHolderOrdenhaEditar viewHolderOrdenha = (EditarOrdenhas.ViewHolderOrdenhaEditar) holder;

        ordenhaescolhida = listaOrdenha.get(position);

        viewHolderOrdenha.textViewGord2.setText(""+ordenhaescolhida.getGord());
        viewHolderOrdenha.textViewProt2.setText(""+ordenhaescolhida.getProt());
        viewHolderOrdenha.textViewCas2.setText(""+ordenhaescolhida.getCas());
        viewHolderOrdenha.textViewLact2.setText(""+ordenhaescolhida.getLact());
        viewHolderOrdenha.textViewSt2.setText(""+ordenhaescolhida.getSt());
        viewHolderOrdenha.textViewEsd2.setText(""+ordenhaescolhida.getEsd());
        viewHolderOrdenha.textViewNu2.setText(""+ordenhaescolhida.getNu());
        viewHolderOrdenha.textViewCel2.setText(""+ordenhaescolhida.getCel());
        viewHolderOrdenha.textViewCcs2.setText(""+ordenhaescolhida.getCcs());

//        viewHolderOrdenha.removerOrdenha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment_EditarOrdenha FEditarOrdenha = new Fragment_EditarOrdenha();
//
//                DatabaseReference databaseordenha = FirebaseDatabase.getInstance().getReference("Ordenhas").child(FEditarOrdenha.getOrdenhaID()).child(ordenhaescolhida.getIdOrdenha());
//
//                databaseordenha.removeValue();
//
//            }
//        });

//        viewHolderOrdenha.atualizarOrdenha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(context, "Atualizar Ordenha", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return listaOrdenha == null ? 0 : listaOrdenha.size();
    }

    public class ViewHolderOrdenhaEditar extends RecyclerView.ViewHolder {

        final TextView textViewGord2;
        final TextView textViewProt2;
        final TextView textViewCas2;
        final TextView textViewLact2;
        final TextView textViewSt2;
        final TextView textViewEsd2;
        final TextView textViewNu2;
        final TextView textViewCel2;
        final TextView textViewCcs2;


        public ViewHolderOrdenhaEditar(@NonNull View itemView) {
            super(itemView);

            textViewGord2 =itemView.findViewById(R.id.textViewGordEditar);
            textViewProt2 = itemView.findViewById(R.id.textViewProtEditar);
            textViewCas2 = itemView.findViewById(R.id.textViewCasEditar);
            textViewLact2 = itemView.findViewById(R.id.textViewLactEditar);
            textViewSt2 = itemView.findViewById(R.id.textViewStEditar);
            textViewEsd2 = itemView.findViewById(R.id.textViewEsdEditar);
            textViewNu2 = itemView.findViewById(R.id.textViewNuEditar);
            textViewCel2 = itemView.findViewById(R.id.textViewCelEditar);
            textViewCcs2 = itemView.findViewById(R.id.textViewCcsEditar);

        }
    }
}