/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

// import com.sun.jdi.connect.spi.Connection;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author manut
 */
public class FormLogin{
    JFrame frame;
    Connection conn;
    PreparedStatement cmd;
    ResultSet rs;
    JLabel headerLb, welcomeLb, userLb, passLb, forgetpassLb;
    JButton btnLogin1, btnRegister1, btnLogin2, btnRegister2;
    JTextField txtusername;
    JPasswordField txtpass;
    JPanel pnl1, pnl2;
    //Constructor
    public FormLogin(){
        // Create Frame
        frame = new JFrame();
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        // Create TextField
        txtusername  = new JTextField();
        txtusername.setBounds(48,120,290,30);

        txtpass = new JPasswordField();
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
        pnl2.add(forgetpassLb);
        pnl2.add(btnLogin2);
        pnl2.add(btnRegister2);        
        // Add to Frame
        frame.add(pnl1, BorderLayout.WEST);
        frame.add(pnl2, BorderLayout.CENTER);
        // Process
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
                if(e.getSource()== btnLogin2){
                    try{
                        String username = txtusername.getText();
                        String password= txtpass.getText();
                        String dbName = "jdbc:mysql://localhost:3306/fooddelivery";
                        String dbUser = "root";
                        String dbPass = "manuth@9273$";
                        conn = DriverManager.getConnection(dbName, dbUser, dbPass);
                        String sql = "SELECT * FROM Users";
                        cmd = conn.prepareStatement(sql);
                        rs = cmd.executeQuery();
                        if(username.equals("")||password.equals("")){
                            JOptionPane.showMessageDialog(null, "Please Enter all Fields");
                        }else{
                            while(rs.next()){
                                if(username.equalsIgnoreCase(rs.getString("USERNAME")) && password.equalsIgnoreCase(rs.getString("PASSWORD"))){
                                    // JOptionPane.showMessageDialog(null,"Login Successful");
                                    new MainForm();
                                    frame.setVisible(false);
                                }
                            }if(rs.isAfterLast()){
                                JOptionPane.showMessageDialog(null,"Username or Password did not match");
                            }
                        }
                    }catch(Exception exception){
                        System.out.println("Error while connecting to the database");
                    }
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
