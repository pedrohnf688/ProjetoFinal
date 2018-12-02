package com.example.phnf2.projetounidadefinal.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_EditarRelatorio;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_EditarUsers;
import com.example.phnf2.projetounidadefinal.modelo.RelatorioProducaoLeite;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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

        final EditarRelatorios.ViewHolderRelatorioEditar holderRelatorio = (EditarRelatorios.ViewHolderRelatorioEditar) holder;

        relatorioescolhido = relatorioList.get(position);

        holderRelatorio.TituloEditar.setText(relatorioescolhido.getTituloRelatorio());
        holderRelatorio.TipoEditar.setText(relatorioescolhido.getTipoRelatorio());
        holderRelatorio.DataEditar.setText(relatorioescolhido.getDataRelatorio());

    }


    @Override
    public int getItemCount() {
        return relatorioList == null ? 0 : relatorioList.size();

    }

    public class ViewHolderRelatorioEditar extends RecyclerView.ViewHolder{

        final TextView TituloEditar;
        final TextView TipoEditar;
        final TextView DataEditar;

        public ViewHolderRelatorioEditar(@NonNull View itemView) {
            super(itemView);

            TituloEditar = itemView.findViewById(R.id.EditarTitulo);
            TipoEditar = itemView.findViewById(R.id.EditarTipo);
            DataEditar = itemView.findViewById(R.id.EditaData);
        }

    }


}
