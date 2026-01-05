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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
/**
 *
 * @author manut
 */
public final class ProductAdmin extends JFrame{
    Container pane;
    Connection conn;
    PreparedStatement cmd; 
    ResultSet rs; 
    JLabel headerLb, searchLb, proNoLb, proNameLb, cateNoLb, priceLb, stockLb;
    JTextField txtSearch, txtProNo, txtProName, txtPrice, txtStock;
    JComboBox cmbCateNo;
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
        txtSearch = new JTextField("Please Enter ProductNo to Search!");
        txtSearch.setBounds(170,20,330,40);
        txtSearch.setForeground(Color.GRAY);
        txtSearch.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Please Enter ProductNo to Search!")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setForeground(Color.GRAY);
                    txtSearch.setText("Please Enter ProductNo to Search!");
                }
            }
        });
        
        txtProNo = new JTextField();
        txtProNo.setBounds(170,85,430,40);
        txtProNo.setEditable(false);
        txtProNo.setBackground(Color.LIGHT_GRAY); 
        txtProNo.setDisabledTextColor(Color.BLACK);

        txtProName = new JTextField();
        txtProName.setBounds(170,155,430,40);
        
        txtPrice = new JTextField();
        txtPrice.setBounds(170,295,430,40);
        
        txtStock = new JTextField();
        txtStock.setBounds(170,365,430,40);
        // Create ComboBox
        cmbCateNo = new JComboBox();
        cmbCateNo.setBounds(170,225,430,40);
        cmbCateNo.addItem(null);  
        cmbCateNo.addItem(1);
        cmbCateNo.addItem(2);
        cmbCateNo.addItem(3);
        cmbCateNo.addItem(4);
        cmbCateNo.addItem(5);
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
        btnMainDish = new JButton("1-Main Dishes");
        btnDrink = new JButton("2-Drinks");
        btnSnack = new JButton("3-Snacks");
        btnDessert = new JButton("4-Desserts");
        btnFastfood = new JButton("5-Fast Food");        
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
        pnlE.add(cmbCateNo);
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
        // Process
        btnSearch.addActionListener(new ActionListener(){
            @Override
              public void actionPerformed(ActionEvent e){
                String input = txtSearch.getText().trim();
                int proNo = Integer.parseInt(input);
                try 
                {
                    dbConnection();
                    String sql = "SELECT ProNo, ProName, CategoryNo, Price, Stock FROM Products WHERE ProNo = ?;";
                    cmd = conn.prepareStatement(sql);                   
                    cmd.setString(1,Integer.toString(proNo));                    
                    rs = cmd.executeQuery();                
                    if (rs.next()==true)
                    {
                        tbProduct.setRowCount(0);
                        tbProduct.addRow(new Object[]{
                        rs.getInt("ProNo"),
                        rs.getString("ProName"),
                        rs.getString("CategoryNo"),
                        rs.getString("Price"),
                        rs.getString("Stock"),
                        });
                        txtProNo.setText(String.valueOf(rs.getInt("ProNo")));
                        txtProName.setText(rs.getString("ProName"));
                        cmbCateNo.setSelectedItem(rs.getInt("CategoryNo"));
                        txtPrice.setText(rs.getString("Price"));
                        txtStock.setText(rs.getString("Stock"));
                    }   
                    else
                        JOptionPane.showMessageDialog(null, "Product is not found");
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (txtProName.getText().trim().isEmpty() || cmbCateNo.getSelectedItem() == null || txtPrice.getText().trim().isEmpty() || txtStock.getText().trim().isEmpty()) 
                {                    
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                }  
                String proName = txtProName.getText();
                int cateNo = (Integer) cmbCateNo.getSelectedItem();
                float price = Float.parseFloat(txtPrice.getText());
                int stock = Integer.parseInt(txtStock.getText());
                try 
                {
                    dbConnection();
                    
                    String sql = "INSERT INTO Products(ProName,CategoryNo,Price,Stock)VALUES(?, ?, ?, ?);";
                    cmd = conn.prepareStatement(sql);            
                    cmd.setString(1, proName); 
                    cmd.setInt(2, cateNo);
                    cmd.setFloat(3, price);                  
                    cmd.setInt(4, stock);
                    cmd.executeUpdate();  
                    JOptionPane.showMessageDialog(null, "Product added successfully!");
                    loadProductTable();
                    txtProName.setText("");
                    cmbCateNo.setSelectedItem(null);
                    txtPrice.setText("");
                    txtStock.setText("");
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Product added failed! Error: " + ex.getMessage());

                }
                catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){ 
                if (txtProNo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please search a product first!");
                    return;
                }
                if (txtProName.getText().trim().isEmpty() || cmbCateNo.getSelectedItem() == null || txtPrice.getText().trim().isEmpty() || txtStock.getText().trim().isEmpty()) 
                {                    
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                }  
                int proNo = Integer.parseInt(txtProNo.getText());
                String proName = txtProName.getText();
                int cateNo = (Integer) cmbCateNo.getSelectedItem();
                float price = Float.parseFloat(txtPrice.getText());
                int stock = Integer.parseInt(txtStock.getText());
                try {
                    dbConnection();        
                    String sql = "UPDATE Products SET ProName = ?, CategoryNo = ?, Price = ?, Stock = ? WHERE ProNo = ?;";
                    cmd = conn.prepareStatement(sql);            
                    cmd.setString(1, proName); 
                    cmd.setInt(2, cateNo);
                    cmd.setFloat(3, price);                  
                    cmd.setInt(4, stock);
                    cmd.setInt(5, proNo);
                    int x = cmd.executeUpdate();    
                    if (x>0){
                        JOptionPane.showMessageDialog(null, "Product updated successfully!"); 
                        loadProductTable();
                        txtSearch.setText("");
                        txtProNo.setText("");
                        txtProName.setText("");
                        cmbCateNo.setSelectedItem(null);
                        txtPrice.setText("");
                        txtStock.setText("");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Product updated failed!");
                } 
                catch (SQLException ex) {
                        ex.printStackTrace();    
                }
                catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        btnDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (txtProNo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please search a product first!");
                    return;
                }
                int proNo = Integer.parseInt(txtProNo.getText());
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Confirm Delete", JOptionPane.YES_NO_OPTION
                );
                if (choice != JOptionPane.YES_OPTION) return;
                try{
                    dbConnection();
                    String sql = "DELETE FROM Products WHERE ProNo = ?";
                    cmd = conn.prepareStatement(sql);                   
                    cmd.setInt(1, proNo);                    
                    int x = cmd.executeUpdate();
                    if(x > 0){
                        txtSearch.setText("");
                        txtProNo.setText("");
                        txtProName.setText("");
                        cmbCateNo.setSelectedItem(null);
                        txtPrice.setText("");
                        txtStock.setText("");
                        loadProductTable();
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
        btnRefresh.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadProductTable();
                txtSearch.setText("");
                txtProNo.setText("");
                txtProName.setText("");
                cmbCateNo.setSelectedItem(null);
                txtPrice.setText("");
                txtStock.setText("");
            }
        });
        btnClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                txtSearch.setText("");
                txtProNo.setText("");
                txtProName.setText("");
                cmbCateNo.setSelectedItem(null);
                txtPrice.setText("");
                txtStock.setText("");
                tbProduct.setRowCount(0);    
            }
        });  
        btnMainDish.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadProductTable(1);
            }
        });
        btnDrink.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadProductTable(2);
            }
        });
        btnSnack.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadProductTable(3);
            }
        });
        btnDessert.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadProductTable(4);
            }
        });
        btnFastfood.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadProductTable(5);
            }
        });
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
    public void loadProductTable() {
        try {
            dbConnection();
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public void loadProductTable(int cateNo) {
        try {
            dbConnection();
            String sql = "SELECT ProNo, ProName, CategoryNo, Price, Stock FROM Products WHERE CategoryNo = ?";
            cmd = conn.prepareStatement(sql);
            cmd.setInt(1, cateNo);
            rs = cmd.executeQuery();
            tbProduct.setRowCount(0); 
            while (rs.next()) {
                tbProduct.addRow(new Object[]{
                    rs.getInt("ProNo"),
                    rs.getString("ProName"),
                    rs.getInt("CategoryNo"),
                    rs.getFloat("Price"),
                    rs.getInt("Stock")
                });
            }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
