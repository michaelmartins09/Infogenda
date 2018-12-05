package br.com.development.infogenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public String inserirAvaliacao(Avaliacao avaliacao){
        ContentValues contentValues;
        long response;

        database = bancocriado.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("nome", avaliacao.getNomeAvaliacao());
        contentValues.put("descricao", avaliacao.getDescricao());
        contentValues.put("tipo_alerta", "sonoro");
        contentValues.put("date", "01/01/2019");
        contentValues.put("tempo_lembrete", 5);

        response = database.insert(this.bancocriado.getDatabaseName(),null, contentValues);
        database.close();

        if (response == -1){
            Log.i("DEBUG/DATABASE", "Erro registrar avaliação");
            return "Erro registrar avaliação";
        }else{
            Log.i("DEBUG/DATABASE", "Avaliação registrada com sucesso");
            return "Avaliação registrada com sucesso";
        }
    }

    //Método para inserir Disciplinas no bano de dados
    public String inserirDisciplina(Disciplina disciplina){
        ContentValues contentValues;
        long response;

        database = bancocriado.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("nome", disciplina.getNomeDisciplina());
        contentValues.put("professor", disciplina.getNomeProfessor());
        contentValues.put("infor_sala", disciplina.getInfoSala());

        response = database.insert(this.bancocriado.getDatabaseName(),null, contentValues);
        database.close();

        if (response == -1){
            Log.i("DEBUG/DATABASE", "Erro ao inserir disciplina");
            return "Erro ao inserir disciplina";
        }else{
            Log.i("DEBUG/DATABASE", "Disciplina registrada com sucesso");
            return "Disciplina registrada com sucesso";
        }
    }


}
