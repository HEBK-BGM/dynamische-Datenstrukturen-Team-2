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
}