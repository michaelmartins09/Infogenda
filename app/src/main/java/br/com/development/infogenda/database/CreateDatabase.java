package br.com.development.infogenda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "infogenda.db";
    public static final int VERSAO = 1;

    //Preparando query para criar tabela de avaliações
    private static final String QUERY_TABELA_AVALIACAO =
            "CREATE TABLE IF NOT EXISTS avaliacao(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome TEXT NOT NULL," +
            "descricao TEXT," +
            "tipo_avaliacao TEXT," +
            "tipo_alerta TEXT NOT NULL," +
            "data BLOB NOT NULL," +
            "tempo_lembrete INTEGER)";

    //Preparando query para criar tabela de disciplinas
    private static final String QUERY_TABELA_DISCIPLINA =
            "CREATE TABLE IF NOT EXISTS disciplina(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "infor_sala TEXT)";

    public CreateDatabase(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_avaliacao = QUERY_TABELA_AVALIACAO;
        String sql_diciplina = QUERY_TABELA_DISCIPLINA;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public static String getNomeBanco() {
        return NOME_BANCO;
    }

    public static int getVERSAO() {
        return VERSAO;
    }
}
