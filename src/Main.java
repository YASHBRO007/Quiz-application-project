public class QuizApp {

    public static void main(String[] args) {
        // Apply dark theme and custom fonts for the input dialog
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 16));
        UIManager.put("OptionPane.minimumSize", new Dimension(500, 180));
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(30, 30, 30));
        UIManager.put("Button.foreground", Color.WHITE);

        // Create a custom input field for the user's name
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        nameField.setBackground(Color.DARK_GRAY);
        nameField.setForeground(Color.WHITE);
        nameField.setCaretColor(Color.WHITE);

        // Show the input dialog
        int result = JOptionPane.showConfirmDialog(
                null,
                nameField,
                "Enter your name:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Validate the input
        if (result != JOptionPane.OK_OPTION || nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name is required to start the quiz.");
            return;
        }

        String userName = nameField.getText().trim();

        // Launch the quiz UI
        SwingUtilities.invokeLater(() -> new QuizUI(userName));
    }
}
