package de.hebk.game;

import de.hebk.model.list.List;

import java.util.Random;

public class Joker {
    private JokerType type;
    private boolean used;

    /**
     * Contructor for a joker
     * @param type
     */
    public Joker(JokerType type) {
        this.type = type;
        used = false;
    }

    /**
     * To get the Joker Type
     * @return Joker Type
     */
    public JokerType getType() {
        return this.type;
    }

    /**
     * To check if the joker is used
     * @return boolean if the joker is used
     */
    public boolean isUsed() {
        return this.used;
    }

    /**
     * Set if the joker is used
     * @param used boolean if used
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Returns possible answers
     * @param question  The given question
     * @return          Possible answers
     */
    public List<String> use(Question question) {
        List<String> answers = new List<>();
        Random rand = new Random();

        switch (type) {
            case TELEPHONE_JOKER:
                if (rand.nextInt(100) <= 75) {
                    answers.insert(question.getAnswers()[question.getCorrect()-1]);
                }
                else {
                    int random = rand.nextInt(4);
                    while (random == question.getCorrect()-1) {
                        random = rand.nextInt(4);
                    }

                    answers.insert(question.getAnswers()[random]);
                }
                break;
            case AUDIENCE_JOKER:
                if (rand.nextInt(100) <= 80) {
                    answers.insert(question.getAnswers()[question.getCorrect()-1]);
                }
                else {
                    int random = rand.nextInt(4);
                    while (random == question.getCorrect()-1) {
                        random = rand.nextInt(4);
                    }

                    answers.insert(question.getAnswers()[random]);
                }
                break;
            case HALF_JOKER:
                for (int i = 0; i < 2; i++) {
                    int random = rand.nextInt(4);
                    answers.toFirst();
                    while (random == question.getCorrect()-1 || (answers.getObject() != null && answers.getObject().equals(question.getAnswers()[random]))) {
                        random = rand.nextInt(4);
                    }

                    answers.insert(question.getAnswers()[random]);
                }

                break;
        }

        setUsed(true);
        return answers;
    }
}