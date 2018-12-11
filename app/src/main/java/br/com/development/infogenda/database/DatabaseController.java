package br.com.development.infogenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLOutput;
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
        contentValues.put("tipo_alerta", "sonoro");
        contentValues.put("date", "01/01/2019");
        contentValues.put("tempo_lembrete", 5);

        response = database.insert(this.bancocriado.getDatabaseName(), null, contentValues);
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
        List<Disciplina> list = new ArrayList<>();
        Cursor cursor = cursorConsulta("disciplina");

        cursor.moveToFirst();

        while (cursor.moveToNext() && !cursor.isNull(cursor.getColumnIndex("nomeDisciplina"))) {
            list.add(
                    new Disciplina(
                            cursor.getInt(1),
                            cursor.getString(cursor.getColumnIndex("nomeDisciplina")),
                            cursor.getString(cursor.getColumnIndex("nomeProfessor")),
                            cursor.getString(cursor.getColumnIndex("infor_sala"))));
            cursor.moveToNext();
        }
        database.close();
        return list;
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

    public int getIdDisciplina(String nomeDisciplina){
        int response;
            String sql = "SELECT * FROM disciplina WHERE nomeDisciplina = '" + nomeDisciplina + "'";
            database = bancocriado.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql,null);
            System.out.println(cursor.getString(cursor.getColumnIndex("nomeDisciplina")));
            response = 0;
            database.close();
        return response;
    }
}
