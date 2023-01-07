package de.hebk.gamemodes;

import de.hebk.Config;
import de.hebk.Question;
import de.hebk.SQLManager;
//import de.hebk.media.sound.SoundManager;
import de.hebk.game.Joker;
import de.hebk.game.JokerType;
import de.hebk.gui.EndGui;
import de.hebk.gui.HardcoreGui;
import de.hebk.gui.StartGui;
import de.hebk.model.stack.Stack;
import de.hebk.model.list.List;

public class Hardcore {
    //SoundManager soundManager = new SoundManager();

    private StartGui frame;
    private Joker[] joker = new Joker[3];

    private Stack<Question> questions = new Stack<>();

    private SQLManager sqlm = new SQLManager(Config.getDatabaseURL());

    private void getHardcoreQuestions(){
        List<Question> tmp;
        tmp = sqlm.getRandomQuestionsFromLevel(15,15);
        tmp.toFirst();

        while(tmp.getObject() != null){
            questions.push(tmp.getObject());
            tmp.remove();
        }
    }

    public Hardcore(StartGui gui){
        this.frame = gui;

        joker[0] = new Joker(JokerType.TELEPHONE_JOKER);
        joker[1] = new Joker(JokerType.AUDIENCE_JOKER);
        joker[2] = new Joker(JokerType.HALF_JOKER);

        getHardcoreQuestions();
        nextQuestion();
    }

    public void nextQuestion(){
        if (!questions.isEmpty()) {
            Question q = questions.pop();

            new HardcoreGui(frame, this, q, joker);
        }
        else {
            stopGame();
        }
    }

    public void stopGame() {
        new EndGui(frame, "Dies ist ein Test");
    }
}
