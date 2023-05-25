/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.files;

import com.mycompany.dbms.datastructures.list.Node;
import com.mycompany.dbms.datastructures.list.SimpleList;
import com.mycompany.dbms.datastructures.table.Input;
import com.mycompany.dbms.datastructures.table.Register;
import com.mycompany.dbms.datastructures.table.Table;
import com.mycompany.dbms.datastructures.table.Validator;
import com.mycompany.dbms.datastructures.trees.BTree;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author jhony
 */
public class Xml {
    
    public static void Read(File Archivo,BTree Tree,DefaultComboBoxModel modeloTabla,JTextArea Campo){
        try {
            String Message = "";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(Archivo);
            doc.getDocumentElement().normalize();
            String Root = doc.getDocumentElement().getNodeName();
            NodeList nList = doc.getElementsByTagName("*");
            
            if(Root.equals("estructuras")){
                String Name = "";
                String PKey = "";
                int isRegister = 0;
                Table T;
                SimpleList Records = new SimpleList();
                for (int i = 0; i < nList.getLength(); i++) {
                    Element element = (Element) nList.item(i);
                    String Tag = element.getTagName();
                    if(!Tag.equals("estructuras") & !Tag.equals("estructura")){
                        String Tag2 = element.getTagName();
                        String Content = element.getTextContent();
                        if(Tag2.equals("tabla")){
                            Name = Content;
                        }else if(Tag2.equals("clave")){
                            PKey = Content;
                        }else if(Tag2.equals("relacion")){
                            isRegister = 1;
                        }else if(isRegister == 0){
                            Tag2 = DeleteSpaces(Tag2);
                            Content = DeleteSpaces(Content);
                            Register n = new Register(Tag2,Content);
                            Records.Add(new Node(n));
                        }
                        if(i == nList.getLength() - 1){
                            Name =DeleteSpaces(Name);
                            PKey = DeleteSpaces(PKey);
                            T = new Table(Name,PKey,Records);
                            if(!Tree.Add(T)) Message += "\nYa existe una tabla con el nombre: " + Name;
                            else modeloTabla.addElement(Name);
                            Records = new SimpleList();
                            T = null;
                        }
                    }else if(!Tag.equals("estructuras") & i !=1){
                        Name = DeleteSpaces(Name);
                        PKey = DeleteSpaces(PKey);
                        T = new Table(Name,PKey,Records);
                        if(!Tree.Add(T)) Message += "\nYa existe una tabla con el nombre: " + Name;
                        else modeloTabla.addElement(Name);
                        
                        Records = new SimpleList();
                        T = null;
                    }
                    
                }
            }else{
                Table T;
                String NameFile = Archivo.getName();
                if(NameFile.equals("eliminar.dat")){
                    System.out.println("ELIMINAR");
                    Element element = (Element) nList.item(1);
                    T = (Table) Tree.Search(element.getTagName());
                    if(T!= null){
                        BTree Records;
                        for (int i = 1; i < nList.getLength(); i++) {
                            element = (Element) nList.item(i);
                            String Tag2 = element.getTagName();
                            String Content = element.getTextContent();
                            if(i%2 != 0) T = (Table) Tree.Search(Tag2);
                            else{
                                Records = T.getRecords();
                                DeleteSpaces(Content);
                                System.out.println("Eliminando: " + Content);
                                Records.Delete(Content);
                            }
                        }
                    }else Message += "\nTABLA NO ENCONTRADA";
                }else if(NameFile.equals("entrada.dat")){
                    //  AGREGAR DATO
                    System.out.println("AGREGAR DATO");
                    Element element = (Element) nList.item(1);
                    T = (Table) Tree.Search(element.getTagName());
                    if(T!= null){
                        Input Data = new Input(T.getNumberColumns(),T.getIndexPKey());
                        int x = 0;
                        for (int i = 1; i < nList.getLength(); i++) {
                            element = (Element) nList.item(i);
                            String Tag2 = element.getTagName();
                            String Content = element.getTextContent();
                            if(Content.contains("\n")) {
                                if(i!=1) T.Add(Data);
                                T = (Table) Tree.Search(element.getTagName());
                                Data = new Input(T.getNumberColumns(),T.getIndexPKey());
                                x = 0;
                            }else{
                                Content = DeleteSpaces(Content);
                                Data.getData()[x] = Content;
                                x++;
                            }
                        }
                        if(Validator.Compare(Data,T.getColumns()))T.Add(Data);
                        else Message += "\nLas entradas de los elementos no coinciden con el tipo de dato";
                    }else Message += "\n Tabla no encontrada";
                }
            }
            Campo.setText(Message);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String DeleteSpaces(String Input){
        String tmp = Input.replaceAll("\\s+", "");
        return tmp;
    }
}

