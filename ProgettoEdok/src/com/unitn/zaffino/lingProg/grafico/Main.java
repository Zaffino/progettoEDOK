package com.unitn.zaffino.lingProg.grafico;

import java.util.LinkedList;

public class Main {

    private static void inizializza(LinkedList<Prodotto> pd, LinkedList<Fornitore> fd){
        fd.add(new Fornitore("iron spa"));
        fd.add(new Fornitore("wood spa"));
        fd.add(new Fornitore("glass srl"));

        pd.add(new Prodotto("cacciaviti",fd.get(0),20));
        pd.add(new Prodotto("specchio", fd.get(2), 40));
        pd.add(new Prodotto("viti", fd.get(0),5));
        pd.add(new Prodotto("asse di legno", fd.get(1), 10));
    };

    public static void main(String[] args) {
        LinkedList<Prodotto> prodottiDisponibili = new LinkedList<>();
        LinkedList<Fornitore> fornitoriDisponibli = new LinkedList<>();
        inizializza(prodottiDisponibili, fornitoriDisponibli);
        Console c = new Console(prodottiDisponibili,fornitoriDisponibli);


    }
}
