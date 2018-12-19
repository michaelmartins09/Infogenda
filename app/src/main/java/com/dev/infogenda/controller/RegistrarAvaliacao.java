package com.dev.infogenda.controller;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dev.infogenda.R;
import com.dev.infogenda.database.DatabaseController;
import com.dev.infogenda.model.Avaliacao;
import com.dev.infogenda.model.Disciplina;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegistrarAvaliacao extends AppCompatActivity {

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
    private Spinner etTempoLembrete;

    //Finalizar
    private Button btnSalvarAvaliacao;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_avaliacao);
        getSupportActionBar().setTitle("Registrar Avaliações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Carregando variáveis
        iniciarVariaveis();
        //Método de evento Touch
        eventosClick();
        //COnfiguração da data e horário como Dialog
        configurarDataPicker();
        configurarTimePicker();
    }

    @Override
    protected void onStart() {
        prepararSpinner();
        super.onStart();
    }

    @Override
    protected void onResume() {
        prepararSpinner();
        super.onResume();
    }

    private void alerta(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    private void iniciarVariaveis() {
        //Infor
        etNomeAvaliacao = (TextInputEditText) findViewById(R.id.etNomeAvaliacao);
        etDescricaoAvaliacao = (TextInputEditText) findViewById(R.id.etDescricaoAvaliacao);

        //Disciplina
        btnAddNovaDisciplina = (Button) findViewById(R.id.btnAddNovaDisciplina);

        //Notificação
        rgroupTipoNotificacao = (RadioGroup) findViewById(R.id.rgroupTipoNotificacao);
        rbtnTipoLuminoso = (RadioButton) findViewById(R.id.rbtnTipoLuminoso);
        rbtnTipoSonoro = (RadioButton) findViewById(R.id.rbtnTipoSonoro);
        rbtnTipoMensagem = (RadioButton) findViewById(R.id.rbtnTipoMensagem);
        etDataAvaliacao = (EditText) findViewById(R.id.etDataAvaliacao);
        etHorarioAvaliacao = (EditText) findViewById(R.id.etHorarioAvaliacao);
        swtLembrete = (Switch) findViewById(R.id.swtLembrete);
        etTempoLembrete = (Spinner) findViewById(R.id.spnTempoLembrete);

        //Finalizar
        btnSalvarAvaliacao = (Button) findViewById(R.id.btnSalvarAvaliacao);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

    }

    private void eventosClick() {
        btnAddNovaDisciplina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrarAvaliacao.this, RegistrarDisciplina.class));
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

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSalvarAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    salvarAvaliacao();
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

                new DatePickerDialog(RegistrarAvaliacao.this, date, calendario
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
                horario = new TimePickerDialog(RegistrarAvaliacao.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etHorarioAvaliacao.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hora, minuto, true);
                horario.show();
            }
        });
    }

    private void prepararSpinner() {
        DatabaseController crud = new DatabaseController(getApplicationContext());

        if (spnDisciplinas == null) {
            spnDisciplinas = (Spinner) findViewById(R.id.spnDisciplinas);
        }

        ArrayAdapter<Disciplina> arrayAdapter =
                new ArrayAdapter<>(this,
                        R.layout.support_simple_spinner_dropdown_item,
                        crud.carregarDisciplinas());
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnDisciplinas.setAdapter(arrayAdapter);
    }

    private boolean validarCampos() {
        if (etNomeAvaliacao.getText().toString().trim().equals("")) {
            etNomeAvaliacao.setError("Informe o nome da Avaliação");
            return false;
        }
        if (etDescricaoAvaliacao.getText().toString().trim().equals("")) {
            etDescricaoAvaliacao.setError("Informe descrição da Avaliação");
            return false;
        }
        if (spnDisciplinas.getSelectedItem().toString().equals("")) {
            alerta("Selecione ou cadastre uma disciplina para continuar");
            return false;
        }
        if (!rbtnTipoMensagem.isChecked() && !rbtnTipoLuminoso.isChecked() && !rbtnTipoSonoro.isChecked()) {
            alerta("Selecione um tipo de alerta");
            return false;
        }
        if (etDataAvaliacao.getText().toString().equals("")) {
            alerta("Escolha uma data para a notificação");
            return false;
        }
        if (etHorarioAvaliacao.getText().toString().equals("")) {
            alerta("Escolha um  horário para a notificação");
            return false;
        }
        return true;
    }

    private void salvarAvaliacao() {
        DatabaseController crud = new DatabaseController(getApplicationContext());
        String[] disciplina = spnDisciplinas.getSelectedItem().toString().split(" - ");
        String tipoAlerta = "";

        int tempoNot = 0;

        if (swtLembrete.isChecked()) {
            String[] splitTemp = etTempoLembrete.getSelectedItem().toString().split(" ");
            tempoNot = Integer.parseInt(splitTemp[0]);
        }

        switch (rgroupTipoNotificacao.getCheckedRadioButtonId()) {
            case R.id.rbtnTipoLuminoso:
                tipoAlerta = rbtnTipoLuminoso.getText().toString();
                break;
            case R.id.rbtnTipoMensagem:
                tipoAlerta = rbtnTipoMensagem.getText().toString();
                break;
            case R.id.rbtnTipoSonoro:
                tipoAlerta = rbtnTipoSonoro.getText().toString();
                break;
        }

        Avaliacao aval = new Avaliacao(
                etNomeAvaliacao.getText().toString(),
                etDescricaoAvaliacao.getText().toString(),
                crud.getDisciplina(disciplina[0]),
                etDataAvaliacao.getText().toString(),
                etHorarioAvaliacao.getText().toString(),
                tipoAlerta,
                tempoNot);

        String response = crud.inserirAvaliacao(aval);
        if (response.contains("Erro")) {
            alerta(response);
        } else {
            finish();
        }
    }
}
