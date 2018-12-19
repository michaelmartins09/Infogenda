package br.com.development.infogenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.development.infogenda.model.Avaliacao;
import br.com.development.infogenda.model.Disciplina;

public class DatabaseController {
    public SQLiteDatabase database;
    public CreateDatabase bancocriado;

    private Context context;

    public DatabaseController(Context context) {
        this.context = context;
        bancocriado = new CreateDatabase(context);
    }

    //Método para inserir Avaliações no bano de dados
    public String inserirAvaliacao(Avaliacao avaliacao) {
        ContentValues contentValues;
        long response;

        database = bancocriado.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("nome", avaliacao.getNomeAvaliacao());
        contentValues.put("descricao", avaliacao.getDescricao());
        contentValues.put("idDisciplina", avaliacao.getDisciplina().getIdDisciplina());
        contentValues.put("dataNotificacao", avaliacao.getDataNotificacao());
        contentValues.put("horarioNotificacao", avaliacao.getHorarioNotificacao());
        contentValues.put("tipoalerta", avaliacao.getTipoalerta());
        contentValues.put("tempolembrete", 5);

        response = database.insert("avaliacao", null, contentValues);
        //Log.i("DEBUG/DATABASE", String.valueOf(contentValues));
        //Log.i("DEBUG/DATABASE", String.valueOf(response));
        database.close();

        if (response == -1) {
            Log.i("DEBUG/DATABASE", "Erro registrar avaliação");
            return "Erro registrar avaliação";
        } else {
            Log.i("DEBUG/DATABASE", "Avaliação registrada com sucesso");
            return "Avaliação registrada com sucesso";
        }
    }

    //Método para inserir Disciplinas no bano de dados
    public String inserirDisciplina(Disciplina disciplina) {
        try {
            ContentValues contentValues;
            long response;

            database = bancocriado.getWritableDatabase();
            contentValues = new ContentValues();
            contentValues.put("nomeDisciplina", disciplina.getNomeDisciplina());
            contentValues.put("nomeProfessor", disciplina.getNomeProfessor());
            contentValues.put("infor_sala", disciplina.getInfoSala());

            response = database.insert("disciplina", null, contentValues);
            database.close();

            if (response == -1) {
                Log.i("DEBUG/DATABASE", "Erro ao inserir disciplina");
                return "Erro ao inserir disciplina";
            } else {
                Log.i("DEBUG/DATABASE", "Disciplina registrada com sucesso");
                return "Disciplina registrada com sucesso";
            }
        } catch (Exception e) {
            return "DEBUG/DATABASE: " + e.getMessage();
        }
    }

    public Cursor cursorConsulta(String tabela) {
        database = bancocriado.getReadableDatabase();
        Cursor cu = database.rawQuery("select ALL _id,* from " + tabela, null);
        return cu;
    }

    public List<Disciplina> carregarDisciplinas() {
        List<Disciplina> listDisciplinas = new ArrayList<>();
        Cursor cursor = cursorConsulta("disciplina");


        if (cursor.moveToFirst()) {
            do {
                //int idDisciplina, String nomeDisciplina, String nomeProfessor, String infoSala
                Disciplina disciplinaList = new Disciplina(null, null, null);
                disciplinaList.setIdDisciplina(cursor.getInt(0));
                disciplinaList.setNomeDisciplina(cursor.getString(cursor.getColumnIndex("nomeDisciplina")));
                disciplinaList.setNomeProfessor(cursor.getString(cursor.getColumnIndex("nomeProfessor")));
                disciplinaList.setInfoSala(cursor.getString(cursor.getColumnIndex("infor_sala")));

                listDisciplinas.add(disciplinaList);
            } while (cursor.moveToNext());
        }
        database.close();
        return listDisciplinas;
    }

    public String limparTabela(String tabela) {
        String response;
        try {
            String where = "_id IS NOT NULL";
            database = bancocriado.getReadableDatabase();
            database.delete(tabela, where, null);
            database.close();
            response = "Tabela limpa com sucesso";
            return response;
        } catch (Exception e) {
            response = "Erro: " + e.getMessage();
            return response;
        }
    }

    public Disciplina getDisciplina(String nomeDisciplina) {
        Disciplina response = null;

        List<Disciplina> temp = carregarDisciplinas();
        for (Disciplina nome : temp) {
            if (nome.getNomeDisciplina().equals(nomeDisciplina)) {
                response = nome;
            }
        }
        return response;
    }

    public Disciplina getDisciplinaById(String idDisciplina) {
        Disciplina response = null;

        List<Disciplina> temp = carregarDisciplinas();
        for (Disciplina nome : temp) {
            if (nome.getNomeDisciplina().equals(idDisciplina)) {
                response = nome;
            }
        }
        return response;
    }

    public List<Avaliacao> carregarAvaliacoes() {
        List<Avaliacao> listAvaliacoes = new ArrayList<>();
        Cursor cursor = cursorConsulta("avaliacao");


        if (cursor.moveToFirst()) {
            do {
                Avaliacao avaliacaoList =
                        new Avaliacao(0, null, null, null, null, null, null, 0);

                avaliacaoList.setIdAvaliacao(cursor.getInt(1));
                avaliacaoList.setNomeAvaliacao(cursor.getString(cursor.getColumnIndex("nome")));
                avaliacaoList.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                avaliacaoList.setDisciplina(getDisciplinaById(cursor.getString(cursor.getColumnIndex("idDisciplina"))));
                avaliacaoList.setDataNotificacao(cursor.getString(cursor.getColumnIndex("dataNotificacao")));
                avaliacaoList.setHorarioNotificacao(cursor.getString(cursor.getColumnIndex("horarioNotificacao")));
                avaliacaoList.setTipoalerta(cursor.getString(cursor.getColumnIndex("tipoalerta")));
                avaliacaoList.setTempLembrete(cursor.getInt(cursor.getColumnIndex("tempolembrete")));

                listAvaliacoes.add(avaliacaoList);
            } while (cursor.moveToNext());
        }
        database.close();
        return listAvaliacoes;
    }
}
