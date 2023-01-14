package de.hebk.gamemodes;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.game.SQLManager;
import de.hebk.model.list.List;

import java.util.Scanner;

public class Normal {
    private final Config config = new Config();
    private SQLManager manager = new SQLManager(Config.getDatabaseURL());

    private int stufe = 1;

    /**
     * Setzt die Geldbeträge für die verschiedenen Level fest.
     * @return
     */
    public int getMoney() {
        switch (stufe) {
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
     * Konstruktor der die Startnachricht ausgibt und den Spielmodus startet.
     */
    public Normal(){
        System.out.println("Du bist im Normalem Spielmodus. Du bekommst 15 Fragen und hast zwei Sicherheitsstufen. Eine bei der 5ten und eine bei der 10ten Frage. Viel Erfolg\n");
        game();
    }

    /**
     * Gibt, wenn verloren, je nach Level ubd Sicherheitsstufe denn Gewinn aus.
     */
    private void verloren() {
        if (stufe < 5) {
            System.out.println("Du hast auf Stufe " + stufe + " verloren und hast so garnichts verdient");
        }
        if (stufe >= 5 && stufe < 10) {
            System.out.println("Du hast auf Stufe " + stufe + " verloren und somit die erste Sicherheitsstufe erreicht. Dein Gewinn beträgt " + getMoney() + " Euros");
        }
        if (stufe >= 10) {
            System.out.println("Du hast auf Stufe " + stufe + " verloren und somit die zweite Sicherheitsstufe erreicht. Dein Gewinn beträgt " + getMoney() + " Euros");
        }
    }

    /**
     * Gibt den gewonnenen Betrag aus.
     */
    private void gewonnen() {
        System.out.println("Du hast auf Stufe " + stufe + " gewonnen und hast so " + getMoney() + " Euros verdient");
    }

    /**
     * Logik, die das Spiel fortführt und je nach richtiger oder falscher Antwort, die jeweilige Funktion ausführt.
     */
    private void game(){

        while (stufe <= 15) {
            Boolean result = stelleFrage();

            if (result == false) {
                verloren();
                break;
            }
            if (stufe == 15) {
                gewonnen();
            }
        }

    }

    /**
     *
     * @return
     */
    private boolean stelleFrage(){
        Scanner sc = new Scanner(System.in);
        List<Question> fragenliste = manager.getRandomQuestionsFromLevel(1,1);
        fragenliste.toFirst();
        Question frage = fragenliste.getObject();
        String[] answers = frage.getAnswers();

        System.out.println("Du bist auf Stufe " + stufe + "und bekommst eine Frage auf Level " + frage.getLevel() + "\n" + frage.getBody()+ "\n" + answers[0] + "\n" + answers[1] + "\n" + answers[2] + "\n" + answers[3]);
        int userAntwort = sc.nextInt();

        if (userAntwort == frage.getCorrect()) {
            System.out.println("Richtig");
            stufe ++;
            return true;
        }
        System.out.println("Falsch. Die richtige Antowort lautet " + frage.getCorrect());
        return false;
    }
}
