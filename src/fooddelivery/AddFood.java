/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author manut
 */
public class AddFood extends JFrame{
    Container pane;
    public AddFood(){
        //Create
        pane = this.getContentPane();
        pane.setLayout(new GridLayout());
        pane.setBackground(Color.GREEN);
        //Show 
        pane.setVisible(true);
    }
    public Container getPane(){
        return pane;
    }
}
