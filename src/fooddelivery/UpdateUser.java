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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class UpdateUser extends UserAdmin{
    JFrame frame;
    JLabel  headerLb,searchLb, userLb, passLb1, passLb2, roleLb, genderLb, emailLb;
    JTextField txtsearch, txtusername,txtemail;
    JPasswordField txtpass1, txtpass2;
    JComboBox cmbGender, cmbRole;
    JButton btnCancel, btnConfirm, btnSearch;
    JPanel pnlN, pnlC;      
    int selectedUserId = -1;
    public UpdateUser(){
        // Create Frame
        frame = new JFrame();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.setBackground(Color.WHITE);
        frame.setLayout(new BorderLayout());
        // Create Label
        headerLb = new JLabel("Update User");
        headerLb.setFont(new Font("Arial", Font.BOLD, 16));
        headerLb.setBounds(200,10,100,20);       
        searchLb = new JLabel("Search: ");
        searchLb.setBounds(50,60,100,20);
        userLb = new JLabel("Username: ");
        userLb.setBounds(30,20,100,20);
        passLb1 = new JLabel("Password: ");
        passLb1.setBounds(30,70,100,20);
        passLb2 = new JLabel("Confirm Password: ");
        passLb2.setBounds(30,120,120,20);
        emailLb = new JLabel("Email: ");
        emailLb.setBounds(30,170,100,20);
        roleLb = new JLabel("User Type: ");
        roleLb.setBounds(30,220,100,20);
        genderLb = new JLabel("Gender: ");
        genderLb.setBounds(30,270,100,20); 
        // Create TextField and PasswordField
        txtsearch = new JTextField("Please Enter ID or Username or Email");
        txtsearch.setBounds(100, 50, 250, 40);
        txtsearch.setForeground(Color.GRAY);
        txtsearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtsearch.getText().equals("Please Enter ID or Username or Email")) {
                    txtsearch.setText("");
                    txtsearch.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtsearch.getText().isEmpty()) {
                    txtsearch.setForeground(Color.GRAY);
                    txtsearch.setText("Please Enter ID or Username or Email");
                }
            }
        });
        txtusername = new JTextField();
        txtusername.setBounds(150,10,300,40);
        txtpass1 = new JPasswordField("");
        txtpass1.setBounds(150,60,300,40);
        txtpass2 = new JPasswordField("");
        txtpass2.setBounds(150,110,300,40);
        txtemail = new JTextField();
        txtemail.setBounds(150,160,300,40);
        // Create ComboBox
        String role [] = {null,"Admin", "Customer", "Deliveryman"};
        cmbRole = new JComboBox(role);
        cmbRole.setBounds(150,210,300,40);
        String gender [] = {"", "Male", "Female", "Other"};
        cmbGender = new JComboBox(gender);
        cmbGender.setBounds(150,260,300,40);
        // Create Button 
        btnCancel = new JButton("Cancel"); 
        btnCancel.setBackground(Color.WHITE);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setBounds(250,310,100,40);
        
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBackground(Color.BLUE);
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setBounds(350,310,100,40);
        
        btnSearch = new JButton("Search");
        btnSearch.setBackground(Color.GREEN);
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBounds(350,50,100,40);
        // Create Panel
        pnlN = new JPanel();
        pnlN.setLayout(null);
        pnlN.setBackground(Color.WHITE);
        pnlN.add(headerLb);
        pnlN.setPreferredSize(new Dimension(0,100));
        pnlN.add(searchLb);
        pnlN.add(txtsearch);
        pnlN.add(btnSearch);
        
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
        btnSearch.addActionListener(new ActionListener(){
            @Override
              public void actionPerformed(ActionEvent e){
                String input = txtsearch.getText().trim();
                int id = 0;
                try {
                    id = Integer.parseInt(input);
                } catch (NumberFormatException ex) {     
                }
                String username = input;
                String email = input;
                try 
                {
                    dbConnection();
                    String sql = "SELECT UserID, Username, Password, Role, Gender, Email FROM Users WHERE UserID = ? OR Username = ? OR Email = ?";
                    cmd = conn.prepareStatement(sql);                   
                    cmd.setInt(1, id);                    
                    cmd.setString(2, username);
                    cmd.setString(3, email);                    
                    rs = cmd.executeQuery();                
                    if (rs.next()==true)
                    {
                        selectedUserId = rs.getInt("UserID");
                        txtusername.setText(rs.getString("Username"));
                        txtpass1.setText(rs.getString("Password"));
                        txtpass2.setText(rs.getString("Password"));
                        txtemail.setText(rs.getString("Email"));
                        cmbRole.setSelectedItem(rs.getString("Role"));
                        cmbGender.setSelectedItem(rs.getString("Gender"));
                    }   
                    else
                        JOptionPane.showMessageDialog(null, "User not found");
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        btnConfirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (selectedUserId == -1) {
                    JOptionPane.showMessageDialog(null, "Please search a user first!");
                    return;
                }
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
                    JOptionPane.showMessageDialog(null, "Passwords do not match!");
                    return;
                }
                try {
                    dbConnection();
                    String sql = "UPDATE Users SET Username= ?, Password = ?, Role = ?, Gender = ?, Email = ? WHERE UserID = ?;";                     
                    cmd = conn.prepareStatement(sql);            
                    cmd.setString(1, username);
                    cmd.setString(2, password);
                    cmd.setString(3, role);
                    cmd.setString(4, gender);
                    cmd.setString(5, email); 
                    cmd.setInt(6, selectedUserId);
                    
                    int x = cmd.executeUpdate();    
                    if (x>0){
                        JOptionPane.showMessageDialog(null, "Update sucessful!");
                        frame.setVisible(false);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Update failed!");
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