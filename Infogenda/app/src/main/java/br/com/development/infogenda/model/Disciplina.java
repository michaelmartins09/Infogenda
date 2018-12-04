package br.com.development.infogenda.model;

public class Disciplina {
    private String nomeDisciplina;
    private String nomeProfessor;
    private String infoSala;

    public Disciplina(String nomeDisciplina, String nomeProfessor, String infoSala) {
        this.nomeDisciplina = nomeDisciplina;
        this.nomeProfessor = nomeProfessor;
        this.infoSala = infoSala;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getInfoSala() {
        return infoSala;
    }

    public void setInfoSala(String infoSala) {
        this.infoSala = infoSala;
    }

    @Override
    public String toString() {
        return "Disciplina{\n" +
                "Disciplina: " + nomeDisciplina + "\n" +
                "Professor: " + nomeProfessor + "\n" +
                "Sala: " + infoSala + "\n" +
                "}";
    }
}
