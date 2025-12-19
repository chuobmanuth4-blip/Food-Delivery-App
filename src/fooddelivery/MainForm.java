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
public class MainForm{
    UserAdmin user;
    ProductAdmin product;
    OrderAdmin order;
    Dashboard report;
    JFrame frame;
    JLabel lbHeader;
    JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    JPanel pnlN, pnlW, pnlC;
    ImageIcon logo;
    // Constuctor
    public MainForm(){
        // Create Frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create image
        logo = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\logo.png");
        // Creat Label
        lbHeader = new JLabel("Get it hot. Get it fast. GetFood.", logo, JLabel.CENTER);
        lbHeader.setFont(new Font("Arial", Font.BOLD, 30));
        // Create Button
        btn1 = new JButton("User");
        btn2 = new JButton("Products");
        btn3 = new JButton("Orders");
        btn4 = new JButton("Reports");
        btn5 = new JButton("Settting ");
        btn6 = new JButton("About");
        btn7 = new JButton("Log Out");
        // Create Panel
        pnlN = new JPanel();
        pnlN.setLayout(new BorderLayout());
        pnlN.setPreferredSize(new Dimension(0,100));
        pnlN.add(lbHeader, BorderLayout.WEST);
        
        pnlW = new JPanel();
        pnlW.setPreferredSize(new Dimension(150,0));
        pnlW.setLayout(new GridLayout(10,1));
        pnlW.add(btn1);
        pnlW.add(btn2);
        pnlW.add(btn3);
        pnlW.add(btn4);
        pnlW.add(btn5);
        pnlW.add(btn6);        
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(new JLabel(""));
        pnlW.add(btn7);
        
        // Center Home Page
        pnlC = new JPanel();
        pnlC.setLayout(new BorderLayout());         
        // Add to Frame
        frame.add(pnlN, BorderLayout.NORTH);
        frame.add(pnlW, BorderLayout.WEST);
        frame.add(pnlC, BorderLayout.CENTER);
        // Process
        btn1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                btn1.setBackground(Color.GREEN);
                btn2.setBackground(null);
                btn3.setBackground(null);
                btn4.setBackground(null);

                user = new UserAdmin();
                pnlC.removeAll();
                pnlC.revalidate();
                pnlC.repaint();
                pnlC.add(user.getPane());
            }
        });
        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                btn1.setBackground(null);                
                btn2.setBackground(Color.GREEN);
                btn3.setBackground(null);                
                btn4.setBackground(null);                                
                product = new ProductAdmin();
                pnlC.removeAll();
                pnlC.revalidate();
                pnlC.repaint();
                pnlC.add(product.getPane());
            }
        });
        btn3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                btn1.setBackground(null);
                btn2.setBackground(null);                
                btn3.setBackground(Color.GREEN);
                btn4.setBackground(null);                                
                order = new OrderAdmin();
                pnlC.removeAll();
                pnlC.revalidate();
                pnlC.repaint();
                pnlC.add(order.getPane());
            }
        });
        btn4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                btn1.setBackground(null);
                btn2.setBackground(null);
                btn3.setBackground(null);
                btn4.setBackground(Color.GREEN);
                report = new Dashboard();
                pnlC.removeAll();
                pnlC.revalidate();
                pnlC.repaint();
                pnlC.add(report.getPane());
            }
        });
        // Show Frame
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
