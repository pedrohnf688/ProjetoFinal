package com.example.phnf2.projetounidadefinal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.modelo.Ordenha;

import java.util.List;

public class ListaOrdenhaAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Ordenha> listaOrdenha;
    private Ordenha ordenhaescolhida;


    public ListaOrdenhaAdapter(Context context, List<Ordenha> listaOrdenha) {
        this.context = context;
        this.listaOrdenha = listaOrdenha;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_ordenha, parent,false);

        ViewHolderOrdenha viewHolderOrdenha = new ViewHolderOrdenha(view);
        return viewHolderOrdenha;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderOrdenha viewHolderOrdenha = (ViewHolderOrdenha) holder;

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

    }

    @Override
    public int getItemCount() {
        return listaOrdenha == null ? 0 : listaOrdenha.size();
    }

    public class ViewHolderOrdenha extends RecyclerView.ViewHolder {

        final TextView textViewGord2;
        final TextView textViewProt2;
        final TextView textViewCas2;
        final TextView textViewLact2;
        final TextView textViewSt2;
        final TextView textViewEsd2;
        final TextView textViewNu2;
        final TextView textViewCel2;
        final TextView textViewCcs2;


        public ViewHolderOrdenha(@NonNull View itemView) {
            super(itemView);

            textViewGord2 =itemView.findViewById(R.id.textViewGord);
            textViewProt2 = itemView.findViewById(R.id.textViewProt);
            textViewCas2 = itemView.findViewById(R.id.textViewCas);
            textViewLact2 = itemView.findViewById(R.id.textViewLact);
            textViewSt2 = itemView.findViewById(R.id.textViewSt);
            textViewEsd2 = itemView.findViewById(R.id.textViewEsd);
            textViewNu2 = itemView.findViewById(R.id.textViewNu);
            textViewCel2 = itemView.findViewById(R.id.textViewCel);
            textViewCcs2 = itemView.findViewById(R.id.textViewCcs);

        }
    }
}
