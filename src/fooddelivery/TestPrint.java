/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author manut
 */
public class TestPrint {
    public TestPrint(){
         try {
            JasperReport jr = (JasperReport)
                    JRLoader.loadObjectFromFile("C:\\Users\\manut\\OneDrive - ACLEDA University of Business Co., Ltd\\My Documents\\AUB BACHELOR CSE\\AUB BACHELOR CSE Y2S1\\CS 214 Java Programming\\Project\\FoodDelivery\\src\\reports\\CafeReceipt.jasper");

            JasperPrint jp = JasperFillManager.fillReport(
                    jr,
                    null,
                    new JREmptyDataSource()
            );

            // SHOW REPORT
            JasperViewer.viewReport(jp, false);

            // PRINT (optional – will open printer dialog)
            // JasperPrintManager.printReport(jp, true);

            System.out.println("Report SHOW test success!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
