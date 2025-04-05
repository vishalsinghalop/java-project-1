package com.example.abcde;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class HighScores extends JFrame {

    private JTextArea scoreArea;
    private static final Map<Integer, String> CATEGORY_MAP = createCategoryMap();

    public HighScores() {
        setTitle("Leader Board");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(scoreArea);
        add(scrollPane, BorderLayout.CENTER);

        loadScores();
    }

    private void loadScores() {
        Map<Integer, List<String>> categoryScores = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("scores.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int category = Integer.parseInt(parts[1]);
                    int score = Integer.parseInt(parts[2]);

                    categoryScores.putIfAbsent(category, new ArrayList<>());
                    categoryScores.get(category).add(name + " - " + score);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayScores(categoryScores);
    }

    private void displayScores(Map<Integer, List<String>> categoryScores) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, List<String>> entry : categoryScores.entrySet()) {
            int categoryID = entry.getKey();
            String categoryName = CATEGORY_MAP.getOrDefault(categoryID, "Unknown Category");
            List<String> scores = entry.getValue();
            scores.sort((a, b) -> Integer.parseInt(b.split(" - ")[1]) - Integer.parseInt(a.split(" - ")[1])); // Sort by score (descending)

            sb.append(categoryName).append(":\n");
            for (String scoreEntry : scores) {
                sb.append(" - ").append(scoreEntry).append("\n");
            }
            sb.append("\n");
        }
        scoreArea.setText(sb.toString());
    }

    private static Map<Integer, String> createCategoryMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(9, "General Knowledge");
        map.put(10, "Entertainment: Books");
        map.put(11, "Entertainment: Film");
        map.put(12, "Entertainment: Music");
        map.put(13, "Entertainment: Musicals & Theatres");
        map.put(14, "Entertainment: Television");
        map.put(15, "Entertainment: Video Games");
        map.put(16, "Entertainment: Board Games");
        map.put(17, "Science & Nature");
        map.put(18, "Science: Computers");
        map.put(19, "Science: Mathematics");
        map.put(20, "Mythology");
        map.put(21, "Sports");
        map.put(22, "Geography");
        map.put(23, "History");
        map.put(24, "Politics");
        map.put(25, "Art");
        map.put(26, "Celebrities");
        map.put(27, "Animals");
        map.put(28, "Vehicles");
        map.put(29, "Entertainment: Comics");
        map.put(30, "Science: Gadgets");
        map.put(31, "Entertainment: Japanese Anime & Manga");
        map.put(32, "Entertainment: Cartoon & Animations");
        return map;
    }

    public static void main(String[] args) {
        new HighScores().setVisible(true);
    }
}
