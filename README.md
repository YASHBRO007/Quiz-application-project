# Quiz-application-project

This is a simple Java Swing-based quiz application that allows users to take a multiple-choice quiz. The app records user names and their scores into a database.

## 📁 Project Structure
JavaQuizApp_WithDatabase/
├── src/
│ ├── database/
│ │ └── DatabaseManager.java
│ ├── model/
│ │ └── Question.java
│ ├── ui/
│ │ ├── QuizFrame.java
│ │ └── ResultFrame.java
│ └── Main.java
├── database/
│ └── quiz_results.db (if using SQLite)
├── lib/
│ └── [your JDBC driver].jar
├── README.md
└── .gitignore


## ✅ Features

- Multiple-choice quiz questions
- Real-time score tracking
- GUI with Java Swing
- Stores user name and score in a database (e.g., SQLite or MySQL)
- Displays final result after completion

## 🛠 Requirements

- Java JDK 8 or higher
- JDBC driver for your database
  - For SQLite: [sqlite-jdbc](https://github.com/xerial/sqlite-jdbc)
  - For MySQL: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)

## If using MySQL:

Create a database and a table using the following SQL:


CREATE DATABASE quizapp;
USE quizapp;

CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    score INT NOT NULL
);

3. Add JDBC Library
Download the JDBC driver JAR and place it in the lib/ folder.

Add it to your classpath when compiling and running.

4. Compile and Run
Using command line (for SQLite):
javac -cp ".:lib/sqlite-jdbc.jar" -d bin src/**/*.java
java -cp ".:bin:lib/sqlite-jdbc.jar" Main

Using an IDE:

Import the project

Add the JDBC JAR to the project dependencies

Run Main.java

💾 Saving Results
After the quiz, users are prompted to enter their name.

Their name and score are saved into the database.

🧪 Example Questions
Questions are hardcoded for simplicity. You can update them in the Question.java or QuizFrame.java.

📌 Notes
Ensure the database file or connection details are accessible when launching the app.

No external frameworks are used, just core Java and JDBC.



✅ Core Feature Implementation

Name Entry Dialog: Prompts the user to enter their name using a customized JOptionPane before starting the quiz.

Quiz UI: Displays one question at a time with 4 options using radio buttons.

Database Connectivity: Fetches questions from a MySQL questions table and inserts user results into a quiz_results table.

Default Questions: Falls back to a hardcoded question set if the database is unavailable or empty.



🛠️ Error Handling and Robustness 

Database Exception Handling: Catches SQLException when fetching or saving data, with user-friendly error messages.

Input Validation: Ensures that:

The name field is not left blank.

A question cannot be submitted without selecting an option.

Fallback Mechanism: Uses default questions if database retrieval fails, ensuring the app always remains functional.



🔄 Integration of Components 

Swing UI + MySQL Integration: Combines GUI components (JFrame, JPanel, JRadioButton, JButton) with JDBC-based backend logic.

Real-time Event Processing: UI responds instantly to user input, reflects question progression, and integrates with database operations.

Result Display & Storage: Seamlessly shows a final score and records it in the database.



🖱️ Event Handling and Processing 

ActionListener: Attached to the Next button to process answers and navigate to the next question.

Input Evaluation: Determines if selected answer is correct and updates score.

Final Event: On last question submission, it shows results and writes data to the DB.



🔍 Data Validation 
User Name Field:

Disallows proceeding with an empty name.

Answer Selection:

Prevents moving to the next question without choosing an answer.

DB Insert Safety: Uses prepared statements to avoid SQL injection vulnerabilities.





📏 Code Quality and Innovative Features 

Dark-Themed UI: Custom dark theme using UIManager for consistent and modern visuals.

Clean Modular Code:

QuizApp: Entry point, handles name input.

QuizUI: Manages the main quiz flow and UI.

Question: Encapsulates question structure.

Fallback Support: Resilient to DB failures with offline quiz functionality.









