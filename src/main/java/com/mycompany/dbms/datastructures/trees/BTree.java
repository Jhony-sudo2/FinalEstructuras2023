/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.trees;
import com.mycompany.dbms.datastructures.list.SimpleList;
import com.mycompany.dbms.datastructures.table.Input;

/**
 *
 * @author jhony
 */
public class BTree {
    private Node Root = new NodeLeaf();
    protected final static int MAX_DEGREE = 5;
    
    public boolean Add(Object T){
        Node node = Search(T,Root);
        return Add(T,node);
        
    }
    
    public boolean Add(Object T,Node node){
        if(Search(T) == null){
            if(node.Add(T,1)){
                System.out.println("Elemento agregado Correctamente: " + T.toString());
            }else{
                System.out.println("Nodo lleno, se procede a hacer una division ya que se lleno con el valor: " + T.toString());
                SplitLeaf((NodeLeaf)node);
            }
            return true;
        }else return false;                    
    }
    
    public boolean Delete(Object T){
        NodeLeaf tmp = (NodeLeaf) Search(T,Root);
        boolean res = false;
        int x = 0;
        System.out.println("INDEX: " + tmp.getIndex());
        for (int i = 0; i < tmp.getIndex(); i++) {
            if(tmp.isEquals((Comparable)T.toString(), (Comparable)tmp.getValue()[i].toString())){
                res = true;
                x = i;
                break;
            }
        }
        if(res){
            tmp.getValue()[x] = null;
            tmp.Move(x,tmp.getValue());
            tmp.ReduceIndex();
            Check(tmp);
            return true;
        }else return false;
        
    }
    
    public void Check(Node tmp){
        if(!tmp.isMinimal() & tmp.getType() != TypeNode.ROOT){
                NodeBr Head = tmp.getDad();
                if(Head != null){
                    int indexReference = Head.getIndexNodeReference(tmp.getValue()[0].toString());
                    System.out.println("EL NODO NO CUMPLE CON EL MINIMO INDEX REFERENCE:" + indexReference);
                    if(!Lend(tmp,indexReference)) {
                        System.out.println("Haciendo una union");
                        Join(tmp,indexReference);
                        Check(Head);
                    }
                }
                
        }
    }
    
    public boolean Lend(Node node,int x){
        NodeBr Head = node.getDad();
        if(x!=0){
            if(Head.getReference()[x-1] !=null & Head.getReference()[x-1].canLend()){
                System.out.println("Prestando en la izquierda");
                NodeLeaf tmp = (NodeLeaf) Head.getReference()[x-1];
                node.getValue()[1] = node.getValue()[0];
                node.getValue()[0] = tmp.getValue()[tmp.getIndex()-1];
                node.setIndex();
                Head.getValue()[0] = tmp.getValue()[tmp.getIndex()-1];
                tmp.getValue()[tmp.getIndex()-1] = null;
                tmp.ReduceIndex();
                return true;
            }
        }
        if(x!=(Head.getIndexReference()-1)){
            if(Head.getReference()[x+1] != null & Head.getReference()[x+1].canLend()){
                if(Head.getReference()[x+1].canLend()){
                    System.out.println("Prestando en la derecha");
                    NodeLeaf tmp =(NodeLeaf) Head.getReference()[x+1];
                    node.getValue()[1] = tmp.getValue()[0];
                    tmp.getValue()[0] = null;
                    tmp.Move(0, tmp.getValue());
                    Head.getValue()[x] = tmp.getValue()[0];
                    tmp.ReduceIndex();
                    node.setIndex();
                    return true;
                }    
            }
        
        }
        return false;
    }
    
    
    public void Join(Node node,int x){
        NodeBr Head = node.getDad();
        Node tmp;
        if(x != 0){
            if(Head.getReference()[x-1] != null){
                tmp = Head.getReference()[x-1];
                Head.getValue()[Head.getIndex()-1] = null;
                Head.ReduceIndex();
                tmp.Add(node.getValue()[0], 1);
                Head.getReference()[Head.getIndexReference()-1] = null;
                Head.reduceReferenceIndex();
                if(tmp.getType() == TypeNode.LEAF) {
                    NodeLeaf tmp2 = (NodeLeaf) tmp;
                    tmp2.setNext(null);
                }
            }
        }
        if(x!=(Head.getIndexReference()-1)){
            if(Head.getReference()[x+1] != null){
                tmp = Head.getReference()[x+1];
                if(tmp.getType() == TypeNode.LEAF) tmp.Add(node.getValue()[x], 1);
                else tmp.Add(node.getValue()[x], 2);
                if(Head.getType() == TypeNode.ROOT){
                    if(Head.getIndex()-1 == 0){
                        node.Add(Head.getValue()[x], 2);
                        Head = null;
                        Root = node;
                        node.setType(TypeNode.ROOT);
                        AddReference((NodeBr)tmp,(NodeBr)node);
                    }
                }
                if(Head != null){
                    Head.getValue()[x] = null;
                    Head.ReduceIndex();
                    Head.Move(x,Head.getValue());
                    Head.getReference()[x] = null;
                    Head.reduceReferenceIndex();
                    Head.Move(x, Head.getReference());
                }
            }   
        }
    }
    
    public void AddReference(NodeBr n1,NodeBr n2){
        for (int i = 0; i < n1.getIndexReference()-1; i++) {
            n2.AddReference(n1.getReference()[i], n1.getReference()[i+1],n1.getValue()[i+1]);
        }
    }
    
    
    
    
    public Object Search(Object T){
        NodeLeaf tmp = (NodeLeaf) Search(T,Root);
        Object res = null;
        for (int i = 0; i < tmp.getIndex(); i++) {
            if(tmp.isEquals((Comparable)T.toString(), (Comparable)tmp.getValue()[i].toString())){
                res = tmp.getValue()[i];
                break;
            }
        }
        if(res != null)System.out.println("OBJETIVO ENCONTRADO");
        else System.out.println("NO SE ENCONTRO EL OBJETIVO");
        return res;
 
    }
    
