package com.example.phnf2.projetounidadefinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_Cadastro;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_CadastroRelatorio;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_EditarUserOrdenha;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_EditarUsers;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_Inicio;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_ListarRelatorio;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_ListarUser;
import com.example.phnf2.projetounidadefinal.fragment.Fragment_Sobre;
import com.example.phnf2.projetounidadefinal.modelo.Administrador;
import com.example.phnf2.projetounidadefinal.util.FirebaseUtil;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment;
    TextView nomeAdmin;
    TextView emailAdmin;
    CircleImageView photoURL;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String nome;
    String email;
    String photo;
    DatabaseReference databaseUsuario;
    private FirebaseDatabase mFirebase;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Menu kkkk = navigationView.getMenu();
        kkkk.findItem(R.id.nav_cadastroUser).setVisible(false);

        mFirebase = FirebaseDatabase.getInstance();
        databaseUsuario = mFirebase.getReference("Admininstrador");

        nomeAdmin = navigationView.getHeaderView(0).findViewById(R.id.NomeAdmin);
        emailAdmin = navigationView.getHeaderView(0).findViewById(R.id.EmailAdmin);
        photoURL = navigationView.getHeaderView(0).findViewById(R.id.imageViewAdmin);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        try {

            nome = user.getDisplayName();
            email = user.getEmail();
            photo = user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null;
            nomeAdmin.setText(nome);
            emailAdmin.setText(email);
            FirebaseUtil.loadProfileIcon(photo,photoURL);

            Administrador administrador = new Administrador(FirebaseUtil.getAdministrador().getIdAdmin(), nome,photo,email);
            databaseUsuario.child(FirebaseUtil.getAdministrador().getIdAdmin()).setValue(administrador);

        }catch (NullPointerException e){
            Log.i("Erro Pedro",e.getMessage());
        }

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

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            AuthUI.getInstance().signOut(this);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {

            toolbar.setTitle("Início");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, new Fragment_Inicio()).addToBackStack(null).commit();
        } else if (id == R.id.nav_cadastroUser) {
            toolbar.setTitle("Cadastro do Usuário");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, new Fragment_Cadastro()).addToBackStack(null).commit();
        } else if (id == R.id.nav_listarUser) {
            toolbar.setTitle("Listar e Cadastrar Dados");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, new Fragment_ListarUser()).addToBackStack(null).commit();
        } else if (id == R.id.nav_editarRelatorio) {
            //Editar e Excluir relatorio
            toolbar.setTitle("Editar Relatório");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal, new Fragment_EditarUsers()).commit();
            //Toast.makeText(this, "Editar Tudo", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.nav_editarOrdenha){
            toolbar.setTitle("Editar Ordenha");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,new Fragment_EditarUserOrdenha()).addToBackStack(null).commit();
//            Toast.makeText(this, "Atualização e Remoção da Ordenha", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.nav_sobre) {
            //Informações do Aplicativo
//            Toast.makeText(this, "Informações Sobre o App e o Setor", Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,new Fragment_Sobre()).addToBackStack(null).commit();
        } else if(id == R.id.nav_sair){
            // Logout dos Usuários logados
            AuthUI.getInstance().signOut(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentPrincipal,new Fragment_Inicio()).addToBackStack(null).commit();
//            return true;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






}
