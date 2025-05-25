package model;

public class Question {
    public int id;
    public String questionText;
    public String optionA, optionB, optionC, optionD;
    public char correctOption;

    public Question(int id, String questionText, String optionA, String optionB, String optionC, String optionD, char correctOption) {
        this.id = id;
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = correctOption;
    }
}