package model;

import java.time.LocalDate;

public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataPrazo;
    private Status status;

    public enum Status {
        PENDENTE,
        EM_ANDAMENTO,
        CONCLUIDA
    }

    public Tarefa(int id, String titulo, String descricao, LocalDate dataPrazo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataPrazo = dataPrazo;
        this.status = Status.PENDENTE;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getDataPrazo() {
        return dataPrazo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public void concluir() {
        this.status = Status.CONCLUIDA;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Título: " + titulo +
                " | Prazo: " + dataPrazo +
                " | Status: " + status;
    }

}
