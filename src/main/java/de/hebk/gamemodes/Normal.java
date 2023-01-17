package de.hebk.gamemodes;

import de.hebk.game.*;
import de.hebk.gui.EndGui;
import de.hebk.gui.StartGui;
import de.hebk.gui.normal.NormalQuestionGUI;
import de.hebk.gui.trueOrNot.Win;
import de.hebk.model.list.List;
import de.hebk.sound.SoundManager;
import de.hebk.gui.trueOrNot.*;
import de.hebk.sound.SoundType;

public class Normal {
    private final Config config = new Config();
    private SQLManager manager = new SQLManager(Config.getDatabaseURL());
    private SoundManager soundManager = new SoundManager();
    private StartGui startGui;
    private Joker[] joker = new Joker[3];
    private Question frage;

    private int stufe = 1;
    private int cash = 0;

    public int getMoney() {
        switch (stufe-1) {
            case 0:
                return 0;
            case 1:
                return 50;
            case 2:
                return 100;
            case 3:
                return 200;
            case 4:
                return 300;
            case 5:
                return 500;
            case 6:
                return 1000;
            case 7:
                return 2000;
            case 8:
                return 4000;
            case 9:
                return 8000;
            case 10:
                return 16000;
            case 11:
                return 32000;
            case 12:
                return 64000;
            case 13:
                return 125000;
            case 14:
                return 500000;
            case 15:
                return 1000000;
        }
        return 0;
    }

    /**
     * Constructor for Normal
     * @param startGui
     */
    public Normal(StartGui startGui){
        this.startGui = startGui;
        soundManager.playSound(SoundType.QUESTION, false);
        joker[0] = new Joker(JokerType.TELEPHONE_JOKER);
        joker[1] = new Joker(JokerType.AUDIENCE_JOKER);
        joker[2] = new Joker(JokerType.HALF_JOKER);

        game();
    }

    /**
     * used when the player wins the game
     */
    private void gewonnen() {
        new Win(startGui);
        soundManager.stopSound();
        soundManager.playSound(SoundType.WIN, false);
    }

    /**
     * return the correct text when the player has lost
     * @return
     */
    private String verlorentext() {
        if (stufe < 5) {
            cash = 0;
            return "Du hast auf Stufe " + stufe + " verloren und hast so gar nichts verdient";
        }
        if (stufe >= 5 && stufe < 10) {
            cash = 500;
            return "Du hast auf Stufe " + stufe + " verloren und somit die erste Sicherheitsstufe erreicht. Dein Gewinn beträgt 500 Euros";
        }
        if (stufe >= 10) {
            cash = 16000;
            return "Du hast auf Stufe " + stufe + " verloren und somit die zweite Sicherheitsstufe erreicht. Dein Gewinn beträgt 16.000 Euros";
        }
        return "Fehler bei verlorentext()";
    }

    /**
     * updates the question and the gui
     * stops the game and let the player win when he has completet level 15
     */
    private void game(){
        if (stufe == 15) {
            gewonnen();
            return;
        }

        newQuestion();
        new NormalQuestionGUI(startGui, this, soundManager, joker);
    }

    /**
     * depending on the level, takes the appropriate question from the database
     */
    public void newQuestion() {
        List<Question> fragenliste = manager.getRandomQuestionsFromLevel(stufe,1);
        fragenliste.toFirst();
        Question question = fragenliste.getObject();

        frage = question;
    }

    /**
     * checks if the answer chosen in the GUI is correct
     * @param pAnswer
     */
    public void checkanswer(int pAnswer){
        if (pAnswer == frage.getCorrect()) {
            soundManager.stopSound();
            soundManager.playSound(SoundType.RIGHT_ANSWER, false);
            soundManager.playNext(SoundType.QUESTION, true);

            System.out.println("Richtig");
            cash = getMoney();

            stufe ++;
            game();
        }else {
            soundManager.stopSound();
            soundManager.playSound(SoundType.WRONG_ANSWER, false);

            new EndGui(startGui,verlorentext(),"normal",stufe,manager);
        }
    }

    /**
     *
     * @return
     */
    public Question getQuestion() {
        return frage;
    }

    /**
     *
     * @return
     */
    public int getStufe() {
        return stufe;
    }
}
