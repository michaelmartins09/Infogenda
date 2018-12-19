package br.com.development.infogenda.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.development.infogenda.R;
import br.com.development.infogenda.model.Disciplina;

public class CursorAdapterDisciplinas extends CursorAdapter {
    private Context context;

    //Dados da ListView
    private TextView tvNomeDisciplina;
    private TextView tvNomeProfessorDisciplina;
    private TextView tvInforSalaDisciplina;

    public CursorAdapterDisciplinas(Context context, Cursor c) {
        super(context, c, 0);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(this.context).inflate(R.layout.adapter_gerenciar_disciplinas, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        String nome = cursor.getString(2);
        String prof = cursor.getString(3);
        String nsal = cursor.getString(4);

        final Disciplina disc = new Disciplina(nome, prof, nsal);

        tvNomeDisciplina = (TextView) view.findViewById(R.id.tvNomeDisciplina);
        tvNomeDisciplina.setText(disc.getNomeDisciplina());

        tvNomeProfessorDisciplina = (TextView) view.findViewById(R.id.tvNomeProfessorDisciplina);
        tvNomeProfessorDisciplina.setText(disc.getNomeProfessor());

        tvInforSalaDisciplina = (TextView) view.findViewById(R.id.tvInforSalaDisciplina);
        tvInforSalaDisciplina.setText(disc.getInfoSala());

    }
}
