package de.hebk.gamemodes;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.game.SQLManager;
import de.hebk.gui.trueOrNot.TrueOrNotStart;
import de.hebk.model.queue.Queue;

import java.util.Random;

public class TrueOrNot {
    private String statement;
    private Question currentQuestion;
    private Queue<Question> questions;

    private boolean correct;

    private boolean rightAnswer;

    private int money = 0;

    TrueOrNotStart start;


    public void startTrueOrNot(){
        createQuestion();
    }

    public void setCurrentQuestion(){
        SQLManager sqlManager = new SQLManager(Config.getDatabaseURL());
        questions = sqlManager.getQueueQuestions();
        currentQuestion=questions.front();
    }
    public String createQuestion(){
        String finalQuestion;
        setCurrentQuestion();
        currentQuestion.getBody();


        finalQuestion = "Die Antwort auf die Frage: "+currentQuestion+", ist: "+getStatement();
        questions.dequeue();
        return finalQuestion;
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
        if(finalRandom==0){
            correct = false;
        }
        else{
            correct = true;
        }
        statement = choice[finalRandom];


        return statement;
    }

    public boolean checkCorrect(){
        if(start.getAnswer()&&getCorrect()){
            rightAnswer=true;
        }
        else if(!start.getAnswer()&&!getCorrect()){
            rightAnswer=true;
        }
        else{
            rightAnswer=false;
        }

        return rightAnswer;
    }

    public boolean getCorrect() {
        return correct;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {

        switch(money){
            case 0: money = 25;break;
            case 25: money = 50;break;
            case 50: money = 75;break;
            case 75: money = 100;break;
            case 100: money = 150;break;
            case 150: money = 200;break;
            case 200: money = 250;break;
            case 250: money = 300;break;
            case 300: money = 400;break;
            case 400: money = 500;break;
            case 500: money = 750;break;
            case 750: money = 1000;break;
            case 1000: money = 1500;break;
            case 1500: money = 2000;break;
            case 2000: money = 3000;break;
            case 3000: money = 4000;break;
            case 4000: money = 6000;break;
            case 6000: money = 8000;break;
            case 8000: money = 12000;break;
            case 12000: money = 16000;break;
            case 16000: money = 24000;break;
            case 24000: money = 32000;break;
            case 32000: money = 48000;break;
            case 48000: money = 64000;break;
            case 64000: money = 94500;break;
            case 94500: money = 125000;break;
            case 125000: money = 312500;break;
            case 312500: money = 500000;break;
            case 500000: money = 750000;break;
            case 750000: money = 1000000;break;
            default: money = 0;break;
        }
        this.money = money;

    }
}
