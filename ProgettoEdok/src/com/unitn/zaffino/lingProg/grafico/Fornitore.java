package com.unitn.zaffino.lingProg.grafico;

import java.sql.*;
import java.util.LinkedList;

public class Fornitore {



    private String nome;
    private int id;

    public Fornitore(String nome) {
        this.nome = nome;
    }

    public Fornitore(int forn_id ,String nome){
        this.nome = nome;
        this.id = forn_id;
    }

    public String getNome() {
        return nome;
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
