package br.com.development.infogenda.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.development.infogenda.R;

public class RegistrarAvaliacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_avaliacoes);
        getSupportActionBar().setTitle("Registrar Avaliações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                alerta("Click Item Configurações Funcionando");
                break;

            case R.id.itemGerenciarDisciplina:
                alerta("Click Item Gerenciar Disciplinas Funcionando");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void alerta(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
