package br.com.development.infogenda.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import br.com.development.infogenda.R;

public class Inicio extends AppCompatActivity {

    private CalendarView calendario;
    private Button btnRegistrarAvaliacoes;
    private Button btnVisualizarAvaliacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        iniciarVariaveis();
        eventosClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemConfiguracoes:
                startActivity(new Intent(Inicio.this, Configuracoes.class));
                break;

            case R.id.itemGerenciarDisciplina:
                startActivity(new Intent(Inicio.this, GerenciarDisciplinas.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void iniciarVariaveis(){
        calendario = (CalendarView) findViewById(R.id.calendario);
        btnRegistrarAvaliacoes = (Button) findViewById(R.id.btnRegistrarAvaliacoes);
        btnVisualizarAvaliacoes = (Button) findViewById(R.id.btnVisualizarAvaliacoes);
    }

    private void eventosClick(){
        btnRegistrarAvaliacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inicio.this, RegistrarAvaliacoes.class));
            }
        });

        btnVisualizarAvaliacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inicio.this, VisualizarAvaliacoes.class));
            }
        });
    }

    private void alerta(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
