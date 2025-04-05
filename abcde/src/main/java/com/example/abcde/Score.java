package com.example.abcde;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener {

    private static String name;
    private int score;
    private int category;

    Score(String name, int score, int category) {
        this.name = name;
        this.score = score;
        this.category = category;
        setTitle("Your Score");

        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Create a background panel with the "Thank You.png" image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/Icons/Thank You.png"));
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 750, 550);
        add(backgroundPanel);

        // Add heading on top of the background
        JLabel heading = new JLabel("Thankyou " + name + ", Here is your score!");
        heading.setBounds(225, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        heading.setForeground(Color.BLACK);
        backgroundPanel.add(heading);

        // Add score text on top of the background
        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(350, 200, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
        // Make text more visible against background if needed
        lblscore.setForeground(Color.BLACK);
        backgroundPanel.add(lblscore);

        // Add button on top of the background
        JButton submit = new JButton("Play Again");
        submit.setBounds(380, 270, 120, 30);
        submit.setBackground(new Color(30, 144, 255));
        submit.setOpaque(true);
        submit.setFocusPainted(false);
        submit.setBorderPainted(false);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        backgroundPanel.add(submit);

        saveScoreToFile();

        setVisible(true);
    }

    private void saveScoreToFile() {
        try (FileWriter fw = new FileWriter("scores.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(name + "," + category + "," + score);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        Welcome wlc = new Welcome(name);
        wlc.setVisible(true);
    }

    public static void main(String[] args) {
        new Score(name, 0, 18);
    }
}