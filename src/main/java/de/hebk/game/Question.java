package de.hebk.game;

import java.util.Random;

public class Question {
    private String body;
    private int level;
    private String[] answers;
    private int correct;

    /**
     * Der Konstruktor setzt die Frage und die Antworten.
     * @param pBody
     * @param pLevel
     * @param pAnswers
     * @param pCorrect
     */
    public Question(String pBody, int pLevel, String[] pAnswers, int pCorrect) {
        this.body = pBody;
        this.level = pLevel;
        this.answers = pAnswers;
        this.correct = pCorrect;
    }

    /**
     * gibt die Frage zurück.
     * @return
     */
    public String getBody() {
        return body;
    }

    /**
     * gibt den Schwierigkeitsgrad der Frage zurück.
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gibt die Antworten zurück.
     * @return
     */
    public String[] getAnswers() {
        return answers;
    }

    /**
     * Gibt die Position der richtigen Frage zurück.
     * @return
     */
    public int getCorrect() {
        return correct;
    }

    /**
     * Gibt die richtige Antwort zurück.
     * @return
     */
    public String getCorrectAnswer() {
        return getAnswers()[getCorrect()-1];
    }

    /**
     * Die Antworten werden durchgemischt.
     */
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