/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author manut
 */
public final class OrderUser extends JFrame{
    Container pane;
    Connection conn;
    PreparedStatement cmdOrder; 
    ResultSet rs; 
    JLabel lbGrandTotal;
    JTextField txtGrandTotal;
    JButton btnClear, btnOrder,btnDelete, btnAddToCart, btnMainDish, btnDrink, btnSnack, btnDessert, btnFastfood;
    JPanel pnlW, pnlE;
    JTable tb;
    DefaultTableModel tbDetails;  
    public int userID;
    public OrderUser(int userID){
        this.userID = userID;
        // Create 
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.red);
        // Create Label
        lbGrandTotal = new JLabel("Grand Total: ", JLabel.CENTER);
        lbGrandTotal.setFont(new Font("Arial", Font.BOLD, 16));
        // Create TextField
        txtGrandTotal = new JTextField();
        // Create Button
        btnMainDish = new JButton("1-Main Dishes");
        btnDrink = new JButton("2-Drinks");
        btnSnack = new JButton("3-Snacks");
        btnDessert = new JButton("4-Desserts");
        btnFastfood = new JButton("5-Fast Food"); 
        btnAddToCart = new JButton("Add to cart"); 
        btnClear = new JButton("Clear"); 
        btnDelete = new JButton("Delete"); 
        btnOrder = new JButton("Order"); 
        
        // Create Table
        tbDetails = new DefaultTableModel();
        tbDetails.addColumn("ProNo");
        tbDetails.addColumn("ProName");
        tbDetails.addColumn("Quantity");
        tbDetails.addColumn("Price");
        tbDetails.addColumn("Total");
        tb = new JTable(tbDetails);
        JScrollPane scroll = new JScrollPane(tb);
        // Create Panel       
        pnlW = new JPanel();
        pnlW.setBackground(Color.WHITE);
        pnlW.setLayout(new BorderLayout());
        // Categories
        JPanel pnlWN = new JPanel();
        pnlWN.setBackground(Color.WHITE);
        pnlWN.setPreferredSize(new Dimension(0,100));
        pnlWN.setLayout(new GridLayout(1,5));
        pnlWN.setBorder(BorderFactory.createTitledBorder("Categories"));
        pnlWN.add(btnMainDish);
        pnlWN.add(btnDrink);
        pnlWN.add(btnSnack);
        pnlWN.add(btnDessert);
        pnlWN.add(btnFastfood);   
        // Menu  Lists
        JPanel pnlWC = new JPanel();
        pnlWC.setBackground(Color.WHITE);
        pnlWC.setBorder(BorderFactory.createTitledBorder("Menu"));
        pnlWC.setLayout(new GridLayout(3,5));
        pnlWC.add(createFoodCard(1, "Burger.png", "Burger", 4.5f));
        pnlWC.add(createFoodCard(2, "Pizza.png", "Pizza", 5f));
        pnlWC.add(createFoodCard(3, "SetBurger.png", "BurgerSet", 10f));
        pnlWC.add(createFoodCard(4, "FriedChicken.png", "Fried Chicken", 7f));
        pnlWC.add(createFoodCard(5, "HotDog.png", "Hot Dog", 2.5f));
        
        pnlWC.add(createFoodCard(6, "Pizza.png", "Pizza", 4.99f));
        pnlWC.add(createFoodCard(7, "Pizza.png", "Pizza", 4.99f));
        pnlWC.add(createFoodCard(8, "Pizza.png", "Pizza", 4.99f));
        pnlWC.add(createFoodCard(9, "Pizza.png", "Pizza", 4.99f));
        pnlWC.add(createFoodCard(10, "Pizza.png", "Pizza", 4.99f));

        pnlWC.add(createFoodCard(11, "Pizza.png", "Pizza", 4.99f));
        pnlWC.add(createFoodCard(12, "Pizza.png", "Pizza", 4.99f));
        pnlWC.add(createFoodCard(13, "Pizza.png", "Pizza", 4.99f));
        pnlWC.add(createFoodCard(14, "Pizza.png", "Pizza", 4.99f));
        pnlWC.add(createFoodCard(15,"Pizza.png", "Pizza", 4.99f));   
        // Add to Cart
        JPanel pnlWS = new JPanel();
        pnlWS.setPreferredSize(new Dimension(0,50));
        pnlWS.add(btnAddToCart);
        
