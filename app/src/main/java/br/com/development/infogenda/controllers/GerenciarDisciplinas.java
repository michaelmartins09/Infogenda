package br.com.development.infogenda.controllers;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.development.infogenda.R;
import br.com.development.infogenda.adapters.AdapterGerenciarDisciplinas;
import br.com.development.infogenda.adapters.CursorAdapterDisciplinas;
import br.com.development.infogenda.database.DatabaseController;
import br.com.development.infogenda.model.Disciplina;

public class GerenciarDisciplinas extends AppCompatActivity {
    private ListView listViewDisciplinas;
    private List<Disciplina> listDisciplinas = new ArrayList<>();

    private TextView statusListDisciplinas;
    private Button btnAddNovaDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_disciplinas);
        //Carregando variáveis
        initVariaveis();
        //COnfiguração da Toolbar
        configToolbar();
        //Eventos de Touch
        eventClick();

        carregarListObject();

    }

    private void configToolbar() {
        getSupportActionBar().setTitle("Gerenciar Disciplinas");
    }

    private void eventClick() {
        btnAddNovaDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GerenciarDisciplinas.this, RegistrarDisciplinas.class));
            }
        });
    }

    private void initVariaveis() {
        listViewDisciplinas = (ListView) findViewById(R.id.listViewDisciplinas);
        statusListDisciplinas = (TextView) findViewById(R.id.statusListDisciplinas);
        btnAddNovaDisciplina = (Button) findViewById(R.id.btnAddNovaDisciplina);
    }

    private void carregarListObject() {
        DatabaseController crud = new DatabaseController(getApplicationContext());
        listDisciplinas = crud.carregarDisciplinas();
        if (listDisciplinas != null && listDisciplinas.size() >= 1) {
            listViewDisciplinas.setVisibility(View.VISIBLE);
            statusListDisciplinas.setVisibility(View.GONE);
            AdapterGerenciarDisciplinas cad = new AdapterGerenciarDisciplinas(listDisciplinas, this);
            listViewDisciplinas.setAdapter(cad);
        } else {
            listViewDisciplinas.setVisibility(View.GONE);
            statusListDisciplinas.setVisibility(View.VISIBLE);
            statusListDisciplinas.setText("Disciplinas não encontradas");
        }
    }

    @Override
    protected void onResume() {
        if (listViewDisciplinas == null) {
            listViewDisciplinas = (ListView) findViewById(R.id.listViewDisciplinas);
        }
        if (statusListDisciplinas == null) {
            statusListDisciplinas = (TextView) findViewById(R.id.statusListDisciplinas);
        }

        carregarListObject();

        super.onResume();
    }
}
