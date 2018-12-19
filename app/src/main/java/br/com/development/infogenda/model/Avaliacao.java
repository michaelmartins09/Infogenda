package br.com.development.infogenda.model;

import java.util.Date;

public class Avaliacao {
    private int idAvaliacao;
    private String nomeAvaliacao;
    private String descricao;
    private Disciplina disciplina;
    private String dataNotificacao;
    private String horarioNotificacao;
    private String tipoAleta;
    private int tempLembrete;

    public Avaliacao(int idAvaliacao, String nomeAvaliacao, String descricao, Disciplina disciplina, String dataNotificacao, String horarioNotificacao, String tipoAleta, int tempLembrete) {
        this.idAvaliacao = idAvaliacao;
        this.nomeAvaliacao = nomeAvaliacao;
        this.descricao = descricao;
        this.disciplina = disciplina;
        this.dataNotificacao = dataNotificacao;
        this.horarioNotificacao = horarioNotificacao;
        this.tipoAleta = tipoAleta;
        this.tempLembrete = tempLembrete;
    }
    public Avaliacao(String nomeAvaliacao, String descricao, Disciplina disciplina, String dataNotificacao, String horarioNotificacao, String tipoAleta, int tempLembrete) {
        this.nomeAvaliacao = nomeAvaliacao;
        this.descricao = descricao;
        this.disciplina = disciplina;
        this.dataNotificacao = dataNotificacao;
        this.horarioNotificacao = horarioNotificacao;
        this.tipoAleta = tipoAleta;
        this.tempLembrete = tempLembrete;
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
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

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(String dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public String getHorarioNotificacao() {
        return horarioNotificacao;
    }

    public void setHorarioNotificacao(String horarioNotificacao) {
        this.horarioNotificacao = horarioNotificacao;
    }

    public String getTipoAleta() {
        return tipoAleta;
    }

    public void setTipoAleta(String tipoAleta) {
        this.tipoAleta = tipoAleta;
    }

    public int getTempLembrete() {
        return tempLembrete;
    }

    public void setTempLembrete(int tempLembrete) {
        this.tempLembrete = tempLembrete;
    }
}
