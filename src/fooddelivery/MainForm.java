/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author manut
 */
public class MainForm extends JFrame{
    JFrame frame;
    JLabel lb1;
    JButton btn1, btn2, btn3, btn4, btn5, btn6;
    JPanel pnlN, pnlW, pnlC;
    ImageIcon img;
    // Constuctor
    public MainForm(){
        // Create Frame
        frame = new JFrame();
        //frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create image
        img = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\logo2.png");
        // Creat Label
        lb1 = new JLabel("Get it hot. Get it fast. GetFood.", img, JLabel.CENTER);
        lb1.setFont(new Font("Arial", Font.BOLD, 30));
        // Create Button
        btn1 = new JButton("Products");
        btn2 = new JButton("Orders");
        btn3 = new JButton("Reports");
        btn4 = new JButton("Settting ");
        btn5 = new JButton("About");
        btn6 = new JButton("Log Out");
        // Create Panel
        pnlN = new JPanel();
        pnlN.setLayout(new BorderLayout());
        pnlN.setPreferredSize(new Dimension(0,100));
        pnlN.add(lb1, BorderLayout.WEST);
        
        pnlW = new JPanel();
        pnlW.setPreferredSize(new Dimension(150,0));
        pnlW.setLayout(new GridLayout(10,1));
        pnlW.add(btn1);
        pnlW.add(btn2);
        pnlW.add(btn3);
        pnlW.add(btn4);
        pnlW.add(btn5); 
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(btn6);
        
        // Center Home Page
        pnlC = new JPanel();
        pnlC.setLayout(new BorderLayout());
        
        JLabel head2Lb = new JLabel("Welcome Admin!", JLabel.CENTER);
        head2Lb.setFont(new Font("Arial", Font.BOLD, 25));
        head2Lb.setPreferredSize(new Dimension(0,50));
        
        JPanel pnlCC = new JPanel();
        pnlCC.setLayout(new GridLayout(1,3));
        pnlCC.setBackground(Color.red);
        JPanel pnlCCL = new JPanel();
        pnlCCL.setBackground(Color.orange);
        pnlCCL.setBorder(BorderFactory.createTitledBorder(""));
        JPanel pnlCCC = new JPanel();
        pnlCCC.setBackground(Color.CYAN);
        pnlCCC.setBorder(BorderFactory.createTitledBorder(""));
        JPanel pnlCCR = new JPanel();
        pnlCCR.setBackground(Color.YELLOW);
        pnlCCR.setBorder(BorderFactory.createTitledBorder(""));
        
        pnlCC.add(pnlCCL);
        pnlCC.add(pnlCCC);
        pnlCC.add(pnlCCR);        
                
        JPanel pnlCS = new JPanel();
        pnlCS.setBackground(Color.BLUE);
        pnlCS.setPreferredSize(new Dimension(0,380));
        
        pnlC.add(head2Lb, BorderLayout.NORTH);
        pnlC.add(pnlCC, BorderLayout.CENTER);
        pnlC.add(pnlCS, BorderLayout.SOUTH);          
        // Add to Frame
        frame.add(pnlN, BorderLayout.NORTH);
        frame.add(pnlW, BorderLayout.WEST);
        frame.add(pnlC, BorderLayout.CENTER);
        // Process
        // Show Frame
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
    }
}
