/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dbms.ui;

/**
 *
 * @author jhony
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicFormExample extends Panel implements ActionListener {
    private JButton addButton;
    private JButton submitButton;
    private JPanel formPanel;
    private int numFields;

    public DynamicFormExample() {
       

        addButton = new JButton("Add Field");
        addButton.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(submitButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addTextField();
        } else if (e.getSource() == submitButton) {
            submitForm();
        }
    }
 
    private void addTextField() {
        numFields++;
        formPanel.add(new JLabel("Field " + numFields));
        formPanel.add(new JTextField(20));
        formPanel.revalidate();
        formPanel.repaint();
    }

    private void submitForm() {
        for (Component component : formPanel.getComponents()) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                System.out.println(textField.getText());
            }
        }
    }

    public static void main(String[] args) {
        new DynamicFormExample();
    }
}

