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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
/**
 *
 * @author manut
 */
public class ProductAdmin extends JFrame{
    Container pane;
    Connection conn;
    PreparedStatement cmd; 
    ResultSet rs; 
    JLabel headerLb, searchLb, proNoLb, proNameLb, cateNoLb, priceLb, stockLb;
    JTextField txtSearch, txtProNo, txtProName, txtCateNo, txtPrice, txtStock;
    JButton btnSearch, btnAdd, btnUpdate, btnDelete, btnRefresh, btnClear, btnMainDish, btnDrink, btnSnack, btnDessert, btnFastfood;
    JPanel pnlW, pnlE, pnlS, pnlN;
    JTable tb;
    DefaultTableModel tbProduct;
    ImageIcon imgProductM, imgSearch;
    public ProductAdmin(){
        // Create 
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        //pane.setBackground(Color.red);
        // Create Image
        imgProductM = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\product-management.png");
        imgSearch = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\search.png");
        // Create Label
        headerLb = new JLabel("Products Management", imgProductM, JLabel.CENTER);
        headerLb.setFont(new Font("Arial", Font.BOLD, 30));
        headerLb.setBounds(400, 10, 400, 70);
        
        searchLb = new JLabel("Search: ");
        searchLb.setFont(new Font("Arial", Font.BOLD, 16));
        searchLb.setBounds(100,25,100,30);
        
        proNoLb = new JLabel("ProductNo: ");
        proNoLb.setFont(new Font("Arial", Font.BOLD, 14));
        proNoLb.setBounds(40,90,100,30);
        
        proNameLb = new JLabel("ProductName: ");
        proNameLb.setFont(new Font("Arial", Font.BOLD, 14));
        proNameLb.setBounds(40,160,120,30);
        
        cateNoLb = new JLabel("CategoryNo: ");
        cateNoLb.setFont(new Font("Arial", Font.BOLD, 14));
        cateNoLb.setBounds(40,230,100,30);
        
        priceLb = new JLabel("Price: ");
        priceLb.setFont(new Font("Arial", Font.BOLD, 14));
        priceLb.setBounds(40,300,100,30);
        
        stockLb = new JLabel("Stock: ");
        stockLb.setFont(new Font("Arial", Font.BOLD, 14));
        stockLb.setBounds(40,370,100,30);
        // Create TextField
        txtSearch = new JTextField();
        txtSearch.setBounds(170,20,330,40);
        
        txtProNo = new JTextField();
        txtProNo.setBounds(170,85,430,40);
        
        txtProName = new JTextField();
        txtProName.setBounds(170,155,430,40);
        
        txtCateNo = new JTextField();
        txtCateNo.setBounds(170,225,430,40);
        
        txtPrice = new JTextField();
        txtPrice.setBounds(170,295,430,40);
        
        txtStock = new JTextField();
        txtStock.setBounds(170,365,430,40);
        // Create Button
        btnSearch = new JButton("Search", imgSearch);
        btnSearch.setBounds(500,20, 100, 40);
        btnSearch.setBackground(Color.WHITE);
        btnSearch.setForeground(Color.CYAN);
        btnAdd = new JButton("Add Product");
        btnUpdate = new JButton("Update Product");
        btnDelete = new JButton("Delete Product");
        btnRefresh = new JButton("Refresh");
        btnClear = new JButton("Clear");
        btnMainDish = new JButton("Main Dishes");
        btnDrink = new JButton("Drinks");
        btnSnack = new JButton("Snacks");
        btnDessert = new JButton("Desserts");
        btnFastfood = new JButton("Fast Food");        
        // Create Table
        tbProduct = new DefaultTableModel();
        tbProduct.addColumn("ProductNo");
        tbProduct.addColumn("ProductName");
        tbProduct.addColumn("CategoryNo");
        tbProduct.addColumn("Price");
        tbProduct.addColumn("Stock");
        tb = new JTable(tbProduct);
        JScrollPane scroll = new JScrollPane(tb);
        loadProductTable();
        // Create Panel
        pnlW = new JPanel();
        pnlW.setLayout(new BorderLayout());
        pnlW.setBorder(BorderFactory.createTitledBorder("Products"));
        pnlW.setPreferredSize(new Dimension(600,0));
        pnlW.setBackground(Color.WHITE);
        pnlW.add(scroll);
        
        pnlE = new JPanel();
        pnlE.setLayout(null);
        pnlE.setPreferredSize(new Dimension(700,0));
        pnlE.setBackground(Color.WHITE);
        pnlE.add(searchLb);
        pnlE.add(txtSearch);       
        pnlE.add(btnSearch);
        pnlE.add(proNoLb);
        pnlE.add(txtProNo);
        pnlE.add(proNameLb);
        pnlE.add(txtProName);
        pnlE.add(cateNoLb);
        pnlE.add(txtCateNo);
        pnlE.add(priceLb);
        pnlE.add(txtPrice);
        pnlE.add(stockLb);
        pnlE.add(txtStock);
        
        pnlS = new JPanel();
        pnlS.setLayout(new BorderLayout());
        pnlS.setPreferredSize(new Dimension(0,100));
        pnlS.setBackground(Color.WHITE);
        
        JPanel pnlSW = new JPanel();
        pnlSW.setLayout(new GridLayout(1,5));
        pnlSW.setBackground(Color.WHITE);
        pnlSW.setPreferredSize(new Dimension(600,0));
        pnlSW.setBorder(BorderFactory.createTitledBorder("Categories"));
        pnlSW.add(btnMainDish);
        pnlSW.add(btnDrink);
        pnlSW.add(btnSnack);
        pnlSW.add(btnDessert);
        pnlSW.add(btnFastfood);
        
        JPanel pnlSE = new JPanel();
        pnlSE.setBackground(Color.WHITE);
        pnlSE.setLayout(new GridLayout(1,5));
        pnlSE.setPreferredSize(new Dimension(690,0));
        pnlSE.setBorder(BorderFactory.createTitledBorder("Actions"));
        pnlSE.add(btnAdd);
        pnlSE.add(btnUpdate);
        pnlSE.add(btnDelete);
        pnlSE.add(btnRefresh);
        pnlSE.add(btnClear);     
        
        pnlS.add(pnlSW, BorderLayout.WEST);
        pnlS.add(pnlSE, BorderLayout.EAST);
                
        pnlN = new JPanel();
        pnlN.setLayout(null);
        pnlN.setPreferredSize(new Dimension(0,100));
        pnlN.setBackground(Color.CYAN);
        pnlN.add(headerLb);
        // Add Components to Frame
        pane.add(pnlN, BorderLayout.NORTH);
        pane.add(pnlS, BorderLayout.SOUTH);
        pane.add(pnlW, BorderLayout.WEST);
        pane.add(pnlE, BorderLayout.EAST);
        // Show
        pane.setVisible(true);
    }
    public Container getPane(){
        return pane;
    }
    public void loadProductTable() {
        try {
            String dbCon = "jdbc:mysql://localhost:3306/fooddelivery";
            String dbUserName = "root";
            String dbPass = "manuth@9273$";  
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbCon, dbUserName, dbPass);
            String sql = "SELECT ProNo, ProName, CategoryNo, Price, Stock FROM Products";
            cmd = conn.prepareStatement(sql);
            rs = cmd.executeQuery();
            tbProduct.setRowCount(0);
            while (rs.next()) {
                    tbProduct.addRow(new Object[]{
                    rs.getInt("ProNo"),
                    rs.getString("ProName"),
                    rs.getString("CategoryNo"),
                    rs.getString("Price"),
                    rs.getString("Stock"),
                });
            }
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
