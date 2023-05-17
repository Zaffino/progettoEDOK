package com.unitn.zaffino.lingProg.grafico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class Prodotto {



    private String nome;
    private int id;
    private Fornitore fornitore;
    private int prezzo;

    public Prodotto(String nome, Fornitore fornitore, int prezzo) {
        this.nome = nome;
        this.fornitore = fornitore;
        this.prezzo = prezzo;
    }

    public Prodotto(String nome, int id, Fornitore fornitore, int prezzo) {
        this.nome = nome;
        this.id = id;
        this.fornitore = fornitore;
        this.prezzo = prezzo;
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
