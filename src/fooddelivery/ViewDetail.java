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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
/**
 *
 * @author manut
 */
public final class ViewDetail extends FoodDelivery{
    JFrame frame;
    PreparedStatement cmd; 
    ResultSet rs;
    JLabel lbHeader;
    JPanel pnlC, pnlN;
    JTable tb;
    DefaultTableModel tbDetail;
    public int OrderNo;
    public ViewDetail(int orderNo){
        this.OrderNo = orderNo;
        // Create Frame
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Create Label
        lbHeader = new JLabel("Order Detail");
        lbHeader.setFont(new Font("Arial", Font.BOLD, 20));
        // Create Table
        tbDetail = new DefaultTableModel();
        tbDetail.addColumn("OrderNo");
        tbDetail.addColumn("ProName");
        tbDetail.addColumn("Quantity");
        tbDetail.addColumn("Price");
        tbDetail.addColumn("Total");
        tb = new JTable(tbDetail);
        tb.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(tb);
        loadDetailTable();
        // Create Panel
        pnlN = new JPanel();
        pnlN.setBackground(Color.WHITE);
        pnlN.setPreferredSize(new Dimension(0,40));
        pnlN.add(lbHeader);
        
        pnlC = new JPanel();
        pnlC.setBackground(Color.WHITE);
        pnlC.setLayout(new BorderLayout());
        pnlC.add(scroll);
        // Add to Frame
        frame.add(pnlN, BorderLayout.NORTH);
        frame.add(pnlC, BorderLayout.CENTER);        
        // Show Frame
        frame.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    } 
    public void loadDetailTable() {
        try {
            dbConnection();
            String sql = "SELECT d.OrderNo, p.ProName, d.Quantity, d.Price, SUM(d.Quantity * d.Price) AS Total FROM Details d JOIN Products p on d.ProNo = p.ProNo WHERE OrderNo = ? GROUP BY d.OrderNo, p.ProName, d.Quantity, d.Price";
            cmd = conn.prepareStatement(sql);
            cmd.setInt(1, OrderNo);
            rs = cmd.executeQuery();
            tbDetail.setRowCount(0);
            while (rs.next()) {
                    tbDetail.addRow(new Object[]{
                    rs.getInt("OrderNo"),
                    rs.getString("ProName"),
                    rs.getInt("Quantity"),
                    rs.getFloat("Price"),
                    rs.getFloat("Total"),
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
