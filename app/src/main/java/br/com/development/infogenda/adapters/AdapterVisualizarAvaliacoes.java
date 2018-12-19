package br.com.development.infogenda.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.development.infogenda.R;
import br.com.development.infogenda.model.Avaliacao;

public class AdapterVisualizarAvaliacoes extends BaseAdapter {
    private Activity activity;
    private List<Avaliacao> listAvaliacao = new ArrayList<>();

    //Dados da ListView
    private TextView tvNomeAvaliacao;
    private TextView tvDataAvaliacao;
    private TextView tvTipoAlerta;

    public AdapterVisualizarAvaliacoes(List<Avaliacao> listAvaliacao, Activity activity) {
        this.listAvaliacao = listAvaliacao;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listAvaliacao.size();
    }

    @Override
    public Object getItem(int position) {
        return listAvaliacao.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Avaliacao aval = listAvaliacao.get(position);

        View view = activity.getLayoutInflater().inflate(R.layout.adapter_visualizar_avaliacoes, parent, false);

        if (aval != null) {
            tvNomeAvaliacao = (TextView) view.findViewById(R.id.tvNomeAvaliacao);
            tvNomeAvaliacao.setText(aval.getNomeAvaliacao());

            tvDataAvaliacao = (TextView) view.findViewById(R.id.tvDataAvaliacao);
            tvDataAvaliacao.setText(aval.getDataNotificacao());

            tvTipoAlerta = (TextView) view.findViewById(R.id.tvTipoAlerta);
            tvTipoAlerta.setText(aval.getTipoalerta());


        }

        return view;
    }

    private void alerta(String msg){
        Toast.makeText(activity.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
