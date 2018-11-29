package br.com.development.infogenda.controllers;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.development.infogenda.R;

public class RegistrarAvaliacoes extends AppCompatActivity {

    //Infor
    private TextInputEditText etNomeAvaliacao;
    private TextInputEditText etDescricaoAvaliacao;

    //Disciplina
    private Spinner spnDisciplinas;
    private Button btnAddNovaDisciplina;

    //Notificação
    private RadioGroup rgroupTipoNotificacao;
    private RadioButton rbtnTipoLuminoso;
    private RadioButton rbtnTipoSonoro;
    private RadioButton rbtnTipoMensagem;
    private EditText etDataAvaliacao;
    private EditText etHorarioAvaliacao;
    private Switch swtLembrete;
    private EditText etTempoLembrete;

    //Finalizar
    private Button btnSalvarAvaliacao;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_avaliacoes);
        getSupportActionBar().setTitle("Registrar Avaliações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        iniciarVariaveis();
        eventosClick();
        configurarDataPicker();
        configurarTimePicker();
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

    private void iniciarVariaveis() {
        //Infor
        etNomeAvaliacao = (TextInputEditText) findViewById(R.id.etNomeAvaliacao);
        etDescricaoAvaliacao = (TextInputEditText) findViewById(R.id.etDescricaoAvaliacao);

        //Disciplina
        spnDisciplinas = (Spinner) findViewById(R.id.spnDisciplinas);
        btnAddNovaDisciplina = (Button) findViewById(R.id.btnAddNovaDisciplina);

        //Notificação
        rgroupTipoNotificacao = (RadioGroup) findViewById(R.id.rgroupTipoNotificacao);
        rbtnTipoLuminoso = (RadioButton) findViewById(R.id.rbtnTipoLuminoso);
        rbtnTipoSonoro = (RadioButton) findViewById(R.id.rbtnTipoSonoro);
        rbtnTipoMensagem = (RadioButton) findViewById(R.id.rbtnTipoMensagem);
        etDataAvaliacao = (EditText) findViewById(R.id.etDataAvaliacao);
        etHorarioAvaliacao = (EditText) findViewById(R.id.etHorarioAvaliacao);
        swtLembrete = (Switch) findViewById(R.id.swtLembrete);
        etTempoLembrete = (EditText) findViewById(R.id.etTempoLembrete);

        //Finalizar
        btnSalvarAvaliacao = (Button) findViewById(R.id.btnSalvarAvaliacao);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

    }

    private void eventosClick() {
        btnAddNovaDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarAvaliacoes.this, GerenciarDisciplinas.class));
            }
        });

        swtLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swtLembrete.isChecked()) {
                    etTempoLembrete.setVisibility(View.VISIBLE);
                } else {
                    etTempoLembrete.setVisibility(View.GONE);
                }
            }
        });
    }

    private void configurarDataPicker() {
        etDataAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendario = Calendar.getInstance();
                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendario.set(Calendar.YEAR, year);
                        calendario.set(Calendar.MONTH, month);
                        calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                        //Atualizando EditText
                        String formatoBr = "dd/MM/yy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatoBr);

                        etDataAvaliacao.setText(sdf.format(calendario.getTime()));
                    }
                };

                new DatePickerDialog(RegistrarAvaliacoes.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void configurarTimePicker() {
        etHorarioAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendario = Calendar.getInstance();
                int hora = calendario.get(Calendar.HOUR);
                int minuto = calendario.get(Calendar.MINUTE);

                TimePickerDialog horario;
                horario = new TimePickerDialog(RegistrarAvaliacoes.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etHorarioAvaliacao.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hora, minuto, true);
                horario.show();
            }
        });
    }
}
