package com.unitn.zaffino.lingProg.grafico;

import java.util.LinkedList;
import java.util.List;

/*
* Interfaccia per le operazioni base col database
* */
public interface DBdao<T> {

    /*
    * metodo che quando implementato prende tutti gli elementi di una table
    * */
    public LinkedList<T> selectAll();


    /*
    * metodo che quando implementato inserisce un oggetto in una table
    * */
    public boolean insert(T x);

    /*
    * metodo che quando implementato, dato un ID, rimuove un oggetto
    * */
    public boolean remove(int x);

    /*
    * metodo che quando implementato, dato un ID, restituisce una lista di oggetti,
    * nel caso del nostro db torna solo una lista vuota o con un solo elemento
    * */
    public LinkedList<T> selectByID(int id);

}