package com.example.phnf2.projetounidadefinal;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.modelo.Relatorio;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity {

    TextView nomeVisualizar;
    TextInputEditText titulo2;
    TextInputEditText tipo2;
    Button buttonaddRelatorio;
    DatabaseReference databaseRelatorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nomeVisualizar = findViewById(R.id.textViewNomeUser);
        titulo2 = findViewById(R.id.titulo);
        tipo2 = findViewById(R.id.tipo);
        buttonaddRelatorio = findViewById(R.id.buttonaddRelatorio);

        Intent intent = getIntent();
        String id = intent.getStringExtra(Main2Activity.ID_USER);
        String nome = intent.getStringExtra(Main2Activity.USERNAME_USER);

        nomeVisualizar.setText(nome);

        databaseRelatorios = FirebaseDatabase.getInstance().getReference("Relatorios").child(id);

       buttonaddRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionarRelatorio();
            }
        });
    }


    private void AdicionarRelatorio(){

        String titulo3 = titulo2.getText().toString().trim();
        String tipo3 = tipo2.getText().toString().trim();

        if(!TextUtils.isEmpty(titulo3) && !TextUtils.isEmpty(tipo3)){

            String idR = databaseRelatorios.push().getKey();

            Relatorio rel = new Relatorio(idR,titulo3,tipo3,DataSistema());

            databaseRelatorios.child(idR).setValue(rel);

        }else{
            Toast.makeText(this, "Preencha Corretamente o Cadastro", Toast.LENGTH_SHORT).show();
        }

    }

    private String DataSistema(){
        Locale locale = new Locale("pt", "BR");
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatador = new SimpleDateFormat("dd' de 'MMMMM' de 'yyyy' - 'HH':'mm'h'", locale);

        return formatador.format(calendar.getTime());
    }
}
