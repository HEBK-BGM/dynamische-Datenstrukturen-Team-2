package de.hebk.gamemodes;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.game.SQLManager;
import de.hebk.gui.StartGui;
import de.hebk.gui.normal.NormalQuestionGUI;
import de.hebk.model.list.List;
import de.hebk.sound.SoundManager;

import java.util.Scanner;

public class Normal {
    private final Config config = new Config();
    private SQLManager manager = new SQLManager(Config.getDatabaseURL());
    private SoundManager soundManager = new SoundManager();

    private int stufe = 1;
    private int cash = 0;

    public int getMoney() {
        switch (stufe) {
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

    public Normal(StartGui startGui){
        NormalQuestionGUI nqgui = new NormalQuestionGUI(startGui, soundManager);
        System.out.println("Du bist im Normalem Spielmodus. Du bekommst 15 Fragen und hast zwei Sicherheitsstufen. Eine bei der 5ten und eine bei der 10ten Frage. Viel Erfolg\n");
        game();
    }

    private void verloren() {
        if (stufe < 5) {
            System.out.println("Du hast auf Stufe " + stufe + " verloren und hast so garnichts verdient");
            cash = 0;
        }
        if (stufe >= 5 && stufe < 10) {
            System.out.println("Du hast auf Stufe " + stufe + " verloren und somit die erste Sicherheitsstufe erreicht. Dein Gewinn beträgt 500 Euros");
            cash = 500;
        }
        if (stufe >= 10) {
            System.out.println("Du hast auf Stufe " + stufe + " verloren und somit die zweite Sicherheitsstufe erreicht. Dein Gewinn beträgt 16.000 Euros");
            cash = 16000;
        }
    }

    private void gewonnen() {
        if (stufe == 3) {
            System.out.println("Herzlichen Glüchwunsch, du hasst alle Fragen geschafft und dir so die " + cash + " Euro verdient");
        }else {
            cash = getMoney();
            System.out.println("Du hast Stufe " + stufe + " erreicht und hast so " + cash + " Euros verdient");
        }
    }

    private void game(){

        while (stufe <= 3) {
            Boolean result = stelleFrage();

            if (result == false) {
                verloren();
                break;
            }
            if (result == true) {
                //überprüft, ob der
                if (stufe == 3) {
                    gewonnen();
                    return;
                }

                Scanner sc = new Scanner(System.in);

                System.out.println("Willst du weiterspielen?");
                //bei 0 entscheidet der Spieler nicht weiterzuspielen und gewinnt
                if (sc.nextInt() == 0) {
                    gewonnen();
                    return;
                }
                stufe ++;
            }
        }
    }

    private boolean stelleFrage(){
        Scanner sc = new Scanner(System.in);
        List<Question> fragenliste = manager.getRandomQuestionsFromLevel(stufe,1);
        fragenliste.toFirst();
        Question frage = fragenliste.getObject();
        String[] answers = frage.getAnswers();

        System.out.println("Du bist auf Stufe " + stufe + " und bekommst eine Frage auf Level " + frage.getLevel() + " Cash: " + cash + "\n" + frage.getBody()+ "\n" + answers[0] + "\n" + answers[1] + "\n" + answers[2] + "\n" + answers[3]);
        int userAntwort = sc.nextInt();

        if (userAntwort == frage.getCorrect()) {
            System.out.println("Richtig");
            cash = getMoney();
            return true;
        }
        System.out.println("Falsch. Die richtige Antowort lautet " + frage.getCorrect());
        return false;
    }
}
