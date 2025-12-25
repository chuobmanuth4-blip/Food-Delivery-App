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
    PreparedStatement cmdOrder, cmdDetail; 
    ResultSet rs; 
    JLabel lbGrandTotal, lbAddress;
    JTextField txtGrandTotal, txtAddress, txtPhone;
    JButton btnClear, btnOrder,btnDelete, btnAddToCart, btnMainDish, btnDrink, btnSnack, btnDessert, btnFastfood;
    JPanel pnlW, pnlE, pnlWC, pnlFastFood, pnlMainDish, pnlDrink, pnlSnack, pnlDessert;
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
        
        lbAddress = new JLabel("Address: ", JLabel.CENTER);
        lbAddress.setFont(new Font("Arial", Font.BOLD, 16));
        // Create TextField
        txtGrandTotal = new JTextField();
        txtAddress = new JTextField();
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
        pnlWC = new JPanel();
        pnlWC = new JPanel(new BorderLayout());
        pnlWC.setBackground(Color.WHITE);
        pnlWC.setBorder(BorderFactory.createTitledBorder("Menu"));
        // Fast Food
        pnlFastFood = new JPanel(new GridLayout(3, 5)); 
        pnlFastFood.add(createFoodCard(1, "Burger.png", "Cheese Burger", 4.5f));
        pnlFastFood.add(createFoodCard(2, "Pizza.png", "Pizza", 5f));
        pnlFastFood.add(createFoodCard(3, "SetBurger.png", "Burger Set", 10f));
        pnlFastFood.add(createFoodCard(4, "Fried Chicken (2 pcs).png", "Fried Chicken", 4f));
        pnlFastFood.add(createFoodCard(5, "Hot Dog.png", "Hog Dog", 2.5f));
        
        pnlFastFood.add(createFoodCard(6, "Chicken Nuggets.png", "Chicken Nuggets", 3f));
        pnlFastFood.add(createFoodCard(7, "French Fries.png", "French Fries", 1f));
        pnlFastFood.add(createFoodCard(8, "Sandwich.png", "Sandwich", 1f));
        pnlFastFood.add(createFoodCard(9, "Cheese Sticks.png", "Cheese Sticks", 2.5f));
        pnlFastFood.add(createFoodCard(10, "Pizza Slice.png", "Pizza Slice", 1f));

        pnlFastFood.add(createFoodCard(11, "FriedChicken.png", "Fried Chicken Set", 5f));
        pnlFastFood.add(createFoodCard(12, "quesadilla.png", "Quesadilla", 1f));
        pnlFastFood.add(createFoodCard(13, "Tacos.png", "Tacos", 1f));
        pnlFastFood.add(createFoodCard(14, "wraps.png", "Wraps", 1f));
        pnlFastFood.add(createFoodCard(15,"onionRing.png", "Onion Rings", 2.5f));  
        // Main Dishes
        pnlMainDish = new JPanel(new GridLayout(3, 5));
        pnlMainDish.add(createFoodCard(16, "Fish Amok.png", "Fish Amok", 4.5f));
        pnlMainDish.add(createFoodCard(17, "Lok Lak.png", "Lok Lak ", 5f));
        pnlMainDish.add(createFoodCard(18, "Khmer Curry Chicken.png", "Khmer Curry Chicken", 4.5f));
        pnlMainDish.add(createFoodCard(19, "Grilled Pork with Rice.png", "Grilled Pork with Rice", 3.5f));
        pnlMainDish.add(createFoodCard(20, "Stir-fried Morning Glory.png", "Stir Fired Morning Glory", 2.5f));
        
        pnlMainDish.add(createFoodCard(21, "Fried Rice with Shrimp.png", "Fried Rice with Shrimp", 2.5f));
        pnlMainDish.add(createFoodCard(22, "Kuy Teav.png", "Kuy Teav", 2f));
        pnlMainDish.add(createFoodCard(23, "BBQ Duck.png", "BBQ Duck", 6f));
        pnlMainDish.add(createFoodCard(24, "Cambodian Sour Soup.png", "Cambodian Sour Soup", 4f));
        pnlMainDish.add(createFoodCard(25, "grilled fish with lemongrass.jpg", "Grilled Fish", 5f));

        pnlMainDish.add(createFoodCard(26, "Fried Tofu with Vegetables.png", "Fried Tofu", 4f));
        pnlMainDish.add(createFoodCard(27, "Cambodian Hotpot.png", "Cambodian Hotpot", 5f));
        pnlMainDish.add(createFoodCard(28, "Beef Noodle.png", "Beef Noodle", 2.5f));
        pnlMainDish.add(createFoodCard(29, "Chicken Wings.png", "Chicken Wings", 3f));
        pnlMainDish.add(createFoodCard(30, "stir-fried squid.png", "Stir Fried Squid", 5f));
        // Drinks
        pnlDrink = new JPanel(new GridLayout(3, 5));
        pnlDrink.add(createFoodCard(31, "Beer.png", "Beer", 0.5f));
        pnlDrink.add(createFoodCard(32, "Coconut.png", "Coconut", 1f));
        pnlDrink.add(createFoodCard(33, "Ice_latter.png", "Iced Latte", 2.5f));
        pnlDrink.add(createFoodCard(34, "Thnol-Coffee.png", "Ice Thnol Coffee", 3f));
        pnlDrink.add(createFoodCard(35, "GreenTea.png", "Green Milk Tea Frappe", 3f));
        
        pnlDrink.add(createFoodCard(36, "Match_latte.png", "Matcha Latte", 2.5f));
        pnlDrink.add(createFoodCard(37, "Matcha_Cream.png", "Matcha Cream", 2.5f));
        pnlDrink.add(createFoodCard(38, "cappuccino.png", "Cappuccino", 2.5f));
        pnlDrink.add(createFoodCard(39, "mineral water.png", "Cambodia Water", 0.5f));
        pnlDrink.add(createFoodCard(40, "ChocolateMilk.png", "Chocolate Milk", 2.5f));

        pnlDrink.add(createFoodCard(41, "LycheeTea.png", "Lychee Tea", 2.5f));
        pnlDrink.add(createFoodCard(42, "PassionJuice.png", "Passion Juice", 2.5f));
        pnlDrink.add(createFoodCard(43, "RaspberyTea.png", "Wildberry Raspberry Tea", 2.5f));
        pnlDrink.add(createFoodCard(44, "MochaChipFrappe.png", "Mocha Chip Frappe", 3f));
        pnlDrink.add(createFoodCard(45,"Americano.png", "Americano", 3f));
        //Snack
        pnlSnack = new JPanel(new GridLayout(3, 5));
        pnlSnack.add(createFoodCard(46, "Pork Cheese Hamburger.png", "Pork Cheese Hamburger", 2.5f));
        pnlSnack.add(createFoodCard(47, "Deep-fried Chicken Wings.png", "Fried Chicken Wings", 2f));
        pnlSnack.add(createFoodCard(48, "BBQ Pork Bun.png", "Pork Bun", 2f));
        pnlSnack.add(createFoodCard(49, "Num Kochay.png", "Num Kochay", 0.5f));
        pnlSnack.add(createFoodCard(50, "kitkat.png", "KitKat", 1.5f));
        
        pnlSnack.add(createFoodCard(51, "Fried Fish Balls.png", "Fried Fish Balls", 1.5f));
        pnlSnack.add(createFoodCard(52, "Cheese Sticks.png", "Cheese Sticks", 0.5f));
        pnlSnack.add(createFoodCard(53, "Fried Dumplings.png", "Fried Dumplings", 0.5f));
        pnlSnack.add(createFoodCard(54, "Potato Chips.png", "Potato Chips", 1f));
        pnlSnack.add(createFoodCard(55, "Mini Burgers.png", "Mini Burgers", 1.5f));

        pnlSnack.add(createFoodCard(56, "Fried Banana.png", "Fried Banana", 2.5f));
        pnlSnack.add(createFoodCard(57, "oreo.png", "Oreo Cookies", 1f));
        pnlSnack.add(createFoodCard(58, "snickers.png", "Snickers", 1f));
        pnlSnack.add(createFoodCard(59, "cheetos.png", "Cheetos", 1.5f));
        pnlSnack.add(createFoodCard(60,"Potatos.png", "lay's Potato Chips", 2f));
        //Dessert
        pnlDessert = new JPanel(new GridLayout(3, 5));
        pnlDessert.add(createFoodCard(61, "Custard Tart.png", "Custard Tart", 1.5f));
        pnlDessert.add(createFoodCard(62, "Banana Cake.png", "Banana Cake", 1f));
        pnlDessert.add(createFoodCard(63, "Cookies (assorted).png", "Cookies", 2f));
        pnlDessert.add(createFoodCard(64, "Fruit Salad.png", "Fruit Salad", 1.5f));
        pnlDessert.add(createFoodCard(65, "Waffles.png", "Waffles", 1.5f));
        
        pnlDessert.add(createFoodCard(66, "Gelato Scoop.png", "Gelato Scoop", 1.5f));
        pnlDessert.add(createFoodCard(67, "Chocolate Mousse.png", "Chocolate Mousse", 0.5f));
        pnlDessert.add(createFoodCard(68, "Mango Sticky Rice.png", "Mango Sticky Rice", 0.5f));
        pnlDessert.add(createFoodCard(69, "Donut.png", "Donuts", 1f));
        pnlDessert.add(createFoodCard(70, "Num.jpg", "Num Chak Krachan", 0.5f));

        pnlDessert.add(createFoodCard(71, "Cupcakes.png", "Cupcakes", 1.5f));
        pnlDessert.add(createFoodCard(72, "Brownie Cake.png", "Brownie Cake", 1.5f));
        pnlDessert.add(createFoodCard(73, "Tiramisu.png", "Tiramisu", 1f));
        pnlDessert.add(createFoodCard(74, "Fruit Crepe.png", "Fruit Crepe", 1.5f));
        pnlDessert.add(createFoodCard(75,"Ice Cream.png", "Ice Cream", 1.0f));
        // Add to Cart
        JPanel pnlWS = new JPanel();
        pnlWS.setBackground(Color.WHITE);
        pnlWS.setLayout(new GridLayout(1,5));
        pnlWS.setPreferredSize(new Dimension(0,50));
        pnlWS.add(new JLabel(""));
        pnlWS.add(new JLabel(""));        
        pnlWS.add(btnAddToCart);
        pnlWS.add(new JLabel(""));
        pnlWS.add(new JLabel(""));

        pnlW.add(pnlWN, BorderLayout.NORTH);
        pnlW.add(pnlWC, BorderLayout.CENTER);
        pnlW.add(pnlWS, BorderLayout.SOUTH);
        // RIGHT
        pnlE = new JPanel();
        pnlE.setLayout(new BorderLayout());
        pnlE.setPreferredSize(new Dimension(450,0));
        pnlE.setBackground(Color.BLUE);
        
        JPanel pnlEN = new JPanel();
        pnlEN.setPreferredSize(new Dimension(0,450));
        pnlEN.setLayout(new BorderLayout());
        pnlEN.setBackground(Color.WHITE);
        pnlEN.setBorder(BorderFactory.createTitledBorder("Summary"));
        pnlEN.add(scroll);
        
        JPanel pnlEC = new JPanel();
        pnlEC.setLayout(new GridLayout(3,2)); 
        pnlEC.setBackground(Color.WHITE);
        pnlEC.add(lbGrandTotal);
        pnlEC.add(txtGrandTotal); 
        pnlEC.add(lbAddress);
        pnlEC.add(txtAddress);  
        pnlEC.add(new JLabel(""));
        pnlEC.add(new JLabel("")); 
        JPanel pnlES = new JPanel();
        pnlES.setLayout(new GridLayout(1,3));
        pnlES.setPreferredSize(new Dimension(0,80));
        pnlES.setBackground(Color.WHITE);
        pnlES.setBorder(BorderFactory.createTitledBorder(""));
        pnlES.add(btnOrder);
        pnlES.add(btnDelete);
        pnlES.add(btnClear);
        pnlE.add(pnlEN, BorderLayout.NORTH);
        pnlE.add(pnlEC, BorderLayout.CENTER);
        pnlE.add(pnlES, BorderLayout.SOUTH);
        // Process
        btnAddToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pnlWC.getComponentCount() == 0) return;
                // Get the currently displayed category panel
                JPanel categoryPanel = (JPanel) pnlWC.getComponent(0);
                for (Component c : categoryPanel.getComponents()) {
                    if (c instanceof JPanel card) {

                        JSpinner sp = (JSpinner) card.getClientProperty("spinner");
                        if (sp == null)
                            continue;
                        int qty = (Integer) sp.getValue();
                        if (qty > 0) {
                            int proNo = (int) card.getClientProperty("proNo");
                            String name = (String) card.getClientProperty("name");
                            float price = (float) card.getClientProperty("price");
                            float total = price * qty;

                            tbDetails.addRow(new Object[]{
                                proNo, name, qty, price, total
                            });

                            sp.setValue(0);
                        }
                    }
                }
                // Update grand total
                double grandTotal = 0;
                for (int row = 0; row < tbDetails.getRowCount(); row++) {
                    grandTotal += Double.parseDouble(
                        tbDetails.getValueAt(row, 4).toString()
                    );
                }
                txtGrandTotal.setText(String.valueOf(grandTotal));
            }
        });
 
        btnOrder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String address = txtAddress.getText();
                if (address.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all required fields!");
                    return; 
                }
                try{
                    dbConnection();
                    String sqlOrder = "INSERT INTO Orders (CustomerID, DeliverymanID, Address, Status) VALUES (?, NULL,?, 'Pending');";
                    cmdOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                    cmdOrder.setInt(1, userID);
                    cmdOrder.setString(2, txtAddress.getText());
                    cmdOrder.executeUpdate();
                    
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
                    cmdDetail = conn.prepareStatement(sqlDetail);
                    for (int i = 0; i < tbDetails.getRowCount(); i++) {
                        int proNo = Integer.parseInt(tbDetails.getValueAt(i, 0).toString());
                        int qty = Integer.parseInt(tbDetails.getValueAt(i, 2).toString());
                        double price = Double.parseDouble(tbDetails.getValueAt(i, 3).toString());
                        
                        cmdDetail.setInt(1, orderNo);
                        cmdDetail.setInt(2, proNo);
                        cmdDetail.setInt(3, qty);
                        cmdDetail.setDouble(4, price);
                        cmdDetail.executeUpdate();
                    }    
                    JOptionPane.showMessageDialog(null, "Your order is success!");
                    tbDetails.setRowCount(0);
                    txtGrandTotal.setText("");
                    txtAddress.setText("");
                    txtPhone.setText("");
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
                txtAddress.setText("");
                txtPhone.setText("");
            }
        });
        
        btnMainDish.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showCategory(pnlMainDish);
            }
        });
        btnFastfood.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showCategory(pnlFastFood);
            }
        });
        btnDrink.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showCategory(pnlDrink);
            }
        });
        btnSnack.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showCategory(pnlSnack);
            }
        });
        btnDessert.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                showCategory(pnlDessert);
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
        Image img = icon.getImage().getScaledInstance(90, 70, Image.SCALE_SMOOTH);
        JLabel lbImg = new JLabel(new ImageIcon(img));
        // Price
        JPanel pnlInfo = new JPanel(new GridLayout(2, 1));
        pnlInfo.setBackground(Color.WHITE);
        
        JLabel lbName = new JLabel(name, JLabel.CENTER);
        lbName.setFont(new Font("Arial", Font.BOLD, 12));
        
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
    public void showCategory(JPanel categoryPanel) {
        pnlWC.removeAll();
        pnlWC.add(categoryPanel);
        pnlWC.revalidate();
        pnlWC.repaint();
    }
}
