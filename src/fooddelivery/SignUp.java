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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author manut
 */
public class SignUp extends JFrame{
    Connection conn;
    PreparedStatement cmd;
    JFrame frame;
    JLabel headerLb, welcomeLb, userLb, passLb1, passLb2, genderLb, emailLb;
    JButton btnLogin1, btnRegister1, btnSignUp, btnRegister2;
    JTextField txtusername, txtEmail;
    JPasswordField txtpass1, txtpass2;
    JCheckBox cb1, cb2;
    JPanel pnl1, pnl2;
    //Constructor
    public SignUp(){
        // Create Frame
        frame = new JFrame();
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create Label
        welcomeLb = new JLabel("Welcome Back!", JLabel.CENTER);
        welcomeLb.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLb.setBounds(0,30,200,30);
        //welcomeLb.setForeground(Color.WHITE);
                
        headerLb = new JLabel("Sign Up", JLabel.CENTER);
        headerLb.setFont(new Font("Arial", Font.BOLD, 20));
        headerLb.setBounds(100,10,200,30);
        headerLb.setForeground(Color.BLACK);
        
        userLb = new JLabel("Username");
        userLb.setBounds(50,50,100,20);
        userLb.setForeground(Color.BLACK);
        
        emailLb = new JLabel("Email");
        emailLb.setBounds(50,105,100,20);
        emailLb.setForeground(Color.BLACK);

        passLb1 = new JLabel("Password");
        passLb1.setBounds(50,160,100,20);
        passLb1.setForeground(Color.BLACK);
       
        passLb2 = new JLabel("Confirm Password");
        passLb2.setBounds(50,220,100,20);
        passLb2.setForeground(Color.BLACK);
        
        genderLb = new JLabel("Gender");
        genderLb.setBounds(50,280,100,20);
        //Create Button
        btnLogin1 = new JButton("Log In");
        btnLogin1.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin1.setBounds(30,100,140,30);
        btnLogin1.setForeground(Color.WHITE);
        btnLogin1.setBackground(Color.red);

        btnRegister1 = new JButton("Sign Up");
        btnRegister1.setFont(new Font("Arial", Font.BOLD, 15));
        btnRegister1.setBounds(30,140,140,30);
        btnRegister1.setForeground(Color.WHITE);
        btnRegister1.setBackground(Color.red);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setFont(new Font("Arial", Font.BOLD, 15));
        btnSignUp.setBounds(48,350,290,30);
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setBackground(Color.BLUE);

        btnRegister2 = new JButton("Alrady have an account? Log In");
        btnRegister2.setFont(new Font("Arial", Font.BOLD, 12));
        btnRegister2.setBounds(48,400,290,30);
        btnRegister2.setForeground(Color.WHITE);
        btnRegister2.setBackground(Color.BLUE);
        // Create TextField
        txtusername  = new JTextField("");
        txtusername.setBounds(48,70,290,30);
        
        txtEmail = new JTextField("");
        txtEmail.setBounds(48,125,290,30);
        
        txtpass1 = new JPasswordField("");
        txtpass1.setBounds(48,180,290,30);
        
        txtpass2 = new JPasswordField("");
        txtpass2.setBounds(48,240,290,30);
        // Create CheckBox
        cb1 = new JCheckBox("Male");
        cb1.setBounds(50,300,50,20);
        cb2 = new JCheckBox("Female");
        cb2.setBounds(120,300,100,20);
        ButtonGroup bg = new ButtonGroup();
        bg.add(cb1);
        bg.add(cb2);
        // Create Panel
        pnl1 = new JPanel();
        pnl1.setBackground(Color.GREEN);
        pnl1.setPreferredSize(new Dimension(200,0));
        pnl1.setLayout(null);
        pnl1.add(welcomeLb);
        pnl1.add(new JLabel());
        pnl1.add(btnLogin1);
        pnl1.add(btnRegister1);      

        pnl2 = new JPanel();
        pnl2.setBackground(Color.white);
        pnl2.setLayout(null);
        pnl2.add(headerLb);
        pnl2.add(userLb);
        pnl2.add(txtusername);
        pnl2.add(emailLb);      
        pnl2.add(txtEmail);
        pnl2.add(passLb1); 
        pnl2.add(txtpass1);
        pnl2.add(passLb2);
        pnl2.add(txtpass2);
        pnl2.add(genderLb);
        pnl2.add(cb1);
        pnl2.add(cb2);
        pnl2.add(btnSignUp);
        pnl2.add(btnRegister2);        
        // Add to Frame
        frame.add(pnl1, BorderLayout.WEST);
        frame.add(pnl2, BorderLayout.CENTER);
        // Process
        btnSignUp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String username = txtusername.getText();
                String password = txtpass1.getText();
                String email = txtEmail.getText();
                String gender;
                if (cb1.isSelected()) {
                    gender = "Male";
                } else if (cb2.isSelected()) {
                    gender = "Female";
                } else {
                    gender = "Not selected";
                }
                String dbCon = "jdbc:mysql://localhost:3306/fooddelivery";
                String dbName = "root";
                String dbPass = "manuth@9273$";
                 try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(dbCon, dbName, dbPass);

                    String sql = "INSERT INTO Users(Username,Password,Gender,Email) VALUES (?, ?, ?, ?);";
                    cmd = conn.prepareStatement(sql);            
                    cmd.setString(1, username); 
                    cmd.setString(2, password);
                    cmd.setString(3, gender);
                    cmd.setString(4, email);
                    cmd.executeUpdate();  
                    JOptionPane.showMessageDialog(null, "Your sign‑up was successful. Please log in to your account!");
                    new FormLogin();
                    frame.setVisible(false);
                    } 
                    catch (SQLException ex) {
                        ex.printStackTrace();
                    }catch (Exception exception) {
                        System.out.println(exception);
                    }
            }
        });
        btnLogin1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new FormLogin();
                frame.setVisible(false);
            }
        });
        btnRegister1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new SignUp();
                frame.setVisible(false);
            }
        });
        btnRegister2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new FormLogin();
                frame.setVisible(false);
            }
        });
        //Show Frame
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
}
