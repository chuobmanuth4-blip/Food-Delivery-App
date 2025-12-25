/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.BorderFactory;
/**
 *
 * @author manuth
 */
public final class OrderDeliveryman extends JFrame{
    Container pane;
    Connection conn;
    PreparedStatement cmd; 
    ResultSet rs; 
    JLabel lbHeader, lbSearch, lbFooter, lbPending, lbCompleted, lbCanceled;
    JTextField txtSearch;
    JButton btnSearch, btnCompleted, btnCanceled, btnRefresh, btnViewDetail;
    ImageIcon imgOrderM, imgSearch;
    JPanel pnlN, pnlC, pnlS;
    JTable tb;
    DefaultTableModel tbOrder;
    public int DeliveryID;
    public OrderDeliveryman(int deliveryID){
        this.DeliveryID = deliveryID;
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.red);
        pane.setVisible(true);
        // Create Image
        imgOrderM = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\order.png");
        imgSearch = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\search.png");
        
        // Create Label
        lbHeader = new JLabel("Orders List",imgOrderM, JLabel.CENTER);
        lbHeader.setFont(new Font("Arial", Font.BOLD, 30));
        lbHeader.setBounds(460, 5, 350, 70);
        
        lbSearch = new JLabel("Search:");
        lbSearch.setFont(new Font("Arial", Font.BOLD, 16));
        lbSearch.setBounds(200, 95, 100, 30);
        
        lbFooter = new JLabel("©2025 GETFOOD | All rights reserved", JLabel.CENTER);
        lbFooter.setFont(new Font("Arial", Font.BOLD, 16));
        
        lbPending = new JLabel("Pending:");
        lbPending.setForeground(Color.GREEN);
        lbPending.setFont(new Font("Arial", Font.BOLD, 15));
        lbPending.setBounds(650, 10, 100, 30);
        loadStatus("Pending");
        
        lbCompleted = new JLabel("Completed:");
        lbCompleted.setForeground(Color.BLUE);
        lbCompleted.setFont(new Font("Arial", Font.BOLD, 15));
        lbCompleted.setBounds(650, 50, 100, 30);
        loadStatus("Completed");
        
