/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author manut
 */
public class UserAdmin extends JFrame{
    Container pane;
    Connection conn;
    PreparedStatement cmd; 
    ResultSet rs; 
    JLabel lbHeader, lbSearch, lbFooter;
    JTextField txtSearch;
    JButton btnSearch, btnCreate, btnEdit, btnDelete, btnRefresh, btnClear;
    ImageIcon imgUserM, imgSearch;
    JPanel pnlN, pnlC, pnlS;
    JTable tb;
    DefaultTableModel tbUser;
    public UserAdmin(){
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.red);
        pane.setVisible(true);
        // Create Image
        imgUserM = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\userMangement.png");
        imgSearch = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\search.png");
        
        // Create Label
        lbHeader = new JLabel("User Management",imgUserM, JLabel.CENTER);
        lbHeader.setFont(new Font("Arial", Font.BOLD, 30));
        lbHeader.setBounds(460, 5, 350, 70);
        
        lbSearch = new JLabel("Search:");
        lbSearch.setFont(new Font("Arial", Font.BOLD, 16));
        lbSearch.setBounds(200, 95, 100, 30);
        
        lbFooter = new JLabel("©2025 GETFOOD | All rights reserved", JLabel.CENTER);
        lbFooter.setFont(new Font("Arial", Font.BOLD, 16));
        // Create TextField
        txtSearch = new JTextField();
        
        txtSearch.setBounds(270, 85, 690, 50);
        // Create Button
        btnSearch = new JButton("Search", imgSearch);
        btnSearch.setBounds(960, 85, 100, 50);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.CYAN);
        
        btnCreate = new JButton("Create User");
        btnEdit = new JButton("Edit User");
        btnDelete = new JButton("Delete User");
        btnRefresh = new JButton("Refresh");
        btnClear = new JButton("Clear");
        // Create Table Model 
        tbUser = new DefaultTableModel();
        tbUser.addColumn("ID");
        tbUser.addColumn("Username");
        tbUser.addColumn("Role");
        tbUser.addColumn("Gender");
        tbUser.addColumn("Email");
        tbUser.addColumn("CreatedAt");
        tb = new JTable(tbUser);
        JScrollPane scroll = new JScrollPane(tb);
        loadUserTable();
        // Create Panel
        pnlN = new JPanel();
        pnlN.setLayout(null);
        pnlN.setBackground(Color.ORANGE);
        pnlN.setPreferredSize(new Dimension(0,150));
                
        pnlN.add(lbHeader);  
        pnlN.add(lbSearch);
        pnlN.add(txtSearch);
        pnlN.add(btnSearch);
        
        pnlC = new JPanel();
        pnlC.setBackground(Color.DARK_GRAY);
        pnlC.setLayout(new BorderLayout());
        pnlC.add(scroll);
        
        pnlS = new JPanel();
        pnlS.setLayout(new BorderLayout());  
        pnlS.setBackground(Color.ORANGE);
        pnlS.setPreferredSize(new Dimension(0,150));
        JPanel pnlSN = new JPanel();
        pnlSN.setLayout(new GridLayout(2,9));
        pnlSN.setPreferredSize(new Dimension(0,110));
        pnlSN.setBackground(Color.ORANGE);
        pnlSN.add(new JLabel("")); 
        pnlSN.add(new JLabel("")); 
        pnlSN.add(btnCreate);
        pnlSN.add(btnEdit);
        pnlSN.add(btnDelete);
        pnlSN.add(btnRefresh);
        pnlSN.add(btnClear); 
        pnlSN.add(new JLabel("")); 
        pnlSN.add(new JLabel("")); 
        
        pnlSN.add(new JLabel("Total Users:     ||")); 
        pnlSN.add(new JLabel("Customers:     ||")); 
        pnlSN.add(new JLabel("Delivery person:     ||"));   
        pnlSN.add(new JLabel("")); 
        pnlSN.add(new JLabel("")); 
        pnlSN.add(new JLabel("")); 
        pnlSN.add(new JLabel("")); 
        pnlSN.add(new JLabel("")); 
        pnlSN.add(new JLabel("")); 
        
        JPanel pnlSS = new JPanel();
        pnlSS.add(lbFooter); 
        pnlSS.setBackground(Color.ORANGE);
        
        pnlS.add(pnlSN, BorderLayout.NORTH);          
        pnlS.add(pnlSS, BorderLayout.CENTER);
        // Proces
        btnSearch.addActionListener(new ActionListener(){
            @Override
              public void actionPerformed(ActionEvent e){
                String input = txtSearch.getText().trim();
                int id = 0;
                try {
                    id = Integer.parseInt(input);
                } catch (NumberFormatException ex) {     
                }
                String username = input;
                String email = input;
                try 
                {
                    String dbCon = "jdbc:mysql://localhost:3306/fooddelivery";
                    String dbName = "root";
                    String dbPass = "manuth@9273$";  
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(dbCon, dbName, dbPass);                    
                    //String sql = "SELECT UserID, Username, Role, Gender, Email, createdAt" + "FROM Users WHERE UserId = ? OR Username = ? OR Email = ?";                                    
                    String sql = "SELECT UserID, Username, Role, Gender, Email, createdAt " + "FROM Users WHERE UserID = ? OR Username = ? OR Email = ?";
                    cmd = conn.prepareStatement(sql);                   
                    cmd.setString(1,Integer.toString(id));                    
                    cmd.setString(2, username);
                    cmd.setString(3, email);                    
                    rs = cmd.executeQuery();                
                    if (rs.next()==true)
                    {
                        tbUser.setRowCount(0);
                        tbUser.addRow(new Object[]{
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Role"),
                            rs.getString("Gender"),
                            rs.getString("Email"),
                            rs.getTimestamp("createdAt")
                        });
                    }   
                    else
                        JOptionPane.showMessageDialog(null, "It is not found");
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadUserTable();
            }
        });
        btnClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                txtSearch.setText("");
                tbUser.setRowCount(0); 
                
            }
        });  
        btnCreate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               new CreateUser();
            }
        });
        
        btnEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               new UpdateUser();
            }
        });
        btnDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               new DeleteUser();
            }
        });
        // Add Components to Frame
        pane.add(pnlN, BorderLayout.NORTH);
        pane.add(pnlC, BorderLayout.CENTER);
        pane.add(pnlS, BorderLayout.SOUTH);        
    }
    public Container getPane(){
        return pane;
    }
    public void loadUserTable() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fooddelivery",
                    "root",
                    "manuth@9273$"
            );
            String sql = "SELECT UserID, Username, Role, Gender, Email, createdAt FROM Users";
            cmd = conn.prepareStatement(sql);
            rs = cmd.executeQuery();

            tbUser.setRowCount(0);
            while (rs.next()) {
                    tbUser.addRow(new Object[]{
                    rs.getInt("UserID"),
                    rs.getString("Username"),
                    rs.getString("Role"),
                    rs.getString("Gender"),
                    rs.getString("Email"),
                    rs.getTimestamp("createdAt")
                });
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
