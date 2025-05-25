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



