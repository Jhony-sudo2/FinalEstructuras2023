/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.list;

/**
 *
 * @author jhony
 */
public class Node {
    private Node Next;
    private Object Value;
    
    public Node(Object Value){
        this.Value = Value;
    }
    
    public Node getNext() {
        return Next;
    }

    public void setNext(Node Next) {
        this.Next = Next;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object Value) {
        this.Value = Value;
    }
    
    
    
}
