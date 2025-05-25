import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String userName = JOptionPane.showInputDialog(null, "Enter your name:", "Welcome to the Java Quiz", JOptionPane.QUESTION_MESSAGE);
        if (userName == null || userName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name is required to start the quiz.");
            return;
        }
        new ui.QuizUI(userName);
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
            JOptionPane.showMessageDialog(this, "Failed to save result to database: " + ex.getMessage());
        }
    }

}