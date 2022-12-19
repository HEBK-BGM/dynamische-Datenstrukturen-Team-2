package de.hebk.gamemodes;

import de.hebk.Config;
import de.hebk.Question;
import de.hebk.model.stack.Stack;
import de.hebk.SQLManager;
import de.hebk.model.list.List;

public class Hardcore {

    private Stack<Question> question;

    SQLManager sqlm = new SQLManager(Config.getDatabaseURL());

    List<Question> name = sqlm.getQuestionsFromLevel(15);

    //question.push(name.getFirst);
    public void Hardcore(){

    }
}
