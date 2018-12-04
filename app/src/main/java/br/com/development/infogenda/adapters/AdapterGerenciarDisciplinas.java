package br.com.development.infogenda.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.development.infogenda.R;
import br.com.development.infogenda.model.Disciplina;

public class AdapterGerenciarDisciplinas extends BaseAdapter {
    private Activity activity;
    private List<Disciplina> listDisciplinas = new ArrayList<>();

    //Dados da ListView
    private TextView tvNomeDisciplina;
    private TextView tvNomeProfessorDisciplina;
    private TextView tvInforSalaDisciplina;
    private ImageView btnRemoverDisciplina;
    private ImageView btnAlterarDisciplina;

    public AdapterGerenciarDisciplinas(List<Disciplina> listDisciplinas, Activity activity) {
        this.listDisciplinas = listDisciplinas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listDisciplinas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDisciplinas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Disciplina disc = listDisciplinas.get(position);

        View view = activity.getLayoutInflater().inflate(R.layout.adapter_gerenciar_disciplinas, parent, false);

        if (disc != null) {
            //Recuperando dados para a listview
            tvNomeDisciplina = (TextView) view.findViewById(R.id.tvNomeDisciplina);
            tvNomeDisciplina.setText(disc.getNomeDisciplina());

            tvNomeProfessorDisciplina = (TextView) view.findViewById(R.id.tvNomeProfessorDisciplina);
            tvNomeProfessorDisciplina.setText(disc.getNomeProfessor());

            tvInforSalaDisciplina = (TextView) view.findViewById(R.id.tvInforSalaDisciplina);
            tvInforSalaDisciplina.setText(disc.getInfoSala());

            btnAlterarDisciplina = (ImageView) view.findViewById(R.id.btnAlterarDisciplina);
            btnAlterarDisciplina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alerta("Click Alterar Disciplina Funcionando");
                }
            });

            btnRemoverDisciplina = (ImageView) view.findViewById(R.id.btnRemoverDisciplina);
            btnRemoverDisciplina.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alerta("Click Remover Disciplina Funcionando");
                }
            });

        }

        return view;
    }

    private void alerta(String msg){
        Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
