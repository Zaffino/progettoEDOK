package com.unitn.zaffino.lingProg.grafico;

public class Prodotto {

    private static int PRODOTTO_COUNT = 0;

    private String nome;
    private int id;
    private Fornitore fornitore;
    private int prezzo;

    public Prodotto(String nome, Fornitore fornitore, int prezzo) {
        this.nome = nome;
        id = PRODOTTO_COUNT+1;
        this.fornitore = fornitore;
        this.prezzo = prezzo;
        PRODOTTO_COUNT++;
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


    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public Fornitore getFornitore() {
        return fornitore;
    }

    public void setFornitore(Fornitore fornitore) {
        this.fornitore = fornitore;
    }

    public String simplifiedToString(){
        return  "id - " + id +
                "\tnome - " + nome +
                "\tfornitore - " + fornitore.getNome() +
                "\tprezzo - " + prezzo;
    }

    @Override
    public String toString() {
        return  "id - " + id +
                "\tnome - " + nome +
                "\tfornitore - " + fornitore +
                "\tprezzo - " + prezzo;
    }
}
