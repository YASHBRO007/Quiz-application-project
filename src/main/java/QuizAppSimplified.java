import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class QuizAppSimplified {

    public static void main(String[] args) {
        String userName = JOptionPane.showInputDialog(null, "Enter your name:", "Welcome to the Java Quiz", JOptionPane.QUESTION_MESSAGE);
        if (userName == null || userName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name is required to start the quiz.");
            return;
        }
        new QuizUI(userName);
    }
}

class Question {
    String questionText;
    String[] options;
    int correctIndex;

    public Question(String questionText, String[] options, int correctIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctIndex = correctIndex;
    }
}

class QuizUI extends JFrame implements ActionListener {

    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private String userName;

    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton nextButton;

    public QuizUI(String userName) {
        this.userName = userName;
        questions = loadQuestions();

        setTitle("Java Quiz - " + userName);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question will appear here");
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        displayQuestion(currentIndex);
        setVisible(true);
    }

    private List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("What is JVM?", new String[]{"Java Virtual Machine", "Java Verified Machine", "Just Virtual Method", "None"}, 0));
        list.add(new Question("Which method is the entry point of a Java program?", new String[]{"start()", "main()", "run()", "init()"}, 1));
        list.add(new Question("What is the size of int in Java?", new String[]{"16 bit", "32 bit", "64 bit", "Depends on OS"}, 1));
        list.add(new Question("Which keyword is used to inherit a class in Java?", new String[]{"this", "super", "extends", "implements"}, 2));
        return list;
    }

    private void displayQuestion(int index) {
        if (index >= questions.size()) return;

        Question q = questions.get(index);
        questionLabel.setText("Q" + (index + 1) + ": " + q.questionText);
        for (int i = 0; i < 4; i++) {
            options[i].setText(q.options[i]);
            options[i].setSelected(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = i;
                break;
            }
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select an answer.");
            return;
        }

        if (selected == questions.get(currentIndex).correctIndex) {
            score++;
        }

        currentIndex++;
        if (currentIndex < questions.size()) {
            displayQuestion(currentIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Quiz Over, " + userName + "! Your score is: " + score + "/" + questions.size());
            System.exit(0);
        }
    }
}