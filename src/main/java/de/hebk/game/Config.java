package de.hebk.game;

public class Config {
    private static String databaseURL = Config.class.getResource("german.db").getFile();
    private static boolean cheats = false;

    public static String getDatabaseURL() {
        return databaseURL;
    }

    public static boolean cheatsEnabled() {
        return cheats;
    }

    public static void setCheating(boolean bool) {
        cheats = bool;
    }

    public static String hardcoreLevelToMoney(int level) {
        String money = "";

        switch (level) {
            case 1 -> money = "150 €";
            case 2 -> money = "300 €";
            case 3 -> money = "600 €";
            case 4 -> money = "900 €";
            case 5 -> money = "1.500 €";
            case 6 -> money = "3.000 €";
            case 7 -> money = "6.000 €";
            case 8 -> money = "12.000 €";
            case 9 -> money = "24.000 €";
            case 10 -> money = "48.000 €";
            case 11 -> money = "96.000 €";
            case 12 -> money = "192.000 €";
            case 13 -> money = "375.000 €";
            case 14 -> money = "1.500.000 €";
            case 15 -> money = "3.000.000 €";
            default -> money = "0 €";
        }

        return money;
    }

    public static String normalLevelToMoney(int level) {
        String money = "";

        switch (level) {
            case 1 -> money = "50 €";
            case 2 -> money = "100 €";
            case 3 -> money = "200 €";
            case 4 -> money = "300 €";
            case 5 -> money = "500 €";
            case 6 -> money = "1.000 €";
            case 7 -> money = "2.000 €";
            case 8 -> money = "4.000 €";
            case 9 -> money = "8.000 €";
            case 10 -> money = "16.000 €";
            case 11 -> money = "32.000 €";
            case 12 -> money = "64.000 €";
            case 13 -> money = "125.000 €";
            case 14 -> money = "500.000 €";
            case 15 -> money = "1.000.000 €";
            default -> money = "0 €";
        }

        return money;
    }

    public static String convertLevelToMoney(int level) {
        String money = "";

        switch (level) {
            case 1 -> money = "25 €";
            case 2 -> money = "50 €";
            case 3 -> money = "75 €";
            case 4 -> money = "100 €";
            case 5 -> money = "150 €";
            case 6 -> money = "200 €";
            case 7 -> money = "250 €";
            case 8 -> money = "300 €";
            case 9 -> money = "400 €";
            case 10 -> money = "500 €";
            case 11 -> money = "750 €";
            case 12 -> money = "1.000 €";
            case 13 -> money = "1.500 €";
            case 14 -> money = "2.000 €";
            case 15 -> money = "3.000 €";
            case 16 -> money = "4.000 €";
            case 17 -> money = "6.000 €";
            case 18 -> money = "8.000 €";
            case 19 -> money = "12.000 €";
            case 20 -> money = "16.000 €";
            case 21 -> money = "24.000 €";
            case 22 -> money = "32.000 €";
            case 23 -> money = "48.000 €";
            case 24 -> money = "64.000 €";
            case 25 -> money = "94.500 €";
            case 26 -> money = "125.000 €";
            case 27 -> money = "312.500 €";
            case 28 -> money = "500.000 €";
            case 29 -> money = "750.000 €";
            case 30 -> money = "1.000.000 €";
            default -> money = "0 €";
        }

        return money;
    }
}