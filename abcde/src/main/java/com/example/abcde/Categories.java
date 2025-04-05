package com.example.abcde;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Categories extends JFrame {

    private static final long serialVersionUID = 1L;
    private JScrollPane scrollPane;
    private static String name;
    public Categories(String name) {
        this.name = name;

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 550);
        setLocationRelativeTo(null);
        setTitle("Quiz Categories");

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Set the background
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Icons/Ques Mark bg.gif"));
        Image scaledImage = backgroundImage.getImage().getScaledInstance(750, 550, Image.SCALE_DEFAULT);
        ImageIcon scaledBackgroundIcon = new ImageIcon(scaledImage);

        // Create a background panel with the image
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledBackgroundIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        backgroundPanel.setOpaque(false);

        // Top panel for the back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);

        JButton backButton = createStyledButton("Back", new Color(0x3D3D3D), new Color(0x1A1A1A));
        backButton.setPreferredSize(new Dimension(100, 40));
        backButton.addActionListener(e -> {
            dispose();
            Welcome wlc = new Welcome(name);
            wlc.setVisible(true);
        });
        topPanel.add(backButton);

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Quiz Categories");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Combine top elements
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.add(topPanel, BorderLayout.WEST);
        headerPanel.add(titlePanel, BorderLayout.CENTER);
        headerPanel.setBorder(new EmptyBorder(20, 20, 10, 20));

        // Create content panel for categories
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new BoxLayout(categoriesPanel, BoxLayout.Y_AXIS));
        categoriesPanel.setOpaque(false);
        categoriesPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Add the categories
        // Sample categories - you can replace these with categories from the Open Trivia DB API
        addCategory(categoriesPanel, "Computer Knowledge", 18);
        addCategory(categoriesPanel, "Science & Nature", 17);
        addCategory(categoriesPanel, "Geographic Quiz", 22);
        addCategory(categoriesPanel, "History", 23);
        addCategory(categoriesPanel, "Sports", 21);
        addCategory(categoriesPanel, "Video Games", 15);
        addCategory(categoriesPanel, "Movies", 11);
        addCategory(categoriesPanel, "Music", 12);
        addCategory(categoriesPanel, "Television", 14);
        addCategory(categoriesPanel, "Books", 10);
        addCategory(categoriesPanel, "Mythology", 20);
        addCategory(categoriesPanel, "Animals", 27);

        // Create a scroll pane for the categories
        scrollPane = new JScrollPane(categoriesPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Custom scroll bar
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0x711889);
                this.trackColor = new Color(0x3D3D3D);
            }

            // Scroll Bar ke button invisible kr rha h
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });

        // Add components to the main panel
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(backgroundPanel);
    }

    private void addCategory(JPanel panel, String categoryName, int categoryId) {
        JButton categoryButton = createCustomButton(categoryName);
        categoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        categoryButton.setMaximumSize(new Dimension(400, 70));
        categoryButton.addActionListener(e -> {
            Quiz_Ques quiz = new Quiz_Ques(name, categoryId);
            dispose();
        });

        panel.add(categoryButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between buttons
    }

    private JButton createStyledButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private JButton createCustomButton(String text) {
        JButton button = new JButton(text) {
            private boolean isHovered = false;
            private boolean isPressed = false;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        isHovered = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        isHovered = false;
                        repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        isPressed = true;
                        repaint();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        isPressed = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Determine colors based on state
                Color startColor = isPressed ? new Color(0x004C99) :
                        (isHovered ? new Color(0x1A75D1) : new Color(0x0066CC));
                Color endColor = isPressed ? new Color(0x6B22B3) :
                        (isHovered ? new Color(0x9D44FF) : new Color(0x8A2BE2));

                // Gradient background
                GradientPaint gradient = new GradientPaint(
                        0, 0, startColor,
                        getWidth(), getHeight(), endColor
                );
                g2d.setPaint(gradient);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                // Gradient border - brighter when hovered
                Color borderStart = isHovered ? new Color(0xFFBB33) : new Color(0xFFA000);
                Color borderEnd = isHovered ? new Color(0xE6E6E6) : new Color(0xC0C0C0);

                GradientPaint borderGradient = new GradientPaint(
                        0, 0, borderStart,
                        getWidth(), getHeight(), borderEnd
                );
                g2d.setPaint(borderGradient);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 20, 20);

                // Add glow effect when hovered
                if (isHovered) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
                    g2d.setColor(new Color(0xFFD700));
                    g2d.setStroke(new BasicStroke(6));
                    g2d.drawRoundRect(-2, -2, getWidth() + 3, getHeight() + 3, 24, 24);
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
                }

                // Draw button text with shadow
                if (!isPressed) {
                    g2d.setColor(new Color(0, 0, 0, 50));
                    FontMetrics fm = g2d.getFontMetrics();
                    int x = (getWidth() - fm.stringWidth(getText())) / 2 + 1;
                    int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent() + 1;
                    g2d.drawString(getText(), x, y);
                }

                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 16));
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

                // Shift text down and right 1px when pressed
                if (isPressed) {
                    x += 1;
                    y += 1;
                }
                g2d.drawString(getText(), x, y);

                g2d.dispose();
            }
        };

        // Button styling
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
                Categories frame = new Categories(name);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}