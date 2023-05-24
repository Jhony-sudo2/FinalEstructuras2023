/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.table;

import com.mycompany.dbms.datastructures.list.Node;
import com.mycompany.dbms.datastructures.list.SimpleList;

/**
 *
 * @author jhony
 */
public class Validator {
    
    public static boolean Compare(Input input,SimpleList Records){
        Node tmp = Records.getRoot();
        for (int i = 0; i <input.getData().length; i++) {
            Register tmp2 = (Register) tmp.getValue();
            if(Compare(input.getData()[i],tmp2.getTipo())) tmp = tmp.getNext();
            else return false;
        }
        return true;
    }
    
    public static boolean Compare(String Value,String Type){
        int T = getType(Type);
        switch(T){
            case 1:
                return isInt(Value);
            case 2:
                return isString(Value);
            case 3:
                return isChar(Value);
            case 4:
                return isDouble(Value);
            case 5:
                return isBoolean(Value);
            default:
                return false;
        }
        
    }
    
    
    public static boolean isInt(String Value){
        try {
            int numero = Integer.parseInt(Value);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("El String no representa un número double válido.");
            return false;
        }
    }
    
    public static boolean isString(String Value){
        return true;
    }
    
    
    public static boolean isDouble(String Value){
        try {
            double numero = Double.parseDouble(Value);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("El String no representa un número double válido.");
            return false;
        }
    }
    
    
    public static boolean isChar(String Value){
        if(Value.length() == 1)return true;
        else return false;
        
    }
    
    public static boolean isBoolean(String Value){
        if(Value.equals("true") | Value.equals("True")) return true;
        else if(Value.equals("false" )|Value.equals("False")) return true;
        else if(Value.equals("0" )| Value.equals("1")) return true;
        else return false;
    
    }
    
    public static int getType(String T){
        int x = 0;
        switch(T){
            case "int":
                return 1;
            case "string":
                return 2;
            case "char":
                return 3;
            case "double":
                return 4;
            case "boolean":
                return 5;
                    
        }
        return x;
    }
    
    
}
