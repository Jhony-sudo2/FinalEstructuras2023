/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.trees;

/**
 *
 * @author jhony
 */
public class NodeBr extends Node{
    private static int Max = BTree.MAX_DEGREE;
    private Node[] Reference = new Node[Max+1];
    private int IndexReference =0;
    public NodeBr(){
      this.setType(TypeNode.BRANCH);
    }
    
    public Node[] getReference(){
        return Reference;
    }
    
    
    public void AddReference(Node n1,Node n2,Object Value){
        if(IndexReference != 0){
            IndexReference--;
            if(!n1.isEquals((Comparable)n1.getValue()[0].toString(),(Comparable)Reference[IndexReference].getValue()[0].toString())){
                int tmp = getIndexNodeReference(n1.getValue()[0].toString());
                IndexReference++;
                for (int i = IndexReference; i > tmp; i--) {
                    Reference[i] = Reference[i-1];
                }
                Reference[tmp] = n1;
                Reference[tmp+1] = n2;
                IndexReference++;
            }else{
                Reference[IndexReference] = n1;
                IndexReference++;
                Reference[IndexReference] = n2;
                IndexReference++;       
            }
        }else{
            Reference[IndexReference] = n1;
            IndexReference++;
            Reference[IndexReference] = n2;
            IndexReference++;
        }
        
        n1.setDad(this);
        n2.setDad(this);
        this.Add(Value,2);
        if(n1.getType() == TypeNode.LEAF){
            for (int i = 0; i <IndexReference; i++) {
                if(i > 4 ) break;
                NodeLeaf tmp1,tmp2;
                tmp1 = (NodeLeaf) Reference[i];
                tmp2 = (NodeLeaf) Reference[i+1];
                tmp1.setNext(tmp2);        
                
            }                                                      
        }
     }
    
    
     public NodeBr SplitLeft(){
        NodeBr newNode = new NodeBr();
        for (int i = 0; i < (Reference.length/2); i++) {
            newNode.Reference[i] = Reference[i];
            Reference[i].setDad(newNode);
            newNode.setIndexReference();
        }
         for (int i = 0; i < (Max/2); i++) {
             newNode.getValue()[i] = this.getValue()[i];
             newNode.setIndex();
         }
        return newNode;
    }
    
    public NodeBr SplitRight(){
        NodeBr newNode = new NodeBr();
        int x=0;
        for (int i = (Reference.length/2); i < Reference.length; i++,x++) {
            newNode.getReference()[x] = Reference[i];
            Reference[i].setDad(newNode);            
            newNode.setIndexReference();
        }
        x=0;
        for (int i = (Max/2)+1; i < Max; i++,x++) {
            newNode.getValue()[x] = this.getValue()[i];
            newNode.setIndex();
        }
        return newNode;
    }

    public int getIndexReference() {
        return IndexReference;
    }
    
    public int getIndexNodeReference(Object T){
        int x = 0;
        for (int i = 0; i <IndexReference+1; i++) {
            if(this.isEquals((Comparable)T, Reference[i].getValue()[0].toString())){
                return x;
            }else x++;
        }
        return x;
    }

    public void setIndexReference() {
        IndexReference++;
    }
    public void reduceReferenceIndex(){
        IndexReference--;
    }
    
    public boolean isReferenceFull(){
        if(IndexReference > Max)return true;
        else return false;
    }
    
    public void Print(){
        System.out.println("------------------------------");
        for (int i = 0; i < this.getValue().length; i++) {
            if(this.getValue()[i] != null){
                System.out.println("VALOR: " + this.getValue()[i].toString());
            }
        }
    
    }
    
    public void MoveReference(int x){
        for (int i = 0; i < 10; i++) {
            
        }
    }
    
    
    
    
    
    
    
    
    
    
}
