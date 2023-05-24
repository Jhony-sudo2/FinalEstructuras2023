/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.list;

import com.mycompany.dbms.datastructures.table.Register;

/**
 *
 * @author jhony
 */
public class SimpleList {
    
    private Node Root;
    private int index = 0;
    
    public SimpleList(){
        this.Root = null;
    }
    
    public void Add(Node T){
        if(Root == null){
            Root = T;
            index++;
        }else{
            Node tmp = Root;
            while(tmp.getNext() != null){
                tmp = tmp.getNext();
            }
            tmp.setNext(T);
            index++;
            
        }
    }
    
    public void Add(Object T){
        Node newNode = new Node(T);
        Add(newNode);
    }
    
    public int getIndex(String Search){
        int x = 0;
        Node tmp = Root;
        while(tmp != null){
            Register tmp2 = (Register) tmp.getValue();
            if(tmp2.getNombre().equals(Search)){
               return x;
            }else{
                tmp = tmp.getNext();
                x++;
            }
        }
        return x;
    }
    
    
    
    public Node getRoot(){
        return Root;
    }
    
    public int getIndex(){
        return index;
    }
    
}
