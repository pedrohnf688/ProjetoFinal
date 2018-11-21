package com.example.phnf2.projetounidadefinal.activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.modelo.Usuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


//    EditText nomeUsuario1;
//    EditText emailUsuario1;
//    EditText senhaUsuario1;
//    Button buttonaddUsuario1;
//    ListView listViewUsuario;
//    DatabaseReference databaseUsuario;
//    ChildEventListener mchildEventListener;
//    List<Usuario> listUsuarios;
//    Button listarKKKK;
//    Button relatorioK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        nomeUsuario1 = findViewById(R.id.nomeUsuario);
//        emailUsuario1 = findViewById(R.id.emailUsuario);
//        senhaUsuario1 = findViewById(R.id.senhaUsuario);
//        buttonaddUsuario1 = findViewById(R.id.buttonaddUsuario);
//        listViewUsuario = findViewById(R.id.ListViewUsuario);
//        listarKKKK = findViewById(R.id.listarkkkkk);
//        relatorioK = findViewById(R.id.RelatorioK);
//
//
//        databaseUsuario = FirebaseDatabase.getInstance().getReference("Usuarios");
//
//        listUsuarios = new ArrayList<>();
//
//        buttonaddUsuario1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AdicionarUsuario();
//            }
//        });
//
//
//        listarKKKK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
//                startActivity(intent);
//            }
//        });
//
////        Main3Activity.class
//        relatorioK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent3 = new Intent(getApplicationContext(),Principal.class);
//                startActivity(intent3);
//            }
//        });
//
//    }
//
//
//    private void AdicionarUsuario(){
//
//        String nome = nomeUsuario1.getText().toString().trim();
//        String email = emailUsuario1.getText().toString().trim();
//        String senha = senhaUsuario1.getText().toString().trim();
//
//        if(!TextUtils.isEmpty(nome) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(senha)){
//
//            String id = databaseUsuario.push().getKey();
//
//            Usuario user = new Usuario(id,nome,email,senha);
//
//            databaseUsuario.child(id).setValue(user);
//
//            nomeUsuario1.setText("");
//            emailUsuario1.setText("");
//            senhaUsuario1.setText("");
//
//            Toast.makeText(this, "Usuario Cadastrado", Toast.LENGTH_SHORT).show();
//
//        }else{
//            Toast.makeText(this, "Preencha Corretamente o Formulário do Usuario", Toast.LENGTH_SHORT).show();
//        }
//
}



}