        lbCanceled = new JLabel("Cancelled:");
        lbCanceled.setForeground(Color.RED);
        lbCanceled.setFont(new Font("Arial", Font.BOLD, 15));
        lbCanceled.setBounds(650, 90, 100, 30);
        loadStatus("Cancelled");
        // Create TextField
        txtSearch = new JTextField("Please Enter OrderNo to Search!");
        txtSearch.setBounds(270, 85, 690, 50);
        txtSearch.setForeground(Color.GRAY);
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Please Enter OrderNo to Search!")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setForeground(Color.GRAY);
                    txtSearch.setText("Please Enter OrderNo to Search!");
                }
            }
        });
        // Create Button
        btnSearch = new JButton("Search", imgSearch);
        btnSearch.setBounds(960, 85, 100, 50);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.CYAN);
        
        btnCompleted = new JButton("Completed");
        btnCompleted.setBounds(50,40,120,50);
        
        btnCanceled = new JButton("Cancelled");
        btnCanceled.setBounds(170,40,120,50);
        
        btnViewDetail = new JButton("View Detail");
        btnViewDetail.setBounds(180,30,120,50);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(300,30,120,50);
        // Create Table Model 
        tbOrder = new DefaultTableModel();
        tbOrder.addColumn("OrderNo");
        tbOrder.addColumn("CustomerName");
        tbOrder.addColumn("Address");
        tbOrder.addColumn("Telephone");
        tbOrder.addColumn("Amount");        
        tbOrder.addColumn("OrderDate");
        tbOrder.addColumn("Status");
        tb = new JTable(tbOrder);
        JScrollPane scroll = new JScrollPane(tb);
        loadOrderTable();
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
        pnlC.setBackground(Color.ORANGE);
        pnlC.setLayout(new BorderLayout());
        pnlC.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        pnlC.add(scroll);
        
        // South
        pnlS = new JPanel();
        pnlS.setLayout(new BorderLayout());  
        pnlS.setBackground(Color.ORANGE);
        pnlS.setPreferredSize(new Dimension(0,160));
        
        JPanel pnlSN = new JPanel();
        pnlSN.setLayout(new BorderLayout());
        pnlSN.setPreferredSize(new Dimension(0,130));
        pnlSN.setBackground(Color.WHITE);
        JPanel pnlSNW = new JPanel();
        pnlSNW.setBackground(Color.WHITE);
        pnlSNW.setBorder(BorderFactory.createTitledBorder("Update Status"));
        pnlSNW.setLayout(null);
        pnlSNW.setPreferredSize(new Dimension(350,0));
        pnlSNW.add(btnCompleted);
        pnlSNW.add(btnCanceled);
        JPanel pnlSNC = new JPanel();
        pnlSNC.setLayout(null);
        pnlSNC.setBackground(Color.WHITE);
        pnlSNC.add(btnRefresh);
        pnlSNC.add(btnViewDetail);
        pnlSNC.add(lbPending);
        pnlSNC.add(lbCompleted);
        pnlSNC.add(lbCanceled);
        
        pnlSN.add(pnlSNW, BorderLayout.WEST);
        pnlSN.add(pnlSNC, BorderLayout.CENTER);
        
        JPanel pnlSS = new JPanel();
        pnlSS.add(lbFooter); 
        pnlSS.setBackground(Color.WHITE);
        
        pnlS.add(pnlSN, BorderLayout.NORTH);          
        pnlS.add(pnlSS, BorderLayout.CENTER);
        // Proces
        btnSearch.addActionListener(new ActionListener(){
            @Override
              public void actionPerformed(ActionEvent e){
                String input = txtSearch.getText().trim();
                int orderNo = Integer.parseInt(input);
                try 
                {
                    dbConnection();
                    String sql = "SELECT o.OrderNo, c.Username AS CustomerName, o.Address, c.Telephone, SUM(d.Quantity * d.Price) AS Amount, o.OrderDate, o.Status FROM Orders o JOIN Users c ON o.CustomerID = c.UserID JOIN Details d ON o.OrderNo = d.OrderNo WHERE o.OrderNo = ? GROUP BY o.OrderNo, c.Username, o.Address, c.Telephone, o.OrderDate, o.Status;";
                    cmd = conn.prepareStatement(sql);                   
                    cmd.setString(1,Integer.toString(orderNo));                    
                    rs = cmd.executeQuery();                
                    if (rs.next()==true)
                    {
                        tbOrder.setRowCount(0);
                        tbOrder.addRow(new Object[]{
                        rs.getInt("OrderNo"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("Telephone"),
                        rs.getFloat("Amount"),
                        rs.getString("OrderDate"),
                        rs.getString("Status"),
                        });
                    }   
                    else
                        JOptionPane.showMessageDialog(null, "Order is not found");
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
                catch (Exception exception) {
                        System.out.println(exception);
                }
            }
        });
        btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                txtSearch.setText("");
                loadOrderTable();
            }
        }); 
        btnCompleted.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int row = tb.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(null, "Please select an order to update status!");
                    return;
                }
                int orderNo = (int) tb.getValueAt(row, 0);
                try {
                    dbConnection();
                    String sql = "UPDATE Orders SET Status = 'Completed' WHERE OrderNo = ?";
                    cmd = conn.prepareStatement(sql);
                    cmd.setInt(1, orderNo);
                    int x = cmd.executeUpdate();
                    if(x > 0){
                        JOptionPane.showMessageDialog(null, "Status updated successfully!");
                        loadOrderTable(); 
                        loadStatus("Pending");
                        loadStatus("Completed");
                        loadStatus("Cancelled");
                    } else {
                        JOptionPane.showMessageDialog(null, "Status updated failed!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        
        btnCanceled.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int row = tb.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(null, "Please select an order to update status!");
                    return;
                }
                int orderNo = (int) tb.getValueAt(row, 0);
                try {
                    dbConnection();
                    String sql = "UPDATE Orders SET Status = 'Cancelled' WHERE OrderNo = ?";
                    cmd = conn.prepareStatement(sql);
                    cmd.setInt(1, orderNo);
                    int x = cmd.executeUpdate();
                    if(x > 0){
                        JOptionPane.showMessageDialog(null, "Status updated successfully!");
                        loadOrderTable(); 
                        loadStatus("Pending");
                        loadStatus("Completed");
                        loadStatus("Cancelled");
                    } else {
                        JOptionPane.showMessageDialog(null, "Status updated failed!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        btnViewDetail.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int row = tb.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(null, "Please select an order!");
                    return;
                }
                int orderNo = (int) tb.getValueAt(row, 0);
                new ViewDetail(orderNo);
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
    public void dbConnection(){
        try{
        String dbCon = "jdbc:mysql://localhost:3306/fooddelivery";
        String dbName = "root";
        String dbPass = "manuth@9273$";                    
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(dbCon, dbName, dbPass);
        }
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public void loadOrderTable() {
        try {
            dbConnection();
            String sql = "SELECT o.OrderNo, c.Username AS CustomerName, o.Address, c.Telephone, SUM(d.Quantity * d.Price) AS Amount, o.OrderDate, o.Status FROM Orders o JOIN Users c ON o.CustomerID = c.UserID JOIN Details d ON o.OrderNo = d.OrderNo WHERE o.DeliverymanID = ? GROUP BY o.OrderNo, c.Username, o.Address, c.Telephone, o.OrderDate, o.Status;";
            cmd = conn.prepareStatement(sql);
            cmd.setInt(1, DeliveryID);
            rs = cmd.executeQuery();
            tbOrder.setRowCount(0);
            while (rs.next()) {
                    tbOrder.addRow(new Object[]{
                    rs.getInt("OrderNo"),
                    rs.getString("CustomerName"),
                    rs.getString("Address"),
                    rs.getString("Telephone"),
                    rs.getFloat("Amount"),
                    rs.getString("OrderDate"),
                    rs.getString("Status"),
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
   public void loadStatus(String status) {
        try {
            dbConnection();
            String sql = "SELECT COUNT(OrderNo) AS Total FROM Orders WHERE Status = ? AND DeliverymanID = ?;";
            cmd = conn.prepareStatement(sql);
            cmd.setString(1, status);
            cmd.setInt(2, DeliveryID);
            rs = cmd.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("Total"); // get the count
                switch (status) {
                    case "Pending":
                        lbPending.setText("Pending: " + count);
                        break;
                    case "Completed":
                        lbCompleted.setText("Completed: " + count);
                        break;
                    case "Cancelled":
                        lbCanceled.setText("Cancelled: " + count);
                        break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}