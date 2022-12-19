package de.hebk.gamemodes;

import de.hebk.Config;
import de.hebk.Question;
import de.hebk.SQLManager;
import de.hebk.model.list.List;
import java.util.Scanner;

import java.util.Scanner;

public class Normal {
    private Config config = new Config();
    private SQLManager manager = new SQLManager(Config.getDatabaseURL());

    private int stufe = 1;
    private int money = 0;

    public int getMoney() {
        if (stufe == 1) {
            return 50;
        }
        if (stufe == 2) {
            return 50;
        }
        return 0;
    }

    public Normal(){
        game();
    }

    private void game(){

        while (true && stufe <= 15) {
            stelleFrage();
        }
    }

    private boolean stelleFrage(){
        Scanner sc = new Scanner(System.in);
        Question frage = manager.getRandomQuestions();
        String[] answers = frage.getAnswers();

        System.out.println(frage.getBody()+ "\n" + answers[0] + "\n" + answers[1] + "\n" + answers[2] + "\n" + answers[3]);
        int userAntwort = sc.nextInt();

        if (userAntwort == frage.getCorrect()) {
            System.out.println("Richtig");
            return true;
        }
        System.out.println("Falsch");
        return false;
    }
}
