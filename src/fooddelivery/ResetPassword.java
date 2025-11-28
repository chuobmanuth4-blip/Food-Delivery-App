/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author manut
 */
public class ResetPassword extends JFrame{
    JFrame frame;
    Connection conn;
    private String email; // email from first form
    JTextField txtUsername;
    JPasswordField txtNewPass, txtConfirm;
    JButton btnReset;
    //Connection conn;

    public ResetPassword(String email) {
        frame = new JFrame();
        this.email = email;

        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        frame.add(txtUsername);

        frame.add(new JLabel("New Password:"));
        txtNewPass = new JPasswordField();
        frame.add(txtNewPass);

        frame.add(new JLabel("Confirm Password:"));
        txtConfirm = new JPasswordField();
        frame.add(txtConfirm);

        btnReset = new JButton("Reset Password");
        frame.add(new JLabel());
        frame.add(btnReset);
        frame.setVisible(true);
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
                    String dbName = "jdbc:mysql://localhost:3306/fooddelivery";
                    String dbUser = "root";
                    String dbPass = "manuth@9273$"; 
                    conn = DriverManager.getConnection(dbName, dbUser, dbPass);
                    // Verify that username belongs to this email
                    String check = "SELECT * FROM Users WHERE Email=? AND Username=?";
                    PreparedStatement pst = conn.prepareStatement(check);
                    pst.setString(1, email);
                    pst.setString(2, username);
                    ResultSet rs = pst.executeQuery();
                    if (rs.next()) {
                        // Hash password
                        String pass = txtNewPass.getText();
                        // Update password safely
                        String update = "UPDATE Users SET Password=? WHERE Email=? AND Username=?";
                        pst = conn.prepareStatement(update);
                        pst.setString(1, pass);
                        pst.setString(2, email);
                        pst.setString(3, username);

                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Password updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Username does not match this email!");
                    }
                } 
                catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
    }
}
