package com.unitn.zaffino.lingProg.grafico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.LinkedList;

public class Main {


    public static Connection connectToDB(String url, String user, String password){
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        return c;
    }

    public static void main(String[] args) {

        String url = null;
        String user = null;
        String password = null;
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            System.out.println("inserisci url del DB:");
            url = reader.readLine();
            System.out.println("inserisci username:");
            user = reader.readLine();
            System.out.println("inserisci password:");
            password = reader.readLine();
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }


        Console console = new Console(connectToDB(url,
                user, password));

    }
}

/*
 * TODO:
 *  
 * */