package br.com.development.infogenda.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.com.development.infogenda.R;

public class RegistrarDisciplinas extends AppCompatActivity {

    private String nomeDisciplina;
    private String nomeProfessor;
    private String nroSala;

    private Button btnCancelarCadastroDisciplina;
    private Button btnSalvarDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_disciplinas);
        getSupportActionBar().setTitle("Adicionar Disciplina");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        return super.onOptionsItemSelected(item);
    }

    private void iniciarVariaveis(){
        btnCancelarCadastroDisciplina = (Button) findViewById(R.id.btnCancelarCadastroDisciplina);
        btnSalvarDisciplina = (Button) findViewById(R.id.btnSalvarDisciplina);
    }

    private void eventosClick(){
        btnCancelarCadastroDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarDisciplinas.this, RegistrarAvaliacoes.class));
            }
        });

        //Botão de salvar a disciplina ainda não implementado
//        btnSalvarDisciplina.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegistrarDisciplinas.this, RegistrarAvaliacoes.class));
//            }
//        });
    }

}
