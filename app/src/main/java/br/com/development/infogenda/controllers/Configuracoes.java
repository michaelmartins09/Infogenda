package br.com.development.infogenda.controllers;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.development.infogenda.R;
import br.com.development.infogenda.database.DatabaseController;

public class Configuracoes extends AppCompatActivity {
    private Button btnConfigLimparDisciplinas;
    private Button btnConfigLimparAvaliacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        initToolbar();
        initVariaveis();
        eventClick();
    }

    private void initToolbar() {
        getSupportActionBar().setTitle("Configurações");
    }

    private void eventClick() {
        btnConfigLimparAvaliacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Configuracoes.this).create();
                alertDialog.setTitle("Limpar Avaliações");
                alertDialog.setMessage("Deseja realmente realizar esta operação?");
                alertDialog.setCancelable(false);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseController crud = new DatabaseController(getApplicationContext());
                        String response = crud.limparTabela("avaliacao");
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

    }

    private void initVariaveis() {
        btnConfigLimparDisciplinas = (Button) findViewById(R.id.btnConfigLimparDisciplinas);
        btnConfigLimparAvaliacoes = (Button) findViewById(R.id.btnConfigLimparAvaliacoes);
    }


}
