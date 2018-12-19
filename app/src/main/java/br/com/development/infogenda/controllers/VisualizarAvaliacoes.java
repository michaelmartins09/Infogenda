package br.com.development.infogenda.controllers;

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
import br.com.development.infogenda.adapters.AdapterVisualizarAvaliacoes;
import br.com.development.infogenda.database.DatabaseController;
import br.com.development.infogenda.model.Avaliacao;

public class VisualizarAvaliacoes extends AppCompatActivity {

    private ListView listViewAvaliacoes;
    private List<Avaliacao> listAvaliacoes = new ArrayList<>();

    private TextView statusListAvaliacao;
    private Button btnAddNovaDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_avaliacoes);

        //Carregando variáveis
        initVariaveis();
        //COnfiguração da Toolbar
        configToolbar();
        //Eventos de Touch
        eventClick();

        carregarListObject();
    }

    private void carregarListObject() {
        DatabaseController crud = new DatabaseController(getApplicationContext());
        listAvaliacoes = crud.carregarAvaliacoes();
        if (listAvaliacoes != null && listAvaliacoes.size() >= 1) {
            listViewAvaliacoes.setVisibility(View.VISIBLE);
            statusListAvaliacao.setVisibility(View.GONE);
            AdapterVisualizarAvaliacoes cad = new AdapterVisualizarAvaliacoes(listAvaliacoes, this);
            listViewAvaliacoes.setAdapter(cad);
        } else {
            listViewAvaliacoes.setVisibility(View.GONE);
            statusListAvaliacao.setVisibility(View.VISIBLE);
            statusListAvaliacao.setText("Avaliações não encontradas");
        }
    }

    private void eventClick() {

    }

    private void configToolbar() {
        getSupportActionBar().setTitle("Visualizar Avaliações");
    }

    private void initVariaveis() {
        listViewAvaliacoes = (ListView) findViewById(R.id.listViewAvaliacoes);
        statusListAvaliacao = (TextView) findViewById(R.id.statusListAvaliacoes);
    }
}
