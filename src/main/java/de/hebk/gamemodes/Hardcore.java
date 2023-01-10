package de.hebk.gamemodes;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.model.stack.Stack;
import de.hebk.game.SQLManager;
import de.hebk.game.Joker;
import de.hebk.game.JokerType;
import de.hebk.gui.EndGui;
import de.hebk.gui.HardcoreGui;
import de.hebk.gui.StartGui;
import de.hebk.model.list.List;
import de.hebk.sound.SoundManager;
import de.hebk.sound.SoundType;

public class Hardcore {

    public String gamemode = "Hardcore";
    public int lvl = 1;
    private StartGui frame;
    private Joker[] joker = new Joker[3];

    private Stack<Question> questions = new Stack<>();

    private SQLManager sqlm = new SQLManager(Config.getDatabaseURL());
    private SoundManager soundManager;

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
        this.soundManager = new SoundManager();

        joker[0] = new Joker(JokerType.TELEPHONE_JOKER);
        joker[1] = new Joker(JokerType.AUDIENCE_JOKER);
        joker[2] = new Joker(JokerType.HALF_JOKER);

        getHardcoreQuestions();
        soundManager.playSound(SoundType.QUESTION, true);
        nextQuestion();
    }

    public void nextQuestion(){
        if (!questions.isEmpty()) {
            Question q = questions.pop();
            new HardcoreGui(frame, soundManager, this, q, joker);

            lvl++;
        }
        else {
            soundManager.playSound(SoundType.WIN, false);
            stopGame();
        }
    }

    public void stopGame() {
        new EndGui(frame, "Du hast den Hardcore Spielmodus verloren!", gamemode, lvl);
    }
}
