package com.unitn.zaffino.lingProg.grafico;

import java.util.LinkedList;
import java.util.List;

public interface DBdao<T> {


    public LinkedList<T> selectAll();

    public boolean insert(T x);

    public boolean remove(int x);

    public LinkedList<T> selectByID(int id);

}