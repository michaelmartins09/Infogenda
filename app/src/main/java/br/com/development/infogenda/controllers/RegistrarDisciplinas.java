package br.com.development.infogenda.controllers;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.development.infogenda.R;
import br.com.development.infogenda.database.DatabaseController;
import br.com.development.infogenda.model.Disciplina;

public class RegistrarDisciplinas extends AppCompatActivity {
    private TextInputEditText etNomeDisciplina;
    private TextInputEditText etNomeProfessor;
    private TextInputEditText etInforSala;

    private Button btnCancelarCadastroDisciplina;
    private Button btnSalvarDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_disciplinas);
        getSupportActionBar().setTitle("Adicionar Disciplina");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Iniciando variáveis
        iniciarVariaveis();
        //Método de evento Touch
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
        switch (item.getItemId()) {
            case R.id.itemConfiguracoes:
                //Cod
                break;
            case R.id.itemGerenciarDisciplina:
                startActivity(new Intent(RegistrarDisciplinas.this, GerenciarDisciplinas.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void iniciarVariaveis() {
        //Carregando EditText
        etNomeDisciplina = (TextInputEditText) findViewById(R.id.etNomeDisciplina);
        etNomeProfessor = (TextInputEditText) findViewById(R.id.etNomeProfessor);
        etInforSala = (TextInputEditText) findViewById(R.id.etInforSala);

        //Carregando botões
        btnCancelarCadastroDisciplina = (Button) findViewById(R.id.btnCancelarCadastroDisciplina);
        btnSalvarDisciplina = (Button) findViewById(R.id.btnSalvarDisciplina);
    }

    private void eventosClick() {
        btnCancelarCadastroDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Botão de salvar a disciplina ainda não implementado
        btnSalvarDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()){
                    salvarDisciplina();
                }
            }
        });
    }

    private void alerta(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private boolean validarCampos() {
        if (etNomeDisciplina.getText().toString().trim().equals("")) {
            etNomeDisciplina.setError("Informe o nome da Disciplina");
            return false;
        }
        if (etNomeProfessor.getText().toString().trim().equals("")) {
            etNomeProfessor.setError("Informe o nome do Professor");
            return false;
        }
        if (etInforSala.getText().toString().trim().equals("")) {
            etInforSala.setError("Informe as informações da Sala");
            return false;
        }
        return true;
    }

    private void salvarDisciplina() {
        Disciplina dic = new Disciplina(
                etNomeDisciplina.getText().toString(),
                etNomeProfessor.getText().toString(),
                etInforSala.getText().toString());

        DatabaseController crud = new DatabaseController(getApplicationContext());
        String response = crud.inserirDisciplina(dic);
        if (response.contains("Erro")){
            alerta(response);
        }else {
            finish();
        }
    }

}
