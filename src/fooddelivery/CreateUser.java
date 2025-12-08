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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author manut
 */
public class CreateUser extends UserAdmin{
    JFrame frame;
    JLabel  headerLb, userLb, passLb1, passLb2, roleLb, genderLb, emailLb;
    JTextField txtusername,txtemail;
    JPasswordField  txtpass1, txtpass2;
    JComboBox cmbGender, cmbRole;
    JButton btnCancel, btnConfirm;
    JPanel pnlN, pnlC;
    public CreateUser(){
        // Create Frame
        frame = new JFrame();
        frame.setSize(500,450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());
        // Create Label
        headerLb = new JLabel("Create User");
        headerLb.setFont(new Font("Arial", Font.BOLD, 16));
        userLb = new JLabel("Username: ");
        userLb.setBounds(30,20,100,20);
        passLb1 = new JLabel("Password: ");
        passLb1.setBounds(30,70,100,20);
        passLb2 = new JLabel("Confirm Password: ");
        passLb2.setBounds(30,120,120,20);
        emailLb = new JLabel("Email: ");
        emailLb.setBounds(30,170,100,20);
        roleLb = new JLabel("User Type");
        roleLb.setBounds(30,220,100,20);
        genderLb = new JLabel("Gender");
        genderLb.setBounds(30,270,100,20);      
        // Create TextField and PasswordField
        txtusername = new JTextField();
        txtusername.setBounds(150,10,300,40);
        txtpass1 = new JPasswordField("");
        txtpass1.setBounds(150,60,300,40);
        txtpass2 = new JPasswordField("");
        txtpass2.setBounds(150,110,300,40);
        txtemail = new JTextField();
        txtemail.setBounds(150,160,300,40);
        // Create ComboBox
        String role [] = {"","Admin", "Customer", "Delivery Person"};
        cmbRole = new JComboBox(role);
        cmbRole.setBounds(150,210,300,40);
        String gender [] = {"", "Male", "Female", "Other"};
        cmbGender = new JComboBox(gender);
        cmbGender.setBounds(150,260,300,40);
        // Create Button 
        btnCancel = new JButton("Cancel"); 
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setBounds(250,330,100,40);
        
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBackground(Color.BLUE);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setBounds(350,330,100,40);
        // Create Panel
        pnlN = new JPanel();
        pnlN.setBackground(Color.WHITE);
        pnlN.add(headerLb);
        
        pnlC = new JPanel();
        pnlC.setLayout(null);
        pnlC.setBackground(Color.WHITE);
        pnlC.setBorder(BorderFactory.createTitledBorder(""));
        pnlC.add(userLb);
        pnlC.add(txtusername);
        pnlC.add(passLb1);
        pnlC.add(txtpass1);
        pnlC.add(passLb2);
        pnlC.add(txtpass2);
        pnlC.add(emailLb);
        pnlC.add(txtemail);
        pnlC.add(roleLb);
        pnlC.add(cmbRole);
        pnlC.add(genderLb);
        pnlC.add(cmbGender);
        pnlC.add(btnCancel);
        pnlC.add(btnConfirm);
        // Process
        btnConfirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String username = txtusername.getText();
                String password = txtpass1.getText();
                String confirmPass = txtpass2.getText();
                String email = txtemail.getText();
                String role = (String) cmbRole.getSelectedItem();
                String gender = (String) cmbGender.getSelectedItem();
                
                
                if (username.isEmpty() || password.isEmpty() || confirmPass.isEmpty() || email.isEmpty() || gender.isEmpty() || role.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields!");
                    return; 
                }
                if(!password.equals(confirmPass)){
                    JOptionPane.showMessageDialog(null, "Your new password do not match!");
                    return;
                }
                try 
                {
                    dbConnection(); 
                    String query = "SELECT * FROM Users WHERE Username = ? OR Email = ?";
                    PreparedStatement cmdCheck;
                    cmdCheck = conn.prepareStatement(query);
                    cmdCheck.setString(1, username);
                    cmdCheck.setString(2, email);
                    rs = cmdCheck.executeQuery();
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Username or Email already exists");
                        return;
                    }
                    String sql = "INSERT INTO Users(Username,Password,Role,Gender,Email) VALUES (?, ?, ?, ?, ?);";
                    cmd = conn.prepareStatement(sql);            
                    cmd.setString(1, username); 
                    cmd.setString(2, password);
                    cmd.setString(3, role);                  
                    cmd.setString(4, gender);
                    cmd.setString(5, email);
                    cmd.executeUpdate();  
                    JOptionPane.showMessageDialog(null, "You have created account successfully!");
                    frame.setVisible(false);
                } 
                catch (SQLException ex) {
                        ex.printStackTrace();
                }
                catch (Exception exception) {
                        System.out.println(exception);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                frame.setVisible(false);
            }
        });
        // Add  Components to Frame
        frame.add(pnlN, BorderLayout.NORTH);
        frame.add(pnlC, BorderLayout.CENTER);
        // Show 
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
}

