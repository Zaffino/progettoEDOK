package com.unitn.zaffino.lingProg.grafico;

public class Fornitore {

    private static int FORNITORE_COUNT = 0;

    private String nome;
    private int id;

    public Fornitore(String nome) {
        this.nome = nome;
        id = FORNITORE_COUNT+1;
        FORNITORE_COUNT++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Fornitore:" +
                "\tnome - " + nome +
                "\tid - " + id;
    }
}
