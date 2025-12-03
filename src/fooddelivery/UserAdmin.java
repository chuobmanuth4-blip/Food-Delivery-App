/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author manut
 */
public class UserAdmin extends JFrame{
    Container pane;
    JLabel lbHeader, lbSearch;
    JTextField txtSearch;
    JButton btnSearch;
    ImageIcon imgUserM, imgSearch;
    JPanel pnlN, pnlC, pnlS;
    public UserAdmin(){
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.red);
        pane.setVisible(true);
        // Create Image
        imgUserM = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\userMangement.png");
        imgSearch = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\search.png");
        
        // Create Label
        lbHeader = new JLabel("User Management",imgUserM, JLabel.CENTER);
        lbHeader.setFont(new Font("Arial", Font.BOLD, 30));
        lbHeader.setBounds(460, 5, 350, 70);
        
        lbSearch = new JLabel("Search:");
        lbSearch.setFont(new Font("Arial", Font.BOLD, 16));
        lbSearch.setBounds(200, 95, 100, 30);
        // Create TextField
        txtSearch = new JTextField();
        txtSearch.setBounds(270, 85, 690, 50);
        // Create Button
        btnSearch = new JButton("Search", imgSearch);
        btnSearch.setBounds(960, 85, 100, 50);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.CYAN);
        // Create Panel
        pnlN = new JPanel();
        pnlN.setLayout(null);
        pnlN.setBackground(Color.ORANGE);
        pnlN.setPreferredSize(new Dimension(0,150));
                
        pnlN.add(lbHeader);  
        pnlN.add(lbSearch);
        pnlN.add(txtSearch);
        pnlN.add(btnSearch);
        
        pnlC = new JPanel();
        pnlC.setBackground(Color.DARK_GRAY);
        
        pnlS = new JPanel();
        pnlS.setBackground(Color.YELLOW);
        pnlS.setPreferredSize(new Dimension(0,200));
        
        // Add Components to Frame
        pane.add(pnlN, BorderLayout.NORTH);
        pane.add(pnlC, BorderLayout.CENTER);
        pane.add(pnlS, BorderLayout.SOUTH);        
    }
    public Container getPane(){
        return pane;
    }
}
