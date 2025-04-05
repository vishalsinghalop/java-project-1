package com.example.abcde;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(login::new);
    }

    /**
     * Create the frame.
     */
    public login() {
        setTitle("LOGIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 450);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(null);
        setContentPane(mainPanel);

        // Set the background
        ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/loginbg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(680, 450, Image.SCALE_DEFAULT);
        ImageIcon backgroundIcon = new ImageIcon(i2);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 680, 450);
        mainPanel.add(backgroundLabel);  // Add background first

        // Labels and fields with improved spacing
        JLabel lblNewLabel = new JLabel("USERNAME:");
        lblNewLabel.setBackground(new Color(255, 255, 255));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(210, 105, 180, 25);
        backgroundLabel.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 12));
        textField.setBounds(210, 125, 220, 30);  // Wider and taller field
        backgroundLabel.add(textField);

        JLabel lblNewLabel_2 = new JLabel("PASSWORD:");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_2.setBounds(210, 180, 180, 25);  // More space between username field and password label
        backgroundLabel.add(lblNewLabel_2);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 200, 220, 30);  // Wider and taller field
        backgroundLabel.add(passwordField);

        // Buttons with improved spacing
        JButton btnlogin = new JButton("Login");
        btnlogin.setForeground(Color.WHITE);
        btnlogin.setBackground(new Color(0, 128, 255));
        btnlogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnlogin.setBounds(240, 250, 160, 35);  // Wider and taller button, more space below fields
        btnlogin.setOpaque(true);
        btnlogin.setBorderPainted(false);
        backgroundLabel.add(btnlogin);

        JButton btnReset = new JButton("Sign in");
        btnReset.setForeground(Color.WHITE);
        btnReset.setBackground(new Color(0, 128, 255));
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));  // Slightly bigger font
        btnReset.setBounds(275, 295, 90, 25);  // Bigger button, more space below login button
        btnReset.setOpaque(true);
        btnReset.setFocusPainted(false);
        btnReset.setBorderPainted(false);

        backgroundLabel.add(btnReset);

        btnlogin.addActionListener(e -> loginUser());
        btnReset.addActionListener(e -> openSignUpFrame());

        setVisible(true);
    }
    private void loginUser() {
        String username = textField.getText().trim();
        String password = new String(passwordField.getPassword());

        File userFile = new File("users/" + username + ".txt");

        if (!userFile.exists()) {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String storedPassword = reader.readLine();
            if (storedPassword.equals(password)) {
                Welcome wlc = new Welcome(username);
                dispose();
                wlc.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading user file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void openSignUpFrame() {
        new SignUpFrame();
    }
}

class SignUpFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public SignUpFrame() {
        setTitle("Sign Up");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel mainPanel = new JPanel(null);
        setContentPane(mainPanel);

        // Set the background
        ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/signinbg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(680, 450, Image.SCALE_DEFAULT);
        ImageIcon backgroundIcon = new ImageIcon(i2);
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 680, 450);
        mainPanel.add(backgroundLabel);

        // Improved spacing for signup form
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblUsername.setBounds(50, 60, 100, 25);
        backgroundLabel.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(150, 60, 200, 30);  // Wider and taller field
        backgroundLabel.add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPassword.setBounds(50, 110, 100, 25);  // More space between fields
        backgroundLabel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 200, 30);  // Wider and taller field
        backgroundLabel.add(passwordField);

        JButton btnRegister = new JButton("Register");
        //btnRegister.setBackground(new Color(0, 128, 255));
        btnRegister.setForeground(Color.BLACK);
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRegister.setBounds(150, 160, 120, 35);  // Bigger button with more space below
//        btnRegister.setOpaque(true);
//        btnRegister.setBorderPainted(false);
        backgroundLabel.add(btnRegister);

        btnRegister.addActionListener(e -> registerUser());

        setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File userDir = new File("users");
        if (!userDir.exists()) {
            userDir.mkdir();
        }

        File userFile = new File(userDir, username + ".txt");

        if (userFile.exists()) {
            JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(userFile))) {
            writer.println(password);
            JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}