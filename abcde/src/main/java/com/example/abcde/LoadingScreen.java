package com.example.abcde;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen extends JFrame {

    public LoadingScreen() {
        setTitle("Loading...");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Hide window decorations

        // Background Panel
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BorderLayout());

        // Loading Label
        JLabel loadingLabel = new JLabel("Loading Questions...", JLabel.CENTER);
        loadingLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        loadingLabel.setForeground(Color.WHITE);

        // Loading Bar
        ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/Loading Animation.gif"));
        Image i2 = i1.getImage().getScaledInstance(400,225,Image.SCALE_DEFAULT);
        ImageIcon backgroundIcon = new ImageIcon(i2);
        JLabel bglabel = new JLabel(backgroundIcon);
        bglabel.add(loadingLabel);

        panel.add(bglabel, BorderLayout.CENTER);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoadingScreen loadingScreen = new LoadingScreen();
                    loadingScreen.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
