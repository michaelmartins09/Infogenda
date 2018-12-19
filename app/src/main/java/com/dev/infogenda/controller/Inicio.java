package com.dev.infogenda.controller;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.dev.infogenda.R;

public class Inicio extends AppCompatActivity {
    private static final int MINHA_PERMISSAO_PARA_MENSAGEM = 0;

    private CalendarView calendario;
    private Button btnVisualizarAvaliacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        configNotification();
        configPermissao();
        iniciarVariaveis();
        eventosClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent12 = new Intent(Inicio.this, Configuracoes.class);
            startActivity(intent12);
            return true;
        }
        if (id == R.id.action_gerenciar_disciplinas){
            Intent intent12 = new Intent(Inicio.this, GerenciadorDisciplinas.class);
            startActivity(intent12);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void configPermissao() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
        if (ContextCompat.checkSelfPermission(Inicio.this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(Inicio.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MINHA_PERMISSAO_PARA_MENSAGEM);
        }
    }

    private void iniciarVariaveis(){
        calendario = (CalendarView) findViewById(R.id.calendario);
        btnVisualizarAvaliacoes = (Button) findViewById(R.id.btnVisualizarAvaliacoes);
    }

    private void eventosClick(){
        btnVisualizarAvaliacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Inicio.this, GerenciadorAvaliacoes.class));
            }
        });
    }

    private void configNotification(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }
}
