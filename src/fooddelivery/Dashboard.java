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
import java.text.NumberFormat;
import java.util.Locale;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
/**
 *
 * @author manuth
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
        imgUser = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\group.png");
        JLabel lbimg1 = new JLabel(imgUser);
        imgOrder = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\orderAmount.png");
        JLabel lbimg2 = new JLabel(imgOrder);
        imgSold = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\sold.png");
        JLabel lbimg3 = new JLabel(imgSold);
        imgProfit = new ImageIcon("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\profits.png"); 
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
        
        // Card
        int CountCust=0, TodayCust=0, OrderNum = 0, OrderToday=0, SoldNum=0, SoldToday=0;
        float Profit = 0, ProfitToday = 0; 
        try {
            dbConnection();
            
            String sql1 = "SELECT COUNT(*) AS CustomerNumber FROM Users WHERE Role = 'Customer';";                     
            cmd = conn.prepareStatement(sql1);            
            rs = cmd.executeQuery();         
            if(rs.next())
                CountCust = rs.getInt(1);
            
            String sql2 = "SELECT COUNT(*) AS CustomerNumber FROM Users WHERE Role = 'Customer' AND DATE(createdAt)= CURDATE();";                     
            cmd = conn.prepareStatement(sql2);            
            rs = cmd.executeQuery();       
            if(rs .next())
                TodayCust = rs.getInt(1);
            
            String sql3 = "SELECT COUNT(*) AS OrderNumber FROM Orders;";                     
            cmd = conn.prepareStatement(sql3);            
            rs = cmd.executeQuery();       
            if(rs .next())
                OrderNum = rs.getInt(1);
            
            String sql4 = "SELECT COUNT(*) AS OrderNumber FROM Orders WHERE DATE(OrderDate) = Date(now());";                     
            cmd = conn.prepareStatement(sql4);            
            rs = cmd.executeQuery();       
            if(rs .next())
                OrderToday = rs.getInt(1);
            
            String sql5 = "SELECT SUM(Details.Quantity) as ItemSold FROM Orders JOIN Details on Orders.OrderNo = Details.OrderNo WHERE Orders.Status = 'Completed';";                     
            cmd = conn.prepareStatement(sql5);            
            rs = cmd.executeQuery();     
            if(rs .next())
                SoldNum = rs.getInt(1);
            
            String sql6 = "SELECT SUM(Details.Quantity) as ItemSold FROM Orders JOIN Details on Orders.OrderNo = Details.OrderNo WHERE Orders.Status = 'Completed' AND DATE(Orders.OrderDate) = Date(now());";                     
            cmd = conn.prepareStatement(sql6);            
            rs = cmd.executeQuery();       
            if(rs .next())
                SoldToday = rs.getInt(1);
            
            String sql7 = "SELECT SUM(d.Quantity * p.Price) FROM Orders o JOIN Details d ON o.OrderNo = d.OrderNo JOIN Products p ON d.ProNo = p.ProNo WHERE o.Status = 'Completed';";                     
            cmd = conn.prepareStatement(sql7);            
            rs = cmd.executeQuery();    
            if(rs .next())
                Profit = rs.getFloat(1);
            
            String sql8 = "SELECT SUM(d.Quantity * p.Price) FROM Orders o JOIN Details d ON o.OrderNo = d.OrderNo JOIN Products p ON d.ProNo = p.ProNo WHERE o.Status = 'Completed' AND DATE(OrderDate) = Date(now());";                     
            cmd = conn.prepareStatement(sql8);            
            rs = cmd.executeQuery();        
            if(rs .next())
                ProfitToday = rs.getFloat(1);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception exception) {
            System.out.println(exception);
        }    
        pnlN1 = new JPanel();
        pnlN1.setLayout(new GridLayout(2,4));
        pnlN1.setBackground(Color.CYAN);
        pnlN1.add(new JLabel(""));        
        pnlN1.add(lbUser);
        pnlN1.add(lbimg1);
        pnlN1.add(new JLabel(""));
        
        pnlN1.add(new JLabel(""));
        JLabel lb1 = new JLabel("" + CountCust, JLabel.CENTER);
        lb1.setFont(new Font("Serif", Font.BOLD, 16));
        lb1.setForeground(Color.WHITE);
        JLabel lb2 = new JLabel("+" + TodayCust, JLabel.CENTER);
        lb2.setFont(new Font("Serif", Font.BOLD, 16));
        lb2.setForeground(Color.BLUE);
        pnlN1.add(lb1);        
        pnlN1.add(lb2);  
        pnlN1.add(new JLabel(""));

        pnlN2 = new JPanel();
        pnlN2.setLayout(new GridLayout(2,4));
        pnlN2.setBackground(Color.PINK);
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
        pnlN3.setBackground(Color.ORANGE);
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
        pnlN4.setBackground(Color.YELLOW);
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
    public void BarChart1(){
        dbConnection();
        try{
            // Add data
            String sql = """
                         SELECT c.CategoryName, SUM(d.Quantity) AS ItemSold 
                         FROM Details d 
                         INNER JOIN Orders o ON d.OrderNo = o.OrderNo
                         INNER JOIN Products p ON d.ProNo = p.ProNo
                         INNER JOIN Categories c ON c.CategoryNo = p.CategoryNo 
                         GROUP BY c.CategoryName;
                         """;
            cmd = conn.prepareStatement(sql);
            rs = cmd.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()){
                int Itemsold = rs.getInt("ItemSold");
                String cateName = rs.getString("CategoryName");
                dataset.setValue(Itemsold, "Items", cateName);
            } 
            JFreeChart barChart;
            // Create Chart
            barChart = ChartFactory.createBarChart( 
                "Items Sold by Category",
                "Category",
                "Value",
                dataset,
                PlotOrientation.HORIZONTAL,
                false,true,false
            );
            // Create ChartPanel
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            // Add to Panel
            pnlC1.removeAll();
            pnlC1.add(chartPanel);
            pnlC1.revalidate();
            pnlC1.repaint();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception exception) {
            System.out.println(exception);
        } 
    }
    public void BarChart2(){
        // Add data
        dbConnection();
        try{
            String sql = """
                         SELECT 
                             WEEKDAY(OrderDate) AS dayIndex,  
                             COUNT(*) AS OrderCount
                         FROM Orders
                         WHERE YEARWEEK(OrderDate, 1) = YEARWEEK(CURDATE(), 1)
                         GROUP BY dayIndex
                         ORDER BY dayIndex;
                         """;
            cmd = conn.prepareStatement(sql);
            rs = cmd.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"};
            for(String d : days){
                dataset.addValue(0, "Orders", d);  
            }
            while(rs.next()){
                int dayIndex = rs.getInt("dayIndex"); 
                String dayName = days[dayIndex];
                int orderCount  = rs.getInt("OrderCount");

                dataset.setValue(orderCount, "Orders", dayName);            
            }
            // Create Chart
            JFreeChart barChart;
            barChart = ChartFactory.createBarChart( 
                "Orders per Day(This week)",
                "",
                "Orders",
                dataset,
                PlotOrientation.VERTICAL,
                false,true,false
            );
            CategoryPlot plot = barChart.getCategoryPlot();
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            // Create ChartPanel
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            // Add to Panel
            pnlC2.removeAll();
            pnlC2.add(chartPanel);
            pnlC2.revalidate();
            pnlC2.repaint();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception exception) {
            System.out.println(exception);
        }
    }
    public void LineChart(){
        dbConnection();
        try{
            String sql = """
                         SELECT 
                             WEEKDAY(o.OrderDate) AS dayIndex,
                             o.Address,
                             SUM(d.Quantity * d.Price) AS Income
                         FROM Orders o
                         INNER JOIN Details d ON o.OrderNo = d.OrderNo
                         WHERE YEARWEEK(o.OrderDate, 1) = YEARWEEK(CURDATE(), 1)
                         GROUP BY dayIndex, o.Address
                         ORDER BY dayIndex;
                         """;
            cmd = conn.prepareStatement(sql);
            rs = cmd.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"};
            String[] locations = {"Sen Sok", "Por Sen Chey", "Toul Kork", "Russei Keo"};

            for (String loc : locations) {
                for (String day : days) {
                    dataset.addValue(0, loc, day);
                }
            }
            while(rs.next()){
                int dayIndex = rs.getInt("dayIndex"); 
                String dayName = days[dayIndex];
                String location = rs.getString("Address");
                double income = rs.getDouble("Income");

                dataset.addValue(income, location, dayName);            
            }
            JFreeChart lineChart;
            lineChart = ChartFactory.createLineChart( 
                "Weekly Income by Location",
                "",
                "Income",
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
            );
            CategoryPlot plot = lineChart.getCategoryPlot();
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setNumberFormatOverride(
                    NumberFormat.getCurrencyInstance(Locale.US)
            );
            // Create ChartPanel
            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setBackground(Color.WHITE);
            //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            // Add to Panel
            pnlC3.removeAll();
            pnlC3.add(chartPanel);
            pnlC3.validate(); 
            pnlC3.repaint();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception exception) {
            System.out.println(exception);
        }
    }
    public void PieChart(){
        dbConnection();
        try{
            String sql = "SELECT Categories.CategoryName, SUM(Products.Price * Details.Quantity ) AS TOTAL FROM Details INNER JOIN Products ON Details.ProNo = Products.ProNo INNER JOIN Categories ON Categories.CategoryNo = Products.CategoryNo GROUP BY Categories.CategoryName;";
            cmd = conn.prepareStatement(sql);
            rs = cmd.executeQuery();
            DefaultPieDataset dataset=new DefaultPieDataset();
            while (rs.next()) {
                String category = rs.getString("CategoryName");
                double total = rs.getDouble("TOTAL");
                dataset.setValue(category, total);
            }
            JFreeChart pieChart = ChartFactory.createPieChart(
               "Income by Category",
               dataset,    
               true, true, false);
            
            PiePlot plot = (PiePlot) pieChart.getPlot();
            NumberFormat currencyFormat =
                    NumberFormat.getCurrencyInstance(Locale.US);

            plot.setLabelGenerator(
                    new StandardPieSectionLabelGenerator(
                            "{0}: {1} ({2})",
                            currencyFormat,
                            NumberFormat.getPercentInstance()
                    )
            );
            // Create ChartPanel
            ChartPanel pieChartPnl = new ChartPanel(pieChart);
            pieChartPnl.setPreferredSize(new java.awt.Dimension(500, 270));
            // Add to Panel
            pnlC4.removeAll();
            pnlC4.add(pieChartPnl);
            pnlC4.validate(); 
            pnlC4.repaint();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
