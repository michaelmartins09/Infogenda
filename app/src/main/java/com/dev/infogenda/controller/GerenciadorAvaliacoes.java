package com.dev.infogenda.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.dev.infogenda.R;
import com.dev.infogenda.custom.CustomListAvaliacoes;
import com.dev.infogenda.database.DatabaseController;
import com.dev.infogenda.model.Avaliacao;

import java.util.List;

public class GerenciadorAvaliacoes extends AppCompatActivity {
    List<Avaliacao> avaliacoes;
    ListView listView;
    private static CustomListAvaliacoes adapter;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador_avaliacoes);

        Button btnAddNovaAvaliacao = (Button) findViewById(R.id.btnAddNovaAvaliacao);
        btnAddNovaAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GerenciadorAvaliacoes.this, RegistrarAvaliacao.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent12 = new Intent(GerenciadorAvaliacoes.this, Configuracoes.class);
            startActivity(intent12);
            return true;
        }
        if (id == R.id.action_gerenciar_disciplinas){
            Intent intent12 = new Intent(GerenciadorAvaliacoes.this, GerenciadorDisciplinas.class);
            startActivity(intent12);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        if (listView == null) {
            listView = (ListView) findViewById(R.id.listViewDisciplinas);
        }
        carregarListObject();
        super.onResume();
    }

    private void carregarListObject() {
        DatabaseController crud = new DatabaseController(getApplicationContext());
        avaliacoes = crud.carregarAvaliacoes();
        listView = (ListView) findViewById(R.id.listAvaliacoes);
        CustomListAvaliacoes cla = new CustomListAvaliacoes(avaliacoes, getApplicationContext(), getLayoutInflater(), this);
        listView.setAdapter(cla);
    }
}
