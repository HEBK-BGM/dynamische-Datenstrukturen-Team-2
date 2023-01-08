package de.hebk.game;

import java.util.Random;

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

    public String getCorrectAnswer() {
        return getAnswers()[getCorrect()-1];
    }

    public void shuffleAnswers() {
        String correntAnswer = getCorrectAnswer();

        Random rand = new Random();
        for (int i = 0; i < answers.length; i++) {
            int randomIndexToSwap = rand.nextInt(answers.length);
            String tmp = answers[randomIndexToSwap];
            answers[randomIndexToSwap] = answers[i];
            answers[i] = tmp;
        }

        for (int i = 0; i < 4; i++) {
            if (answers[i].equals(correntAnswer)) {
                this.correct = i+1;
                break;
            }
        }
    }
}