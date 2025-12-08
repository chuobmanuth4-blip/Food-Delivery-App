/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author manut
 */
public class OrderAdmin extends JFrame {
    Container pane;
    public OrderAdmin(){
        // Create
        pane = this.getContentPane();
        pane.setBackground(Color.red);
        // Show""
        pane.setVisible(true);
    }
    public Container getPane(){
        return pane;
    }
}
