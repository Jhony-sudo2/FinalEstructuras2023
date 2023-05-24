/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.table;

import com.mycompany.dbms.datastructures.trees.BTree;
import com.mycompany.dbms.datastructures.list.*;
/**
 *
 * @author jhony
 */
public class Table {
    private String Name;
    private String PrimaryKey;
    private int IndexPKey;
    private int NumberColumns;
    private BTree Records = new BTree();
    private SimpleList Columns;
    
    
    public Table(String Name,String PrimaryKey,SimpleList nRecords,int IndexPKey){
        this.IndexPKey = IndexPKey;
        this.Columns = nRecords;
        this.Name = Name;
        this.PrimaryKey = PrimaryKey;
        this.NumberColumns = Columns.getIndex();
    }
    
    public Table(String Name,String PrimaryKey,SimpleList nRecords){
        this.Columns = nRecords;
        this.Name = Name;
        this.PrimaryKey = PrimaryKey;
        this.NumberColumns = Columns.getIndex();
        this.IndexPKey = nRecords.getIndex(PrimaryKey);
        System.out.println("VALOR INDEX: " + IndexPKey);
    }
    
    public boolean Add(Input input){
        return Records.Add(input);
    }

    public int getIndexPKey() {
        return IndexPKey;
    }
    
    public int getNumberColumns(){
        return NumberColumns;
    }

    public void setIndexPKey(int IndexPKey) {
        this.IndexPKey = IndexPKey;
    }
    
    

    public String getName() {
        return Name;
    }

    public BTree getRecords() {
        return Records;
    }

    public void setRecords(BTree Records) {
        this.Records = Records;
    }

    public SimpleList getColumns() {
        return Columns;
    }

    public void setColumns(SimpleList Columns) {
        this.Columns = Columns;
    }

    public String getPrimaryKey() {
        return PrimaryKey;
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
    
    
    
    
}
