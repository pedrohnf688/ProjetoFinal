package com.example.phnf2.projetounidadefinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    ChildEventListener mchildEventListener;
    List<Usuario> listUsuarios = new ArrayList<>();
    RecyclerView listViewUsuario;
    DatabaseReference databaseUsuario;
    public final static String USERNAME_USER = "usuarioNome";
    public final static String ID_USER = "usuarioId";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listViewUsuario = findViewById(R.id.ListViewUsuario);
        databaseUsuario = FirebaseDatabase.getInstance().getReference("Usuarios");

        listViewUsuario.addOnItemTouchListener(new RecyclerItemClickListener(this, listViewUsuario, new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {

                        Usuario user3 = listUsuarios.get(position);

                        Toast.makeText(Main2Activity.this, "ID:"+user3.getIdUser() +"Nome:"+user3.getNomeUser(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                        Usuario user3 = listUsuarios.get(position);

                        Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                        intent.putExtra(USERNAME_USER, user3.getNomeUser());
                        intent.putExtra(ID_USER, user3.getIdUser());
                        startActivity(intent);

                    }
                })
        );








    }

    @Override
    protected void onStart() {
        super.onStart();


        databaseUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listUsuarios.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Usuario user3 = dataSnapshot1.getValue(Usuario.class);

                    listUsuarios.add(user3);

                }

                ListAdapter adapter = new ListAdapter(Main2Activity.this, listUsuarios);
                listViewUsuario.setAdapter(adapter);

                RecyclerView.LayoutManager layout = new LinearLayoutManager(Main2Activity.this, LinearLayoutManager.VERTICAL, false);
                listViewUsuario.setLayoutManager(layout);
                listViewUsuario.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}