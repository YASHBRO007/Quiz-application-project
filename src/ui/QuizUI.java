class QuizUI extends JFrame implements ActionListener {

    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private final String userName;

    private final JLabel questionLabel;
    private final JRadioButton[] options;
    private final ButtonGroup group;
    private final JButton nextButton;
    private final JPanel mainPanel;

    public QuizUI(String userName) {
        this.userName = userName;
        questions = loadQuestions();

        setTitle("Java Quiz - " + userName);
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(33, 40, 48));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setForeground(Color.WHITE);
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBackground(new Color(44, 52, 62));
        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("Arial", Font.PLAIN, 15));
            options[i].setBackground(new Color(44, 52, 62));
            options[i].setForeground(Color.WHITE);
            group.add(options[i]);
            optionsPanel.add(options[i]);
        }

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 15));
        nextButton.setBackground(new Color(0, 123, 255));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(33, 40, 48));
        buttonPanel.add(nextButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        displayQuestion(currentIndex);
        setVisible(true);
    }

    private List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_app", "root", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM questions");

            while (rs.next()) {
                String text = rs.getString("question_text");
                String[] opts = {
                    rs.getString("option1"),
                    rs.getString("option2"),
                    rs.getString("option3"),
                    rs.getString("option4")
                };
                int correct = rs.getInt("correct_index");
                list.add(new Question(text, opts, correct));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load questions: " + ex.getMessage());
        }
        return list.isEmpty() ? defaultQuestions() : list;
    }

    private List<Question> defaultQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("What is the size of int in Java?", new String[]{"16 bit", "32 bit", "64 bit", "Depends on OS"}, 1));
        list.add(new Question("Which keyword is used to inherit a class in Java?", new String[]{"this", "super", "extends", "implements"}, 2));
        list.add(new Question("Which company developed Java?", new String[]{"Sun Microsystems", "Oracle", "Microsoft", "IBM"}, 0));
        list.add(new Question("Which method is the entry point of Java?", new String[]{"start()", "main()", "init()", "run()"}, 1));
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
            showResult();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Over, " + userName + "! Your score is: " + score + "/" + questions.size());
        saveResultToDatabase(userName, score);
        dispose();
    }

    private void saveResultToDatabase(String name, int score) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz_app", "root", "1234");
            String sql = "INSERT INTO quiz_results (name, score) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, score);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to save result: " + ex.getMessage());
        }
    }
}
