package com.dev.infogenda.custom;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev.infogenda.R;
import com.dev.infogenda.model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class CustomListDisciplinas extends BaseAdapter {
    private Activity activity;
    private List<Disciplina> listDisciplinas = new ArrayList<>();

    //Dados da ListView
    private TextView tvNomeDisciplina;
    private TextView tvNomeProfessorDisciplina;
    private TextView tvInforSalaDisciplina;

    public CustomListDisciplinas(List<Disciplina> listDisciplinas, Activity activity) {
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

        View view = activity.getLayoutInflater().inflate(R.layout.custom_list_disciplinas, parent, false);

        if (disc != null) {
            //Recuperando dados para a listview
            tvNomeDisciplina = (TextView) view.findViewById(R.id.tvNomeDisciplina);
            tvNomeDisciplina.setText(disc.getNomeDisciplina());

            tvNomeProfessorDisciplina = (TextView) view.findViewById(R.id.tvNomeProfessorDisciplina);
            tvNomeProfessorDisciplina.setText(disc.getNomeProfessor());

            tvInforSalaDisciplina = (TextView) view.findViewById(R.id.tvInforSalaDisciplina);
            tvInforSalaDisciplina.setText(disc.getInfoSala());
        }

        return view;
    }
}
