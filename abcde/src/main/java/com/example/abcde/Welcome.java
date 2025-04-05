package com.example.abcde;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.RenderingHints;

public class Welcome extends JFrame {

    private static final long serialVersionUID = 1L;
    private static String name;

    public Welcome(String name) {
        this.name = name;

        setTitle("Quiz Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 450);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);
        setContentPane(mainPanel);

        // Set the background
        ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/Video bg.gif"));
        Image i2 = i1.getImage().getScaledInstance(680,450,Image.SCALE_DEFAULT);
        ImageIcon backgroundIcon = new ImageIcon(i2);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 680, 450);
        mainPanel.add(backgroundLabel);


        // Create and add buttons
        JButton startQuizButton = new JButton("Start QUIZ");
        startQuizButton.setBounds(220, 150, 240, 30);
        startQuizButton.setBackground(new Color(210, 130, 180)); //Steel blue type 🤩
        startQuizButton.setForeground(Color.WHITE);
        startQuizButton.setBorder(BorderFactory.createCompoundBorder());
        backgroundLabel.add(startQuizButton);

        JButton highScoresButton = new JButton("Leader Board");
        highScoresButton.setBounds(220, 200, 240, 30);
        highScoresButton.setBackground(new Color(70, 130, 180)); //Steel blue type 🤩
        highScoresButton.setForeground(Color.WHITE);
        highScoresButton.setBorder(BorderFactory.createCompoundBorder());
        backgroundLabel.add(highScoresButton);

        JButton aboutUsButton = new JButton("About Us");
        aboutUsButton.setBounds(220, 250, 240, 30);
        aboutUsButton.setBackground(new Color(70, 130, 180)); //Steel blue type 🤩
        aboutUsButton.setForeground(Color.WHITE);
        aboutUsButton.setBorder(BorderFactory.createCompoundBorder());
        backgroundLabel.add(aboutUsButton);


        ImageIcon i4 = new ImageIcon(getClass().getResource("/Icons/Arrow Left.png"));
        Image i5 = i4.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon arrowIcon = new ImageIcon(i5);
        JLabel arrowLabel = new JLabel(arrowIcon);
        arrowLabel.setBounds(290,250,30,30);
        arrowLabel.setVisible(false);
        backgroundLabel.add(arrowLabel);

        // Create the About Us panel (initially invisible)
        JPanel aboutUsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 160));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        aboutUsPanel.setOpaque(false);
        aboutUsPanel.setBounds(324, 130, 350, 270);
        aboutUsPanel.setVisible(false);
        backgroundLabel.add(aboutUsPanel);

        JTextArea aboutUsText = new JTextArea(
                "Welcome to Our QUIZ Application!\n\n" +
                        "We are dedicated to providing the best user experience.\n\n" +
                        "Yeah, we know that it's not perfect but " +
                        "you should also know it's just the beginning!!!\n\n" +
                        "Contact us at: saste_devs@gmail.com"
        );
        aboutUsText.setEditable(false);
        aboutUsText.setLineWrap(true);
        aboutUsText.setWrapStyleWord(true);
        aboutUsText.setOpaque(false);
        aboutUsText.setForeground(Color.WHITE);
        aboutUsPanel.add(aboutUsText, BorderLayout.SOUTH);



        aboutUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                aboutUsPanel.setVisible(!( aboutUsPanel.isVisible() ));

                if (aboutUsPanel.isVisible()) {
                    animateButtonMove(aboutUsButton,220,250,40,250);
                    animateButtonMove(highScoresButton,220,200,40,200);
                    animateButtonMove(startQuizButton,220,150,40,150);
                    arrowLabel.setVisible(true);
                } else {
                    animateButtonMove(aboutUsButton,40,250,220,250);
                    animateButtonMove(highScoresButton,40,200,220,200);
                    animateButtonMove(startQuizButton,40,150,220,150);
                    arrowLabel.setVisible(false);
                }
            }
        });
        // Add this for each button
        startQuizButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startQuizButton.setBackground(new Color(70, 130, 180)); //Steel blue type 🤩
                startQuizButton.setForeground(Color.WHITE);
                startQuizButton.setBorder(BorderFactory.createRaisedBevelBorder());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startQuizButton.setBackground(new Color(70, 130, 180)); //Steel blue type 🤩
                startQuizButton.setForeground(Color.WHITE);
                startQuizButton.setBorder(BorderFactory.createCompoundBorder());
            }
        });
        highScoresButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                highScoresButton.setBackground(new Color(70, 130, 180)); //Steel blue type 🤩
                highScoresButton.setForeground(Color.WHITE);
                highScoresButton.setBorder(BorderFactory.createRaisedBevelBorder());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                highScoresButton.setBackground(new Color(70, 130, 180)); //Steel blue type 🤩
                highScoresButton.setForeground(Color.WHITE);
                highScoresButton.setBorder(BorderFactory.createCompoundBorder());
            }
        });
        aboutUsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aboutUsButton.setBackground(new Color(70, 130, 180)); //Steel blue type 🤩
                aboutUsButton.setForeground(Color.WHITE);
                aboutUsButton.setBorder(BorderFactory.createRaisedBevelBorder());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aboutUsButton.setBackground(new Color(70, 130, 180)); //Steel blue type 🤩
                aboutUsButton.setForeground(Color.WHITE);
                aboutUsButton.setBorder(BorderFactory.createCompoundBorder());
            }
        });

        startQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main screen
                dispose(); // Close current window
                Categories category = new Categories(name); // Create instance of your main menu
                category.setVisible(true);
            }
        });
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighScores().setVisible(true);
            }
        });

    }

    // For smoother movement of those buttons :)
    private void animateButtonMove(JButton button, int startX, int startY, int endX, int endY) {
        Timer timer = new Timer(1, null);
        final int[] currentX = {startX};
        final int[] currentY = {startY};

        timer.addActionListener(e -> {
            if (currentX[0] < endX) currentX[0]++;
            if (currentX[0] > endX) currentX[0]--;
            if (currentY[0] < endY) currentY[0]++;
            if (currentY[0] > endY) currentY[0]--;

            button.setBounds(currentX[0], currentY[0], button.getWidth(), button.getHeight());

            if (currentX[0] == endX && currentY[0] == endY) {
                timer.stop();
            }
        });

        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Welcome frame = new Welcome(name);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
