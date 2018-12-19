package com.dev.infogenda.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.infogenda.R;
import com.dev.infogenda.custom.CustomListDisciplinas;
import com.dev.infogenda.database.DatabaseController;
import com.dev.infogenda.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDisciplinas extends AppCompatActivity {

    private ListView listViewDisciplinas;
    private List<Disciplina> listDisciplinas = new ArrayList<>();

    private TextView statusListDisciplinas;
    private Button btnAddNovaDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciador_disciplinas);

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
                startActivity(new Intent(GerenciadorDisciplinas.this, RegistrarDisciplina.class));
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
            CustomListDisciplinas cld = new CustomListDisciplinas(listDisciplinas, this);
            listViewDisciplinas.setAdapter(cld);
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
