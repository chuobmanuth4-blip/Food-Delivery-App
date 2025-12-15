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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author manut
 */
public class UserForm{
    public int userID; 
    OrderUser order;
    JFrame frame;
    JLabel lb1;
    JButton btn1, btn2, btn3, btn4;
    JPanel pnlN, pnlW, pnlC;
    ImageIcon img;
    // Constuctor
    public UserForm(int userID){
        this.userID = userID;
        // Create Frame
        frame = new JFrame();
        frame.setSize(400,400);
        frame.setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create image
        img = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\logo2.png");
        // Creat Label
        lb1 = new JLabel("Get it hot. Get it fast. GetFood.", img, JLabel.CENTER);
        lb1.setFont(new Font("Arial", Font.BOLD, 30));
        // Create Button
        btn1 = new JButton("Order");
        btn2 = new JButton("Profile");
        btn3 = new JButton("About");        
        btn4 = new JButton("Log Out");
        // Create Panel
        pnlN = new JPanel();
        pnlN.setLayout(new BorderLayout());
        pnlN.setBackground(Color.WHITE);
        pnlN.setPreferredSize(new Dimension(0,100));
        pnlN.add(lb1, BorderLayout.WEST);
        
        pnlW = new JPanel();
        pnlW.setPreferredSize(new Dimension(150,0));
        pnlW.setLayout(new GridLayout(10,1));
        pnlW.add(btn1);
        pnlW.add(btn2);
        pnlW.add(btn3);        
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));        
        pnlW.add(btn4);
        
        pnlC = new JPanel();
        pnlC.setBackground(Color.black);
        pnlC.setLayout(new BorderLayout());
        // Process
        btn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                order = new OrderUser(userID);
                pnlC.removeAll();
                pnlC.revalidate();
                pnlC.repaint();
                pnlC.add(order.getPane());
            }
        });
        // Add to Frame
        frame.add(pnlN, BorderLayout.NORTH);
        frame.add(pnlW, BorderLayout.WEST);
        frame.add(pnlC, BorderLayout.CENTER);
        // Show Frame
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    } 
}
