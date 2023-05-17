package com.unitn.zaffino.lingProg.grafico;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBdaoProdotti implements DBdao<Prodotto>{
    Connection c;


    public DBdaoProdotti(Connection c){
        this.c = c;
    }

    @Override
    public LinkedList<Prodotto> selectAll() {

        LinkedList<Fornitore> listaFornitori = new DBdaoFornitori(c).selectAll();
        //LinkedList<Fornitore> listaFornitori = DatabaseDAO.getAllFornitori(c);
        LinkedList<Prodotto> listaProdotti = new LinkedList<>();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql;
            ResultSet res;
            sql = "SELECT * FROM prodotto;";
            res = stmt.executeQuery(sql);

            while (res.next()){
                int prod_id = res.getInt("id_prodotto");
                int forn_id = res.getInt("id_fornitore");
                int prezzo = res.getInt("prezzo");
                String nome = res.getString("nome");

                Fornitore thisFornitore = null;
                for (Fornitore f: listaFornitori) {
                    if (f.getId() == forn_id) thisFornitore = f;
                }

                Prodotto p = new Prodotto(nome, prod_id, thisFornitore, prezzo);
                listaProdotti.add(p);
            }
            stmt.close();

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return listaProdotti;
    }

    @Override
    public boolean insert(Prodotto p) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "insert into prodotto (nome,prezzo, id_fornitore) " +
                    "values ('"+ p.getNome() +"','" + p.getPrezzo() +"','"  + p.getFornitore().getId() + "')";
            stmt.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(int p) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "delete from prodotto where id_prodotto = " + p +";";
            stmt.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public LinkedList<Prodotto> selectByID(int id) {
        return null;
    }
}
