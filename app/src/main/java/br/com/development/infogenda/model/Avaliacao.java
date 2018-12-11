package br.com.development.infogenda.model;

import java.util.Date;

public class Avaliacao {
    private int idAvaliacao;
    private String nomeAvaliacao;
    private String descricao;
    private Disciplina disciplina;
    private TIPOALERTA tipoAlerta;
    private Date dataNotificacao;
    private Date horarioNotificacao;
    private int tempLembrete;

    public Avaliacao(String nomeAvaliacao, String descricao, Disciplina disciplina, TIPOALERTA tipoAlerta, Date dataNotificacao, Date horarioNotificacao) {
        this.nomeAvaliacao = nomeAvaliacao;
        this.descricao = descricao;
        this.disciplina = disciplina;
        this.tipoAlerta = tipoAlerta;
        this.dataNotificacao = dataNotificacao;
        this.horarioNotificacao = horarioNotificacao;
    }

    public Avaliacao(String nomeAvaliacao, String descricao, Disciplina disciplina, TIPOALERTA tipoAlerta, Date dataNotificacao, Date horarioNotificacao, int tempLembrete) {
        this.nomeAvaliacao = nomeAvaliacao;
        this.descricao = descricao;
        this.disciplina = disciplina;
        this.tipoAlerta = tipoAlerta;
        this.dataNotificacao = dataNotificacao;
        this.horarioNotificacao = horarioNotificacao;
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

    public TIPOALERTA getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(TIPOALERTA tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public Date getDataNotificacao() {
        return dataNotificacao;
    }

    public void setDataNotificacao(Date dataNotificacao) {
        this.dataNotificacao = dataNotificacao;
    }

    public Date getHorarioNotificacao() {
        return horarioNotificacao;
    }

    public void setHorarioNotificacao(Date horarioNotificacao) {
        this.horarioNotificacao = horarioNotificacao;
    }

    public int getTempLembrete() {
        return tempLembrete;
    }

    public void setTempLembrete(int tempLembrete) {
        this.tempLembrete = tempLembrete;
    }
}
