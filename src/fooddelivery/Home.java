/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fooddelivery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author manut
 */
public class Home extends JFrame {
    Container pane;
    JLabel title, subtitle, tagline;
    JPanel content, footer, topPanel;
    public Home() {
        // Frame setup
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setBackground(Color.white);

        // Top Title Area
        topPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        topPanel.setBackground(new Color(0, 153, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));

        title = new JLabel("Welcome to GetFood!", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 50));
        title.setForeground(Color.white);

        subtitle = new JLabel("Get it hot. Get it fast. GetFood.", JLabel.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        subtitle.setForeground(Color.white);

        topPanel.add(title);
        topPanel.add(subtitle);

        // Content Section 
        content = new JPanel(new GridLayout(1, 3, 25, 25));
        content.setBackground(Color.white);
        content.setBorder(BorderFactory.createEmptyBorder(60, 100, 60, 100));

        content.add(createFeatureCard("Fast Delivery", "Your meals are delivered hot and on time."));
        content.add(createFeatureCard("Fresh Foods", "Prepared fresh every time you order."));
        content.add(createFeatureCard("Easy Payment", "Pay with cash, card, or online anytime."));

        // Footer Section 
        footer = new JPanel();
        footer.setBackground(new Color(245, 245, 245));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        tagline = new JLabel("© 2025 GetFood | Designed by Manuth Chuob", JLabel.CENTER);
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tagline.setForeground(Color.gray);
        footer.add(tagline);

        // Add to Frame 
        pane.add(topPanel, BorderLayout.NORTH);
        pane.add(content, BorderLayout.CENTER);
        pane.add(footer, BorderLayout.SOUTH);
        // Show 
        pane.setVisible(true);

    }

    // Feature Card Helper
    private JPanel createFeatureCard(String title, String desc) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.white);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 153, 0), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblTitle = new JLabel(title, JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 153, 0));

        JTextArea lblDesc = new JTextArea(desc);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDesc.setWrapStyleWord(true);
        lblDesc.setLineWrap(true);
        lblDesc.setOpaque(false);
        lblDesc.setEditable(false);
        lblDesc.setFocusable(false);
        lblDesc.setMargin(new Insets(10, 0, 0, 0));

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblDesc, BorderLayout.CENTER);
        return card;
    }

    public Container getPane() {
        return pane;
    }
}
