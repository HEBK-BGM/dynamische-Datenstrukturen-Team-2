package de.hebk;

import de.hebk.game.Config;
import de.hebk.gui.StartGui;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--enable-cheats")) {
            Config.setCheating(true);
        }

        StartGui gui = new StartGui();
    }
}