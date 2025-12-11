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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
/**
 *
 * @author manut
 */
public class OrderAdmin extends JFrame {
    Container pane;   
    Connection conn;
    PreparedStatement cmd; 
    ResultSet rs;
    ImageIcon imgOrderM, imgSearch;
    JLabel lbheader, lbSearch;
    JTextField txtSearch;
    JButton btnSearch, btnAssign, btnRefresh, btnViewOrdDetail, btnPrintReceipt;
    JComboBox cmb;
    JPanel pnlN, pnlC, pnlS;
    JTable tb;
    DefaultTableModel tbOrder;
    public OrderAdmin(){
        // Create
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.red);
        // Create Image 
        imgOrderM = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\order.png");
        // Create Label
        lbheader = new JLabel("Order Management", imgOrderM, JLabel.CENTER);
        lbheader.setFont(new Font("Arial", Font.BOLD, 30));
        
        lbSearch = new JLabel("Search: ");
        lbSearch.setFont(new Font("Arial", Font.BOLD, 16));
        lbSearch.setBounds(20,20,100,30);

        // Create TextField 
        txtSearch = new JTextField();
        txtSearch.setBounds(100,15,500,40);
        // Create Combobox
        cmb = new JComboBox();
        cmb.setBounds(50,30,300,50);
        cmb.addItem("3-Vitur");
        cmb.addItem("2-Visal");
        cmb.addItem("3-Rayuth");
        cmb.addItem("4-Piseth");
        cmb.addItem("5-Savin");
        // Create Button 
        btnSearch = new JButton("Search");
        btnSearch.setBounds(600,15,100,40);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.CYAN);
        
        btnAssign = new JButton("Assign");
        btnAssign.setBounds(350,30,100,50);
        
        btnViewOrdDetail = new JButton("View Detail");
        btnViewOrdDetail.setBounds(30,20,100,50); 
        
        btnRefresh = new JButton("Refesh");
        btnRefresh.setBounds(130,20,100,50);
        
        btnPrintReceipt = new JButton("Print Receipt");
        // Create Table
        tbOrder = new DefaultTableModel();
        tbOrder.addColumn("OrderNo");
        tbOrder.addColumn("CustomerName");
        tbOrder.addColumn("DeliverymanID");
        tbOrder.addColumn("OrderDate");
        tbOrder.addColumn("Total Amount");
        tbOrder.addColumn("Status");
        tb = new JTable(tbOrder);
        JScrollPane scroll = new JScrollPane(tb);
        loadOrderTable();
        // Create Panel
        pnlN = new JPanel();
        pnlN.setBackground(Color.WHITE);
        pnlN.add(lbheader);
        
        // Center 
        pnlC = new JPanel();
        pnlC.setLayout(new BorderLayout());
        // Center West
        JPanel pnlCW = new JPanel();
        pnlCW.setPreferredSize(new Dimension(750,0));
        pnlCW.setBackground(Color.WHITE);
        pnlCW.setBorder(BorderFactory.createTitledBorder(""));
        pnlCW.setLayout(new BorderLayout());
        
        JPanel pnlCWN = new JPanel();
        pnlCWN.setLayout(null);
        pnlCWN.setPreferredSize(new Dimension(0,70));
        pnlCWN.setBackground(Color.WHITE);
        pnlCWN.add(lbSearch);
        pnlCWN.add(txtSearch);
        pnlCWN.add(btnSearch);
        
        JPanel pnlCWC = new JPanel();
        pnlCWC.setLayout(new BorderLayout());
        pnlCWC.setBorder(BorderFactory.createTitledBorder(""));
        pnlCWC.add(scroll);
        pnlCWC.setBackground(Color.WHITE);
        
        JPanel pnlCWS = new JPanel();
        pnlCWS.setLayout(new BorderLayout());
        pnlCWS.setPreferredSize(new Dimension(0,100));
        JPanel pnlCWSW = new JPanel();
        pnlCWSW.setBorder(BorderFactory.createTitledBorder("Deliveryman Assignment"));
        pnlCWSW.setLayout(null);
        pnlCWSW.setPreferredSize(new Dimension(500,0));
        pnlCWSW.setBackground(Color.WHITE);
        pnlCWSW.add(cmb);
        pnlCWSW.add(btnAssign);  
        JPanel pnlCWSE = new JPanel();
        pnlCWSE.setBackground(Color.WHITE);
        pnlCWSE.setLayout(null);
        pnlCWSE.add(btnViewOrdDetail);
        pnlCWSE.add(btnRefresh);        
        pnlCWS.add(pnlCWSW, BorderLayout.WEST);
        pnlCWS.add(pnlCWSE, BorderLayout.CENTER);        
        
        pnlCW.add(pnlCWN, BorderLayout.NORTH);
        pnlCW.add(pnlCWC, BorderLayout.CENTER);
        pnlCW.add(pnlCWS, BorderLayout.SOUTH);
        
        // Center East
        JPanel pnlCE = new JPanel();
        pnlCE.setLayout(new BorderLayout());
        pnlCE.setBackground(Color.WHITE);
        pnlCE.setBorder(BorderFactory.createTitledBorder(""));
        
        JPanel pnlCEC = new JPanel();
        pnlCEC.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        pnlCEC.setBackground(Color.WHITE);
        
        JPanel pnlCES = new JPanel();
        pnlCES.setBackground(Color.WHITE);
        pnlCES.setLayout(new GridLayout(1,5));
        pnlCES.setPreferredSize(new Dimension(0,50));
        pnlCES.add(new JLabel(""));
        pnlCES.add(new JLabel(""));      
        pnlCES.add(btnPrintReceipt);
        pnlCES.add(new JLabel(""));
        pnlCES.add(new JLabel(""));
        
        pnlCE.add(pnlCES, BorderLayout.SOUTH);
        pnlCE.add(pnlCEC, BorderLayout.CENTER);
        
        pnlC.add(pnlCW, BorderLayout.WEST);
        pnlC.add(pnlCE,BorderLayout.CENTER);
        
        pnlS = new JPanel();
        pnlS.setBackground(Color.WHITE);
        pnlS.setPreferredSize(new Dimension(0,60));
        // Add Components to Frame
        pane.add(pnlN, BorderLayout.NORTH);
        pane.add(pnlC, BorderLayout.CENTER);
        pane.add(pnlS, BorderLayout.SOUTH);  
        // Process
        btnAssign.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int row = tb.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(null, "Please select an order to assign!");
                    return;
                }
                int orderNo = (int) tb.getValueAt(row, 0);
                String selected = (String) cmb.getSelectedItem();
                String[] parts = selected.split("-");
                int deliveryId = Integer.parseInt(parts[0]);
                try {
                    dbConnection();
                    String sql = "UPDATE Orders SET DeliveryID = ? WHERE OrderNo = ?";
                    cmd = conn.prepareStatement(sql);
                    cmd.setInt(1, deliveryId);
                    cmd.setInt(2, orderNo);
                    int x = cmd.executeUpdate();
                    if(x > 0){
                        JOptionPane.showMessageDialog(null, "Deliveryman assigned successfully!");
                        loadOrderTable(); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Assignment failed!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
        btnSearch.addActionListener(new ActionListener(){
            @Override
              public void actionPerformed(ActionEvent e){
                String input = txtSearch.getText().trim();
                int orderNo = Integer.parseInt(input);
                try 
                {
                    dbConnection();
                    String sql = "SELECT o.OrderNo, c.Username AS CustomerName, o.DeliveryID, o.OrderDate, p.Amount, o.Status FROM Orders o JOIN Users c ON o.CustomerID = c.UserID LEFT JOIN Payments p ON o.OrderNo = p.OrderNo WHERE o.OrderNo = ?;";
                    cmd = conn.prepareStatement(sql);                   
                    cmd.setString(1,Integer.toString(orderNo));                    
                    rs = cmd.executeQuery();                
                    if (rs.next()==true)
                    {
                        Object deliveryId = rs.getObject("DeliveryID"); // Integer or null
                        tbOrder.setRowCount(0);
                        tbOrder.addRow(new Object[]{
                        rs.getInt("OrderNo"),
                        rs.getString("CustomerName"),
                        deliveryId,
                        rs.getString("OrderDate"),
                        rs.getFloat("Amount"),
                        rs.getString("Status"),
                        
                        });
                    }   
                    else
                        JOptionPane.showMessageDialog(null, "Order is not found");
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadOrderTable();
            }
        });
        // Show
        pane.setVisible(true);
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
            String sql = "SELECT o.OrderNo, c.Username AS CustomerName, o.DeliveryID, o.OrderDate, p.Amount, o.Status FROM Orders o JOIN Users c ON o.CustomerID = c.UserID LEFT JOIN Payments p ON o.OrderNo = p.OrderNo;";
            cmd = conn.prepareStatement(sql);
            rs = cmd.executeQuery();
            tbOrder.setRowCount(0);
            while (rs.next()) {
                    Object deliveryId = rs.getObject("DeliveryID"); // Integer or null
                    tbOrder.addRow(new Object[]{
                    rs.getInt("OrderNo"),
                    rs.getString("CustomerName"),
                    deliveryId,
                    rs.getString("OrderDate"),
                    rs.getFloat("Amount"),
                    rs.getString("Status"),                    
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