    public SimpleList SearchByTag(int index,String Value){
        SimpleList List = new SimpleList();
        NodeLeaf tmp = NodeInitial();
        while(tmp != null){
            for (int i = 0; i < tmp.getIndex(); i++) {
                Input tmp2 = (Input) tmp.getValue()[i];
                String Dato = tmp2.getData()[index];
                if(Dato.equals(Value)) {
                    List.Add(tmp2);
                }
            }
            tmp = tmp.getNext();
        }
        return List;
    
    }
    
    public Node Search(Object T,Node node){
        if(node.getType() == TypeNode.LEAF){
            return node;
        }else{
            int x = 0;
            int indexNode = 0;
            NodeBr tmp = (NodeBr) node;
            Comparable Key2;
            Comparable Key1;
            Comparable Ktmp;
            if(tmp.isInt(T.toString())){
                Key2 = (Comparable) tmp.getValue()[x];
                Key1 = (Comparable) Integer.valueOf(T.toString());
                Ktmp = (Comparable) tmp.getValue()[node.getIndex()-1];
            }else{
                Key2 = tmp.getValue()[x].toString();
                Key1 = T.toString();
                Ktmp = tmp.getValue()[node.getIndex()-1].toString();
            }
            if(node.isLess(Key1, Key2) & !node.isEquals(Key1, Key2)){
                return Search(T,tmp.getReference()[0]);
            }else if((node.isOlder(Key1, Key2)  | node.isEquals(Key1, Key2)) & tmp.getIndex()<=1){
                return Search(T,tmp.getReference()[1]);
            }else if(node.isOlder(Key1, Ktmp) | node.isEquals(Key1, Ktmp)){
                return Search(T,tmp.getReference()[tmp.getIndexReference()-1]);
            }
            
            
            Comparable Key3 = tmp.getValue()[x+1].toString();
            if(tmp.isInt(T.toString())) Key3 = (Comparable) tmp.getValue()[x+1];
            indexNode++;
            while(x<(MAX_DEGREE-1) & Key2!=null & Key3 !=null){
                if((node.isOlder(Key1, Key2) | node.isEquals(Key1, Key2)) & node.isLess(Key1, Key3)){
                    return Search(T,tmp.getReference()[indexNode]);
                }else{
                    x++;
                    indexNode++;
                    if(!tmp.isInt(T.toString())){
                        Key2 = tmp.getValue()[x].toString();
                        Key3 = tmp.getValue()[x+1].toString();
                    }else{
                        Key2 = (Comparable) tmp.getValue()[x];
                        Key3 = (Comparable) tmp.getValue()[x+1];
                    }
                }
            }
            indexNode++;
            return Search(T,tmp.getReference()[indexNode]);
            
        
        }
        
    }
    
    
    
    public void SplitLeaf(NodeLeaf T){
        if(T.getDad() == null){
             NodeBr newNode = new NodeBr();
             NodeLeaf n1 = T.SplitLeft();
             NodeLeaf n2 = T.SplitRight();
             newNode.AddReference(n1, n2, T.getNodeJump());
             Root = newNode;
             newNode.setType(TypeNode.ROOT);
        }else{
            NodeBr Head = T.getDad();
            NodeLeaf n1 = T.SplitLeft();
            NodeLeaf n2 = T.SplitRight();
            Head.AddReference(n1, n2, T.getNodeJump());
            if(Head.isReferenceFull()) SplitBranch(Head);
        }
    }
    
    public void SplitBranch(NodeBr node){
        if(node.getDad() == null){
            NodeBr newNode = new NodeBr();
            NodeBr n1 = node.SplitLeft();
            NodeBr n2 = node.SplitRight();
            newNode.AddReference(n1, n2, node.getNodeJump());
            Root = newNode;
            newNode.setType(TypeNode.ROOT);
        }else{
            NodeBr Head = node.getDad();
            NodeBr n1 = node.SplitLeft();
            NodeBr n2 = node.SplitRight();
            Head.AddReference(n1, n2, node.getNodeJump());
            if(Head.isReferenceFull()) SplitBranch(Head);
        }
    }
    
    
    
    public void Print(Node tmp){
        NodeLeaf output = null;
        if(tmp.getType() == TypeNode.BRANCH | tmp.getType() == TypeNode.ROOT){
            NodeBr B = (NodeBr) tmp; 
            while(tmp.getType() != TypeNode.LEAF){
                if(B.getReference()[0].getType() != TypeNode.LEAF){
                    B = (NodeBr) B.getReference()[0];
                }else tmp = B.getReference()[0];
            }
        }
        output = (NodeLeaf) tmp;
        int x=1;
        while(output!= null){
            System.out.println("NODO:" + x);
            output.Print();
            output = output.getNext();
            x++;
        }
    }
    
    public NodeLeaf NodeInitial(){
        Node tmp = Root;
        NodeLeaf output = null;
        if(tmp.getType() == TypeNode.BRANCH | tmp.getType() == TypeNode.ROOT){
            NodeBr B = (NodeBr) tmp; 
            while(tmp.getType() != TypeNode.LEAF){
                if(B.getReference()[0].getType() != TypeNode.LEAF){
                    B = (NodeBr) B.getReference()[0];
                }else tmp = B.getReference()[0];
            }
        }
        output = (NodeLeaf) tmp;
        return output;
    }
    
    public Node getRoot(){
        return Root;
    }
    
    
}
