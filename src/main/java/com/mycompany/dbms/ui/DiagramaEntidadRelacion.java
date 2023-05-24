/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.ui;

/**
 *
 * @author jhony
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DiagramaEntidadRelacion extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja los rectángulos para las entidades
        g.setColor(Color.WHITE);
        g.fillRect(100, 100, 200, 100);
        g.fillRect(400, 100, 200, 100);

        // Dibuja los nombres de las entidades
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Estudiantes", 150, 150);
        g.drawString("Curso", 450, 150);

        // Dibuja los atributos de la entidad Estudiantes
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("- Nombre", 130, 180);
        g.drawString("- Edad", 130, 200);
        g.drawString("- Curso", 130, 220);
        
        //atributos para Curso
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("- Nombre", 430, 180);
        g.drawString("- Codigo", 430, 200);
        g.drawString("- Catedratico", 430, 220);
        

        // Dibuja las líneas para las relaciones
        g.drawLine(200, 150, 400, 150);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Diagrama de Entidad-Relación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DiagramaEntidadRelacion diagrama = new DiagramaEntidadRelacion();
        frame.getContentPane().add(diagrama);

        frame.setSize(700, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}



