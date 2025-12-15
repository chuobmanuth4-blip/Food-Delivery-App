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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author manut
 */
public final class Dashboard extends JFrame{
    Container pane;
    Connection conn;
    PreparedStatement cmd; 
    ResultSet rs;
    ImageIcon imgUser, imgOrder, imgSold, imgProfit;
    JLabel lbUser, lbOrder, lbSold, lbProfit;
    JPanel pnlN, pnlC, pnlS, pnlN1, pnlN2, pnlN3, pnlN4, pnlC1, pnlC2, pnlC3, pnlC4;
    public Dashboard(){
        // Create 
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        // Create image
        imgUser = new ImageIcon("D:\\Project\\FoodDelivery\\src\\group.png");
        JLabel lbimg1 = new JLabel(imgUser);
        imgOrder = new ImageIcon("D:\\Project\\FoodDelivery\\src\\orderAmount.png");
        JLabel lbimg2 = new JLabel(imgOrder);
        imgSold = new ImageIcon("D:\\Project\\FoodDelivery\\src\\sold.png");
        JLabel lbimg3 = new JLabel(imgSold);
        imgProfit = new ImageIcon("D:\\Project\\FoodDelivery\\src\\profits.png"); 
        JLabel lbimg4 = new JLabel(imgProfit);
        // Create Label
        lbUser = new JLabel("Customers", JLabel.CENTER);
        lbUser.setFont(new Font("Arial", Font.BOLD, 15));
        lbOrder = new JLabel("Orders", JLabel.CENTER);
        lbOrder.setFont(new Font("Arial", Font.BOLD, 16));
        lbSold = new JLabel("Items Sold", JLabel.CENTER);
        lbSold.setFont(new Font("Arial", Font.BOLD, 15));
        lbProfit = new JLabel("Profits", JLabel.CENTER);
        lbProfit.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Create Panel
        // North
        pnlN = new JPanel();
        pnlN.setLayout(new GridLayout(1,4));
        pnlN.setPreferredSize(new Dimension(0,100));
        //Card 1
        int CountCust=0, TodayCust=0, OrderNum = 0, OrderToday=0, SoldNum=0, SoldToday=0;
        float Profit = 0, ProfitToday = 0; 
        try {
            dbConnection();
            
            String sql1 = "SELECT COUNT(*) AS CustomerNumber FROM Users WHERE Role = 'Customer';";                     
            cmd = conn.prepareStatement(sql1);            
            rs = cmd.executeQuery();    // Run     
            if(rs.next())
                CountCust = Integer.parseInt(rs.getString(1));
            
            String sql2 = "SELECT COUNT(*) AS CustomerNumber FROM Users WHERE Role = 'Customer' AND DATE(createdAt)= CURDATE();";                     
            cmd = conn.prepareStatement(sql2);            
            rs = cmd.executeQuery();    // Run     
            if(rs .next())
                TodayCust = Integer.parseInt(rs.getString(1));
            
            String sql3 = "SELECT COUNT(*) AS OrderNumber FROM Orders;";                     
            cmd = conn.prepareStatement(sql3);            
            rs = cmd.executeQuery();    // Run     
            if(rs .next())
                OrderNum = Integer.parseInt(rs.getString(1));
            
            String sql4 = "SELECT COUNT(*) AS OrderNumber FROM Orders WHERE DATE(OrderDate) = Date(now());";                     
            cmd = conn.prepareStatement(sql4);            
            rs = cmd.executeQuery();    // Run     
            if(rs .next())
                OrderToday = Integer.parseInt(rs.getString(1));
            
            String sql5 = "SELECT COUNT(Orders.OrderNo) as ItemSold FROM Orders JOIN Details on Orders.OrderNo = Details.OrderNo;";                     
            cmd = conn.prepareStatement(sql5);            
            rs = cmd.executeQuery();    // Run     
            if(rs .next())
                SoldNum = Integer.parseInt(rs.getString(1));
            
            String sql6 = "SELECT COUNT(Orders.OrderNo) as ItemSold FROM Orders JOIN Details on Orders.OrderNo = Details.OrderNo AND DATE(OrderDate) = Date(now());";                     
            cmd = conn.prepareStatement(sql6);            
            rs = cmd.executeQuery();    // Run     
            if(rs .next())
                SoldToday = Integer.parseInt(rs.getString(1));
            
            String sql7 = "SELECT SUM(Details.Quantity * Details.Price) as Total FROM Orders JOIN Details WHERE Details.OrderNo = Orders.OrderNo;";                     
            cmd = conn.prepareStatement(sql7);            
            rs = cmd.executeQuery();    // Run     
            if(rs .next())
                Profit = Float.parseFloat(rs.getString(1));
            
            String sql8 = "SELECT SUM(Details.Quantity * Details.Price) as Total FROM Orders JOIN Details WHERE Details.OrderNo = Orders.OrderNo AND DATE(OrderDate) = Date(now());";                     
            cmd = conn.prepareStatement(sql8);            
            rs = cmd.executeQuery();    // Run     
            if(rs .next())
                ProfitToday = Float.parseFloat(rs.getString(1));
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception exception) {
            System.out.println(exception);
        }    
        pnlN1 = new JPanel();
        pnlN1.setLayout(new GridLayout(2,4));
        pnlN1.setBackground(Color.WHITE);
        pnlN1.add(new JLabel(""));        
        pnlN1.add(lbUser);
        pnlN1.add(lbimg1);
        pnlN1.add(new JLabel(""));
        
        pnlN1.add(new JLabel(""));
        JLabel lb1 = new JLabel("" + CountCust, JLabel.CENTER);
        lb1.setFont(new Font("Serif", Font.BOLD, 16));
        lb1.setForeground(Color.cyan);
        JLabel lb2 = new JLabel("+" + TodayCust, JLabel.CENTER);
        lb2.setFont(new Font("Serif", Font.BOLD, 16));
        lb2.setForeground(Color.BLUE);
        pnlN1.add(lb1);        
        pnlN1.add(lb2);  
        pnlN1.add(new JLabel(""));

        pnlN2 = new JPanel();
        pnlN2.setLayout(new GridLayout(2,4));
        pnlN2.setBackground(Color.WHITE);
        pnlN2.add(new JLabel(""));
        pnlN2.add(lbOrder);
        pnlN2.add(lbimg2);
        pnlN2.add(new JLabel(""));
        
        pnlN2.add(new JLabel(""));  
        JLabel lb3 = new JLabel("" + OrderNum, JLabel.CENTER);
        lb3.setFont(new Font("Serif", Font.BOLD, 16));
        lb3.setForeground(Color.cyan);
        JLabel lb4 = new JLabel("+" + OrderToday, JLabel.CENTER);
        lb4.setFont(new Font("Serif", Font.BOLD, 16));
        lb4.setForeground(Color.BLUE);
        pnlN2.add(lb3);
        pnlN2.add(lb4);
        pnlN2.add(new JLabel(""));
        
        pnlN3 = new JPanel();
        pnlN3.setLayout(new GridLayout(2,4));
        pnlN3.setBackground(Color.WHITE);
        pnlN3.add(new JLabel(""));  
        pnlN3.add(lbSold);
        pnlN3.add(lbimg3);
        pnlN3.add(new JLabel(""));  
        pnlN3.add(new JLabel(""));  
        JLabel lb5 = new JLabel("" + SoldNum, JLabel.CENTER);
        lb5.setFont(new Font("Serif", Font.BOLD, 16));
        lb5.setForeground(Color.cyan);
        JLabel lb6 = new JLabel("+" + SoldToday, JLabel.CENTER);
        lb6.setFont(new Font("Serif", Font.BOLD, 16));
        lb6.setForeground(Color.BLUE);
        pnlN3.add(lb5);
        pnlN3.add(lb6);
        pnlN3.add(new JLabel(""));  

        pnlN4 = new JPanel();
        pnlN4.setLayout(new GridLayout(2,3));
        pnlN4.setBackground(Color.WHITE);
        pnlN4.add(new JLabel(""));
        pnlN4.add(lbProfit);  
        pnlN4.add(lbimg4);
        pnlN4.add(new JLabel(""));
        pnlN4.add(new JLabel(""));
        JLabel lb7 = new JLabel("$" + Profit, JLabel.CENTER);
        lb7.setFont(new Font("Serif", Font.BOLD, 16));
        lb7.setForeground(Color.cyan);
        JLabel lb8 = new JLabel("+$" + ProfitToday, JLabel.CENTER);
        lb8.setFont(new Font("Serif", Font.BOLD, 16));
        lb8.setForeground(Color.BLUE);
        pnlN4.add(lb7);  
        pnlN4.add(lb8);
        pnlN4.add(new JLabel(""));
       
        pnlN.add(pnlN1);
        pnlN.add(pnlN2);
        pnlN.add(pnlN3);
        pnlN.add(pnlN4);
        // Center
        pnlC = new JPanel();
        pnlC.setLayout(new GridLayout(2,2));
        
        pnlC1 = new JPanel();
        pnlC1.setLayout(new BorderLayout());
        pnlC1.setBackground(Color.CYAN);
        BarChart1();
        pnlC2 = new JPanel();
        pnlC2.setLayout(new BorderLayout());
        pnlC2.setBackground(Color.GREEN);
        BarChart2();
        pnlC3 = new JPanel();
        pnlC3.setLayout(new BorderLayout());
        pnlC3.setBackground(Color.BLUE);
        LineChart();
        pnlC4 = new JPanel();
        pnlC4.setLayout(new BorderLayout());
        pnlC4.setBackground(Color.RED);  
        PieChart();
        
        pnlC.add(pnlC1);
        pnlC.add(pnlC2);
        pnlC.add(pnlC3);
        pnlC.add(pnlC4);         
        
        // South
        pnlS = new JPanel();
        pnlS.setPreferredSize(new Dimension(0,60));
        // Add Components to Frame
        pane.add(pnlN, BorderLayout.NORTH);
        pane.add(pnlC, BorderLayout.CENTER);
        pane.add(pnlS, BorderLayout.SOUTH);        
        // Show
        pane.setVisible(true);
    }
    public Container getPane(){
        return pane;
    }
    public void BarChart1(){
        JFreeChart barChart;
        // Add data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(500, "Income", "Main Dishes");
        dataset.addValue(600, "Income", "Drinks");
        dataset.addValue(700, "Income", "Snacks");
        dataset.addValue(800, "Income", "Desserts");
        dataset.addValue(1200, "Income", "Fast Food");
        // Create Chart
        barChart = ChartFactory.createBarChart( // creteBarChart or createLineChart 
            "Bar Chart Example",
            "Category",
            "Value",
            dataset,
            PlotOrientation.HORIZONTAL,
            true,true,false
        );
        // Create ChartPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // Add to Panel
        pnlC1.add(chartPanel);
    }
    public void BarChart2(){
        JFreeChart barChart;
        // Add data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(500, "Income", "January");
        dataset.addValue(600, "Income", "February");
        dataset.addValue(700, "Income", "March");
        dataset.addValue(800, "Income", "April");
        dataset.addValue(1200, "Income", "May");
        dataset.addValue(900, "Income", "June");
        // Create Chart
        barChart = ChartFactory.createBarChart( // creteBarChart or createLineChart 
            "Bar Chart Example",
            "Category",
            "Value",
            dataset,
            PlotOrientation.VERTICAL,
            true,true,false
        );
        // Create ChartPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // Add to Panel
        pnlC2.add(chartPanel);
    }
    public void LineChart(){
        JFreeChart barChart;
        // Add data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(500, "Income", "January");
        dataset.addValue(600, "Income", "February");
        dataset.addValue(700, "Income", "March");
        dataset.addValue(800, "Income", "April");
        dataset.addValue(1200, "Income", "May");
        dataset.addValue(900, "Income", "June");
        // Create Chart
        barChart = ChartFactory.createLineChart( // creteBarChart or createLineChart 
            "Line Chart Example",
            "Category",
            "Value",
            dataset,
            PlotOrientation.HORIZONTAL,
            true,true,false
        );
        // Create ChartPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // Add to Panel
        pnlC3.add(chartPanel);
    }
    public void PieChart(){
        JFreeChart barChart;
        // Add data
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("January", 500);
        dataset.setValue("February", 600);
        dataset.setValue("March", 700);
        dataset.setValue("April", 800);
        dataset.setValue("May", 1200);
        dataset.setValue("June", 900);
        // Create Chart
        barChart = ChartFactory.createPieChart( // creteBarChart or createLineChart 
            "Pie Chart Example",
            dataset,
            true,true,false
        );
        // Create ChartPanel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // Add to Panel
        pnlC4.add(chartPanel);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
