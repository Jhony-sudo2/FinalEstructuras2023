/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.table;

/**
 *
 * @author jhony
 */
public class Input {
    private int PKey;
    private String[] Data;
    
    public Input(int n,int PKey){
        this.Data = new String[n];
        this.PKey = PKey;
    }
    
    public String[] getData(){
        return Data;
    }
    
    @Override
    public String toString(){
        return Data[PKey];
    }
    
}
