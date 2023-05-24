/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.table;

/**
 *
 * @author jhony
 */
public class Register {
    private String Name;
    private String Type;
    public Register(String Nombre, String Tipo) {
        this.Name = Nombre;
        this.Type = Tipo;
    }

    public String getNombre() {
        return Name;
    }

    public String getTipo() {
        return Type;
    }

    
    @Override
    public String toString(){
        return "Name: " + Name + " Tipo" + Type ;
    }
    
    
    
    
}
