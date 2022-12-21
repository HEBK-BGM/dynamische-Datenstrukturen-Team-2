package de.hebk.gamemodes;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.SQLManager;
import de.hebk.model.queue.Queue;

import java.util.Random;

public class TrueOrNot {
    private String statement;
    private Question currentQuestion;
    private Queue<Question> questions;


    public void startTrueOrNot(){
        createQuestion();
    }

    public void setCurrentQuestion(){
        SQLManager sqlManager = new SQLManager(Config.getDatabaseURL());
        questions = sqlManager.getQueueQuestions();
        currentQuestion=questions.front();
    }
    public void createQuestion(){
        String finalQuestion;
        setCurrentQuestion();
        currentQuestion.getBody();


        finalQuestion = "Die Antwort auf die Frage: "+currentQuestion+", ist: "+getStatement();
        questions.dequeue();

    }
    public String getStatement(){
        String[] choice = new String[2];
        String[] answers = new String[4];
        answers=currentQuestion.getAnswers();
        String[] sortedAnswers = new String[3];
        String correctAnswer = answers[currentQuestion.getCorrect()];
        answers[currentQuestion.getCorrect()]=null;
        for(int i = 0; i < 4; i++){
            if (answers[i]==null){
                i++;
            }
            if(answers[i]!=null){
                for(int y = 0; y < 3; y++) {
                    sortedAnswers[y] = answers[i];
                }
            }
        }
        Random rand = new Random();
        int upperbound1 = 2;
        int random = rand.nextInt(upperbound1);
        choice[0] = sortedAnswers[random];
        choice[1] = correctAnswer;
        int upperbound2 = 1;
        int finalRandom = rand.nextInt(upperbound2);
        statement = choice[finalRandom];


        return statement;
    }

}
