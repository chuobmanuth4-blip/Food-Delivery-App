/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author manut
 */
public class OrderFood extends JFrame {
    Container pane;
    JPanel Npnl, Cpnl, Spnl, CSpnl, SEpnl;
    public OrderFood(){
        // Create
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        // Create Panel
        Npnl = new JPanel();
        Npnl.setLayout(new GridLayout(1,5));
        Npnl.setPreferredSize(new Dimension(0,80));
        Npnl.setBorder(BorderFactory.createTitledBorder("Categories"));
        Npnl.add(new JButton("Fast food"));
        Npnl.add(new JButton("Khmer food"));
        Npnl.add(new JButton("Chinese food"));
        Npnl.add(new JButton("Dessert"));
        Npnl.add(new JButton("Drinks"));
        
        CSpnl = new JPanel();
        CSpnl.add(new JButton("Add to Carts"));
        Cpnl = new JPanel();
        Cpnl.setBorder(BorderFactory.createTitledBorder("Dishes"));
        Cpnl.setLayout(new BorderLayout());
        Cpnl.add(CSpnl, BorderLayout.SOUTH);
        
        SEpnl = new JPanel();
        SEpnl.setPreferredSize(new Dimension(100,0));
        SEpnl.setLayout(new GridLayout(4,1));
        SEpnl.add(new JButton("Order"));
        SEpnl.add(new JButton("Update"));
        SEpnl.add(new JButton("Delete"));
        SEpnl.add(new JButton("Clear")); 
        
        Spnl = new JPanel();
        Spnl.setLayout(new BorderLayout());
        Spnl.setPreferredSize(new Dimension(0,200));
        Spnl.setBorder(BorderFactory.createTitledBorder("Carts"));
        Spnl.add(SEpnl, BorderLayout.EAST);
        // Add 
        pane.add(Npnl, BorderLayout.NORTH);
        pane.add(Cpnl, BorderLayout.CENTER);
        pane.add(Spnl, BorderLayout.SOUTH);        
        // Show
        pane.setVisible(true);
    }
    public Container getPane(){
        return pane;
    }
}
