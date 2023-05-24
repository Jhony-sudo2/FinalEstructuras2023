/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.trees;

import com.mycompany.dbms.datastructures.table.Table;

/**
 *
 * @author jhony
 */
public class NodeLeaf extends Node{
    private NodeLeaf Next;
    
    public NodeLeaf(){
        this.setType(TypeNode.LEAF);
    }
    
    public void Print(){
        for (int i = 0; i < this.getValue().length; i++) {
            if(this.getValue()[i] != null){
                String t = this.getValue()[i].toString();
                System.out.println("VALUE: "  + t);
            }
            else break;
        }
    }
    
    public void setNext(NodeLeaf Next){
        this.Next = Next;
    }

    public NodeLeaf getNext() {
        return Next;
    }
    
    public NodeLeaf SplitLeft(){
        NodeLeaf newNode = new NodeLeaf();
        for (int i = 0; i < (BTree.MAX_DEGREE/2); i++) {
            newNode.getValue()[i] = this.getValue()[i];
            newNode.setIndex();
        }
        return newNode;
    }
    
    public NodeLeaf SplitRight(){
        NodeLeaf newNode = new NodeLeaf();
        int x=0;
        for (int i = (BTree.MAX_DEGREE/2); i < BTree.MAX_DEGREE; i++,x++) {
            newNode.getValue()[x] = this.getValue()[i];
            newNode.setIndex();
        }
        return newNode;
    }
    
    
    
    
    
    
   

    
}
