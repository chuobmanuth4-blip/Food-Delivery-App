/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author manut
 */
public class ForgetPassword extends FoodDelivery{
    PreparedStatement cmd;
    ResultSet rs;
    JFrame frame;
    JLabel headerLb, informLb, emailLb;
    JTextField txtEmail;
    JButton btnNext;
    JPanel pnlN, pnlS, pnlW, pnlE, pnlC;
    public ForgetPassword(){
        // Create Frame
        frame = new JFrame();
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        //Create Label 
        headerLb = new JLabel("Forget Password", JLabel.CENTER);
        headerLb.setFont(new Font("Arial",Font.BOLD, 20));
        headerLb.setBounds(95,5,200,30);
        
        informLb = new JLabel("Enter your email to reset your password.");
        informLb.setFont(new Font("Arial",Font.ITALIC, 16));
        informLb.setBounds(50,40,300,20);
        
        emailLb = new JLabel("Email");
        emailLb.setFont(new Font("Arial",Font.BOLD, 15));
        emailLb.setBounds(30,80,100,20);
        // Create TextField 
        txtEmail = new JTextField();
        txtEmail.setBounds(30,100,325,50);
        // JButton 
        btnNext = new JButton("Next");
        btnNext.setBackground(Color.BLUE);
        btnNext.setBounds(30,180,325,40); 
        btnNext.setForeground(Color.WHITE);
        // Create Panel
        pnlN = new JPanel();
        pnlN.setBackground(Color.GREEN);
        pnlN.setPreferredSize(new Dimension(0,50));
        
        pnlS = new JPanel();
        pnlS.setBackground(Color.GREEN);
        pnlS.setPreferredSize(new Dimension(0,50));
        
        pnlW = new JPanel();
        pnlW.setBackground(Color.GREEN);
        pnlW.setPreferredSize(new Dimension(50,0));
        
        pnlE = new JPanel();
        pnlE.setBackground(Color.GREEN);
        pnlE.setPreferredSize(new Dimension(50,0));
        
        pnlC = new JPanel();
        pnlC.setLayout(null);
        pnlC.add(headerLb);
        pnlC.add(informLb);
        pnlC.add(emailLb);
        pnlC.add(txtEmail);
        pnlC.add(btnNext);
        // Add Componets to Frame
        frame.add(pnlN, BorderLayout.NORTH);
        frame.add(pnlS, BorderLayout.SOUTH);
        frame.add(pnlW, BorderLayout.WEST);
        frame.add(pnlE, BorderLayout.EAST);  
        frame.add(pnlC,BorderLayout.CENTER);     
        // Process 
        btnNext.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String email = txtEmail.getText().trim();
                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter email!");
                    return;
                }               
               try{
                   dbConnection();
                   String sql = "SELECT * FROM Users WHERE Email = ?;";
                   cmd = conn.prepareStatement(sql);
                   cmd.setString(1, email);
                   rs = cmd.executeQuery();
                   if(rs.next()){
                        new ResetPassword(email);
                        frame.setVisible(false);
                   }
                   else{
                       JOptionPane.showMessageDialog(null, "Email not found!");
                   }
               }
               catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        // Show Frame
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
}
