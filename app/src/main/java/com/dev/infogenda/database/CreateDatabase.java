package com.dev.infogenda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class CreateDatabase extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "infogenda.db";
    public static final String TABLE_DISC = "disciplina";
    public static final String TABLE_AVAL = "avaliacao";
    public static final int VERSAO = 1;

    //Preparando query para criar tabela de disciplinas
    private static final String QUERY_TABELA_DISCIPLINA =
            "CREATE TABLE IF NOT EXISTS disciplina(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nomeDisciplina TEXT NOT NULL," +
                    "nomeProfessor TEXT NOT NULL," +
                    "infor_sala TEXT NOT NULL)";

    //Preparando query para criar tabela de avaliações
    private static final String QUERY_TABELA_AVALIACAO =
            "CREATE TABLE IF NOT EXISTS avaliacao(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "descricao TEXT," +
                    "idDisciplina INTEGER NOT NULL CONSTRAINT idDisciplina REFERENCES disciplina (idDisciplina) ON DELETE CASCADE," +
                    "dataNotificacao TEXT NOT NULL," +
                    "horarioNotificacao TEXT NOT NULL," +
                    "tipoalerta TEXT NOT NULL," +
                    "tempolembrete INTEGER)";

    public CreateDatabase(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_TABELA_DISCIPLINA);
        db.execSQL(QUERY_TABELA_AVALIACAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

}