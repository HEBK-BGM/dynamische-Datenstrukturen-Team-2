package de.hebk.game;

import java.net.URL;

public class Config {

    private static String databaseURL = Config.class.getResource("german.db").getFile();

    private static boolean cheats = false;

    /**
     * Die Database wird ausgegeben.
     * @return
     */
    public static String getDatabaseURL() {
        return databaseURL;
    }

    /**
     * Es werden die Cheats zurückgegeben
     * @return
     */
    public static boolean cheatsEnabled() {
        return cheats;
    }

    /**
     * Die Cheats werden gesetzt.
     * @param bool
     */
    public static void setCheating(boolean bool) {
        cheats = bool;
    }

    /**
     * Die Datei des Hintergrunds wird zurückgegeben.
     * @return
     */
    public static URL getBackground() {
        return Config.class.getResource("images/background.png");
    }

    /**
     * Das Wer wird Millionär Icon wird zurückgegeben.
     * @return
     */
    public static URL getAppIcon() {
        return Config.class.getResource("images/icon.png");
    }

    /**
     * Der hardcore Geldbetrag des momentanen Levels wird ausgegeben.
     * @param level
     * @return
     */
    public static int hardcoreLevelToMoney(int level) {
        int money;

        switch (level) {
            case 1 -> money = 150;
            case 2 -> money = 300;
            case 3 -> money = 600;
            case 4 -> money = 900;
            case 5 -> money = 1500;
            case 6 -> money = 3000;
            case 7 -> money = 6000;
            case 8 -> money = 12000;
            case 9 -> money = 24000;
            case 10 -> money = 48000;
            case 11 -> money = 96000;
            case 12 -> money = 192000;
            case 13 -> money = 375000;
            case 14 -> money = 1500000;
            case 15 -> money = 3000000;
            default -> money = 0;
        }

        return money;
    }

    /**
     * Der normale Geldbetrag des momentanen levels wird ausgegeben.
     * @param level
     * @return
     */
    public static int normalLevelToMoney(int level) {
        int money;

        switch (level) {
            case 1 -> money = 50;
            case 2 -> money = 100;
            case 3 -> money = 200;
            case 4 -> money = 300;
            case 5 -> money = 500;
            case 6 -> money = 1000;
            case 7 -> money = 2000;
            case 8 -> money = 4000;
            case 9 -> money = 8000;
            case 10 -> money = 16000;
            case 11 -> money = 32000;
            case 12 -> money = 64000;
            case 13 -> money = 125000;
            case 14 -> money = 500000;
            case 15 -> money = 1000000;
            default -> money = 0;
        }

        return money;
    }

    /**
     * Der TrueOrNot Geldbetrag des momentanen Levels wird ausgegeben.
     * @param level
     * @return
     */
    public static int trueOrNotLevelToMoney(int level) {
        int money;

        switch (level) {
            case 1 -> money = 25;
            case 2 -> money = 50;
            case 3 -> money = 75;
            case 4 -> money = 100;
            case 5 -> money = 150;
            case 6 -> money = 200;
            case 7 -> money = 250;
            case 8 -> money = 300;
            case 9 -> money = 400;
            case 10 -> money = 500;
            case 11 -> money = 750;
            case 12 -> money = 1000;
            case 13 -> money = 1500;
            case 14 -> money = 2000;
            case 15 -> money = 3000;
            case 16 -> money = 4000;
            case 17 -> money = 6000;
            case 18 -> money = 8000;
            case 19 -> money = 12000;
            case 20 -> money = 16000;
            case 21 -> money = 24000;
            case 22 -> money = 32000;
            case 23 -> money = 48000;
            case 24 -> money = 64000;
            case 25 -> money = 94500;
            case 26 -> money = 125000;
            case 27 -> money = 312500;
            case 28 -> money = 500000;
            case 29 -> money = 750000;
            case 30 -> money = 1000000;
            default -> money = 0;
        }

        return money;
    }
}