        pnlW.add(pnlWN, BorderLayout.NORTH);
        pnlW.add(pnlWC, BorderLayout.CENTER);
        pnlW.add(pnlWS, BorderLayout.SOUTH);
        // RIGHT
        pnlE = new JPanel();
        pnlE.setLayout(new BorderLayout());
        pnlE.setPreferredSize(new Dimension(450,0));
        pnlE.setBackground(Color.BLUE);
        JPanel pnlEN = new JPanel();
        pnlEN.setPreferredSize(new Dimension(0,500));
        pnlEN.setLayout(new BorderLayout());
        pnlEN.setBackground(Color.WHITE);
        pnlEN.setBorder(BorderFactory.createTitledBorder("Summary"));
        pnlEN.add(scroll);
        JPanel pnlES = new JPanel();
        pnlES.setLayout(new GridLayout(2,3));
        pnlES.setBackground(Color.WHITE);
        pnlES.setBorder(BorderFactory.createTitledBorder(""));
        pnlES.add(lbGrandTotal);
        pnlES.add(txtGrandTotal);
        pnlES.add(new JLabel(""));
        pnlES.add(btnOrder);
        pnlES.add(btnDelete);
        pnlES.add(btnClear);
        pnlE.add(pnlEN, BorderLayout.NORTH);
        pnlE.add(pnlES, BorderLayout.CENTER);
        // Process
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component[] cards = pnlWC.getComponents();
                for (Component c : cards) {
                    if (c instanceof JPanel panel) {
                        JSpinner sp = (JSpinner) panel.getClientProperty("spinner");
                        int qty = (Integer) sp.getValue();
                        if (qty > 0) {
                            int proNo = (int) panel.getClientProperty("proNo");
                            String name = (String) panel.getClientProperty("name");
                            float price = (float) panel.getClientProperty("price");
                            float total = price * qty;
                            // Add to table
                            tbDetails.addRow(new Object[]{proNo, name, qty, price, total});
                            sp.setValue(0);
                        }
                    }
                }
                double grandTotal = 0;
                for (int row = 0; row < tbDetails.getRowCount(); row++) {
                    grandTotal += Double.parseDouble(tbDetails.getValueAt(row, 4).toString());
                }
                txtGrandTotal.setText(String.valueOf(grandTotal));
            }
        }); 
        btnOrder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    dbConnection();
                    String sqlOrder = "INSERT INTO Orders (CustomerID, DeliveryID, Status) VALUES (?, NULL, 'Pending')";
                    cmdOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                    cmdOrder.setInt(1, userID);
                    int x = cmdOrder.executeUpdate();
                    if(x > 0){
                        JOptionPane.showMessageDialog(null, "Insert Orders is done!");
                    }
                    int orderNo = 0;
                    rs = cmdOrder.getGeneratedKeys();
                    if (rs.next()) {
                        orderNo = rs.getInt(1);
                    }

                    if (orderNo == 0) {
                        JOptionPane.showMessageDialog(null, "Failed to get Order Number!");
                        conn.rollback();
                        return;
                    }
                    String sqlDetail = "INSERT INTO Details(OrderNo, ProNo, Quantity, Price) VALUES (?, ?, ?, ?)";
                    PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
                    for (int i = 0; i < tbDetails.getRowCount(); i++) {
                        int proNo = Integer.parseInt(tbDetails.getValueAt(i, 0).toString());
                        int qty = Integer.parseInt(tbDetails.getValueAt(i, 2).toString());
                        double price = Double.parseDouble(tbDetails.getValueAt(i, 3).toString());
                        
                        psDetail.setInt(1, orderNo);
                        psDetail.setInt(2, proNo);
                        psDetail.setInt(3, qty);
                        psDetail.setDouble(4, price);
                        psDetail.executeUpdate();
                    }
                    JOptionPane.showMessageDialog(null, "Order placed successfully!\nOrder No: " + orderNo);
                    // CLEAR TABLE + TOTAL
                    tbDetails.setRowCount(0);
                    txtGrandTotal.setText("");
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = tb.getSelectedRow(); // get selected row

                if (row == -1) {
                    JOptionPane.showMessageDialog(null, 
                            "Please select a row to delete!",
                            "No Selection",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                tbDetails.removeRow(row);
                double grandTotal = 0;
                for (int i = 0; i < tbDetails.getRowCount(); i++) {
                    grandTotal += Double.parseDouble(tbDetails.getValueAt(i, 4).toString());
                }
                txtGrandTotal.setText(String.valueOf(grandTotal));
            }
        });
        btnClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                tbDetails.setRowCount(0);
                txtGrandTotal.setText("");
            }
        });
        // Add to Frame 
        pane.add(pnlE, BorderLayout.EAST);
        pane.add(pnlW, BorderLayout.CENTER);
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
    public JPanel createFoodCard(int proNo, String imgPath, String name, float price) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(Color.WHITE);
        // Image
        ImageIcon icon = new ImageIcon(getClass().getResource("/fooddelivery/images/" + imgPath));
        Image img = icon.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH);
        JLabel lbImg = new JLabel(new ImageIcon(img));
        // Price
        JPanel pnlInfo = new JPanel(new GridLayout(2, 1));
        pnlInfo.setBackground(Color.WHITE);
        
        JLabel lbName = new JLabel(name, JLabel.CENTER);
        lbName.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lbPrice = new JLabel("$" + price, JLabel.CENTER);
        lbPrice.setFont(new Font("Arial", Font.PLAIN, 13));
        lbPrice.setForeground(Color.DARK_GRAY);
        
        pnlInfo.add(lbName);
        pnlInfo.add(lbPrice);
        // Quantity
        JPanel pnlBottom = new JPanel();
        pnlBottom.setBackground(Color.WHITE);
        JLabel lbQty = new JLabel("Qty:");
        JSpinner spQty = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
        spQty.setPreferredSize(new Dimension(50, 25));
        pnlBottom.add(lbQty);
        pnlBottom.add(spQty);   
        // Add to Card
        card.add(lbImg, BorderLayout.NORTH);
        card.add(pnlInfo, BorderLayout.CENTER);
        card.add(pnlBottom, BorderLayout.SOUTH);     
        // Store Data
        card.putClientProperty("proNo", proNo);
        card.putClientProperty("name", name);
        card.putClientProperty("price", price);
        card.putClientProperty("spinner", spQty);
        return card;
    }
}
