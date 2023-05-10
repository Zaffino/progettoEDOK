package com.unitn.zaffino.lingProg.grafico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;


/*
* La classe deve gestire la sessione in corso
* quando viene creato un oggetto Console viene chiamata la classe privata mainMenu
* che a sua volta chiama altri menu in base agli input dell'utente
* */
public class Console {

    LinkedList<Prodotto> prodotti;
    LinkedList<Fornitore> fornitori;
    Ordine ordine;


    /*
    * copia i prodotti e i fornitori inizializzati in precedenza e apre il main menu
    * */
    public Console(LinkedList<Prodotto> prodottiSessione, LinkedList<Fornitore> fornitoriSessione){
        prodotti = new LinkedList<>();
        fornitori = new LinkedList<>();

        prodotti.addAll(prodottiSessione);
        fornitori.addAll(fornitoriSessione);

        mainMenu();
    }


    /*
    * permette la creazione un nuovo ordine
    * o di uscire dall'applicazione
    * */
    private void mainMenu() {
        System.out.println("Quale operazione desideri compiere?");
        System.out.println("1. Crea nuovo ordine\n" +
                "2. Chiudi");

        int s;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            s = Integer.parseInt(input);
        }
        catch (Exception e){
            s = 0;
        }


        switch (s){

            case 1:
                ordine = new Ordine();
                ordineMenu();
                mainMenu();
                break;

            case 2:
                break;

            default:
                System.err.println("VALORE NON VALIDO");
                mainMenu();
                break;
        }

    }


    private void ordineMenu(){
        System.out.println("Quale operazione desideri compiere?");
        System.out.println("1. Aggiungi nuovi elementi\n" +
                        "2. Elimina elementi\n" +
                        "3. Completa ordine\n" +
                        "4. Annulla ordine");

        int s;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            s = Integer.parseInt(input);
        }
        catch (Exception e){
            s = 0;
        }


        switch (s){

            case 1:
                aggiungiElementoMenu2();
                ordineMenu();
                break;

            case 2:
                if (!ordine.ordineVuoto())
                    rimuoviElementoMenu();
                else System.err.println("L'ORDINE E' VUOTO");
                ordineMenu();
                break;

            case 3:
                System.out.println("il pagamento è di " + ordine.getPrezzoTotale() + "€");
                System.out.println("ordine confermato");

                break;

            case 4:

                System.out.println("ordine annullato");
                break;

            default:
                System.err.println("VALORE NON VALIDO");
                ordineMenu();
                break;
        }


    }


    /*
    * obsoleto:
    * il metodo richiede l'inserimento di 2 valori.
    * il primo è l'id del prodotto
    * il secondo è la quantità del prodotto
    * */
    private void aggiungiElementoMenu(){

        System.out.println("quale elemento vuoi aggiungere?");
        for (Prodotto p: prodotti) {
            System.out.println(p.simplifiedToString());
        }

        int s;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            s = Integer.parseInt(input);
        }
        catch (Exception e){
            s = -1;
        }

        if (s == -1){
            System.err.println("PRODOTTO INESISTENTE");
            return; //c'è un errore e mi torna indietro
        }
        else{
            for (Prodotto p: prodotti) {
                if (p.getId() == s) ordine.getListaProdotti().add(p);
            } //edit this
        }

        System.out.println("in che quantità?");

        int s2;
        reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            s2 = Integer.parseInt(input);
        }
        catch (Exception e){
            s2 = -1;
        }
        if (s2 <= 0) return;
        else{
            ordine.getQuantitaProdotti().add(s2);

            int prezzoDaAggiungere = ordine.getListaProdotti().getLast().getPrezzo() * s2; //calcolo funzionante

            ordine.addPrezzoTotale(prezzoDaAggiungere);
        }

    }


    /*
     * il metodo richiede l'inserimento di 2 valori.
     * il primo è l'id (theID) del prodotto
     * il secondo è la quantità (q) del prodotto
     * */
    private void aggiungiElementoMenu2(){

        int q;
        int theID;

        System.out.println("quale elemento vuoi aggiungere?");
        for (Prodotto p: prodotti) {
            System.out.println(p.simplifiedToString());
        }




        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            theID = Integer.parseInt(input);
        }
        catch (Exception e){
            theID = -1;
            System.err.println("NON E' UN NUMERO");
        }

        System.out.println("in che quantita?");

        reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            q = Integer.parseInt(input);
        }
        catch (Exception e){
            q = -1;
        }



        if (q < 0 ){
            System.err.println("IMPOSSIBILE AGGIUNGERE QUESTA QUANTITA'");
        }
        else if (theID != -1) {
            for (Prodotto p : prodotti) {
                if (p.getId() == theID){
                    ordine.aggiungiProdotto(p,q);
                    return;
                }
            }
        }


    }


    /*
    * il metodo richiede l'inserimento di 1 valore.
    * il valore è l'id del prodotto
    *
    * */
    private void rimuoviElementoMenu(){
        System.out.println("cosa desideri rimuovere?");
        ordine.printListaProdotti();

        int s;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            s = Integer.parseInt(input);
        }
        catch (Exception e){
            s = -1;
            System.err.println("PRODOTTO INESISTENTE");
        }

        if(s>0)
            if (ordine.rimuoviProdotto(s))
                System.out.println("prodotto rimosso");
            else
                System.err.println("PRODOTTO INESISTENTE");
    }

}
