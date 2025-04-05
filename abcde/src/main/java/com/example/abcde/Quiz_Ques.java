package com.example.abcde;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutionException;

public class Quiz_Ques extends JFrame implements ActionListener {

    String[][] questions;
    String[][] answers;
    String[][] useranswers = new String[10][1];
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, hint;

    public static int timer = 15;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;

    private javax.swing.Timer swingTimer;

    String name;
    static int categ;

    Quiz_Ques(String name, int categ) {
        setTitle("Answer Quickly!");
        this.name = name;
        this.categ = categ;
        // Make the frame size fit the screen resolution
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize.width-20, screenSize.height);
        setLocationRelativeTo(null);
        setUndecorated(true);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Main window invisible
        setVisible(false);

        // Loading Label
        LoadingScreen loadingScreen = new LoadingScreen();
        loadingScreen.setVisible(true);

        // Fetching started now in bg
        fetchQuestionsFromAPI(loadingScreen);

    }

    private void initializeUI() {
        try {
            ImageIcon i1 = new ImageIcon(getClass().getResource("/Icons/Quiz Time.jpg"));
            Image i2 = i1.getImage().getScaledInstance(1450,500,Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(0, 0, 1450, 392);
            add(image);
        } catch (Exception e) {
            System.out.println("Error loading background image: " + e.getMessage());
        }

        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);

        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);

        opt1 = new JRadioButton();
        opt1.setBounds(170, 520, 700, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(170, 560, 700, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(170, 600, 700, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(170, 640, 700, 30);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("Next");
        next.setBounds(1100, 550, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setOpaque(true);
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        hint = new JButton("Hint");
        hint.setBounds(1100, 630, 200, 40);
        hint.setFont(new Font("Tahoma", Font.PLAIN, 22));
        hint.setBackground(new Color(30, 144, 255));
        hint.setOpaque(true);
        hint.setFocusPainted(false);
        hint.setBorderPainted(false);
        hint.setForeground(Color.WHITE);
        hint.addActionListener(this);
        add(hint);

        submit = new JButton("Submit");
        submit.setBounds(1100, 710, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setOpaque(true);
        submit.setFocusPainted(false);
        submit.setBorderPainted(false);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);

        qno.setVisible(true);
        question.setVisible(true);
        opt1.setVisible(true);
        opt2.setVisible(true);
        opt3.setVisible(true);
        opt4.setVisible(true);
        next.setVisible(true);
        hint.setVisible(true);
        submit.setVisible(true);

        // Setup the timer
        swingTimer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer--;
                if (timer <= 0) {
                    swingTimer.stop();
                    handleTimeUp();
                }
                repaint(); // Update timer display
            }
        });
    }

    private void fetchQuestionsFromAPI(JFrame loadingScreen) {
        QuizApiService apiService = new QuizApiService();

        // Use SwingWorker to perform API call in background
        SwingWorker<QuizApiService.QuizData, Void> worker = new SwingWorker<>() {
            @Override
            protected QuizApiService.QuizData doInBackground() throws Exception {
                return apiService.fetchQuizQuestions(categ);
            }

            @Override
            protected void done() {
                try {
                    QuizApiService.QuizData quizData = get();
                    questions = quizData.getQuestions();
                    answers = quizData.getAnswers();
                    useranswers = new String[questions.length][1];

                    // Remove loading frame
                    loadingScreen.dispose(); // Close the loading screen
                    initializeUI(); // Load quiz interface
                    setVisible(true);

                    // Start the quiz
                    start(count);
                    swingTimer.start();

                } catch (InterruptedException | ExecutionException e) {
                    loadingScreen.setVisible(false);
                    e.printStackTrace();
                }
            }
        };

        worker.execute();
    }

    private void handleTimeUp() {
        timer = 15;
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);

        if (count == questions.length - 2) {
            next.setEnabled(false);
            submit.setEnabled(true);
        }

        if (count == questions.length - 1) { // Last question, submit
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            calculateScore();
            setVisible(false);
            new Score(name, score,categ);
        } else { // Move to next question
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }
            count++;
            start(count);
        }
        swingTimer.restart();
    }

    private void calculateScore() {
        score = 0;
        for (int i = 0; i < useranswers.length; i++) {
            if (useranswers[i][0] != null && useranswers[i][0].equals(answers[i][1])) {
                score += 10;
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            swingTimer.stop();
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);

            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            if (count == questions.length - 2) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }

            count++;
            start(count);
            timer = 15;
            swingTimer.restart();
        } else if (ae.getSource() == hint) {
            // Show a hint - we'll highlight the correct answer in a different color
            String correctAnswer = answers[count][1];

            if (opt1.getText().equals(correctAnswer)) {
                opt1.setForeground(new Color(0, 128, 0)); // Green color for hint
            } else if (opt2.getText().equals(correctAnswer)) {
                opt2.setForeground(new Color(0, 128, 0));
            } else if (opt3.getText().equals(correctAnswer)) {
                opt3.setForeground(new Color(0, 128, 0));
            } else if (opt4.getText().equals(correctAnswer)) {
                opt4.setForeground(new Color(0, 128, 0));
            }

            // Disable the hint button after use
            hint.setEnabled(false);
        } else if (ae.getSource() == submit) {
            swingTimer.stop();
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            calculateScore();
            setVisible(false);
            new Score(name, score,categ);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (questions != null) { // Only draw timer if questions are loaded
            String time = "Time left - " + timer + " seconds";
            g.setColor(Color.RED);
            g.setFont(new Font("Tahoma", Font.BOLD, 25));

            if (timer > 0) {
                g.drawString(time, 1100, 500);
            } else {
                g.drawString("Times up!!", 1100, 500);
            }
        }
    }

    public void start(int count) {
        // Reset text colors for all options
        opt1.setForeground(Color.BLACK);
        opt2.setForeground(Color.BLACK);
        opt3.setForeground(Color.BLACK);
        opt4.setForeground(Color.BLACK);

        qno.setText("" + (count + 1) + ". ");
        String questionText = questions[count][0].replaceAll("(.{70})", "$1<br>");
        String wrappedQuestion = "<html>" + questionText + "</html>";
        question.setBounds(150, 450, 900, getPreferredHeight(wrappedQuestion, question));
        question.setText(wrappedQuestion);

        // Randomize the order of answers
        String[] options = new String[4];
        options[0] = questions[count][1]; // Correct answer
        options[1] = questions[count][2];
        options[2] = questions[count][3];
        options[3] = questions[count][4];

        // Fisher-Yates shuffle
        for (int i = options.length - 1; i > 0; i--) {
            int index = (int) (Math.random() * (i + 1));
            String temp = options[index];
            options[index] = options[i];
            options[i] = temp;
        }

        opt1.setText(options[0]);
        opt1.setActionCommand(options[0]);

        opt2.setText(options[1]);
        opt2.setActionCommand(options[1]);

        opt3.setText(options[2]);
        opt3.setActionCommand(options[2]);

        opt4.setText(options[3]);
        opt4.setActionCommand(options[3]);

        groupoptions.clearSelection();
    }

    private int getPreferredHeight(String text, JLabel label) {
        FontMetrics metrics = label.getFontMetrics(label.getFont());
        int lineHeight = metrics.getHeight();
        int lines = text.split("<br>").length; // Count lines using <br>
        return lines * lineHeight + 20; // Add some padding
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Quiz_Ques("User", categ));
    }
}
