package ui;

import dao.QuestionDAO;
import model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuizUI extends JFrame {
    private String userName;
    private List<Question> questions;
    private int index = 0;
    private int score = 0;

    JLabel questionLabel;
    JRadioButton a, b, c, d;
    ButtonGroup optionsGroup;
    JButton nextBtn;

    public QuizUI(String userName) {
        this.userName = userName;
        questions = QuestionDAO.getAllQuestions();

        setTitle("Java Quiz App - " + userName);
        setLayout(new BorderLayout());
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel questionPanel = new JPanel(new GridLayout(0, 1));
        questionLabel = new JLabel();
        questionPanel.add(questionLabel);

        a = new JRadioButton();
        b = new JRadioButton();
        c = new JRadioButton();
        d = new JRadioButton();
        optionsGroup = new ButtonGroup();

        optionsGroup.add(a);
        optionsGroup.add(b);
        optionsGroup.add(c);
        optionsGroup.add(d);

        questionPanel.add(a);
        questionPanel.add(b);
        questionPanel.add(c);
        questionPanel.add(d);

        nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> checkAnswerAndNext());

        add(questionPanel, BorderLayout.CENTER);
        add(nextBtn, BorderLayout.SOUTH);

        displayQuestion();

        setVisible(true);
    }

    private void displayQuestion() {
        if (index < questions.size()) {
            Question q = questions.get(index);
            questionLabel.setText("Q" + (index + 1) + ": " + q.questionText);
            a.setText(q.optionA);
            b.setText(q.optionB);
            c.setText(q.optionC);
            d.setText(q.optionD);
            optionsGroup.clearSelection();
        } else {
            showResult();
        }
    }

    private void checkAnswerAndNext() {
        if (index < questions.size()) {
            Question q = questions.get(index);
            char selected = ' ';
            if (a.isSelected()) selected = 'A';
            if (b.isSelected()) selected = 'B';
            if (c.isSelected()) selected = 'C';
            if (d.isSelected()) selected = 'D';

            if (selected == q.correctOption) {
                score++;
            }

            index++;
            displayQuestion();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz completed! Score: " + score + "/" + questions.size());
        System.exit(0);
    }
}