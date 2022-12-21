package de.hebk.game;

public class Question {
    private String body;
    private int level;
    private String[] answers;
    private int correct;


    public Question(String pBody, int pLevel, String[] pAnswers, int pCorrect) {
        this.body = pBody;
        this.level = pLevel;
        this.answers = pAnswers;
        this.correct = pCorrect;
    }

    public String getBody() {
        return body;
    }

    public int getLevel() {
        return level;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrect() {
        return correct;
    }

}