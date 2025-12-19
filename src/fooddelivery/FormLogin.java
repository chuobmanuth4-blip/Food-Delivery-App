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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JRadioButton;
import java.sql.SQLException;
/**
 *
 * @author manut
 */
public class FormLogin extends FoodDelivery{
    JFrame frame;
    PreparedStatement cmd;
    ResultSet rs;
    JLabel headerLb, welcomeLb, userLb, passLb, forgetpassLb;
    JButton btnLogin1, btnRegister1, btnLogin2, btnRegister2;
    JTextField txtusername;
    JPasswordField txtpass;
    JRadioButton rbtn;
    JPanel pnl1, pnl2;
    //Constructor
    public FormLogin(){
        // Create Frame
        frame = new JFrame();
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        // Create Label
        headerLb = new JLabel("Log In", JLabel.CENTER);
        headerLb.setFont(new Font("Arial", Font.BOLD, 20));
        headerLb.setBounds(100,50,200,50);
        headerLb.setForeground(Color.BLACK);
        
        userLb = new JLabel("Username");
        userLb.setBounds(50,100,100,20);
        userLb.setForeground(Color.BLACK);

        passLb = new JLabel("Password");
        passLb.setBounds(50,160,100,20);
        passLb.setForeground(Color.BLACK);
       
        forgetpassLb = new JLabel("Forget Password?");
        forgetpassLb.setBounds(235,215,100,20);
        forgetpassLb.setForeground(Color.BLACK);
        
        welcomeLb = new JLabel("Welcome Back!", JLabel.CENTER);
        welcomeLb.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLb.setBounds(0,30,200,30);
        // Create Button
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

        btnLogin2 = new JButton("Log In");
        btnLogin2.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogin2.setBounds(48,250,290,30);
        btnLogin2.setForeground(Color.WHITE);
        btnLogin2.setBackground(Color.BLUE);

        btnRegister2 = new JButton("Don't have an account? Sign Up");
        btnRegister2.setFont(new Font("Arial", Font.BOLD, 12));
        btnRegister2.setBounds(48,350,290,30);
        btnRegister2.setForeground(Color.WHITE);
        btnRegister2.setBackground(Color.BLUE);
        // Create RadioButton
        rbtn = new JRadioButton("Show password");
        rbtn.setBounds(50,215,150,20);
        // Create TextField
        //txtusername  = new JTextField("Vitur");
        txtusername  = new JTextField("Chuob Manuth");
        txtusername.setBounds(48,120,290,30);

        //txtpass = new JPasswordField("Tur1234");
        txtpass = new JPasswordField("Nuth@9273$");
        txtpass.setBounds(48,180,290,30);
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
        pnl2.add(passLb); 
        pnl2.add(txtpass);
        pnl2.add(rbtn);
        pnl2.add(forgetpassLb);
        pnl2.add(btnLogin2);
        pnl2.add(btnRegister2);        
        // Add to Frame
        frame.add(pnl1, BorderLayout.WEST);
        frame.add(pnl2, BorderLayout.CENTER);
        // Process
        rbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(rbtn.isSelected()) {
                    txtpass.setEchoChar((char)0);
                } else {
                    txtpass.setEchoChar('*');
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
        btnLogin2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String username = txtusername.getText();
                String password = txtpass.getText();
                if(username.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields!");
                    return;
                }
                try{
                    dbConnection();     
                    String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
                    cmd = conn.prepareStatement(sql);
                    cmd.setString(1, username);
                    cmd.setString(2, password);
                    rs = cmd.executeQuery();
                    if (rs.next()){
                        String role  = rs.getString("Role");
                        if(role.equals("Customer")){
                            int userID = rs.getInt("UserID");
                            new UserForm(userID);
                        }
                        else if(role.equals("Admin")){
                            new MainForm();
                        }
                        else if(role.equals("Deliveryman")){
                            int deliveryId = rs.getInt("UserID");
                            new DeliveryForm(deliveryId);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Unknown role!");
                            return;
                        }
                        frame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                    }
                }
                catch(SQLException ex){
                    ex.printStackTrace();
                }
                catch (Exception exception) {
                        System.out.println(exception);
                }
            } 
        });
        btnRegister2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new SignUp();
                frame.setVisible(false);
            }
        });
        forgetpassLb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Your code here
                new ForgetPassword();
                frame.setVisible(false);
            }
        });
        //Show Frame
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
}
