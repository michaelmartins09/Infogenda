package com.dev.infogenda.controller;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dev.infogenda.R;
import com.dev.infogenda.database.DatabaseController;
import com.dev.infogenda.model.Disciplina;

public class RegistrarDisciplina extends AppCompatActivity {

    private TextInputEditText etNomeDisciplina;
    private TextInputEditText etNomeProfessor;
    private TextInputEditText etInforSala;

    private Button btnCancelarCadastroDisciplina;
    private Button btnSalvarDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_disciplina);

        getSupportActionBar().setTitle("Adicionar Disciplina");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Iniciando variáveis
        iniciarVariaveis();
        //Método de evento Touch
        eventosClick();
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
