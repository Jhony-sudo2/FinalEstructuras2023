/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.datastructures.trees;

/**
 *
 * @author jhony
 */
public class QuickSort {
    
    public static void sort(Node[] arr,int index) {
        quicksort(arr, 0,index);
    }
    
    private static void quicksort(Node[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quicksort(arr, low, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partition(Node[] arr, int low, int high) {
        Node pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].isLess((Comparable)arr[j].toString(),(Comparable)pivot.toString())) {
                i++;
                Object temp = arr[i];
                arr[i] = arr[j];
                arr[j] = (Node) temp;
            }
        }
        Object temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = (Node) temp;
        return i+1;
    }
}
