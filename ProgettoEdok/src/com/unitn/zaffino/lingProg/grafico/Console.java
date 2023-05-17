package com.unitn.zaffino.lingProg.grafico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.LinkedList;


/*
 * La classe deve gestire la sessione in corso
 * quando viene creato un oggetto Console viene chiamata la classe privata mainMenu
 * che a sua volta chiama altri menu in base agli input dell'utente
 * */
public class Console {

    LinkedList<Prodotto> prodotti;
    LinkedList<Fornitore> fornitori;
    Ordine ordine;
    Connection connection;
    DBdaoFornitori dBdaoFornitori;
    DBdaoProdotti dBdaoProdotti;


    /*
     * copia i prodotti e i fornitori dal database e apre il main menu
     * */
    public Console(Connection connection){
        prodotti = new LinkedList<>();
        fornitori = new LinkedList<>();
        this.connection = connection;

        this.dBdaoFornitori = new DBdaoFornitori(connection);
        this.dBdaoProdotti = new DBdaoProdotti(connection);


        fornitori.addAll(dBdaoFornitori.selectAll());
        prodotti.addAll(dBdaoProdotti.selectAll());


        mainMenu();
    }

    /*
     * permette la selezione dell'operazione
     * */
    private void mainMenu() {
        System.out.println("Quale operazione desideri compiere?");
        System.out.println("1. Crea nuovo ordine\n" + "2. Registra nuovo fornitore\n" +
                "3. Registra nuovo prodotto\n" + "4. Rimuovi fornitore\n" +
                "5. Rimuovi prodotto\n" + "6. Stampa fornitori\n" +
                "7. Stampa prodotti\n" + "100. Chiudi");

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

            case 2: //non ha bisogno di download
                registraNuovoFornitore();
                fornitori.clear();
                fornitori.addAll(dBdaoFornitori.selectAll());
                mainMenu();
                break;

            case 3: //fa da solo il download
                registraNuovoProdotto();
                prodotti.clear();
                prodotti.addAll(dBdaoProdotti.selectAll());
                mainMenu();
                break;

            case 4:
                rimuoviFornitore();
                mainMenu();
                break;

            case 5:
                rimuoviProdotto();
                mainMenu();
                break;

            case 6:
                for (Fornitore f: dBdaoFornitori.selectAll()
                ) {
                    System.out.println(f.toString());
                }
                mainMenu();
                break;

            case 7:
                for (Prodotto p: dBdaoProdotti.selectAll()
                ) {
                    System.out.println(p.toString());
                }
                mainMenu();
                break;

            case 100:
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
            return;
        }

        boolean nonEsiste = true;
        for (Prodotto p: prodotti) {
            if (p.getId() == theID) nonEsiste = false;
        }
        if (nonEsiste){
            System.err.println("PRODOTTO INESISTENTE");
            return;
        }

        System.out.println("in che quantita?");


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

    /*
     * Menu per registrare un nuovo fornitore, richiede un nome in input
     */
    private void registraNuovoFornitore(){
        System.out.println("inserisci il nome");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            Fornitore f = new Fornitore(input);
            dBdaoFornitori.insert(f);
            //f.uploadToDB(connection);
        }
        catch (Exception e){
            System.err.println("ERRORE INSERIMENTO NOME");
        }
    }

    /*
     * Menu per aggiungere un nuovo prodotto, richiede l'id del fornitore, il nome del prodotto e il prezzo.
     * Se l'id non esiste, non registra il prodotto
     */
    private boolean registraNuovoProdotto(){

        int id_forn, prezzo;
        String nome;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.println("inserisci l'id fornitore");
            String input = reader.readLine();
            id_forn = Integer.parseInt(input);

            System.out.println("inserisci il nome del prodotto");
            nome = reader.readLine();

            System.out.println("inserisci il prezzo del prodotto");
            input = reader.readLine();
            prezzo = Integer.parseInt(input);
        }
        catch (Exception e){
            System.err.println("ERRORE INSERIMENTO VALORI");
            return false;
        }



        LinkedList<Fornitore> f = dBdaoFornitori.selectByID(id_forn);
        if (f.isEmpty()){
            System.err.println("FORNITORE INESISTENTE");
            return false;
        }


        Fornitore fornitore = f.getFirst(); //esiste solo un fornitore per id

        //loadtodb
        Prodotto p = new Prodotto(nome,fornitore,prezzo);
        dBdaoProdotti.insert(p);



        return true;
    }

    /*
    * dato un id, rimuove un prodotto
    * */
    private void rimuoviProdotto(){

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.println("inserisci l'id del prodotto");
            String input = reader.readLine();
            dBdaoProdotti.remove(Integer.parseInt(input));
        }
        catch (Exception e){
            System.err.println("ERRORE INSERIMENTO VALORE");
        }
    }

    /*
     * dato un id, rimuove un rifornitore,
     * i prodotti collegati al fornitore vengono rimossi a cascata come definito dal database
     * */
    private void rimuoviFornitore(){
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            System.out.println("inserisci l'id del fornitore");
            String input = reader.readLine();
            dBdaoFornitori.remove(Integer.parseInt(input));
        }
        catch (Exception e){
            System.err.println("ERRORE INSERIMENTO VALORE");
        }
    }

}


