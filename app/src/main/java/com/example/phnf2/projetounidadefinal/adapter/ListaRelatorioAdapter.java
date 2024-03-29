package com.example.phnf2.projetounidadefinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_CadastroRelatorio;
import com.example.phnf2.projetounidadefinal.modelo.RelatorioProducaoLeite;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListaRelatorioAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<RelatorioProducaoLeite> listaRelatorio;
    private RelatorioProducaoLeite relatorioescolhido;

    public ListaRelatorioAdapter(Context context, List<RelatorioProducaoLeite> listaRelatorio) {
        this.context = context;
        this.listaRelatorio = listaRelatorio;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_relatorios,parent,false);
        ViewHolderRelatorio holderRelatorio = new ViewHolderRelatorio(view);
        return holderRelatorio;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolderRelatorio holderRelatorio = (ViewHolderRelatorio) holder;

        relatorioescolhido = listaRelatorio.get(position);

        holderRelatorio.textViewTitulo2.setText(relatorioescolhido.getTituloRelatorio());
        holderRelatorio.textViewTipo2.setText(relatorioescolhido.getTipoRelatorio());
        holderRelatorio.textViewData2.setText(relatorioescolhido.getDataRelatorio());
        holderRelatorio.photoRelatorio.setImageResource(R.drawable.ic_report);


    }

    @Override
    public int getItemCount() {
        return listaRelatorio == null ? 0 : listaRelatorio.size();
    }


    public class ViewHolderRelatorio extends RecyclerView.ViewHolder{

        final TextView textViewTitulo2;
        final TextView textViewTipo2;
        final TextView textViewData2;
        final ImageView photoRelatorio;

        public ViewHolderRelatorio(@NonNull View itemView) {
            super(itemView);

            textViewTitulo2 = itemView.findViewById(R.id.textViewTitulo);
            textViewTipo2 = itemView.findViewById(R.id.textViewTipo);
            textViewData2 = itemView.findViewById(R.id.textViewData);
            photoRelatorio = itemView.findViewById(R.id.relatorioPhoto);

        }
    }



}
