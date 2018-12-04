package br.com.development.infogenda.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.development.infogenda.R;
import br.com.development.infogenda.adapters.AdapterGerenciarDisciplinas;
import br.com.development.infogenda.model.Disciplina;

public class GerenciarDisciplinas extends AppCompatActivity {
    private ListView listViewDisciplinas;
    private List<Disciplina> listDisciplinas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_disciplinas);
        configToolbar();

        //Aqui carregar do banco os dados das disciplinas e adicionar na LIst
        listDisciplinas.add(new Disciplina("Verificação e Validação Software", "Sam da Silva", "Lab3 e Sala 209"));
        listDisciplinas.add(new Disciplina("Resolução de Problemas I", "Gilleane Guedes", "Lab2"));

        listViewDisciplinas = (ListView) findViewById(R.id.listViewDisciplinas);
        AdapterGerenciarDisciplinas agd = new AdapterGerenciarDisciplinas(listDisciplinas, this);
        listViewDisciplinas.setAdapter(agd);

        //Configuração da toolbar
    }

    private void configToolbar() {
        getSupportActionBar().setTitle("Gerenciar Disciplinas");
    }
}
