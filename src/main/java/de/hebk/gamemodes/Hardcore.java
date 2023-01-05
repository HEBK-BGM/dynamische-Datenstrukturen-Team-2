package de.hebk.gamemodes;

import de.hebk.Config;
import de.hebk.Question;
import de.hebk.SQLManager;
//import de.hebk.media.sound.SoundManager;
import de.hebk.model.stack.Stack;
import de.hebk.model.list.List;

public class Hardcore {
    //SoundManager soundManager = new SoundManager();

    Stack<Question> questions = new Stack<>();

    SQLManager sqlm = new SQLManager(Config.getDatabaseURL());

    private void getHardcoreQuestions(){
        List<Question> tmp;
        tmp = sqlm.getRandomQuestionsFromLevel(15,15);
        tmp.toFirst();

        while(tmp.getObject() != null){
            questions.push(tmp.getObject());
            tmp.remove();
        }
    }

    public void Hardcore(){
        getHardcoreQuestions();
    }

    private String fillQuestion(){
        Question tmp = questions.pop();
        return tmp.getBody();
    }

    private String fillCorrectAnswer(Question tmp){
        String[] answers = tmp.getAnswers();
        return answers[tmp.getCorrect()-1];
    }



}
