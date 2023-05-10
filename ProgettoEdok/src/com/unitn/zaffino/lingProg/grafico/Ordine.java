package com.unitn.zaffino.lingProg.grafico;

import java.util.ArrayList;
import java.util.LinkedList;

public class Ordine {

    private static int ORDINE_COUNT = 0;

    private int id;
    private LinkedList<Prodotto> listaProdotti;
    private ArrayList<Integer> quantitaProdotti;
    private int prezzoTotale;

    /*
    * crea un nuovo ordine vuoto
    * */
    public Ordine() {
        id = ORDINE_COUNT+1;
        listaProdotti = new LinkedList<>();
        quantitaProdotti = new ArrayList<>();
        prezzoTotale = 0;

        ORDINE_COUNT++;
    }

    public int getId() {
        return id;
    }

    public LinkedList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public ArrayList<Integer> getQuantitaProdotti() {
        return quantitaProdotti;
    }


    public boolean ordineVuoto(){
        if (listaProdotti.isEmpty()) return true;
        return false;
    }

    public boolean rimuoviProdotto(int IDProdotto){
        int i = 0;
        for (Prodotto p: listaProdotti) {
            if (IDProdotto == p.getId()){
                int prezzoDaTogliere = p.getPrezzo() * this.getQuantitaProdotti().get(i);
                listaProdotti.remove(p);
                quantitaProdotti.remove(i);
                this.addPrezzoTotale(-prezzoDaTogliere);
                return true;
            }
            i++;
        }
        return false;
    }

    public void aggiungiProdotto(Prodotto prodotto, int quantita){


        int i = 0;
        for (Prodotto p: listaProdotti) {
            if (prodotto.getId() == p.getId()){ //se trovo

                //update quantita
                int nuovaQuantita = quantitaProdotti.get(i) + quantita;
                quantitaProdotti.set(i,nuovaQuantita);

                //update prezzo
                int prezzoAggiunto = p.getPrezzo() * quantita;
                prezzoTotale += prezzoAggiunto;


                return;
            }
            i++;
        }
        listaProdotti.add(prodotto);
        quantitaProdotti.add(quantita);
        prezzoTotale += prodotto.getPrezzo() * quantita;
        return;

    }

    public int getPrezzoTotale() {
        return prezzoTotale;
    }

    public void addPrezzoTotale(int aggiunta) {
        prezzoTotale = prezzoTotale + aggiunta;
    }


    public void printListaProdotti(){
        for (Prodotto p : listaProdotti) {
            System.out.println("id - " + p.getId() + "\tfornitore - " + p.getFornitore() +
                    "\tnome - " + p.getNome() + "\tprezzo - " +p.getPrezzo());
        }
    }



    @Override
    public String toString() {
        return "Ordine: " +
                "\tid - " + id +
                "\tlistaProdotti: " + listaProdotti.toString() +
                "\tprezzoTotale" + prezzoTotale;
    }
}
