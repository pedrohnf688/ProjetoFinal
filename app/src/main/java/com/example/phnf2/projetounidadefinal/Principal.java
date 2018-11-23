package com.example.phnf2.projetounidadefinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.fragment.Fragment_Cadastro;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_CadastroRelatorio;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_Inicio;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_ListarRelatorio;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_ListarUser;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment;
    TextView nomeAdmin;
    TextView emailAdmin;
    ImageView photoURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nomeAdmin = findViewById(R.id.NomeAdmin);
        emailAdmin = findViewById(R.id.EmailAdmin);
        photoURL = findViewById(R.id.imageViewAdmin);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);




        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,new Fragment_Inicio()).addToBackStack(null).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                getSupportFragmentManager().popBackStack();
            }else{
                super.onBackPressed();
            }

//            if (fragment instanceof Fragment_Inicio) {
//                super.onBackPressed();
//
//            }else{
//
//                fragment = new Fragment_Inicio();
//
//                if (fragment != null) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, fragment).commit();
//
//                }
//            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AuthUI.getInstance().signOut(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_inicio){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, new Fragment_Inicio()).addToBackStack(null).commit();
        }else if (id == R.id.nav_cadastroUser) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, new Fragment_Cadastro()).addToBackStack(null).commit();
        } else if (id == R.id.nav_listarUser) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, new Fragment_ListarUser()).addToBackStack(null).commit();
        } else if (id == R.id.nav_listarRelatorio) {
           //Listar cada Relatorio Cadastrado por Usuario
            Toast.makeText(this, "Listar Relatório", Toast.LENGTH_SHORT).show();
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, new Fragment_ListarRelatorio()).commit();
        } else if (id == R.id.nav_atualizarRelatorio) {
            //Atualizar Relatorios
            Toast.makeText(this, "Atualizar Relatório", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_excluirRelatorio) {
            //Excluir Relatorios
            Toast.makeText(this, "Excluir Relatório", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_sobre) {
            //Informações do Aplicativo
            Toast.makeText(this, "Informações Sobre o App e o Setor", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.nav_contato){
            //Informações Sobre o Desenvolvedor do Aplicativo
            Toast.makeText(this, "Contanto do Setor e do Desenvolvedor do App", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.nav_sair){
            // Logout dos Usuários logados
            Toast.makeText(this, "Deslogar da Conta do Usuário Logado (Admin ou User)", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





}
