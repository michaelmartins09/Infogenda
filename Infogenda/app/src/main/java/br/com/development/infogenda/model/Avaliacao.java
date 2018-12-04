package br.com.development.infogenda.model;

import java.util.Date;

public class Avaliacao {
    private String nomeAvaliacao;
    private String descricao;
    private TIPOALERTA tipoAlerta;
    private Date date;
    private int tempLembrete;

    public Avaliacao(String nomeAvaliacao, String descricao, TIPOALERTA tipoAlerta, Date date, int tempLembrete) {
        this.nomeAvaliacao = nomeAvaliacao;
        this.descricao = descricao;
        this.tipoAlerta = tipoAlerta;
        this.date = date;
        this.tempLembrete = tempLembrete;
    }

    public Avaliacao(String nomeAvaliacao, String descricao, TIPOALERTA tipoAlerta, Date date) {
        this.nomeAvaliacao = nomeAvaliacao;
        this.descricao = descricao;
        this.tipoAlerta = tipoAlerta;
        this.date = date;
    }

    public String getNomeAvaliacao() {
        return nomeAvaliacao;
    }

    public void setNomeAvaliacao(String nomeAvaliacao) {
        this.nomeAvaliacao = nomeAvaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TIPOALERTA getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(TIPOALERTA tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTempLembrete() {
        return tempLembrete;
    }

    public void setTempLembrete(int tempLembrete) {
        this.tempLembrete = tempLembrete;
    }
}
