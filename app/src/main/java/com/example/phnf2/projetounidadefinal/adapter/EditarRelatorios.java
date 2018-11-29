package com.example.phnf2.projetounidadefinal.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_EditarRelatorio;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_EditarUsers;
import com.example.phnf2.projetounidadefinal.modelo.RelatorioProducaoLeite;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class EditarRelatorios extends RecyclerView.Adapter {

    private Context context;
    private List<RelatorioProducaoLeite> relatorioList;
    RelatorioProducaoLeite relatorioescolhido;


    public EditarRelatorios(Context context, List<RelatorioProducaoLeite> relatorioList) {
        this.context = context;
        this.relatorioList = relatorioList;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.editar_relatorios,parent,false);
        EditarRelatorios.ViewHolderRelatorioEditar holderRelatorio = new EditarRelatorios.ViewHolderRelatorioEditar(view);
        return holderRelatorio;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EditarRelatorios.ViewHolderRelatorioEditar holderRelatorio = (EditarRelatorios.ViewHolderRelatorioEditar) holder;

        relatorioescolhido = relatorioList.get(position);

        holderRelatorio.TituloEditar.setText(relatorioescolhido.getTituloRelatorio());
        holderRelatorio.TipoEditar.setText(relatorioescolhido.getTipoRelatorio());
        holderRelatorio.DataEditar.setText(relatorioescolhido.getDataRelatorio());


        holderRelatorio.removerRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_EditarRelatorio fragment = new Fragment_EditarRelatorio();

                Toast.makeText(context, "Remover", Toast.LENGTH_SHORT).show();
                DatabaseReference databaserelatorio = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Relatorios").child(fragment.idString()).orderByChild("idRelatorio").equalTo(relatorioescolhido.getIdRelatorio());
                DatabaseReference databaseOrdenha = FirebaseDatabase.getInstance().getReference("Ordenhas").child(relatorioescolhido.getIdRelatorio());

                databaserelatorio.removeValue();
                databaseOrdenha.removeValue();
            }
        });

        holderRelatorio.atualizarRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Atualizar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return relatorioList == null ? 0: relatorioList.size();
    }


    public class ViewHolderRelatorioEditar extends RecyclerView.ViewHolder{

        final TextView TituloEditar;
        final TextView TipoEditar;
        final TextView DataEditar;
        final ImageButton removerRelatorio;
        final ImageButton atualizarRelatorio;



        public ViewHolderRelatorioEditar(@NonNull View itemView) {
            super(itemView);

            TituloEditar = itemView.findViewById(R.id.EditarTitulo);
            TipoEditar = itemView.findViewById(R.id.EditarTipo);
            DataEditar = itemView.findViewById(R.id.EditaData);
            removerRelatorio = itemView.findViewById(R.id.RemRelatorio);
            atualizarRelatorio = itemView.findViewById(R.id.EdiRelatorio);

        }
    }
    
}
