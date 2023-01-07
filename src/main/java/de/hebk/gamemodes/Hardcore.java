package de.hebk.gamemodes;

import de.hebk.game.Config;
import de.hebk.game.Question;
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
