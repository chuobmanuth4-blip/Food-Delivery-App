/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author manut
 */
public class ResetPassword extends FoodDelivery{
    PreparedStatement cmd;
    String email; // email from ForgetPassword Form
    JFrame frame;
    JLabel headerLb, userLb, pass1Lb, pass2Lb, informLb;
    JTextField txtUsername;
    JPasswordField txtNewPass, txtConfirm;
    JButton btnReset;
    public ResetPassword(String Email) {
        this.email = Email;
        // Create Frame
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        // Create Label
        headerLb = new JLabel("Reset Password");
        headerLb.setFont(new Font("Arial", Font.BOLD, 22));
        headerLb.setForeground(Color.BLUE);
        headerLb.setBounds(110,15,200,20);
        informLb = new JLabel("Enter your username and a new password.");
        informLb.setFont(new Font("Arial", Font.ITALIC, 15));
        informLb.setBounds(50,50,300,20);    
        userLb = new JLabel("Username");
        userLb.setBounds(45,90,100,10);
        pass1Lb = new JLabel("New Password");
        pass1Lb.setBounds(45,150,100,10);
        pass2Lb = new JLabel("Confirm Password");
        pass2Lb.setBounds(45,210,100,10);
        // Create TextField
        txtUsername = new JTextField();
        txtUsername.setBounds(43,110,300,30);
        txtNewPass = new JPasswordField();
        txtNewPass.setBounds(43,170,300,30);
        txtConfirm = new JPasswordField();
        txtConfirm.setBounds(43,230,300,30);     
        // Create Button  
        btnReset = new JButton("Reset");
        btnReset.setBackground(Color.GREEN);
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Arial", Font.BOLD, 15));
        btnReset.setBounds(43,290,300,40);
        // Add Components to Frame
        frame.add(headerLb);
        frame.add(informLb);
        frame.add(userLb);
        frame.add(txtUsername);
        frame.add(pass1Lb);
        frame.add(txtNewPass);
        frame.add(pass2Lb); 
        frame.add(txtConfirm);   
        frame.add(btnReset);           
        // Process
        btnReset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String username = txtUsername.getText().trim();
                String pass1 = String.valueOf(txtNewPass.getPassword());
                String pass2 = String.valueOf(txtConfirm.getPassword());
                if (username.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }
                if (!pass1.equals(pass2)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match!");
                    return;
                }
                try {
                    dbConnection();
                    // Verify that username belongs to this email
                    String check = "SELECT * FROM Users WHERE Email=? AND Username=?";
                    cmd = conn.prepareStatement(check);
                    cmd.setString(1, email);
                    cmd.setString(2, username);
                    ResultSet rs = cmd.executeQuery();
                    if (rs.next()) {
                        String pass = txtNewPass.getText();
                        // Update password safely
                        String update = "UPDATE Users SET Password=? WHERE Email=? AND Username=?";
                        cmd = conn.prepareStatement(update);
                        cmd.setString(1, pass);
                        cmd.setString(2, email);
                        cmd.setString(3, username);
                        cmd.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Password updated successfull. Please log in with your new password!");
                        new FormLogin();
                        frame.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Username does not match this email!");
                    }
                } 
                catch (Exception ex) {
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
