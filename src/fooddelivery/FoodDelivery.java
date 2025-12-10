/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fooddelivery;

import javax.swing.UIManager;

/**
 *
 * @author manuth
 */
public class FoodDelivery{

    /**
     * @param args the command line arguments
     */
    // Hello this is my project that using Git
    public static void main(String[] args) {
        try {
            // Set Look and Feel      
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.synth.SynthLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        } 
        new FormLogin();
        //new ForgetPassword();
        //new ResetPassword();
       // new UserForm();
        //new MainForm();
        //new UpdateUser();
        //new DeleteUser();
        //new DeliveryForm();
        //new Payment();
    }
}
