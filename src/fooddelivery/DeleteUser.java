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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author manut
 */
public class DeleteUser {
    Connection conn;
    PreparedStatement cmd; //cmd1, cmd2, cmd3;
    ResultSet rs; 
    JFrame frame;
    JLabel headerLb;
    JTextField txtDelete;
    JButton btnCancel, btnConfirm;
    JPanel pnlW, pnlE, pnlS, pnlN, pnlC;
    public DeleteUser(){
        // Create Frame
        frame = new JFrame();
        frame.setSize(400,350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Create Label
        headerLb = new JLabel("Delete User");
        headerLb.setBounds(100,30,100,20);
        headerLb.setFont(new Font("Arial", Font.BOLD, 16));
        // Create TextField
        txtDelete = new JTextField("Please Enter ID or Username to Delete!");
        txtDelete.setBounds(20,80,250,40);
        txtDelete.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtDelete.getText().equals("Please Enter ID or Username to Delete!")) {
                    txtDelete.setText("");
                    txtDelete.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtDelete.getText().isEmpty()) {
                    txtDelete.setForeground(Color.GRAY);
                    txtDelete.setText("Please Enter ID or Username to Delete!");
                }
            }
        });
        // Create Button
        btnCancel = new JButton("Cancel"); 
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setBounds(70,140,100,40);
        
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBackground(Color.BLUE);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setBounds(170,140,100,40);
        // Create Panel
        pnlW = new JPanel();
        pnlW.setBackground(Color.GREEN);
        pnlW.setPreferredSize(new Dimension(50,0));
        
        pnlE = new JPanel();
        pnlE.setBackground(Color.GREEN);
        pnlE.setPreferredSize(new Dimension(50,0));
        
        pnlS = new JPanel();
        pnlS.setBackground(Color.GREEN);
        pnlS.setPreferredSize(new Dimension(0,50));
        
        pnlN = new JPanel();
        pnlN.setBackground(Color.GREEN);
        pnlN.setPreferredSize(new Dimension(0,50));
        
        pnlC = new JPanel();
        pnlC.setBackground(Color.WHITE);
        pnlC.setBorder(BorderFactory.createTitledBorder(""));
        pnlC.setLayout(null);
        pnlC.add(headerLb);
        pnlC.add(txtDelete);
        pnlC.add(btnCancel);
        pnlC.add(btnConfirm);
        // Process
        btnConfirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String input = txtDelete.getText().trim();
                int id = 0;
                try {
                    id = Integer.parseInt(input);
                } catch (NumberFormatException ex) {     
                }
                String username = input;
                
                try{
                    String dbCon = "jdbc:mysql://localhost:3306/fooddelivery";
                    String dbName = "root";
                    String dbPass = "manuth@9273$";  
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(dbCon, dbName, dbPass);
                    String sql = "DELETE FROM Users WHERE UserID = ? OR Username = ? ";
                    cmd = conn.prepareStatement(sql);                   
                    cmd.setInt(1, id);                    
                    cmd.setString(2, username);
                    int x = cmd.executeUpdate();
                    if(x > 0){
                        JOptionPane.showMessageDialog(null, "Delete successful!");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Delete failed!");
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }catch (Exception exception) {
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
        // Add Components to Frame
        frame.add(pnlW, BorderLayout.WEST);
        frame.add(pnlE, BorderLayout.EAST);
        frame.add(pnlS, BorderLayout.SOUTH);
        frame.add(pnlN, BorderLayout.NORTH);
        frame.add(pnlC, BorderLayout.CENTER);        
        // Show Frame
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }
}
