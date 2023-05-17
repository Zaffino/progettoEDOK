package com.unitn.zaffino.lingProg.grafico;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBdaoFornitori implements DBdao<Fornitore>{

    Connection c;

    public DBdaoFornitori(Connection c){
        this.c = c;
    }

    @Override
    public LinkedList<Fornitore> selectAll() {
        LinkedList<Fornitore> listaFornitori = new LinkedList<>();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql;
            ResultSet res;
            sql = "SELECT * FROM fornitore;";
            res = stmt.executeQuery(sql);
            while (res.next()){
                int id = res.getInt("id_fornitore");
                String nome = res.getString("nome");
                Fornitore f = new Fornitore(id, nome);
                listaFornitori.add(f);
            }
            stmt.close();

        } catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        return listaFornitori;
    }

    @Override
    public boolean insert(Fornitore f) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "insert into fornitore (nome) values ('"+ f.getNome() +"')";
            stmt.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean remove(int f) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "delete from fornitore where id_fornitore = " + f +";";
            stmt.executeUpdate(sql);

        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public LinkedList<Fornitore> selectByID(int id) {
        LinkedList<Fornitore> fornitori = new LinkedList<>();
        Statement stmt = null;
        ResultSet res;
        try {
            stmt = c.createStatement();
            String sql = "SELECT FROM fornitore where id_fornitore = " + id +";";
            res = stmt.executeQuery(sql);
            while (res.next()){
                int itsId = res.getInt("id_fornitore");
                String nome = res.getString("nome");
                Fornitore f = new Fornitore(itsId, nome);
                fornitori.add(f);
            }
            stmt.close();


        }catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());

        }
        return fornitori;
    }
}
