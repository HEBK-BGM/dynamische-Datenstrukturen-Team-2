package de.hebk;

public class Config {
    private static String databaseURL = Config.class.getResource("german.db").getFile();

    public static String getDatabaseURL() {
        return databaseURL;
    }
}