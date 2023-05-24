/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.trees;


/**
 *
 * @author jhony
 */
public abstract class Node<T extends Comparable<T>> {
    private NodeBr Dad = null;
    private TypeNode Type;
    private int Index = 0;
    private Object[] Value = new Object[BTree.MAX_DEGREE];
    
    public boolean isLess(T Key1,T Key2){
        int tmp = Key1.compareTo(Key2);
        if (tmp <= 0) return true;
        else return false;
    }
    
    public boolean isOlder(T Key1,T Key2){
        int tmp = Key1.compareTo(Key2);
        if(tmp > 0) return true;
        else return false;
    }
    
    public boolean isEquals(T Key1,T Key2){
        int tmp = Key1.compareTo(Key2);
        if(tmp == 0)return true;
        else return false;
        
    }
    
    public void Move(int x,Object[] arr){
        for (int i = x; i < arr.length-1; i++) {
            arr[i] = arr[i+1];
        }
    }
    
    public boolean isFull(){
        if (Index > (BTree.MAX_DEGREE-1)) return true;
        else return false;
    }
    
    public boolean Add(Object input,int type){
        Object tmp;
        if(type == 1)tmp = input;
        else{
            if(isInt(input.toString())) tmp = Integer.valueOf(input.toString());
            else tmp = input.toString();
        }
        if(this.getIndex() != 0){
            T T;
            if(isInt(input.toString())){
                T = (T) Integer.valueOf(input.toString());
            }else T = (T) input.toString();
            for (int i = 0; i < this.getIndex()+1; i++) {
                if(Value[i] != null){
                    Object Compare;
                    if(this.isInt(Value[i].toString())){
                        Compare = Integer.valueOf(Value[i].toString());
                    }else Compare =  Value[i].toString();
                    if(isLess(T, (T) Compare) | isEquals(T, (T) Compare)){
                        Adjust(i,tmp);
                        break;
                    }
                }else {
                    Value[i] = tmp;
                    break;
                }
            }
        }else Value[0] = tmp;
        this.setIndex();
            
        return !isFull();
    }
    
    public boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    
    
    
    public int convertInt(String str){
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException e){
            return 0;
        }
    }
    
    public Object getNodeJump(){
        return Value[(BTree.MAX_DEGREE/2)];
    }
    
    public void Adjust(int index,Object T){
        Object tmp = T;
        Object Change = T;
        while(index < Value.length){
             Change = tmp;
             if(Value[index] != null){
                tmp = Value[index];
                Value[index] = Change;
             }else{
                 Value[index] = Change;
                 break;
             }
             index++;
        }
    }
    
    
    
    
    public TypeNode getType() {
        return Type;
    }

    public void setType(TypeNode Type) {
        this.Type = Type;
    }

    public NodeBr getDad() {
        return Dad;
    }

    public void setDad(NodeBr Dad) {
        this.Dad = Dad;
    }

    public int getIndex() {
        return Index;
    }
    
    public void ReduceIndex(){
        Index--;
    }
    public void setIndex() {
        Index++;
    }

    public Object[] getValue(){
        return Value;
    }
    
    public boolean isMinimal(){
        if(Index>=2) return true;
        else return false;
    }
    
    public boolean canLend(){
        if(Index>2) return true;
        else return false;
    }
    

}